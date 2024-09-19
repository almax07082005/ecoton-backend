package ecoton.ecotonbackend.service;

import ecoton.ecotonbackend.entity.roles.Role;
import ecoton.ecotonbackend.entity.roles.UserRole;
import ecoton.ecotonbackend.repository.AuthRoleRepository;
import ecoton.ecotonbackend.repository.AuthUserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

	private final DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
	private final AuthUserRepository userRepository;
	private final AuthRoleRepository roleRepository;

	public CustomOAuth2UserService(AuthUserRepository userRepository, AuthRoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	@Transactional
	public OAuth2User loadUser(OAuth2UserRequest userRequest) {
		System.out.println("Client Registration: " + userRequest.getClientRegistration().getRegistrationId());

		OAuth2User oAuth2User = delegate.loadUser(userRequest);

		String email = oAuth2User.getAttribute("email");

		UserRole user = userRepository.findByUsername(email)
				.orElseGet(() -> {
					Role userRole = roleRepository.findByAuthority("USER")
							.orElseThrow(() -> new RuntimeException("Role USER not found"));

					Set<Role> roles = new HashSet<>();
					roles.add(userRole);
					UserRole newUser = new UserRole(
							email,
							roles
					);
					return userRepository.save(newUser);
				});

		Set<GrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

		return new DefaultOAuth2User(authorities, oAuth2User.getAttributes(), "email");
	}
}

package ecoton.ecotonbackend.service;

import ecoton.ecotonbackend.entity.Role;
import ecoton.ecotonbackend.entity.User;
import ecoton.ecotonbackend.model.dto.LoginResponseDTO;
import ecoton.ecotonbackend.repository.RoleRepository;
import ecoton.ecotonbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.RoleNotFoundException;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

	private final UserRepository userRepository;

	private final RoleRepository roleRepository;

	private final PasswordEncoder passwordEncoder;

	private final AuthenticationManager authenticationManager;

	private final TokenService tokenService;

	public User registerUser(String username, String password) throws RoleNotFoundException {
		String encodedPassword = passwordEncoder.encode(password);
		Role userRole = roleRepository.findByAuthority("USER").orElseThrow(RoleNotFoundException::new);

		Set<Role> roles = new HashSet<>();
		roles.add(userRole);

		return userRepository.save(new User(0, username, encodedPassword, roles));
	}

	public LoginResponseDTO loginUser(String username, String password) throws UsernameNotFoundException {
		try {
			Authentication auth = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(username, password)
			);

			String token = tokenService.generateJwtToken(auth);

			return new LoginResponseDTO(userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("")), token);

		} catch (AuthenticationException e) {
			return new LoginResponseDTO(null, "Authentication failed");
		}
	}

}

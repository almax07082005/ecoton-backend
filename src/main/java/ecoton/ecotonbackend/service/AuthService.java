package ecoton.ecotonbackend.service;

import ecoton.ecotonbackend.entity.roles.Role;
import ecoton.ecotonbackend.entity.roles.UserRole;
import ecoton.ecotonbackend.model.dto.LoginResponseDTO;
import ecoton.ecotonbackend.model.dto.RegistrationResponseDTO;
import ecoton.ecotonbackend.repository.AuthRoleRepository;
import ecoton.ecotonbackend.repository.AuthUserRepository;
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

	private final AuthUserRepository authUserRepository;

	private final AuthRoleRepository roleRepository;

	private final PasswordEncoder passwordEncoder;

	private final AuthenticationManager authenticationManager;

	private final TokenService tokenService;

	public RegistrationResponseDTO registerUser(String username, String password) throws RoleNotFoundException {
		String encodedPassword = passwordEncoder.encode(password);
		Role userRole = roleRepository.findByAuthority("USER").orElseThrow(RoleNotFoundException::new);

		Set<Role> roles = new HashSet<>();
		roles.add(userRole);

		Authentication auth = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(username, password)
		);
		String token = tokenService.generateJwtToken(auth);

		UserRole savedUser = authUserRepository.save(new UserRole(0, username, encodedPassword, roles));

		return new RegistrationResponseDTO(
				savedUser.getUsername(),
				true,
				token
		);
	}

	public LoginResponseDTO loginUser(String username, String password) throws UsernameNotFoundException {
		try {
			Authentication auth = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(username, password)
			);

			String token = tokenService.generateJwtToken(auth);

			return new LoginResponseDTO(authUserRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("")).getUsername(), token);

		} catch (AuthenticationException e) {
			return new LoginResponseDTO(null, "Authentication failed");
		}
	}

}

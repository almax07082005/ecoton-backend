package ecoton.ecotonbackend.controller;

import ecoton.ecotonbackend.entity.roles.User;
import ecoton.ecotonbackend.model.dto.LoginResponseDTO;
import ecoton.ecotonbackend.model.dto.RegistrationRequestDTO;
import ecoton.ecotonbackend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/register")
	public User register(@RequestBody RegistrationRequestDTO registrationDTO) throws RoleNotFoundException {
		return authService.registerUser(registrationDTO.getUsername(), registrationDTO.getPassword());
	}

	@PostMapping("/login")
	public LoginResponseDTO loginUser(@RequestBody RegistrationRequestDTO body) {
		return authService.loginUser(body.getUsername(), body.getPassword());
	}
}
package ecoton.ecotonbackend.controller;

import ecoton.ecotonbackend.model.dto.RegistrationFormRequestDTO;
import ecoton.ecotonbackend.model.dto.RegistrationFormResponseDTO;
import ecoton.ecotonbackend.service.RegistrationFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
@CrossOrigin("*")
@RequiredArgsConstructor
public class RegistrationFormController {

	private final RegistrationFormService registrationFormService;

	@GetMapping("/test")
	public String testEndpoint() {
		return "Access to /register/test works without authentication!";
	}

	@PostMapping("/fill_form")
	public RegistrationFormResponseDTO fillRegistrationForm(@RequestBody RegistrationFormRequestDTO form) {
		return registrationFormService.fillRegistrationForm(form);
	}
}

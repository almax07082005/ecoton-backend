package ecoton.ecotonbackend.controller;

import ecoton.ecotonbackend.model.dto.RegistrationFormOrganizerRequestDTO;
import ecoton.ecotonbackend.model.dto.RegistrationFormUserRequestDTO;
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

	@PostMapping("/fill_form_user")
	public RegistrationFormResponseDTO fillRegistrationForm(@RequestBody RegistrationFormUserRequestDTO form) {
		return registrationFormService.fillRegistrationFormUser(form);
	}

	@PostMapping("/fill_form_organizer")
	public RegistrationFormResponseDTO fillRegistrationFormOrganizer(@RequestBody RegistrationFormOrganizerRequestDTO form) {
		return registrationFormService.fillRegistrationFormOrganizer(form);
	}
}

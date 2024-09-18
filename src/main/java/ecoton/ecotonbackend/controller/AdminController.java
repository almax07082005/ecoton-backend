package ecoton.ecotonbackend.controller;

import ecoton.ecotonbackend.model.dto.ApproveOrganizersRequestDTO;
import ecoton.ecotonbackend.model.dto.ApproveOrganizersResponseDTO;
import ecoton.ecotonbackend.model.dto.PendingOrganizerResponseDTO;
import ecoton.ecotonbackend.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AdminController {

	private final AdminService adminService;

	@GetMapping("/")
	public String helloAdmin() {
		return "Admin level access";
	}

	@GetMapping("/pending_organizers")
	public List<PendingOrganizerResponseDTO> pendingOrganizers() {
		return adminService.pendingOrganizers();
	}

	@PostMapping("/approve_organizers")
	public List<ApproveOrganizersResponseDTO> approveOrganizers(@RequestBody List<ApproveOrganizersRequestDTO> organizers) {
		return adminService.approveOrganizers(organizers);
	}

}
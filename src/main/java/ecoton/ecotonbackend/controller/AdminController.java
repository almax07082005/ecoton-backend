package ecoton.ecotonbackend.controller;

import ecoton.ecotonbackend.model.dto.ApproveOfficialsRequestDTO;
import ecoton.ecotonbackend.model.dto.ApproveOfficialsResponseDTO;
import ecoton.ecotonbackend.model.dto.PendingOfficialResponseDTO;
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

	@GetMapping("/pending_officials")
	public List<PendingOfficialResponseDTO> pendingOfficials() {
		return adminService.pendingOfficials();
	}

	@PostMapping("/approve_officials")
	public List<ApproveOfficialsResponseDTO> approveOrganizers(@RequestBody List<ApproveOfficialsRequestDTO> officials) {
		return adminService.approveOfficials(officials);
	}

}
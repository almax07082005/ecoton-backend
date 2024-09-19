package ecoton.ecotonbackend.controller;

import ecoton.ecotonbackend.dto.OrganizerDTO;
import ecoton.ecotonbackend.request.create.OrganizerCreateRequest;
import ecoton.ecotonbackend.request.update.OrganizerUpdateRequest;
import ecoton.ecotonbackend.service.OrganizerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/organizers")
@RestController
public class OrganizerController {

    private final OrganizerService organizerService;

    @GetMapping("/{id}")
    public ResponseEntity<OrganizerDTO> getOrganizerById(@PathVariable Integer id) {
        return ResponseEntity.ok(organizerService.getOrganizer(id));
    }

    @GetMapping
    public ResponseEntity<List<OrganizerDTO>> getOrganizers() {
        return ResponseEntity.ok(organizerService.getAllOrganizers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrganizerById(@PathVariable Integer id) {
        organizerService.deleteOrganizer(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Void> createOrganizer(@RequestBody OrganizerCreateRequest organizerCreateRequest) {
        organizerService.createOrganizer(organizerCreateRequest);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateOrganizer(@PathVariable Integer id, @RequestBody OrganizerUpdateRequest organizerUpdateRequest) {
        organizerService.updateOrganizer(id, organizerUpdateRequest);
        return ResponseEntity.ok().build();
    }
}

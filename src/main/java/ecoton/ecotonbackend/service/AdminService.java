package ecoton.ecotonbackend.service;

import ecoton.ecotonbackend.entity.OrganizerEntity;
import ecoton.ecotonbackend.entity.PendingOrganizerEntity;
import ecoton.ecotonbackend.model.dto.ApproveOrganizersRequestDTO;
import ecoton.ecotonbackend.model.dto.ApproveOrganizersResponseDTO;
import ecoton.ecotonbackend.model.dto.PendingOrganizerResponseDTO;
import ecoton.ecotonbackend.repository.OrganizerRepository;
import ecoton.ecotonbackend.repository.PendingOrganizerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AdminService {

	private final PendingOrganizerRepository pendingOrganizerRepository;

	private final OrganizerRepository organizerRepository;

	public List<PendingOrganizerResponseDTO> pendingOrganizers() {
		List<PendingOrganizerEntity> pendingOrganizers = pendingOrganizerRepository.findAllByStatus("pending");

		return pendingOrganizers.stream()
				.map(entity -> new PendingOrganizerResponseDTO(
						entity.getId(),
						entity.getUserRole().getUserId(),
						entity.getName(),
						entity.getType(),
						entity.getLegalEntityId()
				))
				.toList();
	}

	public List<ApproveOrganizersResponseDTO> approveOrganizers(List<ApproveOrganizersRequestDTO> organizers) {
		List<Integer> toApprove = organizers.stream()
				.filter(ApproveOrganizersRequestDTO::getIsApproved)
				.map(ApproveOrganizersRequestDTO::getId)
				.toList();

		if (!toApprove.isEmpty()) {
			pendingOrganizerRepository.approvePendingOrganizers(toApprove);
		}

		List<Integer> toReject = organizers.stream()
				.filter(organizer -> !organizer.getIsApproved())
				.map(ApproveOrganizersRequestDTO::getId)
				.toList();

		if (!toReject.isEmpty()) {
			pendingOrganizerRepository.rejectPendingOrganizers(toReject);
		}

		List<Integer> allIds = Stream.concat(toApprove.stream(), toReject.stream()).toList();
		List<PendingOrganizerEntity> updatedOrganizers = pendingOrganizerRepository.findAllById(allIds);

		List<OrganizerEntity> organizersToSave = updatedOrganizers.stream()
				.filter(organizer -> "approved".equals(organizer.getStatus()))
				.map(pending -> new OrganizerEntity(
						pending.getUserRole(),
						pending.getName(),
						pending.getType(),
						pending.getLegalEntityId()
				))
				.toList();

		if (!organizersToSave.isEmpty()) {
			organizerRepository.saveAll(organizersToSave);
		}

		return updatedOrganizers.stream()
				.map(organizer -> new ApproveOrganizersResponseDTO(
						organizer.getId(),
						organizer.getStatus()
				))
				.toList();
	}


}

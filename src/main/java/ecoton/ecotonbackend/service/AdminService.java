package ecoton.ecotonbackend.service;

import ecoton.ecotonbackend.entity.OfficialEntity;
import ecoton.ecotonbackend.entity.PendingOfficialEntity;
import ecoton.ecotonbackend.model.dto.ApproveOfficialsRequestDTO;
import ecoton.ecotonbackend.model.dto.ApproveOfficialsResponseDTO;
import ecoton.ecotonbackend.model.dto.PendingOfficialResponseDTO;
import ecoton.ecotonbackend.repository.OfficialRepository;
import ecoton.ecotonbackend.repository.PendingOfficialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AdminService {

	private final PendingOfficialRepository pendingOfficialRepository;

	private final OfficialRepository officialRepository;

	public List<PendingOfficialResponseDTO> pendingOfficials() {
		List<PendingOfficialEntity> pendingOfficials = pendingOfficialRepository.findAllByStatus("pending");

		return pendingOfficials.stream()
				.map(entity -> new PendingOfficialResponseDTO(
						entity.getId(),
						entity.getUserRole().getUserId(),
						entity.getName(),
						entity.getEmail(),
						entity.getJobTitle()
				))
				.toList();
	}

	public List<ApproveOfficialsResponseDTO> approveOfficials(List<ApproveOfficialsRequestDTO> officials) {
		List<Integer> toApprove = officials.stream()
				.filter(official -> official.getIsApproved() != null && official.getIsApproved())
				.map(ApproveOfficialsRequestDTO::getId)
				.toList();

		if (!toApprove.isEmpty()) {
			pendingOfficialRepository.approvePendingOfficials(toApprove);
		}

		List<Integer> toReject = officials.stream()
				.filter(official -> official.getIsApproved() != null && !official.getIsApproved())
				.map(ApproveOfficialsRequestDTO::getId)
				.toList();

		if (!toReject.isEmpty()) {
			pendingOfficialRepository.rejectPendingOfficials(toReject);
		}

		List<Integer> allIds = Stream.concat(toApprove.stream(), toReject.stream()).toList();
		List<PendingOfficialEntity> updatedOfficials = pendingOfficialRepository.findAllById(allIds);

		List<OfficialEntity> officialsToSave = updatedOfficials.stream()
				.filter(official -> "approved".equals(official.getStatus()))
				.map(pending -> new OfficialEntity(
						pending.getUserRole(),
						pending.getJobTitle()
				))
				.toList();

		if (!officialsToSave.isEmpty()) {
			officialRepository.saveAll(officialsToSave);
		}

		return updatedOfficials.stream()
				.map(official -> new ApproveOfficialsResponseDTO(
						official.getId(),
						official.getStatus()
				))
				.toList();
	}


}

package ecoton.ecotonbackend.service;

import ecoton.ecotonbackend.entity.OrganizerEntity;
import ecoton.ecotonbackend.entity.PendingOrganizerEntity;
import ecoton.ecotonbackend.entity.UserEntity;
import ecoton.ecotonbackend.model.dto.RegistrationFormOrganizerRequestDTO;
import ecoton.ecotonbackend.model.dto.RegistrationFormUserRequestDTO;
import ecoton.ecotonbackend.model.dto.RegistrationFormResponseDTO;
import ecoton.ecotonbackend.repository.AuthUserRepository;
import ecoton.ecotonbackend.repository.OrganizerRepository;
import ecoton.ecotonbackend.repository.PendingOrganizerRepository;
import ecoton.ecotonbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationFormService {

	private final UserRepository userRepository;

	private final AuthUserRepository authUserRepository;

	private final OrganizerRepository organizerRepository;

	private final PendingOrganizerRepository pendingOrganizerRepository;

	public RegistrationFormResponseDTO fillRegistrationFormUser(RegistrationFormUserRequestDTO form) {
		var userToSave = new UserEntity(
				authUserRepository.findById(form.getUserId()).orElseThrow(RuntimeException::new), // change to custom exception
				form.getName(),
				form.getEmail(),
				form.getGender(),
				form.getAge()
		);

		var savedUser = userRepository.save(userToSave);

		return new RegistrationFormResponseDTO(
				savedUser.getUserRole().getUsername(),
				true
		);
	}

	public RegistrationFormResponseDTO fillRegistrationFormOrganizer(RegistrationFormOrganizerRequestDTO form) {
		var organizer = new OrganizerEntity(
				authUserRepository.findById(form.getUserId()).orElseThrow(RuntimeException::new),
				form.getName(),
				form.getType(),
				form.getLegalEntityId()
		);

		var pendingOrganizer = new PendingOrganizerEntity(
				organizer.getUserRole(),
				organizer.getName(),
				organizer.getType(),
				organizer.getLegalEntityId()
		);
		pendingOrganizerRepository.save(pendingOrganizer);

		return new RegistrationFormResponseDTO(
				organizer.getUserRole().getUsername(),
				true
		);
	}
}

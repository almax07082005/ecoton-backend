package ecoton.ecotonbackend.service;

import ecoton.ecotonbackend.entity.OfficialEntity;
import ecoton.ecotonbackend.entity.OrganizerEntity;
import ecoton.ecotonbackend.entity.PendingOfficialEntity;
import ecoton.ecotonbackend.entity.UserEntity;
import ecoton.ecotonbackend.entity.roles.RolesEnum;
import ecoton.ecotonbackend.exceptions.RoleDoesNotExistException;
import ecoton.ecotonbackend.exceptions.UserDoesNotExistException;
import ecoton.ecotonbackend.model.dto.RegistrationFormRequestDTO;
import ecoton.ecotonbackend.model.dto.RegistrationFormResponseDTO;
import ecoton.ecotonbackend.repository.AuthUserRepository;
import ecoton.ecotonbackend.repository.OrganizerRepository;
import ecoton.ecotonbackend.repository.PendingOfficialRepository;
import ecoton.ecotonbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RegistrationFormService {

	private final UserRepository userRepository;

	private final AuthUserRepository authUserRepository;

	private final OrganizerRepository organizerRepository;

	private final PendingOfficialRepository pendingOfficialRepository;

	public RegistrationFormResponseDTO fillRegistrationForm(RegistrationFormRequestDTO form) {
		try {
			return switch (RolesEnum.valueOf(form.getRole())) {
				case USER -> fillRegistrationFormUser(form);
				case ORGANIZER -> fillRegistrationFormOrganizer(form);
				case OFFICIAL -> fillRegistrationFormOfficial(form);
				case ADMIN -> null;
			};
		} catch (NullPointerException | IllegalArgumentException e) {
			throw new RoleDoesNotExistException();
		}
	}

	private RegistrationFormResponseDTO fillRegistrationFormUser(RegistrationFormRequestDTO form) {
		var userToSave = new UserEntity(
				authUserRepository.findById(form.getUserId()).orElseThrow(UserDoesNotExistException::new),
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

	private RegistrationFormResponseDTO fillRegistrationFormOfficial(RegistrationFormRequestDTO form) {
		var userToSave = authUserRepository.findById(form.getUserId());
		var userDetails = userRepository.findById(form.getUserId()).orElseThrow(UserDoesNotExistException::new);

		var official = new OfficialEntity(
				authUserRepository.findById(form.getUserId()).orElseThrow(UserDoesNotExistException::new),
				form.getJobTitle()
		);

		var pendingOrganizer = new PendingOfficialEntity(
				official.getUserRole(),
				userDetails.getName(),
				userToSave.orElseThrow(RuntimeException::new).getUsername(),
				official.getJobTitle()
		);
		pendingOfficialRepository.save(pendingOrganizer);

		return new RegistrationFormResponseDTO(
				official.getUserRole().getUsername(),
				true
		);
	}

	private RegistrationFormResponseDTO fillRegistrationFormOrganizer(RegistrationFormRequestDTO form) {

		var organizerToSave = new OrganizerEntity(
				authUserRepository.findById(form.getUserId()).orElseThrow(UserDoesNotExistException::new),
				form.getName(),
				form.getType(),
				form.getLegalEntityId()
		);
		var organizer = organizerRepository.save(organizerToSave);

		return new RegistrationFormResponseDTO(
				organizer.getUserRole().getUsername(),
				true
		);
	}
}

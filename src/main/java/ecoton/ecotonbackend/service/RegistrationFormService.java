package ecoton.ecotonbackend.service;

import ecoton.ecotonbackend.entity.OfficialEntity;
import ecoton.ecotonbackend.entity.OrganizerEntity;
import ecoton.ecotonbackend.entity.PendingOfficialEntity;
import ecoton.ecotonbackend.entity.UserEntity;
import ecoton.ecotonbackend.entity.roles.RolesEnum;
import ecoton.ecotonbackend.exceptions.LegalEntityDoesNotExistException;
import ecoton.ecotonbackend.exceptions.RoleDoesNotExistException;
import ecoton.ecotonbackend.exceptions.UserDoesNotExistException;
import ecoton.ecotonbackend.model.dto.LegalEntityFirstResponseDTO;
import ecoton.ecotonbackend.model.dto.LegalEntitySecondResponseDTO;
import ecoton.ecotonbackend.model.dto.RegistrationFormRequestDTO;
import ecoton.ecotonbackend.model.dto.RegistrationFormResponseDTO;
import ecoton.ecotonbackend.repository.AuthUserRepository;
import ecoton.ecotonbackend.repository.OrganizerRepository;
import ecoton.ecotonbackend.repository.PendingOfficialRepository;
import ecoton.ecotonbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;


@Service
@RequiredArgsConstructor
public class RegistrationFormService {

	private final UserRepository userRepository;

	private final AuthUserRepository authUserRepository;

	private final OrganizerRepository organizerRepository;

	private final PendingOfficialRepository pendingOfficialRepository;

	private final WebClient webClient = WebClient.create();

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

		try {
			var legalEntityId = Long.parseLong(form.getLegalEntityId());
			var legalEntityExists = getLegalEntityInfo(legalEntityId);
			if (!legalEntityExists) {
				throw new LegalEntityDoesNotExistException();
			}

		} catch (NullPointerException e) {
			throw new LegalEntityDoesNotExistException();
		}

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

	private Boolean getLegalEntityInfo(Long entityId) {
		var uri = "https://egrul.nalog.ru/";

		var response = webClient.post()
				.uri(uri)
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(Map.of("query", entityId))
				.retrieve()
				.bodyToMono(LegalEntityFirstResponseDTO.class)
				.block();

		String token;
		try {
			token = response.getT();
		} catch (NullPointerException e) {
			throw new LegalEntityDoesNotExistException();
		}

		var responseTwo = webClient.get()
				.uri(uri + "search-result/" + token)
				.retrieve()
				.bodyToMono(LegalEntitySecondResponseDTO.class)
				.block();


		try {
			System.out.println(responseTwo.getRows().get(0));
			if (responseTwo.getRows().stream().anyMatch(row -> String.valueOf(entityId).equals(row.getO()))) {
				return true;
			}
		} catch (NullPointerException e) {
			throw new LegalEntityDoesNotExistException();
		}
		return false;

	}
}

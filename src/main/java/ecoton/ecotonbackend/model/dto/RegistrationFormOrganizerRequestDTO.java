package ecoton.ecotonbackend.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class RegistrationFormOrganizerRequestDTO {

	@JsonProperty("user_id")
	private Integer userId;

	private String name;

	private String type;

	@JsonProperty("legal_entity_id")
	private String legalEntityId;

}

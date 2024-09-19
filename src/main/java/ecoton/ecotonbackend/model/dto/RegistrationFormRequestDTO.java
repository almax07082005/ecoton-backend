package ecoton.ecotonbackend.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationFormRequestDTO {

	@JsonProperty("user_id")
	private Integer userId;

	private String email;

	private String name;

	private String role;

	private Integer age;

	private String gender;

	private String jobTitle;

	private String type;

	@JsonProperty("legal_entity_id")
	private String legalEntityId;

}

package ecoton.ecotonbackend.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class RegistrationFormUserRequestDTO {

	@JsonProperty("user_id")
	private Integer userId;

	private Integer age;

	private String name;

	private String email;

	private String gender;

}

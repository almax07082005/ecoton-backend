package ecoton.ecotonbackend.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationFormResponseDTO {

	private String username;

	@JsonProperty("registration_form_success")
	private Boolean registrationFormSuccess;

	private String message = null;

	public RegistrationFormResponseDTO(String username, Boolean registrationFormSuccess) {
		this.username = username;
		this.registrationFormSuccess = registrationFormSuccess;
	}
}

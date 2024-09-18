package ecoton.ecotonbackend.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationResponseDTO {

	private String username;

	@JsonProperty("registration_success")
	private Boolean registrationSuccess;

	private String message = "Registration successful";

	private String jwt;

	public RegistrationResponseDTO(String username, boolean registrationSuccess, String jwt) {
		this.username = username;
		this.registrationSuccess = registrationSuccess;
		this.jwt = jwt;
	}
}

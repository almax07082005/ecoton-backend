package ecoton.ecotonbackend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponseDTO {

	private String username;

	private String jwt;

}

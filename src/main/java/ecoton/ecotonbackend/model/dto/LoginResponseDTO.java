package ecoton.ecotonbackend.model.dto;

import ecoton.ecotonbackend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDTO {

	private User user;

	private String jwt;

}

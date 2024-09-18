package ecoton.ecotonbackend.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PendingOrganizerResponseDTO {

	private Integer id;

	@JsonProperty("user_id")
	private Integer userId;

	private String name;

	private String type;

	private String legalEntityId;

}

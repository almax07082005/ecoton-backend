package ecoton.ecotonbackend.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ApproveOrganizersRequestDTO {

	private Integer id;

	@JsonProperty("is_approved")
	private Boolean isApproved;

}

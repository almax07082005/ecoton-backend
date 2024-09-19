package ecoton.ecotonbackend.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApproveOfficialsRequestDTO {

	private Integer id;

	@JsonProperty("is_approved")
	private Boolean isApproved;

}

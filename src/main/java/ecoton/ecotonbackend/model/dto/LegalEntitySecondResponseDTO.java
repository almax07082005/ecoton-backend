package ecoton.ecotonbackend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LegalEntitySecondResponseDTO {

	private List<SecondResponseRow> rows;

}


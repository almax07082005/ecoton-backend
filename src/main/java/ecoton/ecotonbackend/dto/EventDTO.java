package ecoton.ecotonbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {
    private Integer id;
    private String name;
    private String description;
    private Long mapsId;
    private LocalDateTime dateTime;
    private String imageName;
    private Integer participantsAmount;
    private String orgName;
}

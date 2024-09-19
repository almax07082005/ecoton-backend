package ecoton.ecotonbackend.request.update;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Value
@Builder
@Jacksonized
public class EventUpdateRequest {
    String name;
    String description;
    Long mapsId;
    LocalDateTime dateTime;
    String imageName;
}

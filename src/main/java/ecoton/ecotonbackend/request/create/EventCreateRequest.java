package ecoton.ecotonbackend.request.create;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Value
@Builder
@Jacksonized
public class EventCreateRequest {
    String name;
    String description;
    Long mapsId;
    LocalDateTime dateTime;
    String imageName;
    Integer organizerId;
}

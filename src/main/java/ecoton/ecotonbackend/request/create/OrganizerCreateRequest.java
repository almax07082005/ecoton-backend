package ecoton.ecotonbackend.request.create;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class OrganizerCreateRequest {
    String name;
    String type;
}

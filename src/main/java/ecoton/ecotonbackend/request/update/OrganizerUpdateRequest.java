package ecoton.ecotonbackend.request.update;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class OrganizerUpdateRequest {
    String name;
    String type;
}

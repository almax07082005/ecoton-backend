package ecoton.ecotonbackend.request.create;

import ecoton.ecotonbackend.entity.roles.UserRole;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class OrganizerCreateRequest {
    String name;
    String type;
    UserRole userRole;
    String legalEntityId;
}

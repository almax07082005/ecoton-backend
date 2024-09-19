package ecoton.ecotonbackend.entity;

import ecoton.ecotonbackend.entity.roles.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Entity
@Jacksonized
@EqualsAndHashCode
@Getter
@Setter
@RequiredArgsConstructor
@Builder(toBuilder = true)
@AllArgsConstructor
@Table(name = "organizers")
public class OrganizerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserRole userRole;

    private String name;
    private String type;
    private String legalEntityId;

    @OneToMany(
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "organizer_id")
    private List<EventEntity> events;

    public OrganizerEntity(UserRole userRole, String name, String type, String legalEntityId) {
        this.userRole = userRole;
        this.name = name;
        this.type = type;
        this.legalEntityId = legalEntityId;
    }
}

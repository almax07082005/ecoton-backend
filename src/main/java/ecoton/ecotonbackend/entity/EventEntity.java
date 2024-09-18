package ecoton.ecotonbackend.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "events")
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description; // restricted size
    private Long mapsId;
    private LocalDateTime dateTime;
    private String imageName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<UserEntity> participants;

    @ManyToOne(
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            },
            fetch = FetchType.EAGER,
            optional = false
    )
    @JoinColumn(name = "organizer_id")
    private OrganizerEntity organizer;
}

/*
CRUD for organizers
append to list of users to event

get events for departments:
    private Integer id;
    private String name;
    private String description;
    private Long mapsId;
    private LocalDateTime dateTime;
    private List<name of organizers> organizers;
    private Integer participantsAmount;

get organizers for departments:
    private Integer id;
    private String name;
    private String type;

get participants for departments:
    private Integer id;
    private String name;
    private String email;
    private String gender;
    private Integer age;
*/
package ecoton.ecotonbackend.entity;

import ecoton.ecotonbackend.entity.roles.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.annotations.Fetch;

import java.util.List;

@Entity
@Jacksonized
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pending_organizers")
public class PendingOrganizerEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private UserRole userRole;

	private String name;
	private String type;
	private String legalEntityId;
	@Builder.Default
	private String status = "pending";

	public PendingOrganizerEntity(UserRole userRole, String name, String type, String legalEntityId) {
		this.userRole = userRole;
		this.name = name;
		this.type = type;
		this.legalEntityId = legalEntityId;
		this.status = "pending";
	}
}

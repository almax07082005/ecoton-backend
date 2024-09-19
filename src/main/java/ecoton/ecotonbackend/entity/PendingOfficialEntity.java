package ecoton.ecotonbackend.entity;

import ecoton.ecotonbackend.entity.roles.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Entity
@Jacksonized
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pending_officials")
public class PendingOfficialEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private UserRole userRole;

	private String name;

	private String email;

	private String jobTitle;

	@Builder.Default
	private String status = "pending";

	public PendingOfficialEntity(UserRole userRole, String name, String email, String jobTitle) {
		this.userRole = userRole;
		this.name = name;
		this.email = email;
		this.jobTitle = jobTitle;
		this.status = "pending";
	}
}

package ecoton.ecotonbackend.entity;

import ecoton.ecotonbackend.entity.roles.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "officials")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OfficialEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private UserRole userRole;

	private String jobTitle;

	public OfficialEntity(UserRole userRole, String jobTitle) {
		this.userRole = userRole;
		this.jobTitle = jobTitle;
	}
}

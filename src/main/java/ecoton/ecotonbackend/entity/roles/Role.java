package ecoton.ecotonbackend.entity.roles;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "auth_roles")
@Getter
@Setter
@NoArgsConstructor
public class Role implements GrantedAuthority {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "role_id")
	private Integer roleId;

	private String authority;

	@Override
	public String getAuthority() {
		return this.authority;
	}

	public Role(String authority) {
		this.authority = authority;
	}
}

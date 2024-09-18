package ecoton.ecotonbackend.entity;

import ecoton.ecotonbackend.entity.roles.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserRole userRole;

    private String name;
    private String email;
    private String gender;
    private Integer age;

    public UserEntity(UserRole userRole, String name, String email, String gender, Integer age) {
        this.userRole = userRole;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.age = age;
    }
}

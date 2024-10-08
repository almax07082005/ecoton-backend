package ecoton.ecotonbackend.repository;

import ecoton.ecotonbackend.entity.roles.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRoleRepository extends JpaRepository<Role, Integer> {
	Optional<Role> findByAuthority(String authority);
}
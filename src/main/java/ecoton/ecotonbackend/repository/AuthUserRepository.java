package ecoton.ecotonbackend.repository;

import ecoton.ecotonbackend.entity.roles.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthUserRepository extends JpaRepository<UserRole, Integer> {
	Optional<UserRole> findByUsername(String username);
}
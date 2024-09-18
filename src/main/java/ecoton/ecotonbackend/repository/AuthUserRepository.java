package ecoton.ecotonbackend.repository;

import ecoton.ecotonbackend.entity.roles.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthUserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUsername(String username);
}
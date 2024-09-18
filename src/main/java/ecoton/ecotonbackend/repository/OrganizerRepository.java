package ecoton.ecotonbackend.repository;

import ecoton.ecotonbackend.entity.OrganizerEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizerRepository extends JpaRepository<OrganizerEntity, Integer> {
    Optional<OrganizerEntity> findById(@NonNull Integer id);
    void deleteById(@NonNull Integer id);
}

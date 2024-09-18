package ecoton.ecotonbackend.repository;

import ecoton.ecotonbackend.entity.OrganizerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizerRepository extends JpaRepository<OrganizerEntity, Integer> {
}

package ecoton.ecotonbackend.repository;

import ecoton.ecotonbackend.entity.EventEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Integer> {
    Optional<EventEntity> findById(@NonNull Integer id);
}

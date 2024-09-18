package ecoton.ecotonbackend.repository;

import ecoton.ecotonbackend.entity.PendingOrganizerEntity;
import ecoton.ecotonbackend.model.dto.ApproveOrganizersRequestDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PendingOrganizerRepository extends JpaRepository<PendingOrganizerEntity, Integer> {

	List<PendingOrganizerEntity> findAllByStatus(String status);

	@Modifying
	@Transactional
	@Query("UPDATE PendingOrganizerEntity o SET o.status = 'approved' WHERE o.id IN :ids")
	int approvePendingOrganizers(List<Integer> ids);

	@Modifying
	@Transactional
	@Query("UPDATE PendingOrganizerEntity o SET o.status = 'rejected' WHERE o.id IN :ids")
	int rejectPendingOrganizers(List<Integer> ids);
}


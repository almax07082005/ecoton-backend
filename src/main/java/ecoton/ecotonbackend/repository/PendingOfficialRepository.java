package ecoton.ecotonbackend.repository;

import ecoton.ecotonbackend.entity.PendingOfficialEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PendingOfficialRepository extends JpaRepository<PendingOfficialEntity, Integer> {

	List<PendingOfficialEntity> findAllByStatus(String status);

	@Modifying
	@Transactional
	@Query("UPDATE PendingOfficialEntity o SET o.status = 'approved' WHERE o.id IN :ids")
	void approvePendingOfficials(List<Integer> ids);

	@Modifying
	@Transactional
	@Query("UPDATE PendingOfficialEntity o SET o.status = 'rejected' WHERE o.id IN :ids")
	void rejectPendingOfficials(List<Integer> ids);
}


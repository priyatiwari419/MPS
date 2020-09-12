package mps.commisioner.repository;

import mps.commisioner.entity.ArrestHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArrestHistoryRepository extends JpaRepository<ArrestHistoryEntity,Long> {
}

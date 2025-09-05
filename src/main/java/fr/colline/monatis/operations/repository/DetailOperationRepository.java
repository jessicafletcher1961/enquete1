package fr.colline.monatis.operations.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.colline.monatis.operations.model.DetailOperation;

@Repository 
public interface DetailOperationRepository extends JpaRepository<DetailOperation, Long> {

	Optional<DetailOperation> findByOperationIdAndSequence(
			Long operationId, 
			int sequence);

	boolean existsByOperationIdAndSequence(
			Long operationId, 
			int sequence);

	Set<DetailOperation> findByOperationId(
			Long operationId);

	Set<DetailOperation> findByOperationId(
			Long operationId, 
			Sort tri);

	void deleteByOperationId(
			Long operationId);
}

package fr.colline.monatis.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.colline.monatis.model.Operation;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

	public Optional<Operation> findById(Long id);

	public boolean existsById(Long id);
	
	public Optional<Operation> findByNumero(String numero);

	public boolean existsByNumero(String numero);

	public int countByCompteDepenseId(Long compteId);

	public int countByCompteRecetteId(Long compteId);

	public boolean existsByNumeroAndId(String numero, Long id);

	public boolean existsByNumeroAndIdNot(String numero, Long id);
}

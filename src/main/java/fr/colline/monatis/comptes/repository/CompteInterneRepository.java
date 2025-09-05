package fr.colline.monatis.comptes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.colline.monatis.comptes.model.CompteInterne;

@Repository
public interface CompteInterneRepository extends CompteRepository<CompteInterne> {

	@Query(nativeQuery = true,
			value = "select banque_id from public.compte_interne where id = :id")
	public Long findBanqueIdById(@Param("id") Long id);

	@Query(nativeQuery = true,
			value = "select titulaire_id from public.compte_interne_titulaire where compte_interne_id = :id")
	public List<Long> findTitulairesIdById(Long id);
}

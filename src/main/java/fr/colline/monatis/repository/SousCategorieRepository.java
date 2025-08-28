package fr.colline.monatis.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.colline.monatis.model.SousCategorie;

@Repository
public interface SousCategorieRepository extends ReferenceRepository<SousCategorie> {

	@Query(nativeQuery = true,
			value = "select count(id) from public.detail_operation where sous_categorie_id = :id")
	public int compterDetailOperationParSousCategorieId(@Param("id") Long id);

	@Query(nativeQuery = true,
			value = "select categorie_id from public.sous_categorie where id = :id")
	public Long findCategorieIdById(@Param("id") Long id);
}

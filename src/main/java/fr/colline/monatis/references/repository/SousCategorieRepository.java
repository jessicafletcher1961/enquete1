package fr.colline.monatis.references.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.colline.monatis.references.model.SousCategorie;

@Repository
public interface SousCategorieRepository extends ReferenceRepository<SousCategorie> {

	@Override
	@Query(nativeQuery = true,
			value = "select count(1) <> 0 from public.sous_categorie where nom = :nom")
	public boolean existsByNom(@Param("nom") String nom);
	
	@Query(nativeQuery = true,
			value = "select count(1) <> 0 from public.detail_operation where sous_categorie_id = :id")
	public boolean existsDetailOperationParSousCategorieId(@Param("id") Long id);

	@Query(nativeQuery = true,
			value = "select categorie_id from public.sous_categorie where id = :id")
	public Long findCategorieIdById(@Param("id") Long id);
}

package fr.colline.monatis.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.colline.monatis.model.Beneficiaire;

@Repository
public interface BeneficiaireRepository extends ReferenceRepository <Beneficiaire> {

	@Query(nativeQuery = true,
			value = "select count(1) from public.detail_operation_beneficiaire where beneficiaire_id = :bid")
	public int compterDetailOperationParBeneficiaireId(@Param("bid") Long beneficiaireId);	
}
	
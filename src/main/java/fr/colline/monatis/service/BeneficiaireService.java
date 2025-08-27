package fr.colline.monatis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import fr.colline.monatis.exception.ServiceFonctionnelleErreur;
import fr.colline.monatis.exception.ServiceFonctionnelleException;
import fr.colline.monatis.exception.ServiceProgrammationErreur;
import fr.colline.monatis.exception.ServiceTechniqueErreur;
import fr.colline.monatis.exception.ServiceTechniqueException;
import fr.colline.monatis.model.Beneficiaire;
import fr.colline.monatis.repository.BeneficiaireRepository;
import fr.colline.monatis.repository.ReferenceRepository;

@Service
public class BeneficiaireService extends ReferenceService<Beneficiaire> {

	@Autowired private BeneficiaireRepository repository;
	
	@Override
	protected ReferenceRepository<Beneficiaire> getRepository() {

		return repository;
	}

	@Override
	protected Class<Beneficiaire> getTClass() {
		
		return Beneficiaire.class;
	}
	
	@Override
	protected Beneficiaire controlerEtPreparerPourSuppression(Long beneficiaireId) 
			throws ServiceFonctionnelleException, ServiceTechniqueException {
		
		Assert.notNull(
				beneficiaireId, 
				ServiceProgrammationErreur.ID_NULL.getMessage(getTClass().getSimpleName()));

		Beneficiaire beneficiaire = super.controlerEtPreparerPourSuppression(beneficiaireId);
		
		verifierAbsenceDetailOperationAssocie(beneficiaire.getId(), beneficiaire.getNom());

		return beneficiaire;
	}
	
	private void verifierAbsenceDetailOperationAssocie(Long beneficiaireId, String beneficiaireNom) 
			throws ServiceFonctionnelleException, ServiceTechniqueException {

		int nombreDetailOperation;
		try {
			nombreDetailOperation = repository.compterDetailOperationParBeneficiaireId(beneficiaireId);
		}
		catch ( Throwable t ) {
			throw new ServiceTechniqueException(
					ServiceTechniqueErreur.TECH_RECHERCHE_NOMBRE_DETAIL_OPERATION_PAR_BENEFICIAIRE_ID,
					beneficiaireId);
		}

		if ( nombreDetailOperation > 0 ) {
			throw new ServiceFonctionnelleException(
					ServiceFonctionnelleErreur.BENEFICIAIRE_SUPPRESSION_AVEC_DETAIL_OPERATION, 
					beneficiaireNom,
					nombreDetailOperation);
		}
	}
}

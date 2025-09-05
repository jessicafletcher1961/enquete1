package fr.colline.monatis.references.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.colline.monatis.exception.ServiceFonctionnelleErreur;
import fr.colline.monatis.exception.ServiceFonctionnelleException;
import fr.colline.monatis.exception.ServiceTechniqueErreur;
import fr.colline.monatis.exception.ServiceTechniqueException;
import fr.colline.monatis.references.model.Categorie;
import fr.colline.monatis.references.model.SousCategorie;
import fr.colline.monatis.references.repository.ReferenceRepository;
import fr.colline.monatis.references.repository.SousCategorieRepository;

@Service 
public class SousCategorieService extends ReferenceService<SousCategorie> {

	@Autowired private SousCategorieRepository sousCategorieRepository;

	@Autowired private CategorieService categorieService;
	
	@Override
	protected ReferenceRepository<SousCategorie> getRepository() {

		return sousCategorieRepository;
	}

	@Override
	protected Class<SousCategorie> getTClass() {
		
		return SousCategorie.class;
	}

	@Override
	protected SousCategorie controlerEtPreparerPourCreation(SousCategorie sousCategorie) 
					throws ServiceFonctionnelleException, ServiceTechniqueException {

		sousCategorie = super.controlerEtPreparerPourCreation(sousCategorie);

		verifierCategorieEnregistree(sousCategorie.getCategorie());

		return sousCategorie;
	}
	
	@Override
	protected SousCategorie controlerEtPreparerPourModification(SousCategorie sousCategorie) 
			throws ServiceFonctionnelleException, ServiceTechniqueException {
		
		sousCategorie = super.controlerEtPreparerPourModification(sousCategorie);

		verifierCategorieEnregistree(sousCategorie.getCategorie());
		
		return sousCategorie;
	}
	
	@Override
	protected SousCategorie controlerEtPreparerPourSuppression(Long sousCategorieId) 
			throws ServiceFonctionnelleException, ServiceTechniqueException {
		
		SousCategorie sousCategorie = super.controlerEtPreparerPourSuppression(sousCategorieId);
		
		verifierAbsenceDetailOperationAssocie(sousCategorie.getId(), sousCategorie.getNom());

		return sousCategorie;
	}
	
	private void verifierAbsenceDetailOperationAssocie(Long sousCategorieId, String sousCategorieNom) 
			throws ServiceFonctionnelleException, ServiceTechniqueException {

		boolean presenceDetailOperation;
		try {
			presenceDetailOperation = sousCategorieRepository.existsDetailOperationParSousCategorieId(sousCategorieId);
		}
		catch ( Throwable t ) {
			throw new ServiceTechniqueException(
					ServiceTechniqueErreur.TECH_RECHERCHE_NOMBRE_DETAIL_OPERATION_PAR_SOUS_CATEGORIE_ID,
					sousCategorieId);
		}

		if ( presenceDetailOperation ) {
			throw new ServiceFonctionnelleException(
					ServiceFonctionnelleErreur.SOUS_CATEGORIE_SUPPRESSION_AVEC_DETAIL_OPERATION, 
					sousCategorieNom);
		}
	}
	
	private void verifierCategorieEnregistree(Categorie categorie) 
			throws ServiceTechniqueException, ServiceFonctionnelleException {
	
		if ( categorie == null ) {
			throw new ServiceFonctionnelleException(
					ServiceFonctionnelleErreur.SOUS_CATEGORIE_CATEGORIE_REQUISE);
		}

		if ( categorie.getId() == null 
				|| ! categorieService.isExistantParId(categorie.getId()) ) {
			throw new ServiceFonctionnelleException(
					ServiceFonctionnelleErreur.SOUS_CATEGORIE_CATEGORIE_REQUISE);
		}
	}
}
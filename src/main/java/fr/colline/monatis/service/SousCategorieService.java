package fr.colline.monatis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import fr.colline.monatis.exception.ServiceFonctionnelleErreur;
import fr.colline.monatis.exception.ServiceFonctionnelleException;
import fr.colline.monatis.exception.ServiceProgrammationErreur;
import fr.colline.monatis.exception.ServiceTechniqueErreur;
import fr.colline.monatis.exception.ServiceTechniqueException;
import fr.colline.monatis.model.Categorie;
import fr.colline.monatis.model.SousCategorie;
import fr.colline.monatis.repository.ReferenceRepository;
import fr.colline.monatis.repository.SousCategorieRepository;
import jakarta.annotation.Nullable;

@Service 
public class SousCategorieService extends ReferenceService<SousCategorie> {

	@Autowired private SousCategorieRepository repository;
	
	@Autowired private CategorieService categorieService;
	
	@Override
	protected ReferenceRepository<SousCategorie> getRepository() {

		return repository;
	}

	@Override
	protected Class<SousCategorie> getTClass() {
		
		return SousCategorie.class;
	}

	/**
	 * Contrôles avant création d'une sous-catégorie
	 * 
	 * @param sousCategorie la sous-catégorie à vérifier dans le cadre d'une création
	 * @param categorieId l'id de la catégorie existante à laquelle la sous-catégorie à créer doit être reliée
	 * @return la sous-catégorie prête pour la création
	 * @throws ServiceFonctionnelleException si la sous-catégorie indiquée 
	 * a un id 
	 * ou n'a pas de nom 
	 * ou qu'une autre sous-catégorie a le même nom
	 * ou que la catégorie indiquée n'est pas en base
	 * @throws ServiceTechniqueException si un problème survient avec la base
	 */
	@Override
	protected SousCategorie controlerEtPreparerPourCreation(SousCategorie sousCategorie) 
					throws ServiceFonctionnelleException, ServiceTechniqueException {

		Assert.notNull(
				sousCategorie, 
				ServiceProgrammationErreur.SOUS_CATEGORIE_NULL.getMessage(getTClass().getSimpleName()));

		sousCategorie = super.controlerEtPreparerPourCreation(sousCategorie);

		verifierCategorieEnregistree(sousCategorie.getCategorie());
		
		return sousCategorie;
	}
	
	@Override
	protected SousCategorie controlerEtPreparerPourModification(SousCategorie sousCategorie) 
			throws ServiceFonctionnelleException, ServiceTechniqueException {
		
		Assert.notNull(
				sousCategorie, 
				ServiceProgrammationErreur.SOUS_CATEGORIE_NULL.getMessage(getTClass().getSimpleName()));

		sousCategorie = super.controlerEtPreparerPourModification(sousCategorie);

		verifierCategorieEnregistree(sousCategorie.getCategorie());
		
		return sousCategorie;
	}
	
	@Override
	protected SousCategorie controlerEtPreparerPourSuppression(Long sousCategorieId) 
			throws ServiceFonctionnelleException, ServiceTechniqueException {
		
		Assert.notNull(
				sousCategorieId, 
				ServiceProgrammationErreur.ID_NULL.getMessage(getTClass().getSimpleName()));

		SousCategorie sousCategorie = super.controlerEtPreparerPourSuppression(sousCategorieId);
		
		// Vérification par rapport aux détails des opérations (la sous-categorie ne doit pas y être référencée)
		verifierAbsenceDetailOperationAssocie(sousCategorie.getId(), sousCategorie.getNom());

//		// Rupture du lien entre la catégorie et la sous-catégorie
//		sousCategorie.changerCategorie(null);
//		
		return sousCategorie;
	}
	
	private void verifierCategorieEnregistree (@Nullable Categorie categorie) 
			throws ServiceFonctionnelleException, ServiceTechniqueException {

		if ( categorie == null ) {
			throw new ServiceFonctionnelleException(
					ServiceFonctionnelleErreur.SOUS_CATEGORIE_SANS_CATEGORIE);
		}
		
		if ( categorie.getId() == null 
				|| ! categorieService.isExistantParId(categorie.getId()) ) {
			throw new ServiceFonctionnelleException(
					ServiceFonctionnelleErreur.CATEGORIE_NON_ENREGISTREE_PAR_ID,
					categorie.getId());
		}
	}
	
	private void verifierAbsenceDetailOperationAssocie(Long sousCategorieId, String sousCategorieNom) 
			throws ServiceFonctionnelleException, ServiceTechniqueException {

		int nombreDetailOperation;
		try {
			nombreDetailOperation = repository.compterDetailOperationParSousCategorieId(sousCategorieId);
		}
		catch ( Throwable t ) {
			throw new ServiceTechniqueException(
					ServiceTechniqueErreur.TECH_RECHERCHE_NOMBRE_DETAIL_OPERATION_PAR_SOUS_CATEGORIE_ID,
					sousCategorieId);
		}

		if ( nombreDetailOperation > 0 ) {
			throw new ServiceFonctionnelleException(
					ServiceFonctionnelleErreur.SOUS_CATEGORIE_SUPPRESSION_AVEC_DETAIL_OPERATION, 
					sousCategorieNom,
					nombreDetailOperation);
		}
	}
}
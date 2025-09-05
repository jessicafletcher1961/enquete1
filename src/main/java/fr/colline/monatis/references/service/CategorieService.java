package fr.colline.monatis.references.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.colline.monatis.exception.ServiceFonctionnelleErreur;
import fr.colline.monatis.exception.ServiceFonctionnelleException;
import fr.colline.monatis.exception.ServiceTechniqueException;
import fr.colline.monatis.references.model.Categorie;
import fr.colline.monatis.references.repository.CategorieRepository;
import fr.colline.monatis.references.repository.ReferenceRepository;

@Service
public class CategorieService extends ReferenceService<Categorie>{

	@Autowired private CategorieRepository repository;

	@Override
	protected ReferenceRepository<Categorie> getRepository() {

		return repository;
	}

	@Override
	protected Class<Categorie> getTClass() {
		
		return Categorie.class;
	}

	/**
	 * Vérifie qu'aucune sous-categorie n'est rattachée à cette catégorie
	 *  
	 * @param categorie la catégorie à supprimer
	 * @throws ServiceTechniqueException si un problème survient avec la base
	 * @throws ServiceFonctionnelleException si 
	 * il y a un problème au niveau générique (pas d'id, non existance en base) 
	 * ou que au moins une sous-categorie est rattachée à cette banque 
	 */
	@Override
	protected Categorie controlerEtPreparerPourSuppression(Long categorieId) 
			throws ServiceFonctionnelleException, ServiceTechniqueException {
		
		Categorie categorie = super.controlerEtPreparerPourSuppression(categorieId);

		if ( categorie.getSousCategories() != null
				&& ! categorie.getSousCategories().isEmpty() ) {
			throw new ServiceFonctionnelleException(
					ServiceFonctionnelleErreur.CATEGORIE_SUPPRESSION_AVEC_SOUS_CATEGORIES,
					categorie.getNom(),
					categorie.getSousCategories().size());
		}
		
		return categorie;
	}
}

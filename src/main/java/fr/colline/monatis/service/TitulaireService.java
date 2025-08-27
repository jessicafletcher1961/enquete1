package fr.colline.monatis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.colline.monatis.exception.ServiceFonctionnelleErreur;
import fr.colline.monatis.exception.ServiceFonctionnelleException;
import fr.colline.monatis.exception.ServiceTechniqueException;
import fr.colline.monatis.model.Titulaire;
import fr.colline.monatis.repository.ReferenceRepository;
import fr.colline.monatis.repository.TitulaireRepository;

@Service
public class TitulaireService extends ReferenceService<Titulaire>{

	@Autowired private TitulaireRepository repository;
	
	@Override protected ReferenceRepository<Titulaire> getRepository() {
		
		return repository;
	}

	@Override
	protected Class<Titulaire> getTClass() {
		
		return Titulaire.class;
	}

	/**
	 * Vérifie qu'aucun compte interne n'est rattachée à ce titulaire
	 *  
	 * @param titulaireId l'id du titulaire à supprimer
	 * @return le titulaire à supprimer récupéré en base
	 * @throws ServiceTechniqueException si un problème survient avec la base
	 * @throws ServiceFonctionnelleException si 
	 * il y a un problème au niveau générique (pas d'id, non existance en base) 
	 * ou que au moins un compte interne est encore rattaché à ce titulaire 
	 */
	@Override
	protected Titulaire controlerEtPreparerPourSuppression(Long titulaireId) 
			throws ServiceFonctionnelleException, ServiceTechniqueException {
		
		Titulaire titulaire = super.controlerEtPreparerPourSuppression(titulaireId);
		
		if ( titulaire.getComptesInternes() != null 
				&& ! titulaire.getComptesInternes().isEmpty() ) {
			throw new ServiceFonctionnelleException(
					ServiceFonctionnelleErreur.TITULAIRE_SUPPRESSION_AVEC_COMPTES_INTERNES,
					titulaire.getNom(),
					titulaire.getComptesInternes().size());
		}
		
		return titulaire;
	}
}

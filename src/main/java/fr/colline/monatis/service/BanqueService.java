package fr.colline.monatis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.colline.monatis.exception.ServiceFonctionnelleErreur;
import fr.colline.monatis.exception.ServiceFonctionnelleException;
import fr.colline.monatis.exception.ServiceTechniqueException;
import fr.colline.monatis.model.Banque;
import fr.colline.monatis.repository.BanqueRepository;
import fr.colline.monatis.repository.ReferenceRepository;

@Service
public class BanqueService extends ReferenceService<Banque> {

	@Autowired private BanqueRepository repository;

	@Override
	protected ReferenceRepository<Banque> getRepository() {
		
		return repository;
	}

	@Override
	protected Class<Banque> getTClass() {
		
		return Banque.class;
	}

	/**
	 * Vérifie qu'aucun compte interne n'est rattaché à cette banque
	 *  
	 * @param banque la banque à supprimer
	 * @return la banque à supprimer récupérée en base
	 * @throws ServiceTechniqueException si un problème survient avec la base
	 * @throws ServiceFonctionnelleException si 
	 * il y a un problème au niveau générique (pas d'id, non existance en base) 
	 * ou que au moins un compte interne est rattaché à cette banque 
	 */
	@Override
	protected Banque controlerEtPreparerPourSuppression(Long banqueId) 
			throws ServiceFonctionnelleException, ServiceTechniqueException {
	
		Banque banque = super.controlerEtPreparerPourSuppression(banqueId);
		
		if ( banque.getComptesInternes() != null
				&& ! banque.getComptesInternes().isEmpty() ) {
			throw new ServiceFonctionnelleException(
					ServiceFonctionnelleErreur.BANQUE_SUPPRESSION_AVEC_COMPTES_INTERNES,
					banque.getNom(),
					banque.getComptesInternes().size());
		}
		
		return banque;
	}
}

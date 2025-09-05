package fr.colline.monatis.comptes.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.colline.monatis.comptes.TypeCompteInterne;
import fr.colline.monatis.comptes.model.CompteInterne;
import fr.colline.monatis.comptes.repository.CompteInterneRepository;
import fr.colline.monatis.comptes.repository.CompteRepository;
import fr.colline.monatis.exception.ServiceFonctionnelleErreur;
import fr.colline.monatis.exception.ServiceFonctionnelleException;
import fr.colline.monatis.exception.ServiceTechniqueException;
import fr.colline.monatis.references.model.Banque;
import fr.colline.monatis.references.model.Titulaire;
import fr.colline.monatis.references.service.BanqueService;
import fr.colline.monatis.references.service.TitulaireService;
import jakarta.annotation.Nullable;

@Service public class CompteInterneService extends CompteService<CompteInterne> {

	@Autowired private CompteInterneRepository compteInterneRepository;

	@Autowired private BanqueService banqueService;
	@Autowired private TitulaireService titulaireService;
	
	@Override
	protected CompteRepository<CompteInterne> getRepository() {

		return compteInterneRepository;
	}

	@Override
	protected Class<CompteInterne> getTClass() {

		return CompteInterne.class;
	}

	@Override
	protected CompteInterne controlerEtPreparerPourCreation(
			CompteInterne compteInterne) 
					throws ServiceTechniqueException, ServiceFonctionnelleException {

		// Vérifications id et identifiant 
		compteInterne = super.controlerEtPreparerPourCreation(compteInterne);

		// Vérification validité attributs obligatoires
		verifierTypeCompteInterne(compteInterne.getTypeCompteInterne());
		verifierBanqueEnregistree(compteInterne.getTypeCompteInterne(), compteInterne.getBanque());
		verifierTitulairesEnregistres(compteInterne.getTypeCompteInterne(), compteInterne.getTitulaires());

		// Initialisations avec valeurs par défaut
		if ( compteInterne.getDateSoldeInitial() == null ) {
			compteInterne.setDateSoldeInitial(Timestamp.from(Instant.now()));
		}
		if ( compteInterne.getMontantSoldeInitialEnCentimes() == null ) {
			compteInterne.setMontantSoldeInitialEnCentimes(0L);
		}

		return compteInterne;
	}

	@Override
	protected CompteInterne controlerEtPreparerPourModification(CompteInterne compteInterne) 
			throws ServiceFonctionnelleException, ServiceTechniqueException {

		compteInterne = super.controlerEtPreparerPourModification(compteInterne);

		verifierTypeCompteInterne(compteInterne.getTypeCompteInterne());
		verifierBanqueEnregistree(compteInterne.getTypeCompteInterne(), compteInterne.getBanque());
		verifierTitulairesEnregistres(compteInterne.getTypeCompteInterne(), compteInterne.getTitulaires());

		return compteInterne;
	}
	
	private void verifierTypeCompteInterne(TypeCompteInterne typeCompteInterne) 
			throws ServiceFonctionnelleException {

		if ( typeCompteInterne == null ) {
			throw new ServiceFonctionnelleException (
					ServiceFonctionnelleErreur.COMPTE_INTERNE_TYPE_COMPTE_REQUIS);
		}
	}
	
	private void verifierBanqueEnregistree(TypeCompteInterne typeCompteInterne, Banque banque) 
			throws ServiceFonctionnelleException, ServiceTechniqueException {
		
		if ( banque == null ) {
			if ( typeCompteInterne.isBanqueObligatoire() ) {
				throw new ServiceFonctionnelleException(
						ServiceFonctionnelleErreur.COMPTE_INTERNE_BANQUE_REQUISE);
			}
			else {
				return;
			}
		}
		
		if ( banque.getId() == null 
				|| ! banqueService.isExistantParId(banque.getId()) ) {
			throw new ServiceFonctionnelleException(
					ServiceFonctionnelleErreur.BANQUE_NON_ENREGISTREE_PAR_ID,
					banque.getId());
		}
	}
	
	private void verifierTitulairesEnregistres(TypeCompteInterne typeCompteInterne, @Nullable Set<Titulaire> titulaires) 
			throws ServiceFonctionnelleException, ServiceTechniqueException {

		if ( titulaires == null ) {
			throw new ServiceFonctionnelleException (
					ServiceFonctionnelleErreur.COMPTE_INTERNE_TABLEAU_TITULAIRE_REQUIS);
		}
		
		if ( titulaires.size() == 0 ) {
			if ( typeCompteInterne.isAuMoinsUnTitulaireObligatoire() ) {
				throw new ServiceFonctionnelleException (
						ServiceFonctionnelleErreur.COMPTE_INTERNE_AU_MOINS_UN_TITULAIRE_REQUIS);
			}
			else {
				return;
			}
		}
		
		for ( Titulaire titulaire : titulaires ) {
			if ( titulaire == null ) {
				throw new ServiceFonctionnelleException(
						ServiceFonctionnelleErreur.COMPTE_INTERNE_TABLEAU_TITULAIRE_CONTIENT_UN_TITULAIRE_NULL);
			}
			if ( titulaire.getId() == null 
					|| ! titulaireService.isExistantParId(titulaire.getId()) ) {
				throw new ServiceFonctionnelleException(
						ServiceFonctionnelleErreur.TITULAIRE_NON_ENREGISTRE_PAR_ID,
						titulaire.getId());
			}
		}
	}
}

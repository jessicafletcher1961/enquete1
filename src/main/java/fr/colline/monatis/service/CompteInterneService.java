package fr.colline.monatis.service;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.colline.monatis.exception.ServiceFonctionnelleErreur;
import fr.colline.monatis.exception.ServiceFonctionnelleException;
import fr.colline.monatis.exception.ServiceTechniqueException;
import fr.colline.monatis.model.CompteInterne;
import fr.colline.monatis.repository.CompteInterneRepository;
import fr.colline.monatis.repository.CompteRepository;

@Service public class CompteInterneService extends CompteService<CompteInterne>{

	@Autowired private CompteInterneRepository repository;

	@Override
	protected CompteRepository<CompteInterne> getRepository() {

		return repository;
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

		// Type du compte interne : attribut obligatoire
		if ( compteInterne.getTypeCompteInterne() == null ) {
			throw new ServiceFonctionnelleException (
					ServiceFonctionnelleErreur.COMPTE_INTERNE_TYPE_COMPTE_NULL);
		}

		// Banque obligatoire
		if ( compteInterne.getBanque() == null ) {
			throw new ServiceFonctionnelleException (
					ServiceFonctionnelleErreur.COMPTE_INTERNE_BANQUE_REQUISE);
		}

		// Titulaires : au moins un titulaire requis
		if ( compteInterne.getTitulaires() == null
				|| compteInterne.getTitulaires().size() < 1 ) {
			throw new ServiceFonctionnelleException (
					ServiceFonctionnelleErreur.COMPTE_INTERNE_AU_MOINS_UN_TITULAIRE_REQUIS);
		}

		// Date solde initial : valeur par défaut = now()
		if ( compteInterne.getDateSoldeInitial() == null ) {
			compteInterne.setDateSoldeInitial(Timestamp.from(Instant.now()));
		}
		
		// Montant solde initial : valeur par défaut = 0L
		if ( compteInterne.getMontantSoldeInitialEnCentimes() == null ) {
			compteInterne.setMontantSoldeInitialEnCentimes(0L);
		}

		return compteInterne;
	}

	@Override
	protected CompteInterne controlerEtPreparerPourModification(CompteInterne compteInterne) 
			throws ServiceFonctionnelleException, ServiceTechniqueException {

		compteInterne = super.controlerEtPreparerPourModification(compteInterne);

		if ( compteInterne.getTypeCompteInterne() == null ) {
			throw new ServiceFonctionnelleException (
					ServiceFonctionnelleErreur.COMPTE_INTERNE_TYPE_COMPTE_NULL);
		}

		// Banque obligatoire
		if ( compteInterne.getBanque() == null ) {
			throw new ServiceFonctionnelleException (
					ServiceFonctionnelleErreur.COMPTE_INTERNE_BANQUE_REQUISE);
		}

		// Titulaires : au moins un titulaire requis
		if ( compteInterne.getTitulaires() == null
				|| compteInterne.getTitulaires().size() < 1 ) {
			throw new ServiceFonctionnelleException (
					ServiceFonctionnelleErreur.COMPTE_INTERNE_AU_MOINS_UN_TITULAIRE_REQUIS);
		}


		return compteInterne;
	}
}

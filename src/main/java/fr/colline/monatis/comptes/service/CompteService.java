package fr.colline.monatis.comptes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;

import fr.colline.monatis.comptes.model.Compte;
import fr.colline.monatis.comptes.repository.CompteRepository;
import fr.colline.monatis.exception.ServiceFonctionnelleErreur;
import fr.colline.monatis.exception.ServiceFonctionnelleException;
import fr.colline.monatis.exception.ServiceProgrammationErreur;
import fr.colline.monatis.exception.ServiceTechniqueErreur;
import fr.colline.monatis.exception.ServiceTechniqueException;
import jakarta.annotation.Nullable;

public abstract class CompteService<T extends Compte> {

	public T rechercherParId(Long compteId) 
			throws ServiceTechniqueException {

		Assert.notNull(
				compteId,
				ServiceProgrammationErreur
				.ID_NULL
				.getMessage(getTClass().getSimpleName()));

		try {
			Optional<T> optional = getRepository().findById(compteId);
			return optional.isEmpty() ? null : optional.get();
		}
		catch (Throwable t) {
			throw new ServiceTechniqueException(
					t,
					ServiceTechniqueErreur.TECH_RECHERCHE_COMPTE_PAR_ID,
					getTClass().getSimpleName(),
					compteId );
		}
	}

	public boolean isExistantParId(Long compteId) 
			throws ServiceTechniqueException {

		Assert.notNull(
				compteId,
				ServiceProgrammationErreur
				.ID_NULL
				.getMessage(getTClass().getSimpleName()));

		try {
			return this.getRepository().existsById(compteId);
		}
		catch (Throwable t) {
			throw new ServiceTechniqueException(
					t,
					ServiceTechniqueErreur.TECH_EXISTANCE_COMPTE_PAR_ID,
					compteId,
					getTClass().getSimpleName() );
		}
	}

	public T rechercherParIdentifiant(String compteIdentifiant) 
			throws ServiceTechniqueException {

		Assert.notNull(
				compteIdentifiant,
				ServiceProgrammationErreur
				.IDENTIFIANT_NULL
				.getMessage(getTClass().getSimpleName()));

		try {
			Optional<T> optional = getRepository().findByIdentifiant(compteIdentifiant);
			return optional.isEmpty() ? null : optional.get();
		}
		catch (Throwable t) {
			throw new ServiceTechniqueException(
					t,
					ServiceTechniqueErreur.TECH_RECHERCHE_COMPTE_PAR_IDENTIFIANT,
					getTClass().getSimpleName(),
					compteIdentifiant );
		}
	}

	public boolean isExistantParIdentifiant(String compteIdentifiant) 
			throws ServiceTechniqueException {

		Assert.notNull(
				compteIdentifiant,
				ServiceProgrammationErreur
				.IDENTIFIANT_NULL
				.getMessage(getTClass().getSimpleName()));

		try {
			return this.getRepository().existsByIdentifiant(compteIdentifiant);
		}
		catch (Throwable t) {
			throw new ServiceTechniqueException(
					t,
					ServiceTechniqueErreur.TECH_EXISTANCE_COMPTE_PAR_IDENTIFIANT,
					compteIdentifiant,
					getTClass().getSimpleName() );
		}
	}

	public List<T> rechercherTous() throws ServiceTechniqueException{

		try {
			return getRepository().findAll();
		}
		catch (Throwable t) {
			throw new ServiceTechniqueException (
					t,
					ServiceTechniqueErreur.TECH_RECHERCHE_COMPTE_TOUS,
					getTClass().getSimpleName());
		}
	}

	public List<T> rechercherTous(Sort tri) 
			throws ServiceTechniqueException {

		Assert.notNull(
				tri,
				ServiceProgrammationErreur
				.TRI_NULL
				.getMessage(getTClass().getSimpleName()));

		try {
			return getRepository().findAll(tri);
		}
		catch (Throwable t) {
			throw new ServiceTechniqueException (
					t,
					ServiceTechniqueErreur.TECH_RECHERCHE_COMPTE_TOUS,
					getTClass().getSimpleName());
		}
	}

	public void supprimerTous() 
			throws ServiceTechniqueException {

		try {
			getRepository().deleteAll();
		}
		catch (Throwable t) {
			throw new ServiceTechniqueException (
					t,
					ServiceTechniqueErreur.TECH_SUPPRESSION_COMPTE_TOUS,
					getTClass().getSimpleName());
		}
	}

	public final T creerCompte(T compte) 
			throws ServiceFonctionnelleException, ServiceTechniqueException {

		Assert.notNull(
				compte, 
				ServiceProgrammationErreur.COMPTE_NULL.getMessage(getTClass().getSimpleName()));

		compte = controlerEtPreparerPourCreation(compte);

		return enregistrer(compte);
	}

	public final T modifierCompte(T compte) 
			throws ServiceTechniqueException, ServiceFonctionnelleException {

		Assert.notNull(
				compte, 
				ServiceProgrammationErreur.COMPTE_NULL.getMessage(getTClass().getSimpleName()));

		compte = controlerEtPreparerPourModification(compte);

		return enregistrer(compte);
	}

	public final void supprimerCompte(Long compteId) 
			throws ServiceTechniqueException, ServiceFonctionnelleException {

		Assert.notNull(
				compteId, 
				ServiceProgrammationErreur.ID_NULL.getMessage(getTClass().getSimpleName()));

		T compte = controlerEtPreparerPourSuppression(compteId);

		supprimer(compte);
	}

	protected T enregistrer(T compte) throws ServiceTechniqueException {

		try {
			return getRepository().save(compte);
		}
		catch (Throwable t) {
			throw new ServiceTechniqueException (
					t,
					ServiceTechniqueErreur.TECH_ENREGISTREMENT_COMPTE,
					getTClass().getSimpleName(),
					compte.getIdentifiant());
		}
	}

	protected void supprimer(T compte) 
			throws ServiceTechniqueException {

		try {
			this.getRepository().delete(compte);
		}
		catch (Throwable t) {
			throw new ServiceTechniqueException (
					t,
					ServiceTechniqueErreur.TECH_SUPPRESSION_COMPTE,
					getTClass().getSimpleName(),
					compte.getIdentifiant());
		}
	}

	protected T controlerEtPreparerPourCreation(T compte) 
			throws ServiceFonctionnelleException, ServiceTechniqueException {

		Assert.notNull(
				compte, 
				ServiceProgrammationErreur.COMPTE_NULL.getMessage(getTClass().getSimpleName()));

		verifierCompteNonEnregistre(compte.getId());
		verifierIdentifiantValideEtUnique(compte.getId(), compte.getIdentifiant());
		
		return compte;
	}
	
	protected T controlerEtPreparerPourModification(T compte) 
			throws ServiceFonctionnelleException, ServiceTechniqueException {

		Assert.notNull(
				compte, 
				ServiceProgrammationErreur.COMPTE_NULL.getMessage(getTClass().getSimpleName()));

		verifierCompteEnregistre(compte.getId());
		verifierIdentifiantValideEtUnique(compte.getId(), compte.getIdentifiant());

		return compte;
	}

	protected T controlerEtPreparerPourSuppression(Long compteId) 
			throws ServiceTechniqueException, ServiceFonctionnelleException {

		Assert.notNull(
				compteId, 
				ServiceProgrammationErreur.ID_NULL.getMessage(getTClass().getSimpleName()));

		verifierCompteEnregistre(compteId);
		verifierAbsenceOperationAssociee(compteId);
		
		return rechercherParId(compteId);
	}

	private void verifierCompteEnregistre(@Nullable Long compteId) 
			throws ServiceFonctionnelleException, ServiceTechniqueException {

		if ( compteId == null 
				|| ! isExistantParId(compteId) ) {
			throw new ServiceFonctionnelleException (
					ServiceFonctionnelleErreur.COMPTE_NON_ENREGISTRE_PAR_ID,
					getTClass().getSimpleName());
		}
	}
	
	private void verifierCompteNonEnregistre(@Nullable Long compteId) 
			throws ServiceFonctionnelleException, ServiceTechniqueException {
		
		if ( compteId != null 
				&& isExistantParId(compteId) ) {
			throw new ServiceFonctionnelleException (
					ServiceFonctionnelleErreur.COMPTE_DEJA_ENREGISTRE_PAR_ID,
					compteId,
					getTClass().getSimpleName());
		}
	}

	private void verifierIdentifiantValideEtUnique(
			@Nullable Long compteId,
			@Nullable String compteIdentifiant) 
					throws ServiceFonctionnelleException, ServiceTechniqueException {

		if ( compteIdentifiant == null || compteIdentifiant.isBlank() ) {
			throw new ServiceFonctionnelleException (
					ServiceFonctionnelleErreur.COMPTE_IDENTIFIANT_INVALIDE,
					getTClass().getSimpleName());
		}

		boolean isIdentifiantCreeOuModifie;
		boolean isIdentifiantDejaUtilise;
		
		if ( compteId == null ) {
			isIdentifiantCreeOuModifie = true;
			isIdentifiantDejaUtilise = isExistantParIdentifiant(compteIdentifiant);
		}
		else {
			try {
				isIdentifiantCreeOuModifie = ! getRepository().existsByIdentifiantAndId(compteIdentifiant, compteId);
				isIdentifiantDejaUtilise = getRepository().existsByIdentifiantAndIdNot(compteIdentifiant, compteId); 
			}
			catch ( Throwable t ) {
				throw new ServiceTechniqueException (
						t,
						ServiceTechniqueErreur.TECH_EXISTANCE_COMPTE_PAR_IDENTIFIANT,
						compteIdentifiant);
			}
		}
		
		if ( isIdentifiantCreeOuModifie && isIdentifiantDejaUtilise )
		{
			throw new ServiceFonctionnelleException (
					ServiceFonctionnelleErreur.COMPTE_IDENTIFIANT_DEJA_UTILISE,
					getTClass().getSimpleName(),
					compteIdentifiant);
		}
	}
	
	private void verifierAbsenceOperationAssociee(Long compteId) 
			throws ServiceTechniqueException, ServiceFonctionnelleException {
	
		int nombreOperationAssociee;

		try {
			nombreOperationAssociee = getRepository().countOperationAssociee(compteId);
		}
		catch (Throwable t) {
			throw new ServiceTechniqueException (
					t,
					ServiceTechniqueErreur.TECH_RECHERCHE_NOMBRE_OPERATION_PAR_COMPTE_ID,
					getTClass().getSimpleName(),
					compteId);
		}
		
		if ( nombreOperationAssociee > 0 ) {
			throw new ServiceFonctionnelleException (
					ServiceFonctionnelleErreur.COMPTE_SUPPRESSION_AVEC_OPERATION,
					compteId,
					nombreOperationAssociee);
		}
	}
	
	protected abstract Class<T> getTClass();
	protected abstract CompteRepository<T> getRepository();
}

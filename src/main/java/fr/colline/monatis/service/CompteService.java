package fr.colline.monatis.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;

import fr.colline.monatis.exception.ServiceFonctionnelleErreur;
import fr.colline.monatis.exception.ServiceFonctionnelleException;
import fr.colline.monatis.exception.ServiceProgrammationErreur;
import fr.colline.monatis.exception.ServiceTechniqueErreur;
import fr.colline.monatis.exception.ServiceTechniqueException;
import fr.colline.monatis.model.Compte;
import fr.colline.monatis.repository.CompteRepository;

public abstract class CompteService<T extends Compte> {

	/**
	 * Recherche d'un compte de type "T extends Compte" par son id
	 * 
	 * @param compteId l'id du compte à rechercher
	 * @return le compte correspondant à l'id indiqué, ou null s'il n'a pas été trouvé
	 * @throws IllegalArgumentException si l'id indiqué est null
	 * @throws ServiceTechniqueException si un problème survient avec la base
	 */
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

	/**
	 * Vérifie qu'un compte de type "T extends Compte" existe en base [recherche sur l'id indiqué] 
	 * 
	 * @param compteId l'id du compte à rechercher
	 * @return true si un compte correspondant à l'id indiqué est retrouvé en base
	 * @throws IllegalArgumentException si l'id indiqué est null
	 * @throws ServiceTechniqueException si un problème survient avec la base
	 */
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

	/**
	 * Recherche d'un compte de type "T extends Compte" par son identifiant
	 * 
	 * @param compteIdentifiant l'identifiant du compte à rechercher
	 * @return le compte correspondant à l'identifiant indiqué, ou null s'il n'a pas été trouvé
	 * @throws IllegalArgumentException si l'identifiant indiqué est null
	 * @throws ServiceTechniqueException si un problème survient avec la base
	 */
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

	/**
	 * Vérifie qu'un compte de type "T extends Compte" existe en base [recherche sur l'identifiant indiqué] 
	 * 
	 * @param compteIdentifiant l'identifiant du compte à rechercher
	 * @return true si un compte correspondant à l'identifiant indiqué est retrouvé en base
	 * @throws IllegalArgumentException si l'identifiant indiqué est null
	 * @throws ServiceTechniqueException si un problème survient avec la base
	 */
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

	/**
	 * Retourne la liste non triée des objets de 
	 * type "T extends Compte"
	 * 
	 * @throws ServiceTechniqueException si un problème survient avec la base
	 * @return la liste des objets
	 */
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

	/**
	 * Retourne la liste triée des objets de 
	 * type "T extends Compte"
	 * 
	 * @param tri le critère de tri (org.springframework.data.domain.Sort)
	 * @return la liste des objets
	 * @throws IllegalArgumentException si le critère de tri passé est à null
	 * @throws ServiceTechniqueException si un problème survient avec la base
	 */
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

	/**
	 * Supprimer tous les comptes de type "T extends Compte"
	 * 
	 * @throws ServiceTechniqueException si un problème survient avec la base
	 */
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

	/**
	 * Création d'un nouveau compte de type "T extends Compte"
	 * 
	 * @param compte le compte à créer
	 * @return le compte après enregistrement en base
	 * @throws IllegalArgumentException si compte passé est à null
	 * @throws ServiceFonctionnelleException si le compte à créer est invalide
	 * @throws ServiceTechniqueException si un problème survient avec la base
	 */
	public T creerCompte(T compte) 
			throws ServiceFonctionnelleException, ServiceTechniqueException {

		Assert.notNull(
				compte, 
				ServiceProgrammationErreur
				.COMPTE_NULL
				.getMessage(getTClass().getSimpleName()));

		compte = controlerEtPreparerPourCreation(compte);

		return enregistrer(compte);
	}

	/**
	 * Modification d'un compte de type "T extends Compte"
	 * 
	 * @param compte le compte à modifier
	 * @return le compte après enregistrement en base
	 * @throws IllegalArgumentException si compte passé est à null
	 * @throws ServiceFonctionnelleException si les modifications du compte sont invalides
	 * @throws ServiceTechniqueException si un problème survient avec la base
	 */
	public T modifierCompte(T compte) 
			throws ServiceTechniqueException, ServiceFonctionnelleException {

		Assert.notNull(
				compte, 
				ServiceProgrammationErreur
				.COMPTE_NULL
				.getMessage(getTClass().getSimpleName()));

		compte = controlerEtPreparerPourModification(compte);

		return enregistrer(compte);
	}

	/**
	 * Suppression d'un compte existant de type "T extends Compte"
	 * 
	 * @param compteId l'id du compte à supprimer
	 * @throws IllegalArgumentException si l'id passé est à null
	 * @throws ServiceTechniqueException si un problème survient avec la base
	 * @throws ServiceFonctionnelleException si le compte n'est pas supprimable
	 */
	public void supprimerCompte(Long compteId) 
			throws ServiceTechniqueException, ServiceFonctionnelleException {

		Assert.notNull(
				compteId, 
				ServiceProgrammationErreur
				.ID_NULL
				.getMessage(getTClass().getSimpleName()));

		T compte = controlerEtPreparerPourSuppression(compteId);

		supprimer(compte);
	}

	/**
	 * Enregistrer un compte de type "T extends Compte" en base
	 * 
	 * @param compte le compte à enregistrer en base
	 * @return le compte après enregistrement
	 * @throws ServiceTechniqueException si un problème survient avec la base
	 */
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

	/**
	 * Supprimer un compte de type "T extends Compte" en base
	 * 
	 * @param compte le compte à supprimer en base
	 * @throws ServiceTechniqueException si un problème survient avec la base
	 */
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

	/**
	 * Vérifications avant création d'un compte de type "T extends Compte"
	 * 
	 * @param compte le compte à vérifier dans le cadre d'une création
	 * @return le compte à créer éventuellement préparé pour l'insert
	 * @throws ServiceFonctionnelleException si le compte indiqué a un id ou n'a pas d'identifiant valide ou qu'un autre compte de même type a le même identifiant
	 * @throws ServiceTechniqueException si un problème survient avec la base
	 */
	protected T controlerEtPreparerPourCreation(T compte) 
			throws ServiceFonctionnelleException, ServiceTechniqueException {

		// Le compte à créer ne doit pas avoir d'id
		if ( compte.getId() != null ) {
			throw new ServiceFonctionnelleException (
					ServiceFonctionnelleErreur.COMPTE_ID_NON_NULL,
					getTClass().getSimpleName(),
					compte.getId());
		}

		// Validité de l'identifiant 
		verifierIdentifiantValideEtUnique(compte);
		
		return compte;
	}
	
	/**
	 * Vérifications avant modification d'un compte de type "T extends Compte". Le compte doit comporter un id.
	 * 
	 * @param compte le compte à vérifier dans le cadre d'une modification
	 * @return le compte à créer éventuellement préparé pour l'update
	 * @throws ServiceFonctionnelleException si le compte indiqué n'a pas d'id ou l'id n'existe pas en base, 
	 * ou si l'identifiant a été modifié et que ce nouvel identifiant est invalide ou est déjà utilisé par un autre compte de même type
	 * @throws ServiceTechniqueException si un problème survient avec la base
	 */
	protected T controlerEtPreparerPourModification(T compte) 
			throws ServiceFonctionnelleException, ServiceTechniqueException {

		// Vérification de l'id
		if ( compte.getId() == null ) {
			throw new ServiceFonctionnelleException (
					ServiceFonctionnelleErreur.COMPTE_SUPPRESSION_MODIFICATION_ID_NULL,
					getTClass().getSimpleName());
		}

		// Vérification de l'identifiant
		verifierIdentifiantValideEtUnique(compte);

		return compte;
	}

	/**
	 * Contrôles avant suppression d'un compte de  type "T extends Compte"
	 * 
	 * @param compteId l'id du compte à supprimer
	 * @throws ServiceTechniqueException si un problème survient avec la base
	 * @throws ServiceFonctionnelleException si l'id est null ou qu'aucun compte avec cet id n'existe en base
	 */
	protected T controlerEtPreparerPourSuppression(Long compteId) 
			throws ServiceTechniqueException, ServiceFonctionnelleException {

		// Le compte à supprimer doit exister en base (vérification sur l'id)
		if ( ! isExistantParId(compteId) ) {

			throw new ServiceFonctionnelleException (
					ServiceFonctionnelleErreur.COMPTE_NON_ENREGISTRE_PAR_ID,
					getTClass().getSimpleName(),
					compteId);
		}
		
		// Le compte à supprimer ne doit pas avoir d'opérations associéés
		verifierAbsenceOperationAssociee(compteId);
		
		return rechercherParId(compteId);
	}

	private void verifierIdentifiantValideEtUnique(
			Compte compte) 
					throws ServiceFonctionnelleException, ServiceTechniqueException {

		boolean isMemeIdentifiant;
		boolean isIdentifiantDejaUtilise;

		try {
			if ( compte.getId() == null ) {
				// En cours création
				isMemeIdentifiant = false;
				isIdentifiantDejaUtilise = isExistantParIdentifiant(compte.getIdentifiant());
			}
			else
			{
				// En cours modification 
				isMemeIdentifiant = getRepository().existsByIdentifiantAndId(compte.getIdentifiant(), compte.getId());
				isIdentifiantDejaUtilise = getRepository().existsByIdentifiantAndIdNot(compte.getIdentifiant(), compte.getId()); 
			}
		}
		catch (Throwable t) {
			throw new ServiceTechniqueException (
					t,
					ServiceTechniqueErreur.TECH_EXISTANCE_COMPTE_PAR_IDENTIFIANT,
					compte.getIdentifiant(),
					getTClass().getSimpleName());
		}
		
		if ( ! isMemeIdentifiant ) {
			// Identifiant valide
			if ( compte.getIdentifiant() == null
					|| compte.getIdentifiant().isBlank() ) {
				throw new ServiceFonctionnelleException (
						ServiceFonctionnelleErreur.COMPTE_IDENTIFIANT_INVALIDE,
						getTClass().getSimpleName());
			}

			// Identifiant unique pour le type de compte
			if ( isIdentifiantDejaUtilise ) {
				throw new ServiceFonctionnelleException (
						ServiceFonctionnelleErreur.COMPTE_IDENTIFIANT_DEJA_UTILISE,
						getTClass().getSimpleName(),
						compte.getIdentifiant());
			}
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

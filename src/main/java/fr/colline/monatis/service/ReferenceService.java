package fr.colline.monatis.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import fr.colline.monatis.exception.ServiceFonctionnelleErreur;
import fr.colline.monatis.exception.ServiceFonctionnelleException;
import fr.colline.monatis.exception.ServiceProgrammationErreur;
import fr.colline.monatis.exception.ServiceTechniqueErreur;
import fr.colline.monatis.exception.ServiceTechniqueException;
import fr.colline.monatis.model.Reference;
import fr.colline.monatis.repository.ReferenceRepository;

public abstract class ReferenceService<T extends Reference> {

	/**
	 * Recherche une référence de type "T extends Reference" par son id. 
	 * 
	 * @param referenceId l'id de la référence à rechercher
	 * @throws IllegalArgumentException si l'id indiqué est null
	 * @throws ServiceTechniqueException si un problème survient avec la base
	 * @return la référence correspondant à l'id indiqué, ou null si elle n'a pas été trouvée
	 */
	public T rechercherParId(Long referenceId) 
			throws ServiceTechniqueException {

		Assert.notNull(
				referenceId,
				ServiceProgrammationErreur
				.ID_NULL
				.getMessage(getTClass().getSimpleName()));

		try {
			Optional<T> optional = getRepository().findById(referenceId);
			return optional.isEmpty() ? null : optional.get();
		}
		catch (Throwable t) {
			throw new ServiceTechniqueException(
					t,
					ServiceTechniqueErreur.TECH_RECHERCHE_REFERENCE_PAR_ID,
					getTClass().getSimpleName(),
					referenceId );
		}
	}

	public boolean isExistantParId(Long referenceId) 
			throws ServiceTechniqueException {

		Assert.notNull(
				referenceId,
				ServiceProgrammationErreur.ID_NULL.getMessage(getTClass().getSimpleName()));

		try {
			return getRepository().existsById(referenceId);
		}
		catch (Throwable t) {
			throw new ServiceTechniqueException(
					t,
					ServiceTechniqueErreur.TECH_EXISTANCE_REFERENCE_PAR_ID,
					referenceId,
					getTClass().getSimpleName() );
		}
	}

	/**
	 * Recherche une référence de type "T extends Reference" par son nom
	 * 
	 * @param referenceNom le nom de la référence recherchée
	 * @throws IllegalArgumentException si le nom indiqué est null
	 * @throws ServiceTechniqueException si un problème survient avec la base
	 * @return la référence correspondant au nom indiqué, ou null si elle n'a pas été trouvée
	 */
	public T rechercherParNom(String referenceNom) 
			throws ServiceTechniqueException {

		Assert.hasText(
				referenceNom,
				ServiceProgrammationErreur.NOM_NULL.getMessage(getTClass().getSimpleName()));

		try {
			Optional<T> optional = getRepository().findByNom(referenceNom);
			return optional.isEmpty() ? null : optional.get();
		}
		catch (Throwable t) {
			throw new ServiceTechniqueException(
					t,
					ServiceTechniqueErreur.TECH_RECHERCHE_REFERENCE_PAR_NOM,
					getTClass().getSimpleName(),
					referenceNom );
		}
	}
	
	public boolean isExistantParNom(String referenceNom) throws ServiceTechniqueException {

		Assert.notNull(
				referenceNom,
				ServiceProgrammationErreur.NOM_NULL.getMessage(getTClass().getSimpleName()));

		try {
			return getRepository().existsByNom(referenceNom);
		}
		catch (Throwable t) {
			throw new ServiceTechniqueException(
					t,
					ServiceTechniqueErreur.TECH_EXISTANCE_REFERENCE_PAR_NOM,
					referenceNom,
					getTClass().getSimpleName() );
		}
	}

	/**
	 * Retourne la liste non triée des objets de 
	 * type "T extends Reference"
	 * 
	 * @throws ServiceTechniqueException si un problème survient avec la base
	 * @return la liste des objets
	 */
	public List<T> rechercherTous() 
			throws ServiceTechniqueException {

		try {
			return getRepository().findAll();
		}
		catch (Throwable t) {
			throw new ServiceTechniqueException (
					t,
					ServiceTechniqueErreur.TECH_RECHERCHE_REFERENCE_TOUS,
					getTClass().getSimpleName());
		}
	}

	/**
	 * Retourne la liste triée des objets d'un même type "T extends Reference"
	 * 
	 * @param tri
	 * @return la liste des objets triée
	 * @throws IllegalArgumentException si le critère de tri passé est à null
	 * @throws ServiceTechniqueException si un problème survient avec la base
	 */
	public List<T> rechercherTous(Sort tri) 
			throws ServiceTechniqueException {

		Assert.notNull(
				tri,
				ServiceProgrammationErreur.TRI_NULL.getMessage(getTClass().getSimpleName()));

		try {
			return getRepository().findAll(tri);
		}
		catch (Throwable t) {
			throw new ServiceTechniqueException (
					t,
					ServiceTechniqueErreur.TECH_RECHERCHE_REFERENCE_TOUS,
					getTClass().getSimpleName());
		}
	}

	/**
	 * Supprimer toutes les références de type "T extends Reference"
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
					ServiceTechniqueErreur.TECH_SUPPRESSION_REFERENCE_TOUS,
					getTClass().getSimpleName());
		}
	}

	/**
	 * Création d'une nouvelle référence de type "T extends Reference"
	 * 
	 * @param reference la référence à créer
	 * @return la référence créée
	 * @throws ServiceTechniqueException si un problème survient avec la base
	 * @throws ServiceFonctionnelleException si la référence ne peut pas être créée pour des raisons fonctionnelles
	 */
	public final T creerReference(T reference) 
			throws ServiceTechniqueException, ServiceFonctionnelleException {

		Assert.notNull(
				reference,
				ServiceProgrammationErreur.REFERENCE_NULL.getMessage(getTClass().getSimpleName()));

		reference = controlerEtPreparerPourCreation(reference);

		return enregistrer(reference);
	}

	public final T modifierReference(T reference) 
			throws ServiceTechniqueException, ServiceFonctionnelleException {
		
		Assert.notNull(
				reference,
				ServiceProgrammationErreur.REFERENCE_NULL.getMessage(getTClass().getSimpleName()));

		reference = controlerEtPreparerPourModification(reference);
		
		return enregistrer(reference);
	}

	public final void supprimerReference(Long referenceId) 
			throws ServiceTechniqueException, ServiceFonctionnelleException {

		Assert.notNull(
				referenceId,
				ServiceProgrammationErreur.ID_NULL.getMessage(getTClass().getSimpleName()));

		T reference = controlerEtPreparerPourSuppression(referenceId);

		supprimer(reference);
	}

	protected T enregistrer(T reference) 
			throws ServiceTechniqueException {

		Assert.notNull(
				reference, 
				ServiceProgrammationErreur.REFERENCE_NULL.getMessage(getTClass().getSimpleName()));

		try {
			return this.getRepository().save(reference);
		}
		catch (Throwable t) {
			throw new ServiceTechniqueException (
					t,
					ServiceTechniqueErreur.TECH_ENREGISTREMENT_REFERENCE,
					getTClass().getSimpleName(),
					reference.getNom());
		}
	}

	protected void supprimer(T reference) 
			throws ServiceTechniqueException {

		try {
			this.getRepository().delete(reference);
		}
		catch (Throwable t) {
			throw new ServiceTechniqueException (
					t,
					ServiceTechniqueErreur.TECH_SUPPRESSION_REFERENCE,
					getTClass().getSimpleName(),
					reference.getNom());
		}
	}

	protected T controlerEtPreparerPourCreation(T reference) 
			throws ServiceTechniqueException, ServiceFonctionnelleException {

		Assert.notNull(
				reference, 
				ServiceProgrammationErreur.REFERENCE_NULL.getMessage(getTClass().getSimpleName()));

		verifierReferenceNonEnregistree(reference.getId());
		verifierNomValideEtUnique(reference.getId(), reference.getNom());
		
		return reference;
	}

	protected T controlerEtPreparerPourModification(T reference) 
			throws ServiceFonctionnelleException, ServiceTechniqueException {

		Assert.notNull(
				reference, 
				ServiceProgrammationErreur.REFERENCE_NULL.getMessage(getTClass().getSimpleName()));

		verifierReferenceEnregistree(reference.getId());
		verifierNomValideEtUnique(reference.getId(), reference.getNom());
		
		return reference;
	}

	/**
	 * Contrôles avant suppression d'une référence de type "T extends Reference"
	 * 
	 * @param referenceId l'id de la référence à supprimer
	 * @return la référence à supprimer récupérée en base
	 * @throws ServiceTechniqueException si un problème survient avec la base
	 * @throws ServiceFonctionnelleException si la référence à supprimer n'a pas d'id ou que cet id n'est pas en base
	 */
	protected T controlerEtPreparerPourSuppression(Long referenceId) 
			throws ServiceFonctionnelleException, ServiceTechniqueException {

		Assert.notNull(
				referenceId, 
				ServiceProgrammationErreur.ID_NULL.getMessage(getTClass().getSimpleName()));

		verifierReferenceEnregistree(referenceId);

		return rechercherParId(referenceId);
	}


	private void verifierReferenceEnregistree(@Nullable Long referenceId) 
			throws ServiceFonctionnelleException, ServiceTechniqueException {
		
		if ( referenceId == null
				|| ! isExistantParId(referenceId) ) {
			throw new ServiceFonctionnelleException (
					ServiceFonctionnelleErreur.REFERENCE_NON_ENREGISTREE_PAR_ID,
					getTClass().getSimpleName(),
					referenceId);
		}
	}

	private void verifierReferenceNonEnregistree(@Nullable Long referenceId) 
			throws ServiceFonctionnelleException, ServiceTechniqueException {
	
		if ( referenceId != null 
				&& isExistantParId(referenceId) ) {
			throw new ServiceFonctionnelleException (
					ServiceFonctionnelleErreur.REFERENCE_DEJA_ENREGISTREE_PAR_ID,
					referenceId,
					getTClass().getSimpleName());
		}
	}
	
	private void verifierNomValideEtUnique(
			@Nullable Long referenceId, 
			@Nullable String referenceNom) 
					throws ServiceFonctionnelleException, ServiceTechniqueException {

		if ( referenceNom == null
				|| referenceNom.isBlank() ) {
			throw new ServiceFonctionnelleException (
					ServiceFonctionnelleErreur.REFERENCE_NOM_INVALIDE,
					getTClass().getSimpleName());
		}
		
		boolean isNomCreeOuModifie;
		boolean isNomDejaUtilise;
		
		if ( referenceId == null ) {
			// En cours création
			isNomCreeOuModifie = true;
			isNomDejaUtilise = isExistantParNom(referenceNom);
		}
		else {
			try {
				// En cours modification
				isNomCreeOuModifie = ! getRepository().existsByNomAndId(referenceNom, referenceId);
				isNomDejaUtilise = getRepository().existsByNomAndIdNot(referenceNom, referenceId);
			}
			catch ( Throwable t ) {
				throw new ServiceTechniqueException (
						t,
						ServiceTechniqueErreur.TECH_EXISTANCE_REFERENCE_PAR_NOM,
						referenceNom);
			}
		}

		if ( isNomCreeOuModifie 
				&& isNomDejaUtilise ) {
			throw new ServiceFonctionnelleException (
					ServiceFonctionnelleErreur.REFERENCE_NOM_DEJA_UTILISE,
					getTClass().getSimpleName(),
					referenceNom);
		}
	}
	
	protected abstract Class<T> getTClass();
	protected abstract ReferenceRepository<T> getRepository();
}

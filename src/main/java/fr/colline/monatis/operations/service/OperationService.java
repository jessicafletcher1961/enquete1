package fr.colline.monatis.operations.service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import fr.colline.monatis.comptes.model.Compte;
import fr.colline.monatis.comptes.service.CompteTousTypeService;
import fr.colline.monatis.exception.ServiceFonctionnelleErreur;
import fr.colline.monatis.exception.ServiceFonctionnelleException;
import fr.colline.monatis.exception.ServiceProgrammationErreur;
import fr.colline.monatis.exception.ServiceTechniqueErreur;
import fr.colline.monatis.exception.ServiceTechniqueException;
import fr.colline.monatis.operations.model.DetailOperation;
import fr.colline.monatis.operations.model.Operation;
import fr.colline.monatis.operations.repository.OperationRepository;
import jakarta.annotation.Nullable;

@Service
public class OperationService {

	@Autowired private OperationRepository operationrepository;

	@Autowired private DetailOperationService detailOperationService;
	@Autowired private CompteTousTypeService compteTousTypeService;

	public Operation rechercherParId(Long operationId) 
			throws ServiceTechniqueException {

		Assert.notNull(
				operationId, 
				ServiceProgrammationErreur
				.ID_NULL
				.getMessage(Operation.class.getSimpleName()));

		try {
			Optional<Operation> optional = operationrepository.findById(operationId);
			return optional.isEmpty() ? null : optional.get();
		}
		catch (Throwable t) {
			throw new ServiceTechniqueException(
					t,
					ServiceTechniqueErreur.TECH_RECHERCHE_OPERATION_PAR_ID,
					operationId );
		}
	}

	public boolean isExistantParId(Long operationId) 
			throws ServiceTechniqueException {

		Assert.notNull(
				operationId,
				ServiceProgrammationErreur.ID_NULL.getMessage(Operation.class.getSimpleName()));

		try {
			return operationrepository.existsById(operationId);
		}
		catch (Throwable t) {
			throw new ServiceTechniqueException(
					t,
					ServiceTechniqueErreur.TECH_EXISTANCE_OPERATION_PAR_ID,
					operationId);
		}
	}

	public Operation rechercherParNumero(String operationNumero) 
			throws ServiceTechniqueException {

		Assert.notNull(
				operationNumero,
				ServiceProgrammationErreur.NUMERO_NULL.getMessage());

		try {
			Optional<Operation> optional = operationrepository.findByNumero(operationNumero);
			return optional.isEmpty() ? null : optional.get();
		}
		catch (Throwable t) {
			throw new ServiceTechniqueException(
					t,
					ServiceTechniqueErreur.TECH_RECHERCHE_OPERATION_PAR_NUMERO,
					operationNumero);
		}
	}

	public boolean isExistantParNumero(String operationNumero) throws ServiceTechniqueException {

		Assert.notNull(
				operationNumero,
				ServiceProgrammationErreur.NUMERO_NULL.getMessage());

		try {
			return operationrepository.existsByNumero(operationNumero);
		}
		catch (Throwable t) {
			throw new ServiceTechniqueException(
					t,
					ServiceTechniqueErreur.TECH_EXISTANCE_OPERATION_PAR_NUMERO,
					operationNumero);
		}
	}

	public List<Operation> rechercherTous() 
			throws ServiceTechniqueException {

		try {
			return operationrepository.findAll();
		}
		catch (Throwable t) {
			throw new ServiceTechniqueException (
					t,
					ServiceTechniqueErreur.TECH_RECHERCHE_OPERATION_TOUS);
		}
	}

	public List<Operation> rechercherTous(Sort tri) 
			throws ServiceTechniqueException {

		Assert.notNull(
				tri,
				ServiceProgrammationErreur
				.TRI_NULL
				.getMessage(Operation.class.getSimpleName()));

		try {
			return operationrepository.findAll(tri);
		}
		catch (Throwable t) {
			throw new ServiceTechniqueException (
					t,
					ServiceTechniqueErreur.TECH_RECHERCHE_OPERATION_TOUS);
		}
	}

	public void supprimerTous() 
			throws ServiceTechniqueException {

		try {
			operationrepository.deleteAll();
		}
		catch (Throwable t) {
			throw new ServiceTechniqueException (
					t,
					ServiceTechniqueErreur.TECH_SUPPRESSION_OPERATION_TOUS);
		}
	}

	public Operation creerOperation(
			Operation operation) 
					throws ServiceFonctionnelleException, ServiceTechniqueException {

		Assert.notNull(
				operation, 
				ServiceProgrammationErreur.OPERATION_NULL.getMessage());

		operation = controlerEtPreparerPourCreation(operation);

		return enregistrer(operation);
	}

	public Operation modifierOperation(Operation operation) 
			throws ServiceTechniqueException, ServiceFonctionnelleException {

		Assert.notNull(
				operation, 
				ServiceProgrammationErreur.OPERATION_NULL.getMessage());

		operation = controlerEtPreparerPourModification(operation);

		return enregistrer(operation);
	}

	public void supprimerOperation(Long operationId) 
			throws ServiceFonctionnelleException, ServiceTechniqueException {

		Assert.notNull(
				operationId, 
				ServiceProgrammationErreur.ID_NULL.getMessage(Operation.class.getSimpleName()));

		Operation operation = controlerEtPreparerPourSuppression(operationId);

		supprimer(operation);
	}

	public List<Operation> rechercherOperationDepenseParCompteIdEntreDateDebutEtDateFin(
			Long compteId,
			Timestamp dateDebut, 
			Timestamp dateFin) {
		
		return operationrepository.findByCompteDepenseIdAndDateValeurBetween(
				compteId, dateDebut, dateFin);
	}

	public List<Operation> rechercherOperationRecetteParCompteIdEntreDateDebutEtDateFin(
			Long compteId,
			Timestamp dateDebut, 
			Timestamp dateFin) {
		
		return operationrepository.findByCompteRecetteIdAndDateValeurBetween(
				compteId, dateDebut, dateFin);
	}

	private Operation enregistrer(Operation operation) 
			throws ServiceTechniqueException {

		Assert.notNull(
				operation, 
				ServiceProgrammationErreur.OPERATION_NULL.getMessage());

		try {
			return operationrepository.save(operation);
		}
		catch (Throwable t) {
			throw new ServiceTechniqueException (
					t,
					ServiceTechniqueErreur.TECH_ENREGISTREMENT_OPERATION,
					operation.getNumero());
		}
	}

	private void supprimer(Operation operation) 
			throws ServiceTechniqueException {

		Assert.notNull(
				operation, 
				ServiceProgrammationErreur.OPERATION_NULL.getMessage());

		try {
			operationrepository.deleteById(operation.getId());
		}
		catch (Throwable t) {
			throw new ServiceTechniqueException (
					t,
					ServiceTechniqueErreur.TECH_SUPPRESSION_OPERATION,
					operation.getNumero());
		}
	}

	private Operation controlerEtPreparerPourCreation(
			Operation operation)
					throws ServiceFonctionnelleException, ServiceTechniqueException {

		Assert.notNull(
				operation, 
				ServiceProgrammationErreur.OPERATION_NULL.getMessage());

		verifierOperationNonEnregistree(operation.getId());
		verifierNumeroValideEtUnique(operation.getId(), operation.getNumero());
		verifierCompteDepenseEnregistre(operation.getCompteDepense());
		verifierCompteRecetteEnregistre(operation.getCompteRecette());
		verifierListeDetailOperation(operation);

		return operation;
	}

	private Operation controlerEtPreparerPourModification(Operation operation) 
			throws ServiceFonctionnelleException, ServiceTechniqueException {

		Assert.notNull(
				operation, 
				ServiceProgrammationErreur.OPERATION_NULL.getMessage());

		verifierOperationEnregistree(operation.getId());
		verifierNumeroValideEtUnique(operation.getId(), operation.getNumero());
		verifierCompteDepenseEnregistre(operation.getCompteDepense());
		verifierCompteRecetteEnregistre(operation.getCompteRecette());
		verifierListeDetailOperation(operation);

		return operation;
	}

	private Operation controlerEtPreparerPourSuppression(Long operationId) 
			throws ServiceFonctionnelleException, ServiceTechniqueException  {

		Assert.notNull(
				operationId, 
				ServiceProgrammationErreur.ID_NULL.getMessage(Operation.class.getSimpleName()));

		verifierOperationEnregistree(operationId);

		return rechercherParId(operationId);
	}

	private void verifierOperationEnregistree(@Nullable Long operationId) 
			throws ServiceFonctionnelleException, ServiceTechniqueException {

		if ( operationId == null
				|| ! isExistantParId(operationId) ) {
			throw new ServiceFonctionnelleException (
					ServiceFonctionnelleErreur.OPERATION_NON_ENREGISTREE_PAR_ID,
					operationId );
		}
	}

	private void verifierOperationNonEnregistree(@Nullable Long operationId) 
			throws ServiceFonctionnelleException, ServiceTechniqueException {

		if ( operationId != null 
				&& isExistantParId(operationId) ) {
			throw new ServiceFonctionnelleException (
					ServiceFonctionnelleErreur.OPERATION_DEJA_ENREGISTREE_PAR_ID,
					operationId);
		}
	}

	private void verifierNumeroValideEtUnique(
			@Nullable Long operationId,
			@Nullable String operationNumero) 
					throws ServiceFonctionnelleException, ServiceTechniqueException {

		if ( operationNumero == null || operationNumero.isBlank() ) {
			throw new ServiceFonctionnelleException (
					ServiceFonctionnelleErreur.OPERATION_NUMERO_INVALIDE);
		}

		boolean isNumeroCreeOuModifie;
		boolean isNumeroDejaUtilise;

		if ( operationId == null ) {
			// En cours création
			isNumeroCreeOuModifie = true;
			isNumeroDejaUtilise = isExistantParNumero(operationNumero);
		}
		else {
			// En cours modification
			try {
				isNumeroCreeOuModifie = ! operationrepository.existsByNumeroAndId(operationNumero, operationId);
				isNumeroDejaUtilise = operationrepository.existsByNumeroAndIdNot(operationNumero, operationId);
			}
			catch ( Throwable t ) {
				throw new ServiceTechniqueException (
						t,
						ServiceTechniqueErreur.TECH_EXISTANCE_OPERATION_PAR_NUMERO,
						operationNumero);
			}
		}

		if ( isNumeroCreeOuModifie && isNumeroDejaUtilise ) {
			throw new ServiceFonctionnelleException (
					ServiceFonctionnelleErreur.OPERATION_NUMERO_DEJA_UTILISE,
					operationNumero);
		}
	}

	private void verifierCompteDepenseEnregistre(@Nullable Compte compteDepense) 
			throws ServiceTechniqueException, ServiceFonctionnelleException {

		if ( compteDepense == null ) {
			throw new ServiceFonctionnelleException(
					ServiceFonctionnelleErreur.OPERATION_SANS_COMPTE_DEPENSE);
		}
		
		if ( compteDepense.getId() == null 
				|| ! compteTousTypeService.isExistantParId(compteDepense.getId())) {
			throw new ServiceFonctionnelleException(
					ServiceFonctionnelleErreur.COMPTE_NON_ENREGISTRE_PAR_ID,
					compteDepense.getClass().getSimpleName(),
					compteDepense.getId());
		}
	}

	private void verifierCompteRecetteEnregistre(@Nullable Compte compteRecette) 
			throws ServiceTechniqueException, ServiceFonctionnelleException {

		if ( compteRecette == null ) {
			throw new ServiceFonctionnelleException(
					ServiceFonctionnelleErreur.OPERATION_SANS_COMPTE_RECETTE);
		}
		
		if ( compteRecette.getId() == null 
				|| ! compteTousTypeService.isExistantParId(compteRecette.getId())) {
			throw new ServiceFonctionnelleException(
					ServiceFonctionnelleErreur.COMPTE_NON_ENREGISTRE_PAR_ID,
					compteRecette.getClass().getSimpleName(),
					compteRecette.getId());
		}
	}

	private void verifierListeDetailOperation(Operation operation) 
			throws ServiceFonctionnelleException, ServiceTechniqueException {

		Assert.notNull(operation, ServiceProgrammationErreur.OPERATION_NULL.getMessage());

		Set<DetailOperation> detailsOperation = operation.getDetailsOperation();

		if ( detailsOperation == null 
				|| detailsOperation.isEmpty() ) {
			throw new ServiceFonctionnelleException(
					ServiceFonctionnelleErreur.OPERATION_AU_MOINS_UN_DETAIL_OPERATION_REQUIS);
		}

		Set<Integer> sequences = new HashSet<>();
		Long montantTotalEnCentimes = 0L;
		for ( DetailOperation detailOperation : detailsOperation ) {
			detailOperationService.verifierDetailOperation(detailOperation);
			if ( sequences.contains(detailOperation.getSequence()) ) {
				throw new ServiceFonctionnelleException(
						ServiceFonctionnelleErreur.OPERATION_LISTE_DETAIL_NUMERO_SEQUENCE_DUPLIQUEE,
						detailOperation.getSequence());
			}
			sequences.add(detailOperation.getSequence());
			montantTotalEnCentimes += detailOperation.getMontantDetailEnCentimes();
		}
		if ( ! montantTotalEnCentimes.equals(operation.getMontantTotalEnCentimes()) ) {
			throw new ServiceFonctionnelleException(
					ServiceFonctionnelleErreur.OPERATION_LISTE_DETAIL_SOMME_MONTANTS_ERRONEE,
					operation.getMontantTotalEnCentimes(), 
					montantTotalEnCentimes);
		}
	}
}

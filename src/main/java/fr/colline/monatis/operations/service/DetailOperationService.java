package fr.colline.monatis.operations.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import fr.colline.monatis.exception.ServiceFonctionnelleErreur;
import fr.colline.monatis.exception.ServiceFonctionnelleException;
import fr.colline.monatis.exception.ServiceProgrammationErreur;
import fr.colline.monatis.exception.ServiceTechniqueErreur;
import fr.colline.monatis.exception.ServiceTechniqueException;
import fr.colline.monatis.operations.model.DetailOperation;
import fr.colline.monatis.operations.repository.DetailOperationRepository;
import fr.colline.monatis.references.model.Beneficiaire;
import fr.colline.monatis.references.model.SousCategorie;
import fr.colline.monatis.references.service.BeneficiaireService;
import fr.colline.monatis.references.service.SousCategorieService;

@Service
public class DetailOperationService {

	@Autowired private DetailOperationRepository repository;

	@Autowired private SousCategorieService sousCategorieService;
	@Autowired private BeneficiaireService beneficiaireService;
	
	public DetailOperation rechercherParOperationIdEtSequence(
			Long operationId, 
			int sequence) 
					throws ServiceTechniqueException {

		Assert.notNull(
				operationId, 
				ServiceProgrammationErreur
				.ID_NULL
				.getMessage(DetailOperation.class.getSimpleName()));

		try {
			Optional<DetailOperation> optional = repository.findByOperationIdAndSequence(operationId, sequence);
			return optional.isEmpty() ? null : optional.get();
		}
		catch (Throwable t) {
			throw new ServiceTechniqueException(
					t,
					ServiceTechniqueErreur.TECH_DETAIL_OPERATION_RECHERCHE_PAR_OPERATION_ID_ET_SEQUENCE,
					sequence,
					operationId);
		}
	}

	protected boolean isExistantParOperationIdEtSequence(
			Long operationId,
			int sequence) 
			throws ServiceTechniqueException {

		Assert.notNull(
				operationId,
				ServiceProgrammationErreur
				.ID_NULL
				.getMessage(DetailOperation.class.getSimpleName()));
		try {
			return repository.existsByOperationIdAndSequence(
					operationId,
					sequence);
		}
		catch (Throwable t) {
			throw new ServiceTechniqueException(
					t,
					ServiceTechniqueErreur.TECH_DETAIL_OPERATION_EXISTANCE_PAR_OPERATION_ID_ET_SEQUENCE,
					operationId,
					sequence);
		}
	}
	
	protected Set<DetailOperation> rechercherTousParOperationId(
			Long operationId) throws ServiceTechniqueException {

		Assert.notNull(
				operationId,
				ServiceProgrammationErreur
				.ID_NULL
				.getMessage(DetailOperation.class.getSimpleName()));
		
		try {
			return repository.findByOperationId(operationId);
		}
		catch ( Throwable t) {
			throw new ServiceTechniqueException (
					t,
					ServiceTechniqueErreur.TECH_DETAIL_OPERATION_RECHERCHE_TOUS,
					operationId);
		}
	}

	protected Set<DetailOperation> rechercherTousParOperationId(
			Long operationId,
			Sort tri) throws ServiceTechniqueException {

		Assert.notNull(
				operationId,
				ServiceProgrammationErreur
				.ID_NULL
				.getMessage(DetailOperation.class.getSimpleName()));
		Assert.notNull(
				tri,
				ServiceProgrammationErreur
				.TRI_NULL
				.getMessage(DetailOperation.class.getSimpleName()));
		
		try {
			return repository.findByOperationId(operationId, tri);
		}
		catch (Throwable t) {
			throw new ServiceTechniqueException (
					t,
					ServiceTechniqueErreur.TECH_DETAIL_OPERATION_RECHERCHE_TOUS,
					DetailOperation.class.getSimpleName());
		}
	}

	public void supprimerTous() throws ServiceTechniqueException {

		try {
			repository.deleteAll();
		}
		catch (Throwable t) {
			throw new ServiceTechniqueException (
					t,
					ServiceTechniqueErreur.TECH_DETAIL_OPERATION_SUPPRESSION_TOUS,
					"[toutes]");
		}
	}
	
	protected void supprimerTousParOperationId(Long operationId) throws ServiceTechniqueException {
		
		Assert.notNull(
				operationId,
				ServiceProgrammationErreur
				.ID_NULL
				.getMessage(DetailOperation.class.getSimpleName()));
		
		try {
			repository.deleteByOperationId(operationId);
		}
		catch (Throwable t) {
			throw new ServiceTechniqueException (
					t,
					ServiceTechniqueErreur.TECH_DETAIL_OPERATION_SUPPRESSION_TOUS,
					operationId);
		}
	}

	protected void verifierDetailOperation(DetailOperation detailOperation) 
			throws ServiceFonctionnelleException, ServiceTechniqueException {

		if ( detailOperation.getDateComptabilisation() == null ) {
			detailOperation.setDateComptabilisation(Timestamp.from(Instant.now()));
		}
		verifierSousCategorieEnregistree(detailOperation.getSousCategorie());
		verifierBeneficiairesEnregistres(detailOperation.getBeneficiaires());
	}

	public void retirerDetailOperation(DetailOperation detailOperation) 
			throws ServiceTechniqueException {
		
		this.supprimer(detailOperation.getId());
	}
	
//	private DetailOperation enregistrer(DetailOperation detailOperation) 
//			throws ServiceTechniqueException {
//
//		try {
//			return repository.save(detailOperation);
//		}
//		catch (Throwable t) {
//			throw new ServiceTechniqueException (
//					t,
//					ServiceTechniqueErreur.TECH_DETAIL_OPERATION_ENREGISTREMENT,
//					detailOperation.getSequence(),
//					detailOperation.getOperation().getNumero());
//		}
//	}
//
	private void supprimer(Long detailOperationId) 
			throws ServiceTechniqueException {

		Assert.notNull(
				detailOperationId, 
				ServiceProgrammationErreur
				.ID_NULL
				.getMessage(DetailOperation.class.getSimpleName()));

		try {
			repository.deleteById(detailOperationId);
		}
		catch (Throwable t) {
			throw new ServiceTechniqueException (
					t,
					ServiceTechniqueErreur.TECH_DETAIL_OPERATION_SUPPRESSION,
					detailOperationId);
		}
	}

	private void verifierSousCategorieEnregistree(SousCategorie sousCategorie) 
			throws ServiceFonctionnelleException, ServiceTechniqueException {

		Assert.notNull(sousCategorie, ServiceProgrammationErreur.SOUS_CATEGORIE_NULL.getMessage());

		if ( sousCategorie.getId() == null 
				|| ! sousCategorieService.isExistantParId(sousCategorie.getId())) {
			throw new ServiceFonctionnelleException (
					ServiceFonctionnelleErreur.DETAIL_OPERATION_SOUS_CATEGORIE_REQUISE,
					sousCategorie.getId());
		}
	}

	private void verifierBeneficiairesEnregistres(Set<Beneficiaire> set) 
			throws ServiceTechniqueException, ServiceFonctionnelleException {

		for ( Beneficiaire beneficiaire : set ) {

			if ( beneficiaire.getId() == null 
					|| ! beneficiaireService.isExistantParId(beneficiaire.getId())) {

				throw new ServiceFonctionnelleException(
						ServiceFonctionnelleErreur
						.BENEFICIAIRE_NON_ENREGISTRE_PAR_ID,
						beneficiaire.getId() == null ? "null" : beneficiaire.getId());
			}
		}
	}
}

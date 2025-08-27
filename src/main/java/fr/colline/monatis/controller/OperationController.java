package fr.colline.monatis.controller;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.colline.monatis.controller.dto.DetailOperationRequestDto;
import fr.colline.monatis.controller.dto.OperationCreationRequestDto;
import fr.colline.monatis.controller.dto.OperationModificationRequestDto;
import fr.colline.monatis.controller.dto.OperationResponseDto;
import fr.colline.monatis.controller.dto.mapper.OperationDtoMapper;
import fr.colline.monatis.exception.ControllerErreur;
import fr.colline.monatis.exception.ControllerException;
import fr.colline.monatis.exception.ServiceFonctionnelleException;
import fr.colline.monatis.exception.ServiceTechniqueException;
import fr.colline.monatis.model.Beneficiaire;
import fr.colline.monatis.model.Compte;
import fr.colline.monatis.model.DetailOperation;
import fr.colline.monatis.model.Operation;
import fr.colline.monatis.model.SousCategorie;
import fr.colline.monatis.service.BeneficiaireService;
import fr.colline.monatis.service.CompteTousTypeService;
import fr.colline.monatis.service.DetailOperationService;
import fr.colline.monatis.service.OperationService;
import fr.colline.monatis.service.SousCategorieService;

@RestController
@RequestMapping("/monatis/operations")
public class OperationController {

	@Autowired private OperationService operationService;
	@Autowired private DetailOperationService detailOperationService;

	@Autowired private CompteTousTypeService compteTousTypeService;
	@Autowired private SousCategorieService sousCategorieService;
	@Autowired private BeneficiaireService beneficiaireService;

	private static Random generateurNumeroOperation = new Random(Date.from(Instant.now()).getTime());

	@GetMapping("/all")
	public List<OperationResponseDto> getAllOperation() 
			throws ControllerException {

		try {
			List<OperationResponseDto> resultat = new ArrayList<>();
			Sort tri = Sort.by("numero");
			List<Operation> liste;
			liste = operationService.rechercherTous(tri);
			for ( Operation operation : liste ) {
				resultat.add(OperationDtoMapper.detailedModelToResponseDto(operation));
			}
			return resultat;
		} 
		catch (ServiceTechniqueException e) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		}
	}

	@GetMapping("/get/{numero}")
	public OperationResponseDto getOperationParNumero(
			@PathVariable(name = "numero") String numero) 
					throws ControllerException {

		Assert.hasText(numero, "Le numéro de l'opération est obligatoire");

		try {
			Operation operation = operationService.rechercherParNumero(numero);
			if ( operation == null ) {
				throw new ControllerException(
						ControllerErreur.OPERATION_NON_TROUVEE_PAR_NUMERO,
						numero);
			}
			return OperationDtoMapper.detailedModelToResponseDto(operation);
		} 
		catch (ServiceTechniqueException e) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		}
	}

	@PostMapping("/new")
	public OperationResponseDto creerOperation(
			@RequestBody OperationCreationRequestDto dto) 
					throws ControllerException {

		try {
			Operation operation = new Operation();
			operation = creationRequestDtoToModel(operation, dto);
			operation = operationService.creerOperation(operation);
			return OperationDtoMapper.detailedModelToResponseDto(operation);
		} 
		catch (ServiceFonctionnelleException e) {
			throw new ControllerException(
					e,
					ControllerErreur.OPERATION_CREATION_PROBLEME,
					dto.numero);
		} 
		catch (ServiceTechniqueException e) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		}
	}

	@PutMapping("/mod/{numero}")
	public OperationResponseDto modifierOperation(
			@PathVariable(name = "numero") String numeroInitial,
			@RequestBody OperationModificationRequestDto dto) 
					throws ControllerException {

		Assert.hasText(numeroInitial, "Le numéro de l'opération est obligatoire");

		try {
			Operation operation = operationService.rechercherParNumero(numeroInitial);
			if ( operation == null ) {
				throw new ControllerException(
						ControllerErreur.OPERATION_NON_TROUVEE_PAR_NUMERO,
						numeroInitial);
			}
			operation = modificationRequestDtoToModel(operation, dto);
			operation = operationService.modifierOperation(operation);
			return OperationDtoMapper.detailedModelToResponseDto(operation);
		} 
		catch (ServiceTechniqueException e) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		} 
		catch (ServiceFonctionnelleException e) {
			throw new ControllerException(
					e,
					ControllerErreur.OPERATION_MODIFICATION_PROBLEME,
					numeroInitial);
		}
	}

	@DeleteMapping("/del/{numero}")
	public void supprimerOperation(
			@PathVariable(name = "numero") String numeroInitial) 
					throws ControllerException {

		Assert.hasText(numeroInitial, "Le numéro de l'opération est obligatoire");

		try {
			Operation operation = operationService.rechercherParNumero(numeroInitial);
			if ( operation == null ) {
				throw new ControllerException(
						ControllerErreur.OPERATION_NON_TROUVEE_PAR_NUMERO,
						numeroInitial);
			}
			operationService.supprimerOperation(operation.getId());
		} 
		catch (ServiceTechniqueException e) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		} 
		catch (ServiceFonctionnelleException e) {
			throw new ControllerException(
					e,
					ControllerErreur.OPERATION_SUPPRESSION_PROBLEME,
					numeroInitial);
		}
	}

	private Operation creationRequestDtoToModel(
			Operation operation, 
			OperationCreationRequestDto dto) 
					throws ControllerException {

		// Préparation nouvelle opération
		//
		operation.setNumero(verifierNumero(dto.numero));
		operation.setDateValeur(verifierDate(dto.dateValeur));
		operation.setLibelle(verifierLibelle(dto.libelle));
		operation.setMontantTotalEnCentimes(verifierMontantEnCentimes(dto.montantTotalEnCentimes));
		operation.setCompteDepense(verifierCompteDepenseEnregistre(dto.identifiantCompteDepense));
		operation.setCompteRecette(verifierCompteRecetteEnregistre(dto.identifiantCompteRecette));

		// Préparation de la première ligne de détail
		//
		DetailOperation detailOperation = new DetailOperation();

		detailOperation.setOperation(operation);
		detailOperation.setMontantDetailEnCentimes(verifierMontantEnCentimes(dto.montantTotalEnCentimes));
		detailOperation.setSousCategorie(verifierSousCategorieEnregistree(dto.nomSousCategorie));
		detailOperation.setBeneficiaires(verifierBeneficiairesEnregistres(dto.nomsBeneficiaires));
		detailOperation.setSequence(0);
		detailOperation.setDateComptabilisation(operation.getDateValeur());
		detailOperation.setLibelle(operation.getLibelle());

		operation.getDetailsOperation().add(detailOperation);

		return operation;
	}

	private Operation modificationRequestDtoToModel(
			Operation operation, 
			OperationModificationRequestDto dto) 
					throws ControllerException {

		// Modification de l'opération
		//
		if ( dto.numero != null ) operation.setNumero(verifierNumero(dto.numero));
		if ( dto.dateValeur != null ) operation.setDateValeur(verifierDate(dto.dateValeur));
		if ( dto.libelle != null ) operation.setLibelle(verifierLibelle(dto.libelle));
		if ( dto.montantTotalEnCentimes != null ) operation.setMontantTotalEnCentimes(verifierMontantEnCentimes(dto.montantTotalEnCentimes));
		if ( dto.identifiantCompteDepense != null ) operation.setCompteDepense(verifierCompteDepenseEnregistre(dto.identifiantCompteDepense));
		if ( dto.identifiantCompteRecette != null ) operation.setCompteRecette(verifierCompteRecetteEnregistre(dto.identifiantCompteRecette));

		if ( dto.detailsOperation != null ) operation.setDetailsOperation(modificationRequestDtoToModel(operation, dto.detailsOperation));

		return operation;
	}

	private Set<DetailOperation> modificationRequestDtoToModel(
			Operation operation, 
			List<DetailOperationRequestDto> listeDto) 
					throws ControllerException {

		try {
			Set<DetailOperation> nouvelleListe = new HashSet<>();

			int dernierNumeroSequence = rechercherDerniereSequence(operation.getDetailsOperation());

			for ( DetailOperationRequestDto dto : listeDto ) {
				DetailOperation detailOperation;
				if ( dto.sequence == null ) {
					// Nouveau détail
					detailOperation = new DetailOperation();
					detailOperation.setSequence(++dernierNumeroSequence);
					detailOperation.setOperation(operation);
				}
				else {
					// Détail existant
					detailOperation = detailOperationService.rechercherParOperationIdEtSequence(operation.getId(), dto.sequence);
					if ( detailOperation == null ) {
						throw new ControllerException(
								ControllerErreur.DETAIL_OPERATION_NON_TROUVE_PAR_SEQUENCE,
								operation.getNumero(),
								dto.sequence);
					}
				}

				if ( dto.dateComptabilisation != null ) detailOperation.setDateComptabilisation(verifierDate(dto.dateComptabilisation));
				if ( dto.libelle != null ) detailOperation.setLibelle(verifierLibelle(dto.libelle));
				if ( dto.montantDetailEnCentimes != null ) detailOperation.setMontantDetailEnCentimes(verifierMontantEnCentimes(dto.montantDetailEnCentimes));
				if ( dto.nomSousCategorie != null ) detailOperation.setSousCategorie(verifierSousCategorieEnregistree(dto.nomSousCategorie));
				if ( dto.nomsBeneficiaires != null ) detailOperation.setBeneficiaires(verifierBeneficiairesEnregistres(dto.nomsBeneficiaires));

				nouvelleListe.add(detailOperation);
			}

			return nouvelleListe;
		} 
		catch (ServiceTechniqueException e) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		}
	}

	private String verifierNumero(String numero) {

		if ( numero == null 
				|| numero.isBlank() ) {
			return String.format("RDM-%08d", generateurNumeroOperation.nextInt(1, 100000000));
		}

		return numero;
	}

	private Timestamp verifierDate(Timestamp date) {

		if( date == null ) {
			return Timestamp.from(Instant.now());
		}

		return date;
	}

	private String verifierLibelle(String libelle) {

		return libelle;
	}
	
	private Compte verifierCompteDepenseEnregistre(String identifiantCompteDepense) 
			throws ControllerException {

		if ( identifiantCompteDepense == null 
				|| identifiantCompteDepense.isBlank() ) {
			throw new ControllerException(
					ControllerErreur.OPERATION_COMPTE_DEPENSE_OBLIGATOIRE);
		}
		try {
			Compte compte = compteTousTypeService.rechercherParIdentifiant(identifiantCompteDepense);
			if ( compte == null ) {
				throw new ControllerException(
						ControllerErreur.COMPTE_NON_TROUVE_PAR_IDENTIFIANT, 
						identifiantCompteDepense);
			}
			return compte;
		} 
		catch (ServiceTechniqueException e) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		}
	}

	private Compte verifierCompteRecetteEnregistre(String identifiantCompteRecette) 
			throws ControllerException {

		if ( identifiantCompteRecette == null 
				|| identifiantCompteRecette.isBlank() ) {
			throw new ControllerException(
					ControllerErreur.OPERATION_COMPTE_RECETTE_OBLIGATOIRE);
		}
		try {
			Compte compte = compteTousTypeService.rechercherParIdentifiant(identifiantCompteRecette);
			if ( compte == null ) {
				throw new ControllerException(
						ControllerErreur.COMPTE_NON_TROUVE_PAR_IDENTIFIANT, 
						identifiantCompteRecette);
			}
			return compte;
		} 
		catch (ServiceTechniqueException e) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		}
	}

	private Long verifierMontantEnCentimes(Long montantEnCentimes) throws ControllerException {

		if ( montantEnCentimes == null 
				|| montantEnCentimes.intValue() == 0 ) {
			throw new ControllerException(
					ControllerErreur.OPERATION_MONTANT_INVALIDE);
		}

		return montantEnCentimes;
	}

	private SousCategorie verifierSousCategorieEnregistree(String nomSousCategorie) 
			throws ControllerException {

		if ( nomSousCategorie == null 
				|| nomSousCategorie.isBlank() ) {
			throw new ControllerException(
					ControllerErreur.OPERATION_SOUS_CATEGORIE_OBLIGATOIRE);
		}
		try {
			SousCategorie sousCategorie = sousCategorieService.rechercherParNom(nomSousCategorie);
			if ( sousCategorie == null ) {
				throw new ControllerException(
						ControllerErreur.SOUS_CATEGORIE_NON_TROUVEE_PAR_NOM, 
						nomSousCategorie);
			}
			return sousCategorie;
		} 
		catch (ServiceTechniqueException e) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		}
	}

	private Set<Beneficiaire> verifierBeneficiairesEnregistres(List<String> nomsBeneficiaires) 
			throws ControllerException {

		Set<Beneficiaire> beneficiaires = new HashSet<>();
		if ( nomsBeneficiaires != null 
				&& nomsBeneficiaires.size() > 0 ) {
			try {
				for ( String nomBeneficiaire : nomsBeneficiaires ) {
					Beneficiaire beneficiaire = beneficiaireService.rechercherParNom(nomBeneficiaire);
					if ( beneficiaire == null ) {
						throw new ControllerException(
								ControllerErreur.BENEFICIAIRE_NON_TROUVE_PAR_NOM, 
								nomBeneficiaire);
					}
					beneficiaires.add(beneficiaire);
				}
			} 
			catch (ServiceTechniqueException e) {
				throw new ControllerException(
						e,
						ControllerErreur.ERREUR_TECHNIQUE);
			}
		}

		return beneficiaires;
	}

	private int rechercherDerniereSequence(Set<DetailOperation> detailsOperation) {

		int derniereSequence = 0;
		for ( DetailOperation detailOperation : detailsOperation ) {
			derniereSequence = Math.max(derniereSequence, detailOperation.getSequence());
		}
		return derniereSequence;
	}
}

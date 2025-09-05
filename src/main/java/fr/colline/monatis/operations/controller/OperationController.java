package fr.colline.monatis.operations.controller;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.colline.monatis.comptes.TypeFonctionnementCompte;
import fr.colline.monatis.comptes.model.Compte;
import fr.colline.monatis.comptes.model.CompteInterne;
import fr.colline.monatis.comptes.model.CompteTiers;
import fr.colline.monatis.comptes.service.CompteTousTypeService;
import fr.colline.monatis.exception.ControllerErreur;
import fr.colline.monatis.exception.ControllerException;
import fr.colline.monatis.exception.ServiceFonctionnelleException;
import fr.colline.monatis.exception.ServiceTechniqueException;
import fr.colline.monatis.operations.TypeOperation;
import fr.colline.monatis.operations.controller.dto.detailoperation.DetailOperationRequestDto;
import fr.colline.monatis.operations.controller.dto.operation.OperationBasicResponseDto;
import fr.colline.monatis.operations.controller.dto.operation.OperationCreationRequestDto;
import fr.colline.monatis.operations.controller.dto.operation.OperationModificationRequestDto;
import fr.colline.monatis.operations.controller.dto.operation.OperationDetailedResponseDto;
import fr.colline.monatis.operations.controller.mapper.OperationDtoMapper;
import fr.colline.monatis.operations.model.DetailOperation;
import fr.colline.monatis.operations.model.Operation;
import fr.colline.monatis.operations.service.DetailOperationService;
import fr.colline.monatis.operations.service.OperationService;
import fr.colline.monatis.references.model.Beneficiaire;
import fr.colline.monatis.references.model.SousCategorie;
import fr.colline.monatis.references.service.BeneficiaireService;
import fr.colline.monatis.references.service.SousCategorieService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/monatis/operations")
@Transactional
public class OperationController {

	@Autowired private OperationService operationService;
	@Autowired private DetailOperationService detailOperationService;

	@Autowired private CompteTousTypeService compteTousTypeService;
	@Autowired private SousCategorieService sousCategorieService;
	@Autowired private BeneficiaireService beneficiaireService;

	@GetMapping("/all")
	public List<OperationBasicResponseDto> getAllOperation() 
			throws ControllerException {

		try {
			List<OperationBasicResponseDto> resultat = new ArrayList<>();
			Sort tri = Sort.by("numero");
			List<Operation> liste;
			liste = operationService.rechercherTous(tri);
			for ( Operation operation : liste ) {
				resultat.add(OperationDtoMapper.modelToBasicResponseDto(operation));
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
	public OperationDetailedResponseDto getOperationParNumero(@PathVariable(name = "numero") String numero) 
			throws ControllerException {

		numero = verifierNumero(numero);

		try {
			Operation operation = operationService.rechercherParNumero(numero);
			if ( operation == null ) {
				throw new ControllerException(
						ControllerErreur.OPERATION_NON_TROUVEE_PAR_NUMERO,
						numero);
			}
			return OperationDtoMapper.modelToDetailedResponseDto(operation);
		} 
		catch (ServiceTechniqueException e) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		}
	}

	@PostMapping("/new")
	public OperationDetailedResponseDto creerOperation(@RequestBody OperationCreationRequestDto dto) 
			throws ControllerException {

		try {
			Operation operation = new Operation();
			operation = creationRequestDtoToModel(operation, dto);
			operation = operationService.creerOperation(operation);
			return OperationDtoMapper.modelToDetailedResponseDto(operation);
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
	public OperationDetailedResponseDto modifierOperation(
			@PathVariable(name = "numero") String numero,
			@RequestBody OperationModificationRequestDto dto) 
					throws ControllerException {

		numero = verifierNumero(numero);

		try {
			Operation operation = operationService.rechercherParNumero(numero);
			if ( operation == null ) {
				throw new ControllerException(
						ControllerErreur.OPERATION_NON_TROUVEE_PAR_NUMERO,
						numero);
			}
			operation = modificationRequestDtoToModel(operation, dto);
			operation = operationService.modifierOperation(operation);
			return OperationDtoMapper.modelToDetailedResponseDto(operation);
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
					numero);
		}
	}

	@DeleteMapping("/del/{numero}")
	public void supprimerOperation(@PathVariable(name = "numero") String numero) 
			throws ControllerException {

		numero = verifierNumero(numero);

		try {
			Operation operation = operationService.rechercherParNumero(numero);
			if ( operation == null ) {
				throw new ControllerException(
						ControllerErreur.OPERATION_NON_TROUVEE_PAR_NUMERO,
						numero);
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
					numero);
		}
	}

	private Operation creationRequestDtoToModel(
			Operation operation, 
			OperationCreationRequestDto dto) 
					throws ControllerException {

		// Préparation nouvelle opération
		//
		operation.setTypeOperation(verifierTypeOperation(dto.codeTypeOperation));
		operation.setNumero(verifierNumero(dto.numero));
		operation.setLibelle(verifierLibelle(dto.libelle));
		operation.setDateValeur(verifierDate(dto.dateValeur));
		operation.setMontantTotalEnCentimes(verifierMontantEnCentimes(dto.montantTotalEnCentimes));
		operation.setCompteDepense(verifierCompteDepenseEnregistreEtCompatible(operation.getTypeOperation(), dto.identifiantCompteDepense));
		operation.setCompteRecette(verifierCompteRecetteEnregisteEtCompatible(operation.getTypeOperation(), dto.identifiantCompteRecette));

		// Préparation de la première ligne de détail
		//
		DetailOperation detailOperation = new DetailOperation();
		detailOperation.setSequence(0);
		detailOperation.setLibelle(operation.getLibelle());
		detailOperation.setDateComptabilisation(operation.getDateValeur());
		detailOperation.setMontantDetailEnCentimes(operation.getMontantTotalEnCentimes());
		detailOperation.setSousCategorie(verifierSousCategorieEnregistree(dto.nomSousCategorie));
		detailOperation.setBeneficiaires(verifierBeneficiairesEnregistres(dto.nomsBeneficiaires));
		detailOperation.setOperation(operation);

		operation.getDetailsOperation().add(detailOperation);

		return operation;
	}

	private Operation modificationRequestDtoToModel(
			Operation operation, 
			OperationModificationRequestDto dto) 
					throws ControllerException {

		// Modification de l'opération
		//
		if ( dto.codeTypeOperation != null ) operation.setTypeOperation(verifierTypeOperation(dto.codeTypeOperation));
		if ( dto.numero != null ) operation.setNumero(verifierNumero(dto.numero));
		if ( dto.libelle != null ) operation.setLibelle(verifierLibelle(dto.libelle));
		if ( dto.dateValeur != null ) operation.setDateValeur(verifierDate(dto.dateValeur));
		if ( dto.montantTotalEnCentimes != null ) operation.setMontantTotalEnCentimes(verifierMontantEnCentimes(dto.montantTotalEnCentimes));
		if ( dto.identifiantCompteDepense != null ) operation.setCompteDepense(verifierCompteDepenseEnregistreEtCompatible(operation.getTypeOperation(), dto.identifiantCompteDepense));
		if ( dto.identifiantCompteRecette != null ) operation.setCompteRecette(verifierCompteRecetteEnregisteEtCompatible(operation.getTypeOperation(), dto.identifiantCompteRecette));

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
				if ( dto.libelle != null ) detailOperation.setLibelle(verifierLibelle(dto.libelle));
				if ( dto.dateComptabilisation != null ) detailOperation.setDateComptabilisation(verifierDate(dto.dateComptabilisation));
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

	private String verifierNumero(String numero) 
			throws ControllerException {

		if ( numero == null 
				|| numero.isBlank() ) {
			throw new ControllerException(
					ControllerErreur.OPERATION_NUMERO_OBLIGATOIRE);
		}

		return numero;
	}

	private TypeOperation verifierTypeOperation(String typeOperationCode) 
			throws ControllerException {

		if ( typeOperationCode == null 
				|| typeOperationCode.isBlank() ) {
			throw new ControllerException(
					ControllerErreur.OPERATION_TYPE_OPERATION_OBLIGATOIRE);
		}

		TypeOperation typeOperation = TypeOperation.findByCode(typeOperationCode);
		if ( typeOperation == null ) {
			throw new ControllerException(
					ControllerErreur.TYPE_OPERATION_NON_TROUVE_PAR_CODE,
					typeOperationCode);
		}
		return typeOperation;
	}

	private Timestamp verifierDate(Timestamp date) {

		if( date == null ) {
			return Timestamp.from(Instant.now());
		}

		return date;
	}

	private String verifierLibelle(String libelle) {
		
		if ( libelle == null || libelle.isBlank() ) {

			libelle = null;
		}
		return libelle;
	}

	private Compte verifierCompteDepenseEnregistreEtCompatible(TypeOperation typeOperation, String identifiantCompteDepense) 
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
			if ( CompteTiers.class.isAssignableFrom(compte.getClass()) ) {
				if ( typeOperation != TypeOperation.RECETTE ) {
					throw new ControllerException(
							ControllerErreur.TYPE_COMPTE_DEPENSE_INCOMPATIBLE_TYPE_OPERATION, 
							"TIERS",
							typeOperation.getLibelle());
				}
			}
			else if ( CompteInterne.class.isAssignableFrom(compte.getClass()) ) {

				CompteInterne compteInterne = (CompteInterne) compte;
				TypeFonctionnementCompte typeFonctionnementCompte = compteInterne.getTypeCompteInterne().getTypeFonctionnementCompte();
				testerComptabiliteCompteEnDepense(
						typeOperation, 
						typeFonctionnementCompte);
			}
			else {
				throw new ControllerException(
						ControllerErreur.COMPTE_CLASSE_NON_GEREE, 
						compte.getClass().getSimpleName());

			}
			return compte;
		} 
		catch (ServiceTechniqueException e) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		}
	}

	private Compte verifierCompteRecetteEnregisteEtCompatible(TypeOperation typeOperation, String identifiantCompteRecette) 
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
			if ( CompteTiers.class.isAssignableFrom(compte.getClass()) ) {
				if ( typeOperation != TypeOperation.DEPENSE ) {
					throw new ControllerException(
							ControllerErreur.TYPE_COMPTE_RECETTE_INCOMPATIBLE_TYPE_OPERATION, 
							"TIERS",
							typeOperation.getLibelle());
				}
			}
			else if ( CompteInterne.class.isAssignableFrom(compte.getClass()) ) {

				CompteInterne compteInterne = (CompteInterne) compte;
				TypeFonctionnementCompte typeFonctionnementCompte = compteInterne.getTypeCompteInterne().getTypeFonctionnementCompte();
				testeCompatibiliteCompteEnRecette(typeOperation, typeFonctionnementCompte);
			}
			else {
				throw new ControllerException(
						ControllerErreur.COMPTE_CLASSE_NON_GEREE, 
						compte.getClass().getSimpleName());

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
			return null;
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
		
		if ( nomsBeneficiaires == null 
				|| nomsBeneficiaires.size() == 0 ) {
			return beneficiaires;
		}
		
		for ( String nomBeneficiaire : nomsBeneficiaires ) {
			try {
				Beneficiaire beneficiaire = beneficiaireService.rechercherParNom(nomBeneficiaire);
				if ( beneficiaire == null ) {
					throw new ControllerException(
							ControllerErreur.BENEFICIAIRE_NON_TROUVE_PAR_NOM, 
							nomBeneficiaire);
				}
				beneficiaires.add(beneficiaire);
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

	private void testerComptabiliteCompteEnDepense(TypeOperation typeOperation, TypeFonctionnementCompte typeFonctionnementCompte) throws ControllerException {

		switch ( typeFonctionnementCompte ) {
		case COURANT:
			if ( typeOperation != TypeOperation.TRANSFERT
			&& typeOperation != TypeOperation.ACHAT
			&& typeOperation != TypeOperation.INVESTISSEMENT
			&& typeOperation != TypeOperation.AJUSTEMENT
			&& typeOperation != TypeOperation.DEPENSE) {
				throw new ControllerException(
						ControllerErreur.TYPE_COMPTE_DEPENSE_INCOMPATIBLE_TYPE_OPERATION, 
						typeFonctionnementCompte.getCode(),
						typeOperation.getLibelle());
			}
			break;
		case IMMOBILISATION:
			if ( typeOperation != TypeOperation.VENTE
			&& typeOperation != TypeOperation.ACTUALISATION ) {
				throw new ControllerException(
						ControllerErreur.TYPE_COMPTE_DEPENSE_INCOMPATIBLE_TYPE_OPERATION, 
						typeFonctionnementCompte.getLibelle(),
						typeOperation.getLibelle());
			}
			break;
		case INVESTISSEMENT:
			if ( typeOperation != TypeOperation.LIQUIDATION
			&& typeOperation != TypeOperation.MOINS_VALUE ) {
				throw new ControllerException(
						ControllerErreur.TYPE_COMPTE_DEPENSE_INCOMPATIBLE_TYPE_OPERATION, 
						typeFonctionnementCompte.getLibelle(),
						typeOperation.getLibelle());
			}
			break;
		case AJUSTEMENT:
			if ( typeOperation != TypeOperation.AJUSTEMENT
			&& typeOperation != TypeOperation.ACTUALISATION
			&& typeOperation != TypeOperation.PLUS_VALUE ) {
				throw new ControllerException(
						ControllerErreur.TYPE_COMPTE_DEPENSE_INCOMPATIBLE_TYPE_OPERATION, 
						typeFonctionnementCompte.getLibelle(),
						typeOperation.getLibelle());
			}
			break;
		default:
			throw new ControllerException(
					ControllerErreur.TYPE_FONCTIONNEMENT_COMPTE_NON_GERE, 
					typeFonctionnementCompte.getLibelle());
		}
	}
	
	private void testeCompatibiliteCompteEnRecette(TypeOperation typeOperation, TypeFonctionnementCompte typeFonctionnementCompte) throws ControllerException {
		
		switch ( typeFonctionnementCompte ) {
		case COURANT:
			if ( typeOperation != TypeOperation.TRANSFERT
			&& typeOperation != TypeOperation.VENTE
			&& typeOperation != TypeOperation.LIQUIDATION
			&& typeOperation != TypeOperation.AJUSTEMENT
			&& typeOperation != TypeOperation.RECETTE) {
				throw new ControllerException(
						ControllerErreur.TYPE_COMPTE_RECETTE_INCOMPATIBLE_TYPE_OPERATION, 
						typeFonctionnementCompte.getCode(),
						typeOperation.getLibelle());
			}
			break;
		case IMMOBILISATION:
			if ( typeOperation != TypeOperation.ACHAT
			&& typeOperation != TypeOperation.ACTUALISATION ) {
				throw new ControllerException(
						ControllerErreur.TYPE_COMPTE_RECETTE_INCOMPATIBLE_TYPE_OPERATION, 
						typeFonctionnementCompte.getLibelle(),
						typeOperation.getLibelle());
			}
			break;
		case INVESTISSEMENT:
			if ( typeOperation != TypeOperation.INVESTISSEMENT
			&& typeOperation != TypeOperation.PLUS_VALUE ) {
				throw new ControllerException(
						ControllerErreur.TYPE_COMPTE_RECETTE_INCOMPATIBLE_TYPE_OPERATION, 
						typeFonctionnementCompte.getLibelle(),
						typeOperation.getLibelle());
			}
			break;
		case AJUSTEMENT:
			if ( typeOperation != TypeOperation.AJUSTEMENT
			&& typeOperation != TypeOperation.ACTUALISATION
			&& typeOperation != TypeOperation.MOINS_VALUE ) {
				throw new ControllerException(
						ControllerErreur.TYPE_COMPTE_RECETTE_INCOMPATIBLE_TYPE_OPERATION, 
						typeFonctionnementCompte.getLibelle(),
						typeOperation.getLibelle());
			}
			break;
		default:
			throw new ControllerException(
					ControllerErreur.TYPE_FONCTIONNEMENT_COMPTE_NON_GERE, 
					typeFonctionnementCompte.getLibelle());
		}
	}
}

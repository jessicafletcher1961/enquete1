package fr.colline.monatis.controller;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.colline.monatis.controller.dto.OperationCreationRequestDto;
import fr.colline.monatis.exception.ControllerErreur;
import fr.colline.monatis.exception.ControllerException;
import fr.colline.monatis.exception.ServiceFonctionnelleException;
import fr.colline.monatis.exception.ServiceTechniqueException;
import fr.colline.monatis.model.Banque;
import fr.colline.monatis.model.Beneficiaire;
import fr.colline.monatis.model.Categorie;
import fr.colline.monatis.model.CompteInterne;
import fr.colline.monatis.model.CompteTiers;
import fr.colline.monatis.model.SousCategorie;
import fr.colline.monatis.model.Titulaire;
import fr.colline.monatis.service.BanqueService;
import fr.colline.monatis.service.BeneficiaireService;
import fr.colline.monatis.service.CategorieService;
import fr.colline.monatis.service.CompteInterneService;
import fr.colline.monatis.service.CompteTiersService;
import fr.colline.monatis.service.OperationService;
import fr.colline.monatis.service.SousCategorieService;
import fr.colline.monatis.service.TitulaireService;
import fr.colline.monatis.typologie.TypeCompteInterne;

@RestController
@RequestMapping("/monatis/init")
public class InitController {

	@Autowired private CategorieService categorieService; 
	@Autowired private SousCategorieService sousCategorieService; 
	@Autowired private BanqueService banqueService; 
	@Autowired private TitulaireService titulaireService; 
	@Autowired private BeneficiaireService beneficiaireService; 

	@Autowired private CompteTiersService compteTiersService; 
	@Autowired private CompteInterneService compteInterneService; 

	@Autowired private OperationController operationController; 
	@Autowired private OperationService operationService; 

	@GetMapping("/insertall")
	public void initialisation() 
			throws ControllerException {

		supprimerTout();

		initialiseReferences();
		initialiseComptes();
		initialiseOperations();
	}

	@GetMapping("/deleteall")
	public void suppression() 
			throws ControllerException {

		supprimerTout();
	}

	private void supprimerTout() 
			throws ControllerException {

		try {
			// OPERATIONS
			operationService.supprimerTous();

			// COMPTES
			compteTiersService.supprimerTous();
			compteInterneService.supprimerTous();

			// REFERENCES
			titulaireService.supprimerTous();
			sousCategorieService.supprimerTous();
			categorieService.supprimerTous();
			banqueService.supprimerTous();
			beneficiaireService.supprimerTous();
			
		} catch (ServiceTechniqueException e) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		}
	}

	private void initialiseReferences() 
			throws ControllerException {

		try {

			// CATEGORIES ET SOUS-CATEGORIES
			//
			SousCategorie sousCategorie;

			Categorie fonctionnement = categorieService.creerReference(new Categorie(
					"FONCTIONNEMENT",
					"Frais de fonctionnement"));

			sousCategorie = new SousCategorie(
					"ALIMENTATION",
					"Repas maison");
			sousCategorie.changerCategorie(fonctionnement);
			sousCategorieService.creerReference(sousCategorie);

			sousCategorie = new SousCategorie(
					"BOX",
					"Abonnement box internet");
			sousCategorie.changerCategorie(fonctionnement);
			sousCategorieService.creerReference(sousCategorie);
			
			sousCategorie = new SousCategorie(
					"MENAGE",
					"Ménage à domicile");
			sousCategorie.changerCategorie(fonctionnement);
			sousCategorieService.creerReference(sousCategorie);

			Categorie loisirs = categorieService.creerReference(new Categorie(
					"LOISIRS",
					"Loisirs : restaurants, location de vacances..."));

			sousCategorie = new SousCategorie(
					"RESTO",
					"Restaurant");
			sousCategorie.changerCategorie(loisirs);
			sousCategorieService.creerReference(sousCategorie);
			
			sousCategorie = new SousCategorie(
					"INTERNET",
					"Services Internet : canal+, prime vidéo, deezer, midjourney...");
			sousCategorie.changerCategorie(loisirs);
			sousCategorieService.creerReference(sousCategorie);
			
			Categorie virementInterne = categorieService.creerReference(new Categorie(
					"VIREMENT_INTERNE",
					"Déplacement des fonds d'un compte interne à un autre"));
			
			sousCategorie = new SousCategorie(
					"VIREMENT_INTERNE",
					"Déplacement des fonds d'un compte interne à un autre");
			sousCategorie.changerCategorie(virementInterne);
			sousCategorieService.creerReference(sousCategorie);
			
			Categorie revenus = categorieService.creerReference(new Categorie(
					"REVENUS",
					"Revenus divers"));

			sousCategorie = new SousCategorie(
					"SALAIRES",
					"Salaires versés par l'employeur");
			sousCategorie.changerCategorie(revenus);
			sousCategorieService.creerReference(sousCategorie);
			
			sousCategorie = new SousCategorie(
					"RETRAITES_SECU",
					"Retraites versées par l'assurance maladie");
			sousCategorie.changerCategorie(revenus);
			sousCategorieService.creerReference(sousCategorie);
			
			// BANQUES 
			//
			banqueService.creerReference(new Banque(
					"CEIDF",
					"Caisse d'épargne Ile de France"));

			// TITULAIRES
			//
			titulaireService.creerReference(new Titulaire(
					"ODILE",
					"Odile Zirah Danis"));
			titulaireService.creerReference(new Titulaire(
					"THIERRY",
					"Thierry Danis"));

			// BENEFICIAIRES
			//
			beneficiaireService.creerReference(new Beneficiaire(
					"ODILE", 
					"Odile Zirah Danis"));
			beneficiaireService.creerReference(new Beneficiaire(
					"THIERRY", 
					"Thierry Danis"));
			beneficiaireService.creerReference(new Beneficiaire(
					"NICOLE", 
					"Nicole Schmitt Zirah"));

		} catch (ServiceTechniqueException e) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		} catch (ServiceFonctionnelleException e) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_INITIALISATION);
		}
	}

	private void initialiseComptes() throws ControllerException {

		try {
			// COMPTES DE TIERS
			compteTiersService.creerCompte(new CompteTiers(
					"SHIVA-PONTOISE",
					"SHIVA - agence de Pontoise"));
			compteTiersService.creerCompte(new CompteTiers(
					"SHIVA-LE-RAINCY",
					"SHIVA - agence du Raincy"));
			compteTiersService.creerCompte(new CompteTiers(
					"IMPOTS",
					"Impôts divers : revenus, taxes foncières, ..."));
			compteTiersService.creerCompte(new CompteTiers(
					"AMENDES",
					"Amendes diverses"));
			compteTiersService.creerCompte(new CompteTiers(
					"PENSION",
					"Retraite versée par DRFIP PAYS DE LA LOIRE"));

			// COMPTES INTERNES
			CompteInterne compteInterne;
			Banque caisseEpargne = banqueService.rechercherParNom("CEIDF");
			Titulaire odile = titulaireService.rechercherParNom("ODILE");
			Titulaire thierry = titulaireService.rechercherParNom("THIERRY");

			// Création Livret A Odile
			compteInterne = new CompteInterne(
					"LA_O", 
					"Livret A Odile",
					TypeCompteInterne.LIVRETS,
					Timestamp.valueOf("2025-01-01 00:00:00"), 
					1500000L);
			compteInterne.changerBanque(caisseEpargne);
			compteInterne.ajouterTitulaire(odile);
			compteInterneService.creerCompte(compteInterne);
			
			// Création Livret A Thierry
			compteInterne = new CompteInterne(
					"LA_T", 
					"Livret A Thierry",
					TypeCompteInterne.LIVRETS,
					Timestamp.valueOf("2025-01-01 00:00:00"), 
					1500000L);
			compteInterne.changerBanque(caisseEpargne);
			compteInterne.ajouterTitulaire(thierry);
			compteInterneService.creerCompte(compteInterne);

			// Création Compte joint
			compteInterne = new CompteInterne(
					"CJ", 
					"Compte joint",
					TypeCompteInterne.COMPTE_EUROS,
					Timestamp.valueOf("2025-01-01 00:00:00"), 
					10000L);
			compteInterne.changerBanque(caisseEpargne);
			compteInterne.ajouterTitulaire(thierry);
			compteInterne.ajouterTitulaire(odile);
			compteInterneService.creerCompte(compteInterne);

		} catch (ServiceTechniqueException e) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		} catch (ServiceFonctionnelleException e) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_INITIALISATION);
		}
	}

	private void initialiseOperations() 
			throws ControllerException {

		try {
			// REFERENCES ET COMPTES UTILISES
			SousCategorie menage =  sousCategorieService.rechercherParNom("MENAGE");
			SousCategorie virementInterne = sousCategorieService.rechercherParNom("VIREMENT_INTERNE");
			SousCategorie pension = sousCategorieService.rechercherParNom("RETRAITES_SECU");

			CompteInterne compteJoint = compteInterneService.rechercherParIdentifiant("CJ");	
			CompteInterne livretAOdile = compteInterneService.rechercherParIdentifiant("LA_O");	

			CompteTiers shivaPontoise = compteTiersService.rechercherParIdentifiant("SHIVA-PONTOISE");
			CompteTiers shivaLeRaincy = compteTiersService.rechercherParIdentifiant("SHIVA-LE-RAINCY");
			CompteTiers retraitePension = compteTiersService.rechercherParIdentifiant("PENSION");

			Beneficiaire odile = beneficiaireService.rechercherParNom("ODILE");
			Beneficiaire thierry = beneficiaireService.rechercherParNom("THIERRY");
			Beneficiaire nicole = beneficiaireService.rechercherParNom("NICOLE");

			// OPERATIONS : CREATION
			
			OperationCreationRequestDto operationCreationRequestDto;
			
			operationCreationRequestDto = new OperationCreationRequestDto();
			operationCreationRequestDto.dateValeur = Timestamp.from(Instant.now());
			operationCreationRequestDto.identifiantCompteDepense = compteJoint.getIdentifiant();
			operationCreationRequestDto.identifiantCompteRecette = shivaPontoise.getIdentifiant();
			operationCreationRequestDto.libelle = "Paiement mensuel SHIVA Osny";
			operationCreationRequestDto.montantTotalEnCentimes = 30000L;
			operationCreationRequestDto.nomsBeneficiaires = new ArrayList<>();
			operationCreationRequestDto.nomsBeneficiaires.add(odile.getNom());
			operationCreationRequestDto.nomsBeneficiaires.add(thierry.getNom());
			operationCreationRequestDto.nomSousCategorie = menage.getNom();
			operationCreationRequestDto.numero = "CHQ 00000001";
			operationController.creerOperation(operationCreationRequestDto);

			operationCreationRequestDto = new OperationCreationRequestDto();
			operationCreationRequestDto.dateValeur = Timestamp.from(Instant.now());
			operationCreationRequestDto.identifiantCompteDepense = compteJoint.getIdentifiant();
			operationCreationRequestDto.identifiantCompteRecette = shivaLeRaincy.getIdentifiant();
			operationCreationRequestDto.libelle = "Paiement mensuel SHIVA Le Raincy";
			operationCreationRequestDto.montantTotalEnCentimes = 45000L;
			operationCreationRequestDto.nomsBeneficiaires = new ArrayList<>();
			operationCreationRequestDto.nomsBeneficiaires.add(nicole.getNom());
			operationCreationRequestDto.nomSousCategorie = menage.getNom();
			operationCreationRequestDto.numero = "	";
			operationController.creerOperation(operationCreationRequestDto);
			
			operationCreationRequestDto = new OperationCreationRequestDto();
			operationCreationRequestDto.dateValeur = Timestamp.from(Instant.now());
			operationCreationRequestDto.identifiantCompteDepense = livretAOdile.getIdentifiant();
			operationCreationRequestDto.identifiantCompteRecette = compteJoint.getIdentifiant();
			operationCreationRequestDto.libelle = "Virement interne en prévision achat voiture";
			operationCreationRequestDto.montantTotalEnCentimes = 4500000L;
			operationCreationRequestDto.nomsBeneficiaires = new ArrayList<>();
			operationCreationRequestDto.nomSousCategorie = virementInterne.getNom();
			operationCreationRequestDto.numero = "00000003";
			operationController.creerOperation(operationCreationRequestDto);

			operationCreationRequestDto = new OperationCreationRequestDto();
			operationCreationRequestDto.dateValeur = Timestamp.from(Instant.now());
			operationCreationRequestDto.identifiantCompteDepense = retraitePension.getIdentifiant();
			operationCreationRequestDto.identifiantCompteRecette = compteJoint.getIdentifiant();
			operationCreationRequestDto.libelle = "Virement pension ETAT";
			operationCreationRequestDto.montantTotalEnCentimes = 150000L;
			operationCreationRequestDto.nomsBeneficiaires = new ArrayList<>();
			operationCreationRequestDto.nomsBeneficiaires.add(odile.getNom());
			operationCreationRequestDto.nomSousCategorie = pension.getNom();
			operationCreationRequestDto.numero = "00000004";
			operationController.creerOperation(operationCreationRequestDto);

		} catch (ServiceTechniqueException e) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		}
	}
}

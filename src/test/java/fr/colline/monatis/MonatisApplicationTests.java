package fr.colline.monatis;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
import fr.colline.monatis.service.BanqueServiceTests;
import fr.colline.monatis.service.BeneficiaireService;
import fr.colline.monatis.service.CategorieService;
import fr.colline.monatis.service.CompteInterneService;
import fr.colline.monatis.service.CompteTiersService;
import fr.colline.monatis.service.CompteTiersServiceTests;
//import fr.colline.monatis.service.OperationService;
import fr.colline.monatis.service.SousCategorieService;
import fr.colline.monatis.service.TitulaireService;
import fr.colline.monatis.typologie.TypeCompteInterne;

@SpringBootTest
public class MonatisApplicationTests {

	@Autowired private CategorieService categorieService;
	@Autowired private BeneficiaireService beneficiaireService;
	@Autowired private TitulaireService titulaireService;
	@Autowired private BanqueService banqueService;
	@Autowired private SousCategorieService sousCategorieService;

	@Autowired private CompteTiersService compteTiersService;
	@Autowired private CompteInterneService compteInterneService;

//	@Autowired private OperationService operationService;

	@Test
	void serviceTests()
	{
		Result result = JUnitCore.runClasses(
				BanqueServiceTests.class,
				CompteTiersServiceTests.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println(result.wasSuccessful());
	}

	@Test
	void initialisationBase() {
		try {
			// -------------------------------------------
			// CATEGORIES ET SOUS-CATEGORIES
			// BANQUES
			// TITULAIRES
			// BENEFICIAIRES
			creationReferenceTest();
			// -------------------------------------------
			// COMPTES INTERNES
			creationCompteInterne();
			// -------------------------------------------
			// COMPTES DE TIERS
			creationCompteTiers();
			// -------------------------------------------
			// OPERATIONS
			creationOperation();

		} catch (Throwable t) {
			// TODO Auto-generated catch block
			t.printStackTrace();
		}
	}

	private void creationReferenceTest() 
			throws ServiceTechniqueException, ServiceFonctionnelleException {

		// CATEGORIES ET SOUS-CATEGORIES
		//
		SousCategorie sousCategorie;

		Categorie fonctionnement = categorieService.creerReference(new Categorie(
				"FONCTIONNEMENT",
				"Frais de fonctionnement"));

		sousCategorie = new SousCategorie(
				"ALIMENTATION",
				"Repas maison");
		sousCategorie.setCategorie(fonctionnement);
		sousCategorieService.creerReference(sousCategorie);

		sousCategorie = new SousCategorie(
				"BOX",
				"Abonnement box internet");
		sousCategorie.setCategorie(fonctionnement);
		sousCategorieService.creerReference(sousCategorie);
		
		sousCategorie = new SousCategorie(
				"MENAGE",
				"Ménage à domicile");
		sousCategorie.setCategorie(fonctionnement);
		sousCategorieService.creerReference(sousCategorie);

		Categorie loisirs = categorieService.creerReference(new Categorie(
				"LOISIRS",
				"Loisirs : restaurants, location de vacances..."));

		sousCategorie = new SousCategorie(
				"RESTO",
				"Restaurant");
		sousCategorie.setCategorie(loisirs);
		sousCategorieService.creerReference(sousCategorie);
		
		sousCategorie = new SousCategorie(
				"INTERNET",
				"Services Internet : canal+, prime vidéo, deezer, midjourney...");
		sousCategorie.setCategorie(loisirs);
		sousCategorieService.creerReference(sousCategorie);
		
		Categorie virementInterne = categorieService.creerReference(new Categorie(
				"VIREMENT_INTERNE",
				"Déplacement des fonds d'un compte interne à un autre"));
		
		sousCategorie = new SousCategorie(
				"VIREMENT_INTERNE",
				"Déplacement des fonds d'un compte interne à un autre");
		sousCategorie.setCategorie(virementInterne);
		sousCategorieService.creerReference(sousCategorie);
		
		Categorie revenus = categorieService.creerReference(new Categorie(
				"REVENUS",
				"Revenus divers"));

		sousCategorie = new SousCategorie(
				"SALAIRES",
				"Salaires versés par l'employeur");
		sousCategorie.setCategorie(revenus);
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
	}

	private void creationCompteTiers() 
			throws ServiceFonctionnelleException, ServiceTechniqueException {

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
	}

	private void creationCompteInterne() 
			throws ServiceTechniqueException, ServiceFonctionnelleException {

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
	}

	private void creationOperation() 
			throws ServiceFonctionnelleException, ServiceTechniqueException {

//		SousCategorie menage =  sousCategorieService.rechercherParNom("MENAGE");
//		SousCategorie virementInterne = sousCategorieService.rechercherParNom("VIREMENT_INTERNE");
//		SousCategorie pension = sousCategorieService.rechercherParNom("PENSION");
//
//		CompteInterne compteJoint = compteInterneService.rechercherParIdentifiant("CJ");	
//		CompteInterne livretAOdile = compteInterneService.rechercherParIdentifiant("LA_O");	
//
//		CompteTiers shivaPontoise = compteTiersService.rechercherParIdentifiant("SHIVA-PONTOISE");
//		CompteTiers shivaLeRaincy = compteTiersService.rechercherParIdentifiant("SHIVA-LE-RAINCY");
//		CompteTiers retraitePension = compteTiersService.rechercherParIdentifiant("PENSION");
//		
//		Beneficiaire odile = beneficiaireService.rechercherParNom("ODILE");
//		Beneficiaire thierry = beneficiaireService.rechercherParNom("THIERRY");
//		Beneficiaire nicole = beneficiaireService.rechercherParNom("NICOLE");
//
//		operationService.creerOperation(
//				new Operation(
//						Timestamp.from(Instant.now()),
//						"0000-0001",
//						"--",
//						30000L));
//
//		operationService.creerOperation(
//				new Operation(
//						Timestamp.from(Instant.now()),
//						"0000-0002",
//						"--",
//						45000L));
//
//		operationService.creerOperation(
//				new Operation(
//						Timestamp.from(Instant.now()),
//						"0000-0003",
//						"Préparation de lourdes dépenses",
//						100000L));
//
//		operationService.creerOperation(
//				new Operation(
//						Timestamp.from(Instant.now()),
//						"0000-0004",
//						"Retraite état Odile",
//						150000L));
	}
}

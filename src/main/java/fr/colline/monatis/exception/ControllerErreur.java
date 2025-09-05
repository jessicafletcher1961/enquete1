package fr.colline.monatis.exception;

import org.springframework.http.HttpStatus;

public enum ControllerErreur {

	/**
			HttpStatus.INTERNAL_SERVER_ERROR,
			TypeErreur.TECHNIQUE,
			"TEC-0001",
			"Un problème technique est survenu"),
	 */
	ERREUR_TECHNIQUE(
			HttpStatus.INTERNAL_SERVER_ERROR,
			TypeErreur.TECHNIQUE,
			"TEC-0001",
			"Un problème technique est survenu"),

	/**
			HttpStatus.INTERNAL_SERVER_ERROR,
			TypeErreur.TECHNIQUE,
			"TEC-0002",
			"Un problème est survenu lors du chargement d'initialisation de la base"),
	 */
	ERREUR_INITIALISATION(
			HttpStatus.INTERNAL_SERVER_ERROR,
			TypeErreur.TECHNIQUE,
			"TEC-0002",
			"Un problème est survenu lors du chargement d'initialisation de la base"),

	/**
			HttpStatus.NOT_FOUND,
			TypeErreur.FONCTIONNELLE,
			"BNQ-0001",
			"Aucune banque de nom '%s' n'a été trouvée"),
	 */
	BANQUE_NON_TROUVEE_PAR_NOM(
			HttpStatus.NOT_FOUND,
			TypeErreur.FONCTIONNELLE,
			"BNQ-0001",
			"Aucune banque de nom '%s' n'a été trouvée"),

	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"BNQ-0002",
			"La banque '%s' n'a pas pu être crée"),
	 */
	BANQUE_CREATION_PROBLEME(
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"BNQ-0002",
			"La banque '%s' n'a pas pu être créée"),

	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"BNQ-0003",
			"La banque '%s' n'a pas pu être modifiée"),
	 */
	BANQUE_MODIFICATION_PROBLEME(
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"BNQ-0003",
			"La banque '%s' n'a pas pu être modifiée"),

	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"BNQ-0004",
			"La banque '%s' n'a pas pu être supprimée"),
	 */
	BANQUE_SUPPRESSION_PROBLEME(
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"BNQ-0004",
			"La banque '%s' n'a pas pu être supprimée"),

	BANQUE_NOM_OBLIGATOIRE(			
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"BNQ-0005",
			"Une banque doit obligatoirement avoir un nom"),
	/**
			HttpStatus.NOT_FOUND,
			TypeErreur.FONCTIONNELLE,
			"BEN-0001",
			"Aucun bénéficiaire de nom '%s' n'a été trouvé"),
	 */
	BENEFICIAIRE_NON_TROUVE_PAR_NOM(
			HttpStatus.NOT_FOUND,
			TypeErreur.FONCTIONNELLE,
			"BEN-0001",
			"Aucun bénéficiaire de nom '%s' n'a été trouvé"),

	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"BEN-0002",
			"Le bénéficiaire de nom '%s' n'a pas pu être créé"),
	 */
	BENEFICIAIRE_CREATION_PROBLEME(
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"BEN-0002",
			"Le bénéficiaire de nom '%s' n'a pas pu être créé"),

	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"BEN-0003",
			"Le bénéficiaire de nom '%s' n'a pas pu être modifié"),
	 */
	BENEFICIAIRE_MODIFICATION_PROBLEME(
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"BEN-0003",
			"Le bénéficiaire de nom '%s' n'a pas pu être modifié"),

	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"BEN-0004",
			"Le bénéficiaire de nom '%s' n'a pas pu être supprimé"),
	 */
	BENEFICIAIRE_SUPPRESSION_PROBLEME(
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"BEN-0004",
			"Le bénéficiaire de nom '%s' n'a pas pu être supprimé"),

	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"BEN-0005",
			"Un bénéficiaire doit obligatoirement avoir un nom"),
	 */
	BENEFICIAIRE_NOM_OBLIGATOIRE(			
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"BEN-0005",
			"Un bénéficiaire doit obligatoirement avoir un nom"),

	/**
			HttpStatus.NOT_FOUND,
			TypeErreur.FONCTIONNELLE,
			"CAT-0001",
			"Aucune categorie de nom '%s' n'a été trouvée"),
	 */
	CATEGORIE_NON_TROUVEE_PAR_NOM(
			HttpStatus.NOT_FOUND,
			TypeErreur.FONCTIONNELLE,
			"CAT-0001",
			"Aucune categorie de nom '%s' n'a été trouvée"),

	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"CAT-0002",
			"La catégorie de nom '%s' n'a pas pu être créée"),
	 */
	CATEGORIE_CREATION_PROBLEME(
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"CAT-0002",
			"La catégorie de nom '%s' n'a pas pu être créée"),

	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"CAT-0003",
			"La catégorie de nom '%s' n'a pas pu être modifiée"),
	 */
	CATEGORIE_MODIFICATION_PROBLEME(
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"CAT-0003",
			"La catégorie de nom '%s' n'a pas pu être modifiée"),

	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"CAT-0004",
			"La catégorie de nom '%s' n'a pas pu être supprimée"),
	 */
	CATEGORIE_SUPPRESSION_PROBLEME(
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"CAT-0004",
			"La catégorie de nom '%s' n'a pas pu être supprimée"),

	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"CAT-0005",
			"Une catégorie doit obligatoirement avoir un nom"),
	 */
	CATEGORIE_NOM_OBLIGATOIRE(			
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"CAT-0005",
			"Une catégorie doit obligatoirement avoir un nom"),

	/**
			HttpStatus.NOT_FOUND,
			TypeErreur.FONCTIONNELLE,
			"SSC-0001",
			"Aucune sous-categorie de nom '%s' n'a été trouvée"), 
	 */
	SOUS_CATEGORIE_NON_TROUVEE_PAR_NOM(
			HttpStatus.NOT_FOUND,
			TypeErreur.FONCTIONNELLE,
			"SSC-0001",
			"Aucune sous-categorie de nom '%s' n'a été trouvée"), 

	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"SSC-0002",
			"La sous-catégorie de nom '%s' n'a pas pu être créée"),
	 */
	SOUS_CATEGORIE_CREATION_PROBLEME(
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"SSC-0002",
			"La sous-catégorie de nom '%s' n'a pas pu être créée"),
	
	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"SSC-0003",
			"La sous-catégorie de nom '%s' n'a pas pu être modifiée"),
	 */
	SOUS_CATEGORIE_MODIFICATION_PROBLEME(
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"SSC-0003",
			"La sous-catégorie de nom '%s' n'a pas pu être modifiée"),

	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"SSC-0004",
			"La sous-categorie '%s' n'a pas pu être supprimée"),
	 */
	SOUS_CATEGORIE_SUPPRESSION_PROBLEME(
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"SSC-0004",
			"La sous-categorie '%s' n'a pas pu être supprimée"),

	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"SSC-0005",
			"Une sous-catégorie doit obligatoirement être associée à une catégorie de référence déterminée par son nom"),
	 */
	SOUS_CATEGORIE_NOM_CATEGORIE_OBLIGATOIRE(
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"SSC-0005",
			"Une sous-catégorie doit obligatoirement être associée à une catégorie de référence déterminée par son nom"),

	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"SSC-0006",
			"Une sous-catégorie doit obligatoirement avoir un nom"),
	 */
	SOUS_CATEGORIE_NOM_OBLIGATOIRE(			
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"SSC-0006",
			"Une sous-catégorie doit obligatoirement avoir un nom"),

	/**
			HttpStatus.NOT_FOUND,
			TypeErreur.FONCTIONNELLE,
			"TIT-0001",
			"Aucune titulaire de nom '%s' n'a été trouvé"),
	 */
	TITULAIRE_NON_TROUVE_PAR_NOM(
			HttpStatus.NOT_FOUND,
			TypeErreur.FONCTIONNELLE,
			"TIT-0001",
			"Aucune titulaire de nom '%s' n'a été trouvé"),

	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"TIT-0002",
			"Le titulaire de nom '%s' n'a pas pu être créé"), 
	 */
	TITULAIRE_CREATION_PROBLEME(
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"TIT-0002",
			"Le titulaire de nom '%s' n'a pas pu être créé"), 
	
	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"TIT-0003",
			"Le titulaire de nom '%s' n'a pas pu être modifié"),
	 */
	TITULAIRE_MODIFICATION_PROBLEME(
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"TIT-0003",
			"Le titulaire de nom '%s' n'a pas pu être modifié"),
	
	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"TIT-0004",
			"Le titulaire de nom '%s' n'a pas pu être supprmé"),
	 */
	TITULAIRE_SUPPRESSION_PROBLEME(
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"TIT-0004",
			"Le titulaire de nom '%s' n'a pas pu être supprmé"),
	
	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"TIT-0005",
			"Un titulaire doit obligatoirement avoir un nom"),
	 */
	TITULAIRE_NOM_OBLIGATOIRE(			
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"TIT-0005",
			"Un titulaire doit obligatoirement avoir un nom"),
	
	/**
			HttpStatus.NOT_FOUND,
			TypeErreur.FONCTIONNELLE,
			"CPT-0001",
			"Aucun compte (interne ou tiers) d'identifiant '%s' n'a été trouvé"),
	 */
	COMPTE_NON_TROUVE_PAR_IDENTIFIANT(
			HttpStatus.NOT_FOUND,
			TypeErreur.FONCTIONNELLE,
			"CPT-0001",
			"Aucun compte (interne ou tiers) d'identifiant '%s' n'a été trouvé"),

	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"CPT-0002",
			"Un compte de type de fonctionnement '%s' est incompatible en dépense avec des opération de type '%s'"),
	 */
	TYPE_COMPTE_DEPENSE_INCOMPATIBLE_TYPE_OPERATION(
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"CPT-0002",
			"Un compte de type de fonctionnement '%s' est incompatible en dépense avec des opération de type '%s'"),

	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"CPT-0003",
			"Un compte de type de fonctionnement '%s' est incompatible en recette avec des opération de type '%s'"),
	 */
	TYPE_COMPTE_RECETTE_INCOMPATIBLE_TYPE_OPERATION(
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"CPT-0003",
			"Un compte de type de fonctionnement '%s' est incompatible en recette avec des opération de type '%s'"),
	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"CPT-0004",
			"Le type de fonctionnement de compte '%s' n'est pas géré"),
	 */
	TYPE_FONCTIONNEMENT_COMPTE_NON_GERE(
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"CPT-0004",
			"Le type de fonctionnement de compte '%s' n'est pas géré"),

	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"CPT-0005",
			"La classe de compte '%s' n'est pas gérée"),
	 */
	COMPTE_CLASSE_NON_GEREE(
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"CPT-0005",
			"La classe de compte '%s' n'est pas gérée"),
	
	/**
			HttpStatus.NOT_FOUND,
			TypeErreur.FONCTIONNELLE,
			"CPTI-0001",
			"Aucun compte interne d'identifiant '%s' n'a été trouvé"),
	 */
	COMPTE_INTERNE_NON_TROUVE_PAR_IDENTIFIANT(
			HttpStatus.NOT_FOUND,
			TypeErreur.FONCTIONNELLE,
			"CPTI-0001",
			"Aucun compte interne d'identifiant '%s' n'a été trouvé"),

	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"CPTI-0002",
			"Le compte interne '%s' n'a pas pu être créé"), 
	 */
	COMPTE_INTERNE_CREATION_PROBLEME(
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"CPTI-0002",
			"Le compte interne '%s' n'a pas pu être créé"), 

	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"CPTI-0003",
			"Le compte interne '%s' n'a pas pu être modifié"), 
	 */
	COMPTE_INTERNE_MODIFICATION_PROBLEME(
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"CPTI-0003",
			"Le compte interne '%s' n'a pas pu être modifié"), 

	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"CPTI-0004",
			"Le compte interne '%s' n'a pas pu être supprimé"), 
	 */
	COMPTE_INTERNE_SUPPRESSION_PROBLEME(
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"CPTI-0004",
			"Le compte interne '%s' n'a pas pu être supprimé"), 

	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"CPTI-0005",
			"La liste des titulaires du compte interne est obligatoire"),
	 */
	COMPTE_INTERNE_TABLEAU_NOM_TITULAIRE_OBLIGATOIRE(
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"CPTI-0005",
			"La liste des titulaires du compte interne est obligatoire"),

	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"CPTI-0006",
			"Le compte interne '%s' doit avoir une banque"),
	 */
	COMPTE_INTERNE_BANQUE_OBLIGATOIRE(
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"CPTI-0006",
			"Le compte interne '%s' doit avoir une banque"),

	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"CPTI-0007",
			"Un compte interne doit obligatoirement avoir un identifiant"),
	 */
	COMPTE_INTERNE_IDENTIFIANT_OBLIGATOIRE(
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"CPTI-0007",
			"Un compte interne doit obligatoirement avoir un identifiant"),
	
	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"CPTI-0008",
			"Un compte interne doit obligatoirement avoir un type"),
	 */
	COMPTE_INTERNE_TYPE_COMPTE_OBLIGATOIRE(			
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"CPTI-0008",
			"Un compte interne doit obligatoirement avoir un type"),

	/**
			HttpStatus.NOT_FOUND,
			TypeErreur.FONCTIONNELLE,
			"CPTI-0009",
			"Aucun type de compte interne de code '%s' n'a été trouvé"),
	 */
	COMPTE_INTERNE_TYPE_COMPTE_NON_TROUVE_PAR_CODE(
			HttpStatus.NOT_FOUND,
			TypeErreur.FONCTIONNELLE,
			"CPTI-0009",
			"Aucun type de compte interne de code '%s' n'a été trouvé"),

	
	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"CPTI-0010",
			"Le compte interne d'identifiant '%s' doit avoir au moins un titulaire"),
	 */
	COMPTE_INTERNE_AU_MOINS_UN_TITULAIRE_REQUIS(
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"CPTI-0010",
			"Le compte interne doit avoir au moins un titulaire"),

	/**
			HttpStatus.NOT_FOUND,
			TypeErreur.FONCTIONNELLE,
			"CPT-0001",
			"Aucun compte tiers d'identifiant '%s' n'a été trouvé"), 
	 */
	COMPTE_TIERS_NON_TROUVE_PAR_IDENTIFIANT(
			HttpStatus.NOT_FOUND,
			TypeErreur.FONCTIONNELLE,
			"CPTT-0001",
			"Aucun compte tiers d'identifiant '%s' n'a été trouvé"), 
	
	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"CPTT-0002",
			"Le compte tiers '%s' n'a pas pu être créé"), 
	 */
	COMPTE_TIERS_CREATION_PROBLEME(
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"CPTT-0002",
			"Le compte tiers '%s' n'a pas pu être créé"), 
	
	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"CPTT-0003",
			"Le compte tiers '%s' n'a pas pu être modifié"), 
	 */
	COMPTE_TIERS_MODIFICATION_PROBLEME(			
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"CPTT-0003",
			"Le compte tiers '%s' n'a pas pu être modifié"), 

	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"CPTT-0004",
			"Le compte interne '%s' n'a pas pu être supprimé"), 
	 */
	COMPTE_TIERS_SUPPRESSION_PROBLEME(		
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"CPTT-0004",
			"Le compte interne '%s' n'a pas pu être supprimé"),

	/**
			HttpStatus.NOT_FOUND,
			TypeErreur.FONCTIONNELLE,
			"OPE-0001",
			"Aucune opération de numéro '%s' n'a été trouvée"),
	 */
	OPERATION_NON_TROUVEE_PAR_NUMERO(
			HttpStatus.NOT_FOUND,
			TypeErreur.FONCTIONNELLE,
			"OPE-0001",
			"Aucune opération de numéro '%s' n'a été trouvée"),
	
	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"OPE-0002",
			"L'operation de numéro '%s' doit avoir au moins une ligne de détail"),
	 */
	OPERATION_AU_MOINS_UNE_LIGNE_DE_DETAIL_REQUISE(
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"OPE-0002",
			"L'operation de numéro '%s' doit avoir au moins une ligne de détail"),

	/**
			TypeErreur.FONCTIONNELLE,
			"OPE-0003",
			"L'operation de numéro '%s' doit avoir un montant valide"),
	 */
	OPERATION_MONTANT_INVALIDE(
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"OPE-0003",
			"L'operation de numéro '%s' doit avoir un montant valide"),

	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"OPE-0004",
			"Une opération doit avoir un compte de recette sur lequel imputer le montant"),	
	 */
	OPERATION_COMPTE_RECETTE_OBLIGATOIRE(
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"OPE-0004",
			"Une opération doit avoir un compte de recette sur lequel imputer le montant"),	

	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"OPE-0005",
			"Une opération doit avoir un compte de dépense sur lequel imputer le montant"),	
	 */
	OPERATION_COMPTE_DEPENSE_OBLIGATOIRE(
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"OPE-0005",
			"Une opération doit avoir un compte de dépense sur lequel imputer le montant"),

	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"OPE-0006",
			"Une opération doit avoir une sous-categorie sur laquelle imputer le montant"),
	 */
	OPERATION_SOUS_CATEGORIE_OBLIGATOIRE(
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"OPE-0006",
			"Une opération doit avoir une sous-categorie sur laquelle imputer le montant"),

	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"OPE-0007",
			"L'opération '%s' n'a pas pu être créée"),
	 */
	OPERATION_CREATION_PROBLEME(
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"OPE-0007",
			"L'opération '%s' n'a pas pu être créée"),

	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"OPE-0008",
			"L'opération '%s' n'a pas pu être modifiée"),
	 */
	OPERATION_MODIFICATION_PROBLEME(
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"OPE-0008",
			"L'opération '%s' n'a pas pu être modifiée"),

	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"OPE-0009",
			"L'opération '%s' n'a pas pu être supprimée"),
	 */
	OPERATION_SUPPRESSION_PROBLEME(
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"OPE-0009",
			"L'opération '%s' n'a pas pu être supprimée"),
	 
	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"OPE-0010",
			"L'opération numéro '%s' n'a pas de ligne de détail correspondant à la séquence %s"),
	 */
	DETAIL_OPERATION_NON_TROUVE_PAR_SEQUENCE(
			HttpStatus.BAD_REQUEST,
			TypeErreur.FONCTIONNELLE,
			"OPE-0010",
			"L'opération numéro '%s' n'a pas de ligne de détail correspondant à la séquence %s"), 

	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.PROGRAMMATION,
			"OPE-0011",
			"Une opération doit obligatoirement avoir un numero"),
	 */
	OPERATION_NUMERO_OBLIGATOIRE(			
			HttpStatus.BAD_REQUEST,
			TypeErreur.PROGRAMMATION,
			"OPE-0011",
			"Une opération doit obligatoirement avoir un numero"), 

	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.PROGRAMMATION,
			"OPE-0012",
			"Une opération doit obligatoirement avoir un type"),
	 */
	OPERATION_TYPE_OPERATION_OBLIGATOIRE(			
			HttpStatus.BAD_REQUEST,
			TypeErreur.PROGRAMMATION,
			"OPE-0012",
			"Une opération doit obligatoirement avoir un type"),

	/**
			HttpStatus.BAD_REQUEST,
			TypeErreur.PROGRAMMATION,
			"OPE-0013",
			"Aucun type d'opération de code '%s' n'a été trouvé"),
	 */
	TYPE_OPERATION_NON_TROUVE_PAR_CODE(			
			HttpStatus.BAD_REQUEST,
			TypeErreur.PROGRAMMATION,
			"OPE-0013",
			"Aucun type d'opération de code '%s' n'a été trouvé"),
	
	
	;
	private HttpStatus status;

	private TypeErreur type;
	
	private String code;

	private String pattern;

	public HttpStatus getStatus() {
		return status;
	}

	public TypeErreur getType() {
		return type;
	}
	
	public String getCode() {
		return code;
	}

	public String getPattern() {
		return pattern;
	}

	private ControllerErreur(
			HttpStatus status,
			TypeErreur type, 
			String code, 
			String pattern) {

		this.type = type;
		this.status = status;
		this.code = code;
		this.pattern = pattern;
	}

	public String getMessage(Object[] values) {

		return String.format(pattern, values);
	}
}

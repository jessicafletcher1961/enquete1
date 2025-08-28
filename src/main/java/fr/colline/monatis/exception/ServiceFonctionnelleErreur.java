package fr.colline.monatis.exception;

import fr.colline.monatis.typologie.TypeErreur;

public enum ServiceFonctionnelleErreur {

	// ------------------------- //
	// --- DOMAINE REFERENCE --- //
	// ------------------------- //
	/**
			TypeErreur.PROGRAMMATION,
			"REF-0001",
			"Dans le cadre de la création d'une nouvelle référence [type %s], il ne doit pas y avoir d'id (id présent : %s)"),
	 */
	REFERENCE_CREATION_ID_NON_NULL(
			TypeErreur.PROGRAMMATION,
			"REF-0001",
			"Dans le cadre de la création d'une nouvelle référence [type %s], il ne doit pas y avoir d'id (id présent : %s)"),

	/**
			TypeErreur.PROGRAMMATION,
			"REF-0002",
			"Une référence [type %s] doit obligatoirement avoir un nom valide"),
	 */
	REFERENCE_NOM_INVALIDE(
			TypeErreur.PROGRAMMATION,
			"REF-0003",
			"Une référence [type %s] doit obligatoirement avoir un nom valide"),

	/**
			TypeErreur.FONCTIONNELLE,
			"REF-0004",
			"Une autre référence [type %s] utilise déjà le nom '%s'"),
	 */
	REFERENCE_NOM_DEJA_UTILISE(
			TypeErreur.FONCTIONNELLE,
			"REF-0004",
			"Une autre référence [type %s] utilise déjà le nom '%s'"),

	/**
			TypeErreur.PROGRAMMATION,
			"REF-0005",
			"Dans le cadre de la suppression ou de la modification d'une référence [type %s], il doit obligatoirement y avoir un id)"),
	 */
	REFERENCE_SUPPRESSION_MODIFICATION_ID_NULL(
			TypeErreur.PROGRAMMATION,
			"REF-0005",
			"Dans le cadre de la suppression ou de la modification d'une référence [type %s], il doit obligatoirement y avoir un id)"),

	/**
			TypeErreur.PROGRAMMATION,
			"REF-0006",
			"Aucune référence [type %s] correspondant à l'id %s n'est enregistrée en base"),
	 */
	REFERENCE_NON_ENREGISTREE_PAR_ID (
			TypeErreur.FONCTIONNELLE,
			"REF-0006",
			"Aucune référence [type %s] correspondant à l'id %s n'est enregistrée en base"),

	/**
			TypeErreur.PROGRAMMATION,
			"REF-0007",
			"La référence indiquée (id %s) est déjà enregistrée en base [type %s]"),
	 */
	REFERENCE_DEJA_ENREGISTREE_PAR_ID(			
			TypeErreur.PROGRAMMATION,
			"REF-0007",
			"La référence indiquée (id %s) est déjà enregistrée en base [type %s]"),

	// --- DOMAINE BANQUE

	/**
			TypeErreur.FONCTIONNELLE,
			"BNQ-0001",
			"La banque %s ne peut être supprimée car elle est encore reliée à %s comptes internes"),
	 */
	BANQUE_SUPPRESSION_AVEC_COMPTES_INTERNES(
			TypeErreur.FONCTIONNELLE,
			"BNQ-0001",
			"La banque '%s' ne peut être supprimée car elle est encore reliée à %s comptes internes"),

	/**
			TypeErreur.FONCTIONNELLE,
			"BNQ-0002",
			"Aucune banque correspondant à l'id %s n'est enregistrée en base"),
	 */
	BANQUE_NON_ENREGISTREE_PAR_ID(
			TypeErreur.FONCTIONNELLE,
			"BNQ-0002",
			"Aucune banque correspondant à l'id %s n'est enregistrée en base"),

	/**
	BANQUE_NON_ENREGISTREE_PAR_NOM(
			TypeErreur.FONCTIONNELLE,
			"BNQ-0003",
			"Aucune banque correspondant au nom '%s' n'est enregistrée en base"),
	 */
	BANQUE_NON_ENREGISTREE_PAR_NOM(
			TypeErreur.FONCTIONNELLE,
			"BNQ-0003",
			"Aucune banque correspondant au nom '%s' n'est enregistrée en base"),
	
	
	// --- DOMAINE BENEFICIARE

	/**
			TypeErreur.FONCTIONNELLE,
			"BEN-0001",
			"Aucun bénéficiaire correspondant à l'id %s n'est enregistré en base"),
	 */
	BENEFICIAIRE_NON_ENREGISTRE_PAR_ID(
			TypeErreur.FONCTIONNELLE,
			"BEN-0001",
			"Aucun bénéficiaire correspondant à l'id %s n'est enregistré en base"),

	BENEFICIAIRE_SUPPRESSION_AVEC_DETAIL_OPERATION(
			TypeErreur.FONCTIONNELLE,
			"BEN-0002",
			"Le bénéficiaire '%s' ne peut être supprimé car il est encore relié à %s lignes de détail d'opérations"),
	 
	// --- DOMAINE CATEGORIE

	/**
			TypeErreur.FONCTIONNELLE,
			"CAT-0001",
			"La catégorie '%s' ne peut être supprimée car elle est encore reliée à %s sous-categories"),
	 */
	CATEGORIE_SUPPRESSION_AVEC_SOUS_CATEGORIES(
			TypeErreur.FONCTIONNELLE,
			"CAT-0001",
			"La catégorie '%s' ne peut être supprimée car elle est encore reliée à %s sous-categories"),

	/**
			TypeErreur.FONCTIONNELLE,
			"CAT-0002",
			"Aucune catégorie correspondant à l'id %s n'est enregistrée en base"),
	 */
	CATEGORIE_NON_ENREGISTREE_PAR_ID(
			TypeErreur.FONCTIONNELLE,
			"CAT-0002",
			"Aucune catégorie correspondant à l'id %s n'est enregistrée en base"),
	
	// --- DOMAINE SOUS-CATEGORIE

	/**
			TypeErreur.FONCTIONNELLE,
			"SSC-0001",
			"Aucune sous-categorie d'id %s n'est enregistrée en base"),
	 */
	SOUS_CATEGORIE_NON_ENREGISTREE_PAR_ID(
			TypeErreur.FONCTIONNELLE,
			"SSC-0001",
			"Aucune sous-categorie d'id %s n'est enregistrée en base"),

	/**
			TypeErreur.FONCTIONNELLE,
			"SSC-0002",
			"Une sous-catégorie est obligatoirement associée à une catégorie"),
	 */
	SOUS_CATEGORIE_CATEGORIE_REQUISE(
			TypeErreur.FONCTIONNELLE,
			"SSC-0002",
			"Une sous-catégorie est obligatoirement associée à une catégorie"),
	
	/**
			TypeErreur.FONCTIONNELLE,
			"SSC-0003",
			"La sous-catégorie '%s' ne peut être supprimée car elle est encore reliée à %s lignes de détail d'opérations"),
	 */
	SOUS_CATEGORIE_SUPPRESSION_AVEC_DETAIL_OPERATION(
			TypeErreur.FONCTIONNELLE,
			"SSC-0003",
			"La sous-catégorie '%s' ne peut être supprimée car elle est encore reliée à %s lignes de détail d'opérations"),

	// --- DOMAINE TITULAIRE

	/**
			TypeErreur.FONCTIONNELLE,
			"TIT-0001",
			"Le titulaire '%s' ne peut être supprimé car il est encore relié à %s comptes internes"),
	 */
	TITULAIRE_SUPPRESSION_AVEC_COMPTES_INTERNES(
			TypeErreur.FONCTIONNELLE,
			"TIT-0001",
			"Le titulaire '%s' ne peut être supprimé car il est encore relié à %s comptes internes"),

	/**
			TypeErreur.PROGRAMMATION,
			"TIT-0002",
			"Aucun titulaire d'id %s n'est enregistré en base"),
	 */
	TITULAIRE_NON_ENREGISTRE_PAR_ID(
			TypeErreur.PROGRAMMATION,
			"TIT-0002",
			"Aucun titulaire correspondant à l'id %s n'est enregistré en base"),

	/**
			TypeErreur.PROGRAMMATION,
			"TIT-0003",
			"Aucun titulaire correspondant au nom '%s' n'est enregistré en base"),
	 */
	TITULAIRE_NON_ENREGISTRE_PAR_NOM(
			TypeErreur.PROGRAMMATION,
			"TIT-0003",
			"Aucun titulaire correspondant au nom '%s' n'est enregistré en base"),
	
	
	// ----------------------- //
	// --- DOMAINE COMPTES --- //
	// ----------------------- //

	/**
			TypeErreur.FONCTIONNELLE,
			"CPT-0001",
			"Un compte [type %s] doit obligatoirement avoir un identifiant valide"),
	 */
	COMPTE_IDENTIFIANT_INVALIDE(
			TypeErreur.FONCTIONNELLE,
			"CPT-0001",
			"Un compte [type %s] doit obligatoirement avoir un identifiant valide"),
	
	/**
			TypeErreur.FONCTIONNELLE,
			"CPT-0002",
			"Un autre compte [type %s] utilise déjà l'identifiant '%s'"),
	 */
	COMPTE_IDENTIFIANT_DEJA_UTILISE(
			TypeErreur.FONCTIONNELLE,
			"CPT-0002",
			"Un autre compte [type %s] utilise déjà l'identifiant '%s'"),

	/**
			TypeErreur.PROGRAMMATION,
			"CPT-0003",
	 		"Dans le cadre de la création d'un nouveau compte [type %s], il ne doit pas y avoir d'id (id présent : %s)"),
	 */
	COMPTE_ID_NON_NULL(
			TypeErreur.PROGRAMMATION,
			"CPT-0003",
	 		"Dans le cadre de la création d'un nouveau compte [type %s], il ne doit pas y avoir d'id (id présent : %s)"),

	/**
			TypeErreur.PROGRAMMATION,
			"CPT-0004",
			"Aucun compte [type %s] correspondant à l'id %s n'est enregistré en base"),
	 */
	COMPTE_NON_ENREGISTRE_PAR_ID(
			TypeErreur.PROGRAMMATION,
			"CPT-0004",
			"Aucun compte [type %s] correspondant à l'id %s n'est enregistré en base"),

	/**
			TypeErreur.PROGRAMMATION,
			"CPT-0005",
			"Le compte indiqué (id %s) est déjà enregistrée en base [type %s]"),
	 */
	COMPTE_DEJA_ENREGISTRE_PAR_ID(
			TypeErreur.PROGRAMMATION,
			"CPT-0005",
			"Le compte indiqué (id %s) est déjà enregistrée en base [type %s]"),

	/**
			TypeErreur.FONCTIONNELLE,
			"CPT-0007",
			"Le compte '%s' ne peut être supprimé car il est encore relié à %s opérations"),
	 */
	COMPTE_SUPPRESSION_AVEC_OPERATION(
			TypeErreur.FONCTIONNELLE,
			"CPT-0007",
			"Le compte '%s' ne peut être supprimé car il est encore relié à %s opérations"),


// --- SOUS-DOMAINE COMPTE INTERNE
	
	/**
			TypeErreur.FONCTIONNELLE,
			"INT-0001",
			"Le compte interne doit obligatoirement être associé à un type de compte (compte en euros, livret, assurance-vie..."), 
	 */
	COMPTE_INTERNE_TYPE_COMPTE_REQUIS(
			TypeErreur.FONCTIONNELLE,
			"INT-0001",
			"Le compte interne doit obligatoirement être associé à un type de compte (compte courant, livret, assurance-vie...)"), 
	
	/**
			TypeErreur.FONCTIONNELLE,
			"INT-0002",
			"Le compte interne doit avoir au moins un titulaire"),
	 */
	COMPTE_INTERNE_AU_MOINS_UN_TITULAIRE_REQUIS(
			TypeErreur.FONCTIONNELLE,
			"INT-0002",
			"Le compte interne doit avoir au moins un titulaire"),

	/**
			TypeErreur.FONCTIONNELLE,
			"INT-0003",
			"Le compte interne doit avoir une banque"),
	 */
	COMPTE_INTERNE_BANQUE_REQUISE(
			TypeErreur.PROGRAMMATION,
			"INT-0003",
			"Le compte interne doit avoir une banque"),
	
	/**
			TypeErreur.PROGRAMMATION,
			"INT-0004",
			"Le compte interne doit avoir un tableau de titulaires"),
	 */
	COMPTE_INTERNE_TABLEAU_TITULAIRE_REQUIS(
			TypeErreur.PROGRAMMATION,
			"INT-0004",
			"Le compte interne doit avoir un tableau de titulaires"),

	/**
			TypeErreur.PROGRAMMATION,
			"INT-0005",
			"Le tableau de titulaires contient un titulaire null"),
	 */
	COMPTE_INTERNE_TABLEAU_TITULAIRE_CONTIENT_UN_TITULAIRE_NULL(
			TypeErreur.PROGRAMMATION,
			"INT-0005",
			"Le tableau de titulaires contient un titulaire null"),
	
	// -------------------------- //
	// --- DOMAINE OPERATIONS --- //
	// -------------------------- //

	/**
			TypeErreur.FONCTIONNELLE,
			"OPE-0001",
			"Une autre opération utilise déjà le numéro '%s'"),
	 */
	OPERATION_NUMERO_DEJA_UTILISE(
			TypeErreur.FONCTIONNELLE,
			"OPE-0001",
			"Une autre opération utilise déjà le numéro '%s'"),

	/**
			TypeErreur.PROGRAMMATION,
			"OPE-0002",
			"Aucune opération correspondant à l'id %s n'est enregistrée en base"),
	 */
	OPERATION_NON_ENREGISTREE_PAR_ID(
			TypeErreur.PROGRAMMATION,
			"OPE-0002",
			"Aucune opération correspondant à l'id %s n'est enregistrée en base"),
	
	/**
			TypeErreur.PROGRAMMATION,
			"OPE-0003",
			"Une opération doit obligatoirement avoir un numéro valide"),
	 */
	OPERATION_NUMERO_INVALIDE(
			TypeErreur.PROGRAMMATION,
			"OPE-0003",
			"Une opération doit obligatoirement avoir un numéro valide"),

	/**
			TypeErreur.PROGRAMMATION,
			"OPE-0004",
			"L'opération indiquée (id %s) est déjà enregistrée en base"),
	 */
	OPERATION_DEJA_ENREGISTREE_PAR_ID(
			TypeErreur.PROGRAMMATION,
			"OPE-0004",
			"L'opération indiquée (id %s) est déjà enregistrée en base"),

	/**
			TypeErreur.FONCTIONNELLE,
			"OPE-0005",
			"L'opération doit avoir au moins une ligne de détail"),	
	 */
	OPERATION_AU_MOINS_UN_DETAIL_OPERATION_REQUIS(
			TypeErreur.FONCTIONNELLE,
			"OPE-0005",
			"L'opération doit avoir au moins une ligne de détail"),	
	 
	/**
			TypeErreur.FONCTIONNELLE,
			"OPE-0006",
			"L'opération contient au moins deux lignes de détail avec le même numéro de séquence (%s)"),
	 */
	OPERATION_LISTE_DETAIL_NUMERO_SEQUENCE_DUPLIQUEE(
			TypeErreur.FONCTIONNELLE,
			"OPE-0006",
			"L'opération contient au moins deux lignes de détail avec le même numéro de séquence (%s)"),

	/**
	 TypeErreur.FONCTIONNELLE,
	 "OPE-0007",
	 "Une opération est obligatoirement associée à un compte de dépense"),
	 */
	OPERATION_SANS_COMPTE_DEPENSE(
			TypeErreur.FONCTIONNELLE,
			"OPE-0007",
			"Une opération est obligatoirement associée à un compte de dépense"),

	/**
			TypeErreur.FONCTIONNELLE,
			"OPE-0008",
			"Une opération est obligatoirement associée à un compte de recette"),
	 */
	OPERATION_SANS_COMPTE_RECETTE(
			TypeErreur.FONCTIONNELLE,
			"OPE-0008",
			"Une opération est obligatoirement associée à un compte de recette"),

	/**
			TypeErreur.FONCTIONNELLE,
			"DOP-0007",
			"Le montant de l'opération (%s) ne correspond pas à la somme des montants de ses lignes de détail (%s)"),
	 */
	OPERATION_LISTE_DETAIL_SOMME_MONTANTS_ERRONEE(			
			TypeErreur.FONCTIONNELLE,
			"DOP-0007",
			"Le montant de l'opération (%s) ne correspond pas à la somme des montants de ses lignes de détail (%s)"),

	 
	// --- SOUS-DOMAINE DETAILS OPERATIONS

	/**
			TypeErreur.PROGRAMMATION,
			"DOP-0001",
			"Dans le cadre de la création d'une nouvelle ligne de détail d'opération, il ne doit pas y avoir d'id (id présent : %s)"),
	 */
	DETAIL_OPERATION_CREATION_ID_NON_NULL(
			TypeErreur.PROGRAMMATION,
			"DOP-0001",
			"Dans le cadre de la création d'une nouvelle ligne de détail d'opération, il ne doit pas y avoir d'id (id présent : %s)"),

	/**
			TypeErreur.FONCTIONNELLE,
			"DOP-0002",
			"La ligne de détail ne peut être déplacée de l'opération d'id %s à l'opération d'id %s"),
	 */
	DETAIL_OPERATION_CHANGEMENT_OPERATION_IMPOSSIBLE(
			TypeErreur.FONCTIONNELLE,
			"DOP-0002",
			"La ligne de détail ne peut être déplacée de l'opération d'id %s à l'opération d'id %s"),
	
	/**
			TypeErreur.PROGRAMMATION,
			"DOP-0003",
			"Aucune ligne de détail d'id %s n'est enregistrée en base"),
	 */
	DETAIL_OPERATION_NON_ENREGISTREE_PAR_ID(
			TypeErreur.PROGRAMMATION,
			"DOP-0003",
			"Aucune ligne de détail d'id %s n'est enregistrée en base"),

	/**
			TypeErreur.FONCTIONNELLE,
			"DOP-0004",
			"La ligne de détail doit obligatoirement être placée dans une sous-catégorie existante"),
	 */
	DETAIL_OPERATION_SOUS_CATEGORIE_REQUISE(			
			TypeErreur.FONCTIONNELLE,
			"DOP-0004",
			"La ligne de détail doit obligatoirement être placée dans une sous-catégorie existante"),
	
	
	;

	private TypeErreur type;
	
	private String code;

	private String pattern;

	protected TypeErreur getType() {
		return type;
	}

	protected String getCode() {
		return code;
	}

	protected String getPattern() {
		return pattern;
	}

	private ServiceFonctionnelleErreur(TypeErreur typeErreur, String code, String pattern) {
		this.type = typeErreur;
		this.code = code;
		this.pattern = pattern;
	}

	public String getMessage(Object...values) {

		return String.format(pattern, values);
	}
}

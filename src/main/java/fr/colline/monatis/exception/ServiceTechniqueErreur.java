package fr.colline.monatis.exception;

public enum ServiceTechniqueErreur {

	/**
			TypeErreur.TECHNIQUE,
			"TECH-0001",
			"Un problème technique est survenu lors de la recherche de la référence [%s] d'id %s"),
	 */
	TECH_RECHERCHE_REFERENCE_PAR_ID(
			TypeErreur.TECHNIQUE,
			"TECH-0001",
			"Un problème technique est survenu lors de la recherche de la référence [%s] d'id %s"),

	/**
			TypeErreur.TECHNIQUE,
			"TECH-0002",
			"Un problème technique est survenu lors de la recherche de la référence [%s] de nom '%s'"),
	 */
	TECH_RECHERCHE_REFERENCE_PAR_NOM(
			TypeErreur.TECHNIQUE,
			"TECH-0002",
			"Un problème technique est survenu lors de la recherche de la référence [%s] de nom '%s'"),

	/**
			TypeErreur.TECHNIQUE,
			"TECH-0003",
			"Un problème technique est survenu lors de la recherche de toutes les références [%s]"),
	 */
	TECH_RECHERCHE_REFERENCE_TOUS(
			TypeErreur.TECHNIQUE,
			"TECH-0003",
			"Un problème technique est survenu lors de la recherche de toutes les références [%s]"),
	
	TECH_RECHERCHE_COMPTE_PAR_ID(
			TypeErreur.TECHNIQUE,
			"TECH-0004",
			"Un problème technique est survenu lors de la recherche du compte [%s] d'id [%s]"),
	TECH_RECHERCHE_COMPTE_PAR_IDENTIFIANT(
			TypeErreur.TECHNIQUE,
			"TECH-0005",
			"Un problème technique est survenu lors de la recherche du compte [%s] d'identifiant [%s]"),
	/**
			"TECH-0006",
			"Un problème technique est survenu lors de la recherche de tous les comptes [%s]"),
	 */
	TECH_RECHERCHE_COMPTE_TOUS(
			TypeErreur.TECHNIQUE,
			"TECH-0006",
			"Un problème technique est survenu lors de la recherche de tous les comptes [%s]"),
	
	/**
			TypeErreur.TECHNIQUE,
			"TECH-00XX",
			"Un problème technique est survenu lors de la recherche de l'opération d'id %s"),
	 */
	TECH_RECHERCHE_OPERATION_PAR_ID(
			TypeErreur.TECHNIQUE,
			"TECH-00XX",
			"Un problème technique est survenu lors de la recherche de l'opération d'id %s"),

	/**
			TypeErreur.TECHNIQUE,
			"TECH-00XX",
			"Un problème technique est survenu lors de la recherche de la ligne de détail %s de l'operation d'id %s"),
	 */
	TECH_DETAIL_OPERATION_RECHERCHE_PAR_OPERATION_ID_ET_SEQUENCE(
			TypeErreur.TECHNIQUE,
			"TECH-00XX",
			"Un problème technique est survenu lors de la recherche de la ligne de détail %s de l'operation d'id %s"),

	/**
			TypeErreur.TECHNIQUE,
			"TECH-00XX",
			"Un problème technique est survenu lors de la recherche des lignes de détail de l'opérations d'id %s"),
	 */
	TECH_DETAIL_OPERATION_RECHERCHE_TOUS(
			TypeErreur.TECHNIQUE,
			"TECH-00XX",
			"Un problème technique est survenu lors de la recherche des lignes de détail de l'opérations d'id %s"),
	
	/**
			TypeErreur.TECHNIQUE,
			"TECH-00XX",
			"Un problème technique est survenu lors de la suppression des lignes de détail de l'opérations d'id %s"),
	 */
	TECH_DETAIL_OPERATION_SUPPRESSION_TOUS(
			TypeErreur.TECHNIQUE,
			"TECH-00XX",
			"Un problème technique est survenu lors de la suppression des lignes de détail de l'opérations d'id %s"),
	
	/**
			TypeErreur.TECHNIQUE,
			"TECH-00XX",
			"Un problème technique est survenu lors de la recherche de l'opération de numéro '%s'"),
	 */
	TECH_RECHERCHE_OPERATION_PAR_NUMERO(
			TypeErreur.TECHNIQUE,
			"TECH-00XX",
			"Un problème technique est survenu lors de la recherche de l'opération de numéro '%s'"),

	/**
			TypeErreur.TECHNIQUE,
			"TECH-00XX",
			"Un problème technique est survenu lors de la recherche de toutes les opérations"), 
	 */
	TECH_RECHERCHE_OPERATION_TOUS(
			TypeErreur.TECHNIQUE,
			"TECH-00XX",
			"Un problème technique est survenu lors de la recherche de toutes les opérations"), 

	TECH_EXISTANCE_COMPTE_PAR_ID(
			TypeErreur.TECHNIQUE,
			"TECH-0007",
			"Un problème technique est survenu lors de la vérification de l'existance de l'id %s dans les comptes [%s]"),

	/**
			TypeErreur.TECHNIQUE,
			"TECH-0008",
			"Un problème technique est survenu lors de la vérification de l'existance de l'identifiant '%s' dans les comptes [%s]"),
	 */
	TECH_EXISTANCE_COMPTE_PAR_IDENTIFIANT(
			TypeErreur.TECHNIQUE,
			"TECH-0008",
			"Un problème technique est survenu lors de la vérification de l'existance de l'identifiant '%s' dans les comptes [%s]"),
	
	/**
			TypeErreur.TECHNIQUE,
			"TECH-0009",
			"Un problème technique est survenu lors de la vérification de l'existance de l'id %s dans les références [%s]"),
	 */
	TECH_EXISTANCE_REFERENCE_PAR_ID(
			TypeErreur.TECHNIQUE,
			"TECH-0009",
			"Un problème technique est survenu lors de la vérification de l'existance de l'id %s dans les références [%s]"),

	/**
			TypeErreur.TECHNIQUE,
			"TECH-0010",
			"Un problème technique est survenu lors de la vérification de l'existance du nom '%s' dans les références [%s]"),
	 */
	TECH_EXISTANCE_REFERENCE_PAR_NOM(
			TypeErreur.TECHNIQUE,
			"TECH-0010",
			"Un problème technique est survenu lors de la vérification de l'existance du nom '%s' dans les références [%s]"),
	
	/**
			TypeErreur.TECHNIQUE,
			"TECH-00XX",
			"Un problème technique est survenu lors de la vérification de l'existance de l'id %s dans les opérations"),
	 */
	TECH_EXISTANCE_OPERATION_PAR_ID(
			TypeErreur.TECHNIQUE,
			"TECH-00XX",
			"Un problème technique est survenu lors de la vérification de l'existance de l'id %s dans les opérations"),

	/**
			TypeErreur.TECHNIQUE,
			"TECH-00XX",
			"Un problème technique est survenu lors de la vérification de l'existance du numero '%s' dans les opérations"),
	 */
	TECH_EXISTANCE_OPERATION_PAR_NUMERO(
			TypeErreur.TECHNIQUE,
			"TECH-00XX",
			"Un problème technique est survenu lors de la vérification de l'existance du numero '%s' dans les opérations"),
	
	/**
			TypeErreur.TECHNIQUE,
			"TECH-00XX",
			"Un problème technique est survenu lors de la vérification de l'existance de la ligne de détail %s de l'operation d'id %s"),
	 */
	TECH_DETAIL_OPERATION_EXISTANCE_PAR_OPERATION_ID_ET_SEQUENCE(
			TypeErreur.TECHNIQUE,
			"TECH-00XX",
			"Un problème technique est survenu lors de la vérification de l'existance de la ligne de détail %s de l'operation d'id %s"),
	
	/**
			TypeErreur.TECHNIQUE,
			"TECH-0011",
			"Un problème technique est survenu lors de l'enregistrement de la référence [%s] de nom '%s"'),
	 */
	TECH_ENREGISTREMENT_REFERENCE(
			TypeErreur.TECHNIQUE,
			"TECH-0011",
			"Un problème technique est survenu lors de l'enregistrement de la référence [%s] de nom '%s'"),
	TECH_ENREGISTREMENT_COMPTE(
			TypeErreur.TECHNIQUE,
			"TECH-0012",
			"Un problème technique est survenu lors de l'enregistrement du compte [%s] d'identifiant '%s'"),
	
	/**
			TypeErreur.TECHNIQUE,
			"TECH-00XX",
			"Un problème technique est survenu lors de l'enregistrement de l'opération de numero '%s'"),
	 */
	TECH_ENREGISTREMENT_OPERATION(
			TypeErreur.TECHNIQUE,
			"TECH-00XX",
			"Un problème technique est survenu lors de l'enregistrement de l'opération de numero '%s'"),

	/**
			TypeErreur.TECHNIQUE,
			"TECH-00XX",
			"Un problème technique est survenu lors de l'enregistrement de la ligne de détail numéro %s de l'opération de numéro '%s'"), 
	 */
	TECH_DETAIL_OPERATION_ENREGISTREMENT(
			TypeErreur.TECHNIQUE,
			"TECH-00XX",
			"Un problème technique est survenu lors de l'enregistrement de la ligne de détail numéro %s de l'opération de numéro '%s'"), 

	/**
			"TECH-00XX",
			"Un problème technique est survenu lors de la modifications du compte [s] d'identifiant %s"),
	 */
	TECH_MODIFICATION_COMPTE(
			TypeErreur.TECHNIQUE,
			"TECH-00XX",
			"Un problème technique est survenu lors de la modifications du compte [s] d'identifiant %s"),

	/**
			TypeErreur.TECHNIQUE,
			"TECH-0013",
			"Un problème technique est survenu lors de la suppression de la référence [%s] de nom '%s'"),
	 */
	TECH_SUPPRESSION_REFERENCE(
			TypeErreur.TECHNIQUE,
			"TECH-0013",
			"Un problème technique est survenu lors de la suppression de la référence [%s] de nom '%s'"),
	TECH_SUPPRESSION_COMPTE(
			TypeErreur.TECHNIQUE,
			"TECH-0014",
			"Un problème technique est survenu lors de la suppression du compte [%s] d'identifiant %s"),
	
	/**
			TypeErreur.TECHNIQUE,
			"TECH-00XX",
			"Un problème technique est survenu lors de la suppression de l'opération de numéro '%s'"),
	 */
	TECH_SUPPRESSION_OPERATION(
			TypeErreur.TECHNIQUE,
			"TECH-00XX",
			"Un problème technique est survenu lors de la suppression de l'opération de numéro '%s'"),
	
	/**
			TypeErreur.TECHNIQUE,
			"TECH-0015",
			"Un problème technique est survenu lors de la suppression de toutes les références [%s]"),
	 */
	TECH_SUPPRESSION_REFERENCE_TOUS(
			TypeErreur.TECHNIQUE,
			"TECH-0015",
			"Un problème technique est survenu lors de la suppression de toutes les références [%s]"),
	TECH_SUPPRESSION_COMPTE_TOUS(
			TypeErreur.TECHNIQUE,
			"TECH-0016",
			"Un problème technique est survenu lors de la suppression de tous les comptes [%s]"),
	
	/**
			TypeErreur.TECHNIQUE,
			"TECH-00XX",
			"Un problème technique est survenu lors de la suppression de toutes les opérations"),
	 */
	TECH_SUPPRESSION_OPERATION_TOUS(
			TypeErreur.TECHNIQUE,
			"TECH-00XX",
			"Un problème technique est survenu lors de la suppression de toutes les opérations"),

	/**
			TypeErreur.TECHNIQUE,
			"TECH-00XX",
			"Un problème technique est survenu lors de la suppression de la ligne de détail d'id %s"), 
	 */
	TECH_DETAIL_OPERATION_SUPPRESSION(
			TypeErreur.TECHNIQUE,
			"TECH-00XX",
			"Un problème technique est survenu lors de la suppression de la ligne de détail d'id %s"), 
	
	TECH_RECHERCHE_NOMBRE_OPERATION_PAR_COMPTE_ID(
			TypeErreur.TECHNIQUE,
			"TECH-00XX",
			"Un problème technique est survenu lors de la recherche du nombre d'opération concernées par le compte d'id %s"),

	/**
			"TECH-00XX",
			"Un problème technique est survenu lors de la recherche du nombre de lignes de détail d'opération concernées par la sous-catégorie d'id %s"),
	 */
	TECH_RECHERCHE_NOMBRE_DETAIL_OPERATION_PAR_SOUS_CATEGORIE_ID(
			TypeErreur.TECHNIQUE,
			"TECH-00XX",
			"Un problème technique est survenu lors de la recherche du nombre de lignes de détail d'opération concernées par la sous-catégorie d'id %s"),

	/**
			TypeErreur.TECHNIQUE,
			"TECH-00XX",
			"Un problème technique est survenu lors de la recherche du nombre de lignes de détail d'opération concernées par le bénéficiaire d'id %s"),
	 */
	TECH_RECHERCHE_NOMBRE_DETAIL_OPERATION_PAR_BENEFICIAIRE_ID(
			TypeErreur.TECHNIQUE,
			"TECH-00XX",
			"Un problème technique est survenu lors de la recherche du nombre de lignes de détail d'opération concernées par le bénéficiaire d'id %s"),
	
	/**
			"PROG-0001",
			"La recherche par nom ne s'applique qu'aux références spécialisées, pas à la référence générique %s"),
	 */
	PROG_RECHERCHE_PAR_NOM_IMPOSSIBLE(
			TypeErreur.PROGRAMMATION,
			"PROG-0001",
			"La recherche par nom ne s'applique qu'aux références spécialisées, pas à la référence générique %s"),

	/**
			"PROG-0002",
			"La recherche par identifiant ne s'applique qu'aux comptes spécialisées, pas aux comptes génériques %s"),
	 */
	PROG_RECHERCHE_PAR_IDENTIFIANT_IMPOSSIBLE(
			TypeErreur.PROGRAMMATION,
			"PROG-0002",
			"La recherche par identifiant ne s'applique qu'aux comptes spécialisées, pas aux comptes génériques %s"), 
	
	/**
			TypeErreur.PROGRAMMATION,
			"PROG-0003",
			"La recherche de l'ancienne catégorie de la sous-catégorie %s a échoué"),
	 */
	TECH_GESTION_BIDIRECTIONNALITE_SOUS_CATEGORIE_CATEGORIE(
			TypeErreur.PROGRAMMATION,
			"PROG-0003",
			"La recherche de l'ancienne catégorie de la sous-catégorie %s a échoué"),

	/**
			TypeErreur.PROGRAMMATION,
			"PROG-0003",
			"La recherche de l'ancienne banque du compte interne %s a échoué"),
	 */
	TECH_GESTION_BIDIRECTIONNALITE_COMPTE_INTERNE_BANQUE(
			TypeErreur.PROGRAMMATION,
			"PROG-0003",
			"La recherche de l'ancienne banque du compte interne %s a échoué"),
	
	/**
			TypeErreur.PROGRAMMATION,
			"PROG-0004",
			"La recherche des anciens titulaires du compte interne %s a échoué"),
	 */
	TECH_GESTION_BIDIRECTIONNALITE_COMPTE_INTERNE_TITULAIRES(
			TypeErreur.PROGRAMMATION,
			"PROG-0004",
			"La recherche des anciens titulaires du compte interne %s a échoué"),

	 
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

	private ServiceTechniqueErreur(TypeErreur type, String code, String pattern) {

		this.type = type;
		this.code = code;
		this.pattern = pattern;
	}

	public String getMessage(Object...values) {

		return String.format(pattern, values);
	}
}

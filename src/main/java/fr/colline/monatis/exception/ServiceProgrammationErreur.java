package fr.colline.monatis.exception;

public enum ServiceProgrammationErreur {

	/**
			TypeErreur.PROGRAMMATION,
			"PARAM-0001",
			"L'id passé en paramètre de cette méthode est obligatoire [%s]"),
	 */
	ID_NULL(
			TypeErreur.PROGRAMMATION,
			"PARAM-0001",
			"L'id passé en paramètre de cette méthode est obligatoire [%s]"),

	/**
			TypeErreur.PROGRAMMATION,
			"PARAM-0002",
			"Le numéro passé en paramètre de cette méthode est obligatoire"),
	 */
	NUMERO_NULL(
			TypeErreur.PROGRAMMATION,
			"PARAM-0002",
			"Le numéro passé en paramètre de cette méthode est obligatoire"),

	/**
			TypeErreur.PROGRAMMATION,
			"PARAM-0003",
			"Le numero passé en paramètre de cette méthode est obligatoire et ne doit pas être vide"),
	
	 */
	NUMERO_VIDE(
			TypeErreur.PROGRAMMATION,
			"PARAM-0003",
			"Le numero passé en paramètre de cette méthode est obligatoire et ne doit pas être vide"),
	
	/**
			TypeErreur.PROGRAMMATION,
			"PARAM-00XX",
			"Le nom passé en paramètre de cette méthode est obligatoire [%s]"),
	 */
	NOM_NULL(
			TypeErreur.PROGRAMMATION,
			"PARAM-00XX",
			"Le nom passé en paramètre de cette méthode est obligatoire [%s]"),

	/**
			"PARAM-0003",
			"L'identifiant passé en paramètre de cette méthode est obligatoire [%s]"),
	 */
	IDENTIFIANT_NULL(
			TypeErreur.PROGRAMMATION,
			"PARAM-0003",
			"L'identifiant passé en paramètre de cette méthode est obligatoire [%s]"),


	/**
			TypeErreur.PROGRAMMATION,
			"PARAM-0004",
			"Le nom passé en paramètre de cette méthode est obligatoire et ne doit pas être vide [%s]"),
	 */
	NOM_VIDE(
			TypeErreur.PROGRAMMATION,
			"PARAM-0004",
			"Le nom passé en paramètre de cette méthode est obligatoire et ne doit pas être vide [%s]"),
	/**
			TypeErreur.PROGRAMMATION,
			"RARAM-0005",
			"La ligne de détail passée en paramètre de cette méthode est obligatoire"),
	 */
	DETAIL_OPERATION_NULL	(
			TypeErreur.PROGRAMMATION,
			"RARAM-0005",
			"La ligne de détail passée en paramètre de cette méthode est obligatoire"),

	//	/**
	//	 * La ligne de détail passée en paramètre de cette méthode ne doit avoir un id valide<br>
	//	 * <i>Pas de values</i>
	//	 */
	//	DETAIL_OPERATION_CHAMP_ID_NON_NULL(
	//			"EP-0004",
	//			"La ligne de détail passée en paramètre de cette méthode ne doit pas avoir d'id"),
	//

	/**
			TypeErreur.PROGRAMMATION,
			"PARAM-0006",
			"L'opération passée en paramètre de cette méthode est obligatoire"),
	 */
	OPERATION_NULL(
			TypeErreur.PROGRAMMATION,
			"PARAM-0006",
			"L'opération passée en paramètre de cette méthode est obligatoire"),

	/**
	 * La sous-catégorie passée en paramètre de cette méthode est obligatoire<br>
	 */
	SOUS_CATEGORIE_NULL(
			TypeErreur.PROGRAMMATION,
			"PARAM-0007",
			"La sous-catégorie passée en paramètre de cette méthode est obligatoire"),

	/**
	 * Le tableau de bénéficiaires passé en paramètre de cette méthode est obligatoire<br>
	 */
	TABLEAU_BENEFICIAIRE_NULL(
			TypeErreur.PROGRAMMATION,
			"PARAM-0008",
			"Le tableau de bénéficiaires passé en paramètre de cette méthode est obligatoire"), 

	TABLEAU_TITULAIRES_NULL(
			TypeErreur.PROGRAMMATION,
			"PARAM-XX",
			"Le tableau de titulaires passé en paramètre de cette méthode est obligatoire [%s]"),
	//	/**
	//	 * L'opération passée en paramètre de cette méthode doit avoir un numéro<br>
	//	 * <i>Pas de values</i>
	//	 */
	//	OPERATION_CHAMP_NUMERO_VIDE(
	//			"EP-0008",
	//			"L'opération passée en paramètre de cette méthode doit avoir un numéro"),
	//
	//	/**
	//	 * L'opération passée en paramètre de cette méthode ne doit pas avoir d'id<br>
	//	 * <i>Pas de values</i>
	//	 */
	//	OPERATION_CHAMP_ID_NON_NULL(
	//			"EP-0009",
	//			"L'opération passée en paramètre de cette méthode ne doit pas avoir d'id"),
	//
	/**
	 * Le compte à débiter passé en paramètre de cette méthode est obligatoire<br>
	 * value1 : contexte (T.class.simpleName() ou autre String comme "Operation" par exemple...)
	 */
	COMPTE_DEPENSE_NULL(
			TypeErreur.PROGRAMMATION,
			"PARAM-0009",
			"Le compte à débiter passé en paramètre de cette méthode est obligatoire"),

	/**
	 * Le compte à créditer passé en paramètre de cette méthode est obligatoire<br>
	 * value1 : contexte (T.class.simpleName() ou autre String comme "Operation" par exemple...)
	 */
	COMPTE_RECETTE_NULL(
			TypeErreur.PROGRAMMATION,
			"PARAM-0010",
			"Le compte à créditer passé en paramètre de cette méthode est obligatoire"), 

	/**
	 * Le compte interne passé en paramètre de cette méthode est obligatoire<br>
	 * value1 : contexte (T.class.simpleName() ou autre String comme "Operation" par exemple...)
	 */
	COMPTE_INTERNE_NULL(
			TypeErreur.PROGRAMMATION,
			"PARAM-0011",
			"Le compte interne passé en paramètre de cette méthode est obligatoire [%s]"),

	//	/**
	//	 * Le compte interne passé en paramètre de cette méthode doit avoir un id<br>
	//	 * <i>Pas de values</i>
	//	 */
	//	COMPTE_INTERNE_CHAMP_ID_NULL(
	//			"EP-0013",
	//			"Le compte interne passé en paramètre de cette méthode doit avoir un id"),
	//
	/**
			TypeErreur.PROGRAMMATION,
			"PARAM-0012",
			"Le titulaire passé en paramètre de cette méthode est obligatoire"),
	 */
	TITULAIRE_NULL(
			TypeErreur.PROGRAMMATION,
			"PARAM-0012",
			"Le titulaire passé en paramètre de cette méthode est obligatoire"),

	/**
			TypeErreur.PROGRAMMATION,
			"PARAM-00XX",
			"Le bénéficiare passé en paramètre de cette méthode est obligatoire"),
	 */
	BENEFICIAIRE_NULL(
			TypeErreur.PROGRAMMATION,
			"PARAM-00XX",
			"Le bénéficiare passé en paramètre de cette méthode est obligatoire"),
	
	//	/**
	//	 * Le titulaire passé en paramètre de cette méthode doit avoir un id<br>
	//	 * <i>Pas de values</i>
	//	 */
	//	TITULAIRE_CHAMP_ID_NULL(
	//			"EP-0015",
	//			"Le titulaire passé en paramètre de cette méthode doit avoir un id"),
	//
	/**
			TypeErreur.PROGRAMMATION,
			"PARAM-0013",
			"Le compte passé en paramètre de cette méthode est obligatoire [%s]"),
	 */
	COMPTE_NULL(
			TypeErreur.PROGRAMMATION,
			"PARAM-0013",
			"Le compte passé en paramètre de cette méthode est obligatoire [%s]"),

	/**
	 * Le compte passé en paramètre de cette méthode est obligatoire<br>
	 * value1 : contexte (T.class.simpleName() ou autre String comme "Operation" par exemple...)
	 */
	TRI_NULL(
			TypeErreur.PROGRAMMATION,
			"PARAM-0014",
			"Le tri passé en paramètre de cette méthode est obligatoire [%s]"),

	/**
			TypeErreur.PROGRAMMATION,
			"PARAM-0015",
			"La référence passée en paramètre de cette méthode est obligatoire [%s]"),
	 */
	REFERENCE_NULL(
			TypeErreur.PROGRAMMATION,
			"PARAM-0015",
			"La référence passée en paramètre de cette méthode est obligatoire [%s]"),

	/**
			TypeErreur.PROGRAMMATION,
			"PARAM-0016",
			"La catégorie passée en paramètre de cette méthode est obligatoire [%s]"),
	 */
	CATEGORIE_NULL(
			TypeErreur.PROGRAMMATION,
			"PARAM-0016",
			"La catégorie passée en paramètre de cette méthode est obligatoire [%s]"),

	/**
	 * La catégorie passée en paramètre de cette méthode est obligatoire [%s]<br>
	 * value1 : contexte (T.class.simpleName() ou autre String comme "Operation" par exemple...)
	 */
	BANQUE_NULL(
			TypeErreur.PROGRAMMATION,
			"PARAM-00XX",
			"La banque passée en paramètre de cette méthode est obligatoire [%s]"),
	
	/**
	MONTANT_NULL(
			TypeErreur.PROGRAMMATION,
			"PARAM-00XX",
			"Le montant passé en paramètre de cette méthode est obligatoire"),
	 */
	MONTANT_NULL(
			TypeErreur.PROGRAMMATION,
			"PARAM-00XX",
			"Le montant passé en paramètre de cette méthode est obligatoire"),
	
	;

	private TypeErreur type;

	private String code;

	private String pattern;

	public TypeErreur getType() {
		return type;
	}

	public String getCode() {
		return code;
	}

	public String getPattern() {
		return pattern;
	}

	private ServiceProgrammationErreur(
			TypeErreur type,
			String code, 
			String pattern) {

		this.type = type;
		this.code = code;
		this.pattern = pattern;
	}

	public String getMessage(Object...values) {

		return String.format(pattern, values);
	}
}

package fr.colline.monatis.comptes;

public enum TypeFonctionnementCompte {

	/**
	COURANT ("CN", "Compte en numéraire utilisé pour les dépenses et les recettes courantes du foyer"),
	 */
	COURANT ("CN", "Compte en numéraire utilisé pour les dépenses et les recettes courantes du foyer"),
	
	/**
	INVESTISSEMENT ("CINVST", "Compte d'investissement du foyer donnant lieu à des plus ou moins values ou des versements d'intérêts (ex: titres, livrets, assurances-vie...)"),
	 */
	INVESTISSEMENT ("CINVST", "Compte d'investissement du foyer donnant lieu à des plus ou moins values ou des versements d'intérêts (ex: titres, livrets, assurances-vie...)"),

	/**
	IMMOBILISATION ("CIMMOB", "Bien du foyer dont la valeur dépend d'un marché (ex: immobilier, or, oeuvres d'art...)"),
	 */
	IMMOBILISATION ("CIMMOB", "Bien du foyer dont la valeur dépend d'un marché (ex: immobilier, or, oeuvres d'art...)"),

	/**
	AJUSTEMENT("CA", "Compte technique d'enregistrement de rectifications diverses"),
	 */
	AJUSTEMENT("CA", "Compte technique d'enregistrement de rectifications diverses"),
	
	/**
	TIERS("TIERS", "Compte de tiers non géré par le foyer"),
	 */
	TIERS("TIERS", "Compte de tiers non géré par le foyer"),
	;	
	
	private String code;
	
	private String libelle;
	
	private TypeFonctionnementCompte(String code, String libelle) {
	
		this.code = code;
		this.libelle = libelle;
	}

	public String getCode() {
		return code;
	}

	public String getLibelle() {
		return libelle;
	}

	public static TypeFonctionnementCompte findByCode(String code) {
		
		if ( code != null && !code.isBlank() ) {
			
			for ( TypeFonctionnementCompte value : TypeFonctionnementCompte.values() ) {
			
				if ( value.code.equalsIgnoreCase(code) ) {
					return value;
				}
			}
		}
		
		return null;
	}
}

package fr.colline.monatis.comptes;

public enum TypeCompteInterne {

	/**
	COURANT ("COURANT", true, true, "Compte utilisé pour les échanges avec des tiers", TypeFonctionnementCompte.COURANT),
	 */
	COURANT ("COURANT", false, false, "Compte utilisé pour les échanges avec des tiers", TypeFonctionnementCompte.COURANT),
	
	/**
	LIQUIDE("LIQUIDE", false, false, "Argent liquide", TypeFonctionnementCompte.COURANT),
	 */
	LIQUIDE("LIQUIDE", false, false, "Argent liquide", TypeFonctionnementCompte.COURANT),

	/**
	LIVRET ("LIVRET", false, false, "Livret donnant droit au versement d'intérêts", TypeFonctionnementCompte.INVESTISSEMENT),
	 */
	LIVRET ("LIVRET", false, false, "Livret donnant droit au versement d'intérêts", TypeFonctionnementCompte.INVESTISSEMENT),

	/**
	PEA("PEA", false, false, "Plan Epargne en Actions", TypeFonctionnementCompte.INVESTISSEMENT),
	 */
	PEA("PEA", false, false, "Plan Epargne en Actions", TypeFonctionnementCompte.INVESTISSEMENT),

	/**
	ASSURANCE_VIE ("ASS-VIE", false, false, "Assurance-vie", TypeFonctionnementCompte.INVESTISSEMENT),
	 */
	ASSURANCE_VIE ("ASS-VIE", false, false, "Assurance-vie", TypeFonctionnementCompte.INVESTISSEMENT),

	/**
	COMPTE_TITRES ("TITRES", false, false, "Compte titres", TypeFonctionnementCompte.INVESTISSEMENT),
	 */
	COMPTE_TITRES ("TITRES", false, false, "Compte titres", TypeFonctionnementCompte.INVESTISSEMENT),

	/**
	BIEN_IMMOBILIER("IMMOBILIER", false, false, "Bien immobilier", TypeFonctionnementCompte.IMMOBILISATION),
	 */
	BIEN_IMMOBILIER("IMMOBILIER", false, false, "Bien immobilier", TypeFonctionnementCompte.IMMOBILISATION),

	/**
	BIEN_PRECIEUX("PRECIEUX", false, false, "Bien précieux (bijoux, pièces d'or, oeuvres d'art...)", TypeFonctionnementCompte.IMMOBILISATION),
	 */
	BIEN_PRECIEUX("PRECIEUX", false, false, "Bien précieux (bijoux, pièces d'or, oeuvres d'art...)", TypeFonctionnementCompte.IMMOBILISATION),

	/**
	AJUSTEMENT("AJUSTEMENT", false, false, "Enregistrement d'une opération de modification du solde", TypeFonctionnementCompte.AJUSTEMENT),
	 */
	AJUSTEMENT("AJUSTEMENT", false, false, "Enregistrement d'une opération de modification du solde", TypeFonctionnementCompte.AJUSTEMENT),

	;
	
	private String code;
	
	private String libelle;
	
	private boolean isBanqueObligatoire;
	
	private boolean isAuMoinsUnTitulaireObligatoire;
	
	private TypeFonctionnementCompte typeFonctionnementCompte;
	
	private TypeCompteInterne(
			String code, 
			boolean isBanqueObligatoire, 
			boolean isAuMoinsUnTitulaireObligatoire, 
			String libelle, 
			TypeFonctionnementCompte typeFonctionnementCompte) {
	
		this.code = code;
		this.isBanqueObligatoire = isBanqueObligatoire;
		this.isAuMoinsUnTitulaireObligatoire = isAuMoinsUnTitulaireObligatoire();
		this.libelle = libelle;
		this.typeFonctionnementCompte = typeFonctionnementCompte;
	}

	public String getCode() {
		return code;
	}

	public String getLibelle() {
		return libelle;
	}

	public boolean isBanqueObligatoire() {
		return isBanqueObligatoire;
	}

	public boolean isAuMoinsUnTitulaireObligatoire() {
		return isAuMoinsUnTitulaireObligatoire;
	}

	public TypeFonctionnementCompte getTypeFonctionnementCompte() {
		return typeFonctionnementCompte;
	}

	public static TypeCompteInterne findByCode(String code) {
		
		if ( code != null && !code.isBlank() ) {
			
			for ( TypeCompteInterne value : TypeCompteInterne.values() ) {
			
				if ( value.code.equalsIgnoreCase(code) ) {
					return value;
				}
			}
		}
		
		return null;
	}
}

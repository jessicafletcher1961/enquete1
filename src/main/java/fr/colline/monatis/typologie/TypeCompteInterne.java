package fr.colline.monatis.typologie;

public enum TypeCompteInterne {

	COMPTE_EUROS ("EUROS", "Compte en euros"),
	LIVRETS ("LIV", "Livret"),
	ASSURANCE_VIE ("VIE", "Assurance-vie"),
	COMPTE_CAPITALISATION ("CC", "Compte de capitalisation"),
	PEA("PEA", "Plan Epargne en actions"),
	IMMOBILIER("IMM", "Immobilier"),
	EMPRUNT("EMP", "Emprunt"),
	LIQUIDE("LIQ", "Argent liquide"),
	OR("OR", "Pièces d'or"),
	;
	
	private String code;
	
	private String libelle;
	
	private TypeCompteInterne(String code, String libelle) {
	
		this.code = code;
		this.libelle = libelle;
	}

	public String getCode() {
		return code;
	}

	public String getLibelle() {
		return libelle;
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

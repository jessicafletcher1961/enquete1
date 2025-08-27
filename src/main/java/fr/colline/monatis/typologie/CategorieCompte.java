package fr.colline.monatis.typologie;

public enum CategorieCompte {

	INTERNE("INTERNE", "Compte interne"),
	TIERS("TIERS", "CompteTiers"),
	;
	
	private String code;
	
	private String libelle;
	
	public String getCode() {
		return code;
	}

	protected String getLibelle() {
		return libelle;
	}

	private CategorieCompte(String code, String libelle) {
		
		this.code = code;
		this.libelle = libelle;
	}
	
	public static CategorieCompte findByCode(String code) {
		
		if ( code != null && !code.isBlank() ) {
			
			for ( CategorieCompte value : CategorieCompte.values() ) {
			
				if ( value.code.equalsIgnoreCase(code) ) {
					return value;
				}
			}
		}
		
		return null;
	}
}

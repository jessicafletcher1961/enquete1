package fr.colline.monatis.exception;

public enum TypeErreur {

	PROGRAMMATION ("PROG", "Erreur de programmation"),
	FONCTIONNELLE ("FONC", "Erreur fonctionnelle"),
	TECHNIQUE ("TECH", "Erreur technique"),
	
	;
	
	private String code;
	
	private String libelle;
	
	protected String getCode() {
		return code;
	}

	public String getLibelle() {
		return libelle;
	}

	private TypeErreur(String code, String libelle) {
		this.code = code;
		this.libelle = libelle;
	}
	
	public static TypeErreur findByCode(String code) {
		if ( code != null && !code.isBlank() ) {
			for ( TypeErreur value : TypeErreur.values() ) {
				if ( value.code.equalsIgnoreCase(code) ) {
					return value;
				}
			}
		}
		return null;
	}
}

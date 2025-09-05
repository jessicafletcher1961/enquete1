package fr.colline.monatis.operations;

public enum TypeOperation {

	/** compte tiers -> compte courant */
	RECETTE ("RECETTE", "Encaissement du versement d'un tiers"),
	
	/** compte courant -> compte tiers */
	DEPENSE ("DEPENSE", "Versement à un tiers"),
	
	/** compte courant <-> compte courant */
	TRANSFERT ("TRANSFERT", "Transfert entre comptes courants (ex: retrait de liquide au distributeur, ou dépôt produit vente en liquide sur compte bancaire"),
	
	/** compte courant -> compte investissement */
	INVESTISSEMENT ("INVEST", "Versement sur un compte d'investissement"),
	
	/** compte investissement -> compte courant */
	LIQUIDATION ("LIQUID", "Liquidation de tout ou partie d'un compte d'investissement"),
	
	/** compte ajustement -> compte investissement */
	PLUS_VALUE ("PLUS_V", "Enregistrement d'une plus-value (ou d'un versement d'intérêts)"),
	
	/** compte investissement -> compte ajustement */
	MOINS_VALUE ("MOINS_V", "Enregistrement d'une moins-value"),

	/** compte courant -> compte immobilisation */
	ACHAT ( "ACHAT", "Achat d'un bien"),
	
	/** compte immobilisation -> compte courant */
	VENTE ( "VENTE", "Vente d'un bien"),
	
	/** compte immobilisation <-> compte ajustement */
	/** compte ajustement <-> compte immobilisation */
	ACTUALISATION ("ACTUAL", "Actualisation de la valeur d'un bien"),
	
	/** compte ajustement <-> compte courant */
	/** compte courant <-> compte ajustement */
	AJUSTEMENT ("AJUST", "Ajustement du solde d'un compte"),
	
	;
	
	private String code;
	
	private String libelle;
	
	private TypeOperation(String code, String libelle) {
		
		this.code = code;
		this.libelle = libelle;
	}

	public String getCode() {
		return code;
	}

	public String getLibelle() {
		return libelle;
	}

	public static TypeOperation findByCode(String code) {
		
		if ( code != null && !code.isBlank() ) {
			
			for ( TypeOperation value : TypeOperation.values() ) {
			
				if ( value.code.equalsIgnoreCase(code) ) {
					return value;
				}
			}
		}
		
		return null;
	}
}

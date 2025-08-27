package fr.colline.monatis.controller.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import fr.colline.monatis.typologie.CategorieCompte;

public class CompteInterneResponseDto extends CompteResponseDto implements Serializable {

	private static final long serialVersionUID = -6251189489199865586L;
	
	public Timestamp dateSoldeInitial;
	public Long montantSoldeInitialEnCentimes;
	public String typeCompteInterne;
	public BanqueResponseDto banque;
	public List<TitulaireResponseDto> titulaires;
	
	public CompteInterneResponseDto() {
		
		this.categorieCompte = CategorieCompte.INTERNE.getCode();
	}
}

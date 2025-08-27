package fr.colline.monatis.controller.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import fr.colline.monatis.typologie.CategorieCompte;

public class CompteInterneRequestDto extends CompteRequestDto implements Serializable {

	private static final long serialVersionUID = -2903673429241830093L;
	
	public Timestamp dateSoldeInitial;
	public Long montantSoldeInitialEnCentimes;
	public String typeCompteInterne;

	public String nomBanque;
	public List<String> nomsTitulaires;
	
	public CompteInterneRequestDto() {
		
		categorieCompte = CategorieCompte.INTERNE.getCode();
	}
}

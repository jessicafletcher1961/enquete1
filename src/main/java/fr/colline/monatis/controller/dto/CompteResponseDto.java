package fr.colline.monatis.controller.dto;

import java.io.Serializable;

public abstract class CompteResponseDto implements Serializable {

	private static final long serialVersionUID = 4231222336273055623L;

	public Long id;
	public String identifiant;
	public String libelle;
	
	public String categorieCompte;
}

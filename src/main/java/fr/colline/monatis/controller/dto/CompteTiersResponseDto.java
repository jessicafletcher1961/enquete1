package fr.colline.monatis.controller.dto;

import java.io.Serializable;

import fr.colline.monatis.typologie.CategorieCompte;

public class CompteTiersResponseDto extends CompteResponseDto implements Serializable {

	private static final long serialVersionUID = -8266113439223832305L;

	public CompteTiersResponseDto() {
		this.categorieCompte = CategorieCompte.TIERS.getCode();
	}
}

package fr.colline.monatis.comptes.controller.mapper;

import fr.colline.monatis.comptes.CategorieCompte;
import fr.colline.monatis.comptes.controller.dto.CategorieCompteResponseDto;

public class CategorieCompteDtoMapper {

	public static CategorieCompteResponseDto modelToResponseDto(CategorieCompte categorieCompte) {
		
		CategorieCompteResponseDto dto = new CategorieCompteResponseDto();
		
		dto.code = categorieCompte.getCode();
		dto.libelle = categorieCompte.getLibelle();
		
		return dto;
	}
}

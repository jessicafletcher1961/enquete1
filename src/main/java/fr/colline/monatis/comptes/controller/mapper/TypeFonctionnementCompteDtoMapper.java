package fr.colline.monatis.comptes.controller.mapper;

import fr.colline.monatis.comptes.TypeFonctionnementCompte;
import fr.colline.monatis.comptes.controller.dto.TypeFonctionnementCompteResponseDto;

public class TypeFonctionnementCompteDtoMapper {

	public static TypeFonctionnementCompteResponseDto modelToResponseDto(
			TypeFonctionnementCompte typeFonctionnementCompte) {
		
		TypeFonctionnementCompteResponseDto dto = new TypeFonctionnementCompteResponseDto();
		
		dto.code = typeFonctionnementCompte.getCode();
		dto.libelle = typeFonctionnementCompte.getLibelle();
		
		return dto;
	}
}

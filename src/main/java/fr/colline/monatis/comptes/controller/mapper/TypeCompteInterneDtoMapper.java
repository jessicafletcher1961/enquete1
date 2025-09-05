package fr.colline.monatis.comptes.controller.mapper;

import fr.colline.monatis.comptes.TypeCompteInterne;
import fr.colline.monatis.comptes.controller.dto.internes.TypeCompteInterneResponseDto;

public class TypeCompteInterneDtoMapper {

	public static TypeCompteInterneResponseDto modelToResponseDto(TypeCompteInterne typeCompteInterne) {
		
		TypeCompteInterneResponseDto dto = new TypeCompteInterneResponseDto();
		
		dto.code = typeCompteInterne.getCode();
		dto.libelle = typeCompteInterne.getLibelle();
		
		return dto;
	}
}

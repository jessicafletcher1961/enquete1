package fr.colline.monatis.controller.dto.mapper;

import fr.colline.monatis.controller.dto.SousCategorieResponseDto;
import fr.colline.monatis.model.SousCategorie;

public class SousCategorieDtoMapper {

	public static SousCategorieResponseDto simpleModelToResponseDto(SousCategorie sousCategorie) {

		SousCategorieResponseDto dto = new SousCategorieResponseDto();
		
		dto.id = sousCategorie.getId();
		dto.nom = sousCategorie.getNom();
		dto.libelle = sousCategorie.getLibelle();
		
		return dto;
	}
	
	public static SousCategorieResponseDto detailedModelToResponseDto(SousCategorie sousCategorie) {
		
		SousCategorieResponseDto dto = simpleModelToResponseDto(sousCategorie);
		
		dto.categorie = CategorieDtoMapper.simpleModelToResponseDto(sousCategorie.getCategorie());
		
		return dto;
	}
}

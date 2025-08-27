package fr.colline.monatis.controller.dto.mapper;

import java.util.ArrayList;

import fr.colline.monatis.controller.dto.CategorieResponseDto;
import fr.colline.monatis.controller.dto.SousCategorieResponseDto;
import fr.colline.monatis.model.Categorie;
import fr.colline.monatis.model.SousCategorie;

public class CategorieDtoMapper {

	public static CategorieResponseDto simpleModelToResponseDto(Categorie categorie) {

		CategorieResponseDto dto = new CategorieResponseDto();
		
		dto.id = categorie.getId();
		dto.nom = categorie.getNom();
		dto.libelle = categorie.getLibelle();
		
		return dto;
	}
	
	public static CategorieResponseDto detailedModelToResponseDto(Categorie categorie) {
		
		CategorieResponseDto dto = simpleModelToResponseDto(categorie);

		dto.sousCategories = new ArrayList<SousCategorieResponseDto>();
		if ( categorie.getSousCategories() != null ) {
			for ( SousCategorie sousCategorie : categorie.getSousCategories() ) {
				dto.sousCategories.add(SousCategorieDtoMapper.simpleModelToResponseDto(sousCategorie));
			}
		}
		
		return dto;
	}
}

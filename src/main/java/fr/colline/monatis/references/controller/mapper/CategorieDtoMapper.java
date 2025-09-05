package fr.colline.monatis.references.controller.mapper;

import java.util.ArrayList;
import java.util.Collections;

import fr.colline.monatis.references.controller.dto.categories.CategorieBasicResponseDto;
import fr.colline.monatis.references.controller.dto.categories.CategorieDetailedResponseDto;
import fr.colline.monatis.references.controller.dto.categories.CategorieSimpleResponseDto;
import fr.colline.monatis.references.model.Categorie;
import fr.colline.monatis.references.model.SousCategorie;

public class CategorieDtoMapper {

	public static CategorieBasicResponseDto modelToBasicResponseDto(Categorie categorie) {

		CategorieBasicResponseDto dto = new CategorieBasicResponseDto();

		dto.nom = categorie.getNom();
		dto.libelle = categorie.getLibelle();
		
		return dto;

	}

	public static CategorieSimpleResponseDto modelToSimpleResponseDto(Categorie categorie) {

		CategorieSimpleResponseDto dto = new CategorieSimpleResponseDto();
		
		dto.id = categorie.getId();
		dto.nom = categorie.getNom();
		dto.libelle = categorie.getLibelle();
		
		return dto;
	}
	
	public static CategorieDetailedResponseDto modelToDetailedResponseDto(Categorie categorie) {
		
		CategorieDetailedResponseDto dto = new CategorieDetailedResponseDto();

		dto.id = categorie.getId();
		dto.nom = categorie.getNom();
		dto.libelle = categorie.getLibelle();
		dto.sousCategories = new ArrayList<>();
		if ( categorie.getSousCategories() != null ) {
			for ( SousCategorie sousCategorie : categorie.getSousCategories() ) {
				dto.sousCategories.add(SousCategorieDtoMapper.modelToBasicResponseDto(sousCategorie));
			}
			Collections.sort(dto.sousCategories, (o1, o2) -> {
				return o1.nom.compareTo(o2.nom);
			});
		}
		
		return dto;
	}
}

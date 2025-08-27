package fr.colline.monatis.controller.dto.mapper;

import java.util.ArrayList;

import fr.colline.monatis.controller.dto.TitulaireResponseDto;
import fr.colline.monatis.model.CompteInterne;
import fr.colline.monatis.model.Titulaire;

public class TitulaireDtoMapper {

	public static TitulaireResponseDto simpleModelToResponseDto(Titulaire titulaire) {

		TitulaireResponseDto dto = new TitulaireResponseDto();
		
		dto.id = titulaire.getId();
		dto.nom = titulaire.getNom();
		dto.libelle = titulaire.getLibelle();
		
		return dto;
	}

	public static TitulaireResponseDto detailedModelToResponseDto(Titulaire titulaire) {

		TitulaireResponseDto dto = TitulaireDtoMapper.simpleModelToResponseDto(titulaire);

		dto.comptesInternes = new ArrayList<>();
		if ( titulaire.getComptesInternes() != null ) {
			for ( CompteInterne compteInterne : titulaire.getComptesInternes() ) {
				dto.comptesInternes.add(CompteInterneDtoMapper.simpleModelToResponseDto(compteInterne));
			}
		}
		
		return dto;
	}
}

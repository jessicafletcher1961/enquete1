package fr.colline.monatis.controller.dto.mapper;

import fr.colline.monatis.controller.dto.CompteTiersResponseDto;
import fr.colline.monatis.model.CompteTiers;

public class CompteTiersDtoMapper {

	public static CompteTiersResponseDto simpleModelToResponseDto(CompteTiers compteTiers) {

		CompteTiersResponseDto dto = new CompteTiersResponseDto();
		
		dto.id = compteTiers.getId();
		dto.identifiant = compteTiers.getIdentifiant();
		dto.libelle = compteTiers.getLibelle();
		
		return dto;
	}

	public static CompteTiersResponseDto detailedModelToResponseDto(CompteTiers compteTiers) {

		CompteTiersResponseDto dto = CompteTiersDtoMapper.simpleModelToResponseDto(compteTiers);
		
		return dto;
	}
}

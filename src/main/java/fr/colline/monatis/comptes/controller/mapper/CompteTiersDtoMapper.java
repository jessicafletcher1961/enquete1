package fr.colline.monatis.comptes.controller.mapper;

import fr.colline.monatis.comptes.controller.dto.tiers.CompteTiersBasicResponseDto;
import fr.colline.monatis.comptes.controller.dto.tiers.CompteTiersDetailedResponseDto;
import fr.colline.monatis.comptes.controller.dto.tiers.CompteTiersSimpleResponseDto;
import fr.colline.monatis.comptes.model.CompteTiers;

public class CompteTiersDtoMapper {

	public static CompteTiersBasicResponseDto modelToBasicResponseDto(CompteTiers compteTiers) {

		CompteTiersBasicResponseDto dto = new CompteTiersBasicResponseDto();
		
		dto.identifiant = compteTiers.getIdentifiant();
		dto.libelle = compteTiers.getLibelle();

		return dto;
	}

	public static CompteTiersSimpleResponseDto modelToSimpleResponseDto(CompteTiers compteTiers) {

		CompteTiersSimpleResponseDto dto = new CompteTiersSimpleResponseDto();
		
		dto.identifiant = compteTiers.getIdentifiant();
		dto.libelle = compteTiers.getLibelle();

		dto.id = compteTiers.getId();

		return dto;
	}

	public static CompteTiersDetailedResponseDto modelToDetailedResponseDto(CompteTiers compteTiers) {

		CompteTiersDetailedResponseDto dto = new CompteTiersDetailedResponseDto();
		
		dto.id = compteTiers.getId();
		dto.identifiant = compteTiers.getIdentifiant();
		dto.libelle = compteTiers.getLibelle();

		return dto;
	}
}

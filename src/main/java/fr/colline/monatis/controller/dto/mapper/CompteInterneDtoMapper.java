package fr.colline.monatis.controller.dto.mapper;

import java.util.ArrayList;

import fr.colline.monatis.controller.dto.CompteInterneResponseDto;
import fr.colline.monatis.model.CompteInterne;
import fr.colline.monatis.model.Titulaire;

public class CompteInterneDtoMapper {

	public static CompteInterneResponseDto simpleModelToResponseDto(CompteInterne compteInterne) {

		CompteInterneResponseDto dto = new CompteInterneResponseDto();

		dto.id = compteInterne.getId();
		dto.identifiant = compteInterne.getIdentifiant();
		dto.libelle = compteInterne.getLibelle();

		dto.dateSoldeInitial = compteInterne.getDateSoldeInitial();
		dto.montantSoldeInitialEnCentimes = compteInterne.getMontantSoldeInitialEnCentimes();
		dto.typeCompteInterne = compteInterne.getTypeCompteInterne().getCode();

		return dto;		
	}

	public static CompteInterneResponseDto detailedModelToResponseDto(CompteInterne compteInterne) {

		CompteInterneResponseDto dto = simpleModelToResponseDto(compteInterne);

		dto.banque = BanqueDtoMapper.simpleModelToResponseDto(compteInterne.getBanque());
		dto.titulaires = new ArrayList<>();
		for ( Titulaire titulaire : compteInterne.getTitulaires() ) {
			dto.titulaires.add(TitulaireDtoMapper.simpleModelToResponseDto(titulaire));
		}

		return dto;
	}
}

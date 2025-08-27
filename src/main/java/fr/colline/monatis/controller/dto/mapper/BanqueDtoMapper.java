package fr.colline.monatis.controller.dto.mapper;

import java.util.ArrayList;

import fr.colline.monatis.controller.dto.BanqueResponseDto;
import fr.colline.monatis.model.Banque;
import fr.colline.monatis.model.CompteInterne;

public class BanqueDtoMapper {

	public static BanqueResponseDto simpleModelToResponseDto(Banque banque) {
		
		BanqueResponseDto dto = new BanqueResponseDto();

		dto.id = banque.getId();
		dto.nom = banque.getNom();
		dto.libelle = banque.getLibelle();
		
		return dto;
	}

	public static BanqueResponseDto detailedModelToResponseDto(Banque banque) {
		
		BanqueResponseDto dto = simpleModelToResponseDto(banque);
		
		dto.comptesInternes = new ArrayList<>();
		if ( banque.getComptesInternes() != null ) {
			for ( CompteInterne compteInterne : banque.getComptesInternes() ) {
				dto.comptesInternes.add(CompteInterneDtoMapper.simpleModelToResponseDto(compteInterne));
			}
		}

		return dto;
	}
}

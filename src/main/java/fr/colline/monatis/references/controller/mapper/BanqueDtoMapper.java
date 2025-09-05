package fr.colline.monatis.references.controller.mapper;

import java.util.ArrayList;
import java.util.Collections;

import fr.colline.monatis.comptes.controller.mapper.CompteInterneDtoMapper;
import fr.colline.monatis.comptes.model.CompteInterne;
import fr.colline.monatis.references.controller.dto.banques.BanqueBasicResponseDto;
import fr.colline.monatis.references.controller.dto.banques.BanqueDetailedResponseDto;
import fr.colline.monatis.references.controller.dto.banques.BanqueSimpleResponseDto;
import fr.colline.monatis.references.model.Banque;

public class BanqueDtoMapper {

	public static BanqueBasicResponseDto modelToBasicResponseDto(Banque banque) {

		BanqueBasicResponseDto dto = new BanqueBasicResponseDto();

		dto.nom = banque.getNom();
		dto.libelle = banque.getLibelle();
		
		return dto;
	}

	public static BanqueSimpleResponseDto modelToSimpleResponseDto(Banque banque) {
		
		BanqueSimpleResponseDto dto = new BanqueSimpleResponseDto();
		
		dto.id = banque.getId();
		dto.nom = banque.getNom();
		dto.libelle = banque.getLibelle();
		
		return dto;
	}

	public static BanqueDetailedResponseDto modelToDetailedResponseDto(Banque banque) {
		
		BanqueDetailedResponseDto dto = new BanqueDetailedResponseDto();
		
		dto.id = banque.getId();
		dto.nom = banque.getNom();
		dto.libelle = banque.getLibelle();
		dto.comptesInternes = new ArrayList<>();
		if ( banque.getComptesInternes() != null ) {
			for ( CompteInterne compteInterne : banque.getComptesInternes() ) {
				dto.comptesInternes.add(CompteInterneDtoMapper.modelToSimpleResponseDto(compteInterne));
			}
			Collections.sort(dto.comptesInternes, (o1, o2) -> {
				return o1.identifiant.compareTo(o2.identifiant);
			});
		}

		return dto;
	}
}

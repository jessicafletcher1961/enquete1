package fr.colline.monatis.comptes.controller.mapper;

import java.util.ArrayList;

import fr.colline.monatis.comptes.controller.dto.internes.CompteInterneBasicResponseDto;
import fr.colline.monatis.comptes.controller.dto.internes.CompteInterneDetailedResponseDto;
import fr.colline.monatis.comptes.controller.dto.internes.CompteInterneSimpleResponseDto;
import fr.colline.monatis.comptes.model.CompteInterne;
import fr.colline.monatis.references.controller.mapper.BanqueDtoMapper;
import fr.colline.monatis.references.controller.mapper.TitulaireDtoMapper;
import fr.colline.monatis.references.model.Titulaire;

public class CompteInterneDtoMapper {

	public static CompteInterneBasicResponseDto modelToBasicResponseDto(CompteInterne compteInterne) {

		CompteInterneBasicResponseDto dto = new CompteInterneBasicResponseDto();

		dto.identifiant = compteInterne.getIdentifiant();
		dto.libelle = compteInterne.getLibelle();
		
		return dto;
		
	}
	
	public static CompteInterneSimpleResponseDto modelToSimpleResponseDto(CompteInterne compteInterne) {

		CompteInterneSimpleResponseDto dto = new CompteInterneSimpleResponseDto();

		dto.identifiant = compteInterne.getIdentifiant();
		dto.libelle = compteInterne.getLibelle();

		dto.id = compteInterne.getId();
		dto.dateSoldeInitial = compteInterne.getDateSoldeInitial();
		dto.montantSoldeInitialEnCentimes = compteInterne.getMontantSoldeInitialEnCentimes();
		dto.typeCompteInterne = TypeCompteInterneDtoMapper.modelToResponseDto(compteInterne.getTypeCompteInterne());
		
		return dto;		
	}

	public static CompteInterneDetailedResponseDto modelToDetailedResponseDto(CompteInterne compteInterne) {

		CompteInterneDetailedResponseDto dto = new CompteInterneDetailedResponseDto();

		dto.id = compteInterne.getId();
		dto.identifiant = compteInterne.getIdentifiant();
		dto.libelle = compteInterne.getLibelle();
		dto.dateSoldeInitial = compteInterne.getDateSoldeInitial();
		dto.montantSoldeInitialEnCentimes = compteInterne.getMontantSoldeInitialEnCentimes();
		dto.typeCompteInterne = TypeCompteInterneDtoMapper.modelToResponseDto(compteInterne.getTypeCompteInterne());
		
		dto.typeFonctionnementCompte = TypeFonctionnementCompteDtoMapper.modelToResponseDto(compteInterne.getTypeCompteInterne().getTypeFonctionnementCompte());
		if ( compteInterne.getBanque() != null ) {
			dto.banque = BanqueDtoMapper.modelToBasicResponseDto(compteInterne.getBanque());
		}
		dto.titulaires = new ArrayList<>();
		if ( compteInterne.getTitulaires() != null ) {
			for ( Titulaire titulaire : compteInterne.getTitulaires() ) {
				dto.titulaires.add(TitulaireDtoMapper.modelToBasicResponseDto(titulaire));
			}
		}

		return dto;
	}
}

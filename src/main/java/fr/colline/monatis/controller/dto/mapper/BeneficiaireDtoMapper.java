package fr.colline.monatis.controller.dto.mapper;

import fr.colline.monatis.controller.dto.BeneficiaireResponseDto;
import fr.colline.monatis.model.Beneficiaire;

public class BeneficiaireDtoMapper {

	public static BeneficiaireResponseDto simpleModelToResponseDto(Beneficiaire beneficiaire) {
		
		BeneficiaireResponseDto dto = new BeneficiaireResponseDto();
		
		dto.id = beneficiaire.getId();
		dto.nom = beneficiaire.getNom();
		dto.libelle = beneficiaire.getLibelle();
		
		return dto;
	}

	public static BeneficiaireResponseDto detailedModelToResponseDto(Beneficiaire beneficiaire) {
		
		return simpleModelToResponseDto(beneficiaire);
	}
}
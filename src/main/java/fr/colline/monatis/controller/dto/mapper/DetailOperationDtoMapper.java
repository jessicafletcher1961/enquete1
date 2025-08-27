package fr.colline.monatis.controller.dto.mapper;

import java.util.ArrayList;

import fr.colline.monatis.controller.dto.DetailOperationResponseDto;
import fr.colline.monatis.model.Beneficiaire;
import fr.colline.monatis.model.DetailOperation;

public class DetailOperationDtoMapper {

	public static DetailOperationResponseDto simpleModelToResponseDto(DetailOperation detailOperation) {

		DetailOperationResponseDto dto = new DetailOperationResponseDto();
		
		dto.id = detailOperation.getId();
		dto.sequence = detailOperation.getSequence();
		dto.dateComptabilisation = detailOperation.getDateComptabilisation();
		dto.libelle = detailOperation.getLibelle();
		dto.montantDetailEnCentimes = detailOperation.getMontantDetailEnCentimes();
		
		return dto;
	}

	public static DetailOperationResponseDto detailedModelToResponseDto(DetailOperation detailOperation) {
		
		DetailOperationResponseDto dto = simpleModelToResponseDto(detailOperation);
		
		dto.sousCategorie = SousCategorieDtoMapper.detailedModelToResponseDto(detailOperation.getSousCategorie());
		dto.beneficiaires = new ArrayList<>();
		for ( Beneficiaire beneficiaire : detailOperation.getBeneficiaires() ) {
			dto.beneficiaires.add(BeneficiaireDtoMapper.detailedModelToResponseDto(beneficiaire));
		}
		
		return dto;
	}
}

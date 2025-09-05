package fr.colline.monatis.operations.controller.mapper;

import java.util.ArrayList;

import fr.colline.monatis.operations.controller.dto.detailoperation.DetailOperationBasicResponseDto;
import fr.colline.monatis.operations.controller.dto.detailoperation.DetailOperationDetailedResponseDto;
import fr.colline.monatis.operations.controller.dto.detailoperation.DetailOperationSimpleResponseDto;
import fr.colline.monatis.operations.model.DetailOperation;
import fr.colline.monatis.references.controller.mapper.BeneficiaireDtoMapper;
import fr.colline.monatis.references.controller.mapper.SousCategorieDtoMapper;
import fr.colline.monatis.references.model.Beneficiaire;

public class DetailOperationDtoMapper {

	public static DetailOperationBasicResponseDto modelToBasicResponseDto(DetailOperation detailOperation) {
		
		DetailOperationBasicResponseDto dto = new DetailOperationBasicResponseDto();
		
		dto.sequence = detailOperation.getSequence();
		dto.dateComptabilisation = detailOperation.getDateComptabilisation();
		dto.montantDetailEnCentimes = detailOperation.getMontantDetailEnCentimes();
		dto.libelle = detailOperation.getLibelle();
		
		return dto;
	}

	public static DetailOperationSimpleResponseDto modelToSimpleResponseDto(DetailOperation detailOperation) {

		DetailOperationSimpleResponseDto dto = new DetailOperationSimpleResponseDto();
		
		dto.id = detailOperation.getId();
		dto.sequence = detailOperation.getSequence();
		dto.dateComptabilisation = detailOperation.getDateComptabilisation();
		dto.montantDetailEnCentimes = detailOperation.getMontantDetailEnCentimes();
		dto.libelle = detailOperation.getLibelle();
		
		return dto;
	}

	public static DetailOperationDetailedResponseDto modelToDetailedResponseDto(DetailOperation detailOperation) {
		
		DetailOperationDetailedResponseDto dto = new DetailOperationDetailedResponseDto();

		dto.id = detailOperation.getId();
		dto.sequence = detailOperation.getSequence();
		dto.dateComptabilisation = detailOperation.getDateComptabilisation();
		dto.montantDetailEnCentimes = detailOperation.getMontantDetailEnCentimes();
		dto.libelle = detailOperation.getLibelle();
		if ( detailOperation.getSousCategorie() != null ) {
			dto.sousCategorie = SousCategorieDtoMapper.modelToBasicResponseDto(detailOperation.getSousCategorie());
		}
		dto.beneficiaires = new ArrayList<>();
		if ( detailOperation.getBeneficiaires() != null ) {
			for ( Beneficiaire beneficiaire : detailOperation.getBeneficiaires() ) {
				dto.beneficiaires.add(BeneficiaireDtoMapper.modelToBasicResponseDto(beneficiaire));
			}
		}
		
		return dto;
	}
}

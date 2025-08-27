package fr.colline.monatis.controller.dto.mapper;

import java.util.ArrayList;

import fr.colline.monatis.controller.dto.OperationResponseDto;
import fr.colline.monatis.model.DetailOperation;
import fr.colline.monatis.model.Operation;

public class OperationDtoMapper {

	public static OperationResponseDto simpleResponseModelToDto(Operation operation) {

		OperationResponseDto dto = new OperationResponseDto();
		
		dto.id = operation.getId();
		dto.dateValeur = operation.getDateValeur();
		dto.numero = operation.getNumero();
		dto.libelle = operation.getLibelle();
		dto.montantTotalEnCentimes = operation.getMontantTotalEnCentimes();
		
		return dto;
	}
	
	public static OperationResponseDto detailedModelToResponseDto(Operation operation) {
		
		OperationResponseDto dto = simpleResponseModelToDto(operation);
		
		dto.compteRecette = CompteTousTypeDtoMapper.detailedModelToResponseDto(operation.getCompteRecette());
		dto.compteDepense = CompteTousTypeDtoMapper.detailedModelToResponseDto(operation.getCompteDepense());
		
		dto.detailsOperation = new ArrayList<>();
		for ( DetailOperation detailOperation : operation.getDetailsOperation() ) {
			dto.detailsOperation.add(DetailOperationDtoMapper.detailedModelToResponseDto(detailOperation));
		}
		
		return dto;
	}
}

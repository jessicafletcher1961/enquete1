package fr.colline.monatis.operations.controller.mapper;

import java.util.ArrayList;

import fr.colline.monatis.comptes.controller.mapper.CompteDtoMapper;
import fr.colline.monatis.comptes.controller.mapper.TypeOperationDtoMapper;
import fr.colline.monatis.operations.controller.dto.operation.OperationBasicResponseDto;
import fr.colline.monatis.operations.controller.dto.operation.OperationDetailedResponseDto;
import fr.colline.monatis.operations.controller.dto.operation.OperationSimpleResponseDto;
import fr.colline.monatis.operations.model.DetailOperation;
import fr.colline.monatis.operations.model.Operation;

public class OperationDtoMapper {

	public static OperationBasicResponseDto modelToBasicResponseDto(Operation operation) {
		
		OperationBasicResponseDto dto = new OperationBasicResponseDto();
		
		dto.id = operation.getId();
		dto.typeOperation = TypeOperationDtoMapper.modelToResponseDto(operation.getTypeOperation());
		dto.numero = operation.getNumero();
		dto.dateValeur = operation.getDateValeur();
		dto.libelle = operation.getLibelle();
		dto.montantTotalEnCentimes = operation.getMontantTotalEnCentimes();
		if ( operation.getCompteDepense() != null) {
			dto.identifiantCompteDepense = operation.getCompteDepense().getIdentifiant();
		}
		if ( operation.getCompteRecette() != null ) {
			dto.identifiantCompteRecette = operation.getCompteRecette().getIdentifiant();
		}
		dto.detailsOperation = new ArrayList<>();
		if ( operation.getDetailsOperation() != null 
				&& operation.getDetailsOperation().size() > 1 ) {
			for ( DetailOperation detailOperation : operation.getDetailsOperation() ) {
				dto.detailsOperation.add(DetailOperationDtoMapper.modelToBasicResponseDto(detailOperation));
			}
		}
		
		return dto;
	}
	
	public static OperationSimpleResponseDto modelToSimpleResponseDto(Operation operation) {

		OperationSimpleResponseDto dto = new OperationSimpleResponseDto();
		
		dto.id = operation.getId();
		dto.typeOperation = TypeOperationDtoMapper.modelToResponseDto(operation.getTypeOperation());
		dto.numero = operation.getNumero();
		dto.dateValeur = operation.getDateValeur();
		dto.libelle = operation.getLibelle();
		dto.montantTotalEnCentimes = operation.getMontantTotalEnCentimes();
		if ( operation.getCompteDepense() != null) {
			dto.compteDepense = CompteDtoMapper.modelToBasicResponseDto(operation.getCompteDepense());
		}
		if ( operation.getCompteRecette() != null ) {
			dto.compteRecette = CompteDtoMapper.modelToBasicResponseDto(operation.getCompteRecette());
		}
		dto.detailsOperation = new ArrayList<>();
		if ( operation.getDetailsOperation() != null 
				&& operation.getDetailsOperation().size() > 1 ) {
			for ( DetailOperation detailOperation : operation.getDetailsOperation() ) {
				dto.detailsOperation.add(DetailOperationDtoMapper.modelToSimpleResponseDto(detailOperation));
			}
		}
		
		return dto;
	}
	
	public static OperationDetailedResponseDto modelToDetailedResponseDto(Operation operation) {
		
		OperationDetailedResponseDto dto = new OperationDetailedResponseDto();
		
		dto.id = operation.getId();
		dto.typeOperation = TypeOperationDtoMapper.modelToResponseDto(operation.getTypeOperation());
		dto.numero = operation.getNumero();
		dto.dateValeur = operation.getDateValeur();
		dto.libelle = operation.getLibelle();
		dto.montantTotalEnCentimes = operation.getMontantTotalEnCentimes();
		if ( operation.getCompteDepense() != null) {
			dto.compteDepense = CompteDtoMapper.modelToSimpleResponseDto(operation.getCompteDepense());
		}
		if ( operation.getCompteRecette() != null ) {
			dto.compteRecette = CompteDtoMapper.modelToSimpleResponseDto(operation.getCompteRecette());
		}
		dto.detailsOperation = new ArrayList<>();
		if ( operation.getDetailsOperation() != null 
				&& operation.getDetailsOperation().size() > 1 ) {
			for ( DetailOperation detailOperation : operation.getDetailsOperation() ) {
				dto.detailsOperation.add(DetailOperationDtoMapper.modelToDetailedResponseDto(detailOperation));
			}
		}
		
		return dto;
	}
}

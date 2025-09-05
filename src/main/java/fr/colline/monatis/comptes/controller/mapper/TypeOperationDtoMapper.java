package fr.colline.monatis.comptes.controller.mapper;

import fr.colline.monatis.operations.TypeOperation;
import fr.colline.monatis.operations.controller.dto.operation.TypeOperationResponseDto;

public class TypeOperationDtoMapper {

	public static TypeOperationResponseDto modelToResponseDto(TypeOperation typeOperation) {
		
		TypeOperationResponseDto dto = new TypeOperationResponseDto();
		
		dto.code = typeOperation.getCode();
		dto.libelle = typeOperation.getLibelle();
		
		return dto;
	}
}

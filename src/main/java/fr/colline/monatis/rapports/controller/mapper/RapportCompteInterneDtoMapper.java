package fr.colline.monatis.rapports.controller.mapper;

import java.util.ArrayList;

import fr.colline.monatis.comptes.controller.mapper.TypeCompteInterneDtoMapper;
import fr.colline.monatis.operations.controller.mapper.OperationDtoMapper;
import fr.colline.monatis.operations.model.Operation;
import fr.colline.monatis.rapports.controller.dto.RapportCompteInterneDto;
import fr.colline.monatis.rapports.model.RapportCompteInterne;

public class RapportCompteInterneDtoMapper {

	public static RapportCompteInterneDto simpleModelToResponseDto(
			RapportCompteInterne rapport) {
		
		RapportCompteInterneDto dto = new RapportCompteInterneDto();
		
		dto.identifiantCompte = rapport.getCompteInterne().getIdentifiant();
		dto.libelleCompte = rapport.getCompteInterne().getLibelle();
		dto.typeCompteInterne = TypeCompteInterneDtoMapper.modelToResponseDto(rapport.getCompteInterne().getTypeCompteInterne());
		dto.dateSoldeInitial = rapport.getDateSoldeInitial();
		dto.dateSoldeFinal = rapport.getDateSoldeFinal();
		dto.montantSoldeInitialEnCentimes = rapport.getMontantSoldeInitialEnCentimes();
		dto.montantTotalDepenseEnCentimes = rapport.getMontantTotalDepenseEnCentimes();
		dto.montantTotalRecetteEnCentimes = rapport.getMontantTotalRecetteEnCentimes();
		dto.montantSoldeFinalEnCentimes = rapport.getMontantSoldeFinalEnCentimes();
		
		return dto;
	}
	
	public static RapportCompteInterneDto detailedModelToResponseDto(
			RapportCompteInterne rapport) {

		RapportCompteInterneDto dto = simpleModelToResponseDto(rapport);
		
		if ( rapport.getListeOperation() != null ) {
			dto.listeOperation = new ArrayList<>();
			for ( Operation operation : rapport.getListeOperation() ) {
				dto.listeOperation.add(OperationDtoMapper.modelToDetailedResponseDto(operation));
			}
		}
		
		return dto;
	}
}

package fr.colline.monatis.rapports.controller.dto;

import java.sql.Timestamp;
import java.util.List;

import fr.colline.monatis.comptes.controller.dto.internes.TypeCompteInterneResponseDto;
import fr.colline.monatis.operations.controller.dto.operation.OperationDetailedResponseDto;

public class RapportCompteInterneDto {

	public String identifiantCompte;
	public String libelleCompte;
	public TypeCompteInterneResponseDto typeCompteInterne;
	public Timestamp dateSoldeInitial;
	public Long montantSoldeInitialEnCentimes;
	public Long montantTotalRecetteEnCentimes;
	public Long montantTotalDepenseEnCentimes;
	public Timestamp dateSoldeFinal;
	public Long montantSoldeFinalEnCentimes;
	
	public List<OperationDetailedResponseDto> listeOperation; 
}

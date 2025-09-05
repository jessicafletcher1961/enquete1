package fr.colline.monatis.operations.controller.dto.operation;

import java.sql.Timestamp;
import java.util.List;

import fr.colline.monatis.comptes.controller.dto.CompteResponseDto;
import fr.colline.monatis.operations.controller.dto.detailoperation.DetailOperationDetailedResponseDto;

public class OperationDetailedResponseDto {

	public Long id;
	public TypeOperationResponseDto typeOperation;
	public String numero;
	public Timestamp dateValeur;
	public Long montantTotalEnCentimes;
	public String libelle;
	public CompteResponseDto compteDepense;
	public CompteResponseDto compteRecette;
	public List<DetailOperationDetailedResponseDto> detailsOperation;
}

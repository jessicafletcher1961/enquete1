package fr.colline.monatis.operations.controller.dto.operation;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import fr.colline.monatis.comptes.controller.dto.CompteResponseDto;
import fr.colline.monatis.operations.controller.dto.detailoperation.DetailOperationSimpleResponseDto;

public class OperationSimpleResponseDto implements Serializable {

	private static final long serialVersionUID = -748760118944977912L;

	public Long id;
	public TypeOperationResponseDto typeOperation;
	public String numero;
	public Timestamp dateValeur;
	public Long montantTotalEnCentimes;
	public String libelle;
	public CompteResponseDto compteDepense;
	public CompteResponseDto compteRecette;
	public List<DetailOperationSimpleResponseDto> detailsOperation;
}

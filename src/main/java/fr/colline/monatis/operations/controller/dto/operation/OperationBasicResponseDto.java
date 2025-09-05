package fr.colline.monatis.operations.controller.dto.operation;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import fr.colline.monatis.operations.controller.dto.detailoperation.DetailOperationBasicResponseDto;

public class OperationBasicResponseDto implements Serializable {

	private static final long serialVersionUID = -227416719941487153L;

	public Long id;
	public TypeOperationResponseDto typeOperation;
	public String numero;
	public Timestamp dateValeur;
	public Long montantTotalEnCentimes;
	public String libelle;
	public String identifiantCompteDepense;
	public String identifiantCompteRecette;
	public List<DetailOperationBasicResponseDto> detailsOperation;
}

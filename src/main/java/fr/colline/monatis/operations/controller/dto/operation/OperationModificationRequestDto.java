package fr.colline.monatis.operations.controller.dto.operation;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import fr.colline.monatis.operations.controller.dto.detailoperation.DetailOperationRequestDto;

public class OperationModificationRequestDto implements Serializable{

	private static final long serialVersionUID = -8623646198594089047L;

	public String codeTypeOperation;
	public String numero;
	public String libelle;
	public Timestamp dateValeur;
	public Long montantTotalEnCentimes;
	public String identifiantCompteDepense;
	public String identifiantCompteRecette;

	public List<DetailOperationRequestDto> detailsOperation;
}

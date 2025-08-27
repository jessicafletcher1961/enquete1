package fr.colline.monatis.controller.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class OperationModificationRequestDto implements Serializable{

	private static final long serialVersionUID = -8623646198594089047L;

	public String numero;
	public Timestamp dateValeur;
	public String libelle;
	public Long montantTotalEnCentimes;
	public String identifiantCompteDepense;
	public String identifiantCompteRecette;

	public List<DetailOperationRequestDto> detailsOperation;
}

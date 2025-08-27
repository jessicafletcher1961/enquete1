package fr.colline.monatis.controller.dto;

import java.sql.Timestamp;
import java.util.List;

public class OperationResponseDto {

	public Long id;
	public String numero;
	public Timestamp dateValeur;
	public String libelle;
	public CompteResponseDto compteDepense;
	public CompteResponseDto compteRecette;
	public Long montantTotalEnCentimes;
	
	public List<DetailOperationResponseDto> detailsOperation;
}

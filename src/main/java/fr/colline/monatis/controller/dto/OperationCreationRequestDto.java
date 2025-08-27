package fr.colline.monatis.controller.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class OperationCreationRequestDto implements Serializable {

	private static final long serialVersionUID = 3142818869051389552L;

	public String numero;
	public Timestamp dateValeur;
	public String libelle;
	public Long montantTotalEnCentimes;
	public String identifiantCompteDepense;
	public String identifiantCompteRecette;

	public String nomSousCategorie;
	public List<String> nomsBeneficiaires;
}

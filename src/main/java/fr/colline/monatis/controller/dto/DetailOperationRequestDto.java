package fr.colline.monatis.controller.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class DetailOperationRequestDto implements Serializable {

	private static final long serialVersionUID = 2648157917286552091L;

	public Integer sequence;
	
	public Long montantDetailEnCentimes;
	public Timestamp dateComptabilisation;
	public String libelle;

	public String nomSousCategorie;
	public List<String> nomsBeneficiaires;
}

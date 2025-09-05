package fr.colline.monatis.operations.controller.dto.detailoperation;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class DetailOperationRequestDto implements Serializable {

	private static final long serialVersionUID = 2648157917286552091L;

	public Long id;
	public Integer sequence;
	public String libelle;
	public Timestamp dateComptabilisation;
	public Long montantDetailEnCentimes;
	public String nomSousCategorie;
	public List<String> nomsBeneficiaires;
}

package fr.colline.monatis.operations.controller.dto.detailoperation;

import java.io.Serializable;
import java.sql.Timestamp;

public class DetailOperationSimpleResponseDto implements Serializable {

	private static final long serialVersionUID = -7282455283259186385L;

	public Long id;
	public Integer sequence;
	public String libelle;
	public Timestamp dateComptabilisation;
	public Long montantDetailEnCentimes;
}

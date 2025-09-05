package fr.colline.monatis.operations.controller.dto.detailoperation;

import java.io.Serializable;
import java.sql.Timestamp;

public class DetailOperationBasicResponseDto implements Serializable {

	private static final long serialVersionUID = -1014713757868418416L;

	public Integer sequence;
	public String libelle;
	public Timestamp dateComptabilisation;
	public Long montantDetailEnCentimes;
}

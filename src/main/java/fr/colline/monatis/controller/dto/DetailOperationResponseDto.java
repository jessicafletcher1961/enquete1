package fr.colline.monatis.controller.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class DetailOperationResponseDto implements Serializable {

	private static final long serialVersionUID = -8722453135180610357L;
	
	public Long id;
	public int sequence;
	public Long montantDetailEnCentimes;
	public Timestamp dateComptabilisation;
	public String libelle;
	
	public SousCategorieResponseDto sousCategorie;
	public List<BeneficiaireResponseDto> beneficiaires;
}

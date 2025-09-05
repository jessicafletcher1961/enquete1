package fr.colline.monatis.operations.controller.dto.detailoperation;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import fr.colline.monatis.references.controller.dto.beneficiaires.BeneficiaireBasicResponseDto;
import fr.colline.monatis.references.controller.dto.souscategories.SousCategorieBasicResponseDto;

public class DetailOperationDetailedResponseDto implements Serializable {

	private static final long serialVersionUID = -8722453135180610357L;
	
	public Long id;
	public int sequence;
	public String libelle;
	public Timestamp dateComptabilisation;
	public Long montantDetailEnCentimes;
	public SousCategorieBasicResponseDto sousCategorie;
	public List<BeneficiaireBasicResponseDto> beneficiaires;
}

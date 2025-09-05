package fr.colline.monatis.comptes.controller.dto.tiers;

import java.io.Serializable;

import fr.colline.monatis.comptes.CategorieCompte;
import fr.colline.monatis.comptes.controller.dto.CompteResponseDto;
import fr.colline.monatis.comptes.controller.mapper.CategorieCompteDtoMapper;

public class CompteTiersBasicResponseDto extends CompteResponseDto implements Serializable {

	private static final long serialVersionUID = 7340916415216102470L;

	public CompteTiersBasicResponseDto() {
		
		categorieCompte = CategorieCompteDtoMapper.modelToResponseDto(
				CategorieCompte.TIERS);
	}
}

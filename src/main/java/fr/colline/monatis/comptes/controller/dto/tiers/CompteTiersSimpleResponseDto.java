package fr.colline.monatis.comptes.controller.dto.tiers;

import java.io.Serializable;

import fr.colline.monatis.comptes.CategorieCompte;
import fr.colline.monatis.comptes.controller.dto.CompteResponseDto;
import fr.colline.monatis.comptes.controller.mapper.CategorieCompteDtoMapper;

public class CompteTiersSimpleResponseDto extends CompteResponseDto implements Serializable {

	private static final long serialVersionUID = 5225022472405377658L;

	public Long id;

	public CompteTiersSimpleResponseDto() {
		
		categorieCompte = CategorieCompteDtoMapper.modelToResponseDto(
				CategorieCompte.TIERS);
	}
}

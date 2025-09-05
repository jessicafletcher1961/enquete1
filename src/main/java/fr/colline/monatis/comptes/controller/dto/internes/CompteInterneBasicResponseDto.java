package fr.colline.monatis.comptes.controller.dto.internes;

import java.io.Serializable;

import fr.colline.monatis.comptes.CategorieCompte;
import fr.colline.monatis.comptes.controller.dto.CompteResponseDto;
import fr.colline.monatis.comptes.controller.mapper.CategorieCompteDtoMapper;

public class CompteInterneBasicResponseDto extends CompteResponseDto implements Serializable {

	private static final long serialVersionUID = 5856428020795862939L;

	public CompteInterneBasicResponseDto() {
		
		categorieCompte = CategorieCompteDtoMapper.modelToResponseDto(
				CategorieCompte.INTERNE);
	}
}

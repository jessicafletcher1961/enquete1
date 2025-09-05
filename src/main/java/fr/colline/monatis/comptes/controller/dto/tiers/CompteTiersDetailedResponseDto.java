package fr.colline.monatis.comptes.controller.dto.tiers;

import java.io.Serializable;

import fr.colline.monatis.comptes.CategorieCompte;
import fr.colline.monatis.comptes.TypeFonctionnementCompte;
import fr.colline.monatis.comptes.controller.dto.CompteResponseDto;
import fr.colline.monatis.comptes.controller.dto.TypeFonctionnementCompteResponseDto;
import fr.colline.monatis.comptes.controller.mapper.CategorieCompteDtoMapper;
import fr.colline.monatis.comptes.controller.mapper.TypeFonctionnementCompteDtoMapper;

public class CompteTiersDetailedResponseDto extends CompteResponseDto implements Serializable {

	private static final long serialVersionUID = -8266113439223832305L;

	public Long id;
	
	public TypeFonctionnementCompteResponseDto typeFonctionnement; 

	public CompteTiersDetailedResponseDto() {
		
		this.categorieCompte = CategorieCompteDtoMapper.modelToResponseDto(
				CategorieCompte.TIERS);
		this.typeFonctionnement = TypeFonctionnementCompteDtoMapper.modelToResponseDto(
				TypeFonctionnementCompte.TIERS);
	}
}

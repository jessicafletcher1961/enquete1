package fr.colline.monatis.comptes.controller.dto.internes;

import java.io.Serializable;
import java.sql.Timestamp;

import fr.colline.monatis.comptes.CategorieCompte;
import fr.colline.monatis.comptes.controller.dto.CompteResponseDto;
import fr.colline.monatis.comptes.controller.mapper.CategorieCompteDtoMapper;

public class CompteInterneSimpleResponseDto extends CompteResponseDto implements Serializable {

	private static final long serialVersionUID = 7723843322713142392L;

	public Long id;
	public Timestamp dateSoldeInitial;
	public Long montantSoldeInitialEnCentimes;
	public TypeCompteInterneResponseDto typeCompteInterne;
	
	public CompteInterneSimpleResponseDto() {
		
		this.categorieCompte = CategorieCompteDtoMapper.modelToResponseDto(
				CategorieCompte.INTERNE);
	}
}

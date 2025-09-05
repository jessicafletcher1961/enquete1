package fr.colline.monatis.comptes.controller.dto.internes;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import fr.colline.monatis.comptes.CategorieCompte;
import fr.colline.monatis.comptes.controller.dto.CompteResponseDto;
import fr.colline.monatis.comptes.controller.dto.TypeFonctionnementCompteResponseDto;
import fr.colline.monatis.comptes.controller.mapper.CategorieCompteDtoMapper;
import fr.colline.monatis.references.controller.dto.banques.BanqueBasicResponseDto;
import fr.colline.monatis.references.controller.dto.titulaires.TitulaireBasicResponseDto;

public class CompteInterneDetailedResponseDto extends CompteResponseDto implements Serializable {

	private static final long serialVersionUID = -6251189489199865586L;
	
	public Long id;
	public Timestamp dateSoldeInitial;
	public Long montantSoldeInitialEnCentimes;
	public TypeCompteInterneResponseDto typeCompteInterne;
	public TypeFonctionnementCompteResponseDto typeFonctionnementCompte;
	public BanqueBasicResponseDto banque;
	public List<TitulaireBasicResponseDto> titulaires;
	
	public CompteInterneDetailedResponseDto() {
		
		this.categorieCompte = CategorieCompteDtoMapper.modelToResponseDto(
				CategorieCompte.INTERNE);
	}
}

package fr.colline.monatis.comptes.controller.dto.internes;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import fr.colline.monatis.comptes.controller.dto.CompteRequestDto;

public class CompteInterneRequestDto extends CompteRequestDto implements Serializable {

	private static final long serialVersionUID = -2903673429241830093L;
	
	public Timestamp dateSoldeInitial;
	public Long montantSoldeInitialEnCentimes;
	public String codeTypeCompteInterne;
	public String nomBanque;
	public List<String> nomsTitulaires;
}

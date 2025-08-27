package fr.colline.monatis.controller.dto;

import java.io.Serializable;
import java.util.List;

public class BanqueResponseDto implements Serializable {

	private static final long serialVersionUID = 4737243715513175497L;

	public Long id;
	public String nom;
	public String libelle;

	public List<CompteInterneResponseDto> comptesInternes;
}

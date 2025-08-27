package fr.colline.monatis.controller.dto;

import java.io.Serializable;
import java.util.List;

public class TitulaireResponseDto implements Serializable {

	private static final long serialVersionUID = -3175805347965441873L;

	public Long id;
	public String nom;
	public String libelle;

	public List<CompteInterneResponseDto> comptesInternes;
}

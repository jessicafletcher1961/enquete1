package fr.colline.monatis.references.controller.dto.titulaires;

import java.io.Serializable;
import java.util.List;

import fr.colline.monatis.comptes.controller.dto.internes.CompteInterneBasicResponseDto;
import fr.colline.monatis.references.controller.dto.ReferenceResponseDto;

public class TitulaireDetailedResponseDto extends ReferenceResponseDto implements Serializable {

	private static final long serialVersionUID = -3175805347965441873L;

	public Long id;
	public List<CompteInterneBasicResponseDto> comptesInternes;
}

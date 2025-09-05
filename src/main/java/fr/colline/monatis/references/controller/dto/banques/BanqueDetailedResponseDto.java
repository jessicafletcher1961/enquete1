package fr.colline.monatis.references.controller.dto.banques;

import java.io.Serializable;
import java.util.List;

import fr.colline.monatis.comptes.controller.dto.internes.CompteInterneSimpleResponseDto;
import fr.colline.monatis.references.controller.dto.ReferenceResponseDto;

public class BanqueDetailedResponseDto extends ReferenceResponseDto implements Serializable {

	private static final long serialVersionUID = 4737243715513175497L;

	public Long id;
	public List<CompteInterneSimpleResponseDto> comptesInternes;
}

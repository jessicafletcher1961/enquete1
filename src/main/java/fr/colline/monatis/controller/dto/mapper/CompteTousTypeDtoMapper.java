package fr.colline.monatis.controller.dto.mapper;

import fr.colline.monatis.controller.dto.CompteResponseDto;
import fr.colline.monatis.model.Compte;
import fr.colline.monatis.model.CompteInterne;
import fr.colline.monatis.model.CompteTiers;

public class CompteTousTypeDtoMapper {

	public static CompteResponseDto simpleModelToResponseDto(Compte compte) {

		if ( CompteInterne.class.isAssignableFrom(compte.getClass()) ) {
			return CompteInterneDtoMapper.simpleModelToResponseDto((CompteInterne) compte);
		}
		else if (CompteTiers.class.isAssignableFrom(compte.getClass()) ) {
			return CompteTiersDtoMapper.simpleModelToResponseDto((CompteTiers) compte);
		}
		else return null;
	}

	public static CompteResponseDto detailedModelToResponseDto(Compte compte) {

		if ( CompteInterne.class.isAssignableFrom(compte.getClass()) ) {
			return CompteInterneDtoMapper.detailedModelToResponseDto((CompteInterne) compte);
		}
		else if (CompteTiers.class.isAssignableFrom(compte.getClass()) ) {
			return CompteTiersDtoMapper.detailedModelToResponseDto((CompteTiers) compte);
		}
		else return null;
	}
}

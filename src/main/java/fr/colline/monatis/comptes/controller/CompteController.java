package fr.colline.monatis.comptes.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.colline.monatis.comptes.controller.dto.CompteResponseDto;
import fr.colline.monatis.comptes.controller.mapper.CompteDtoMapper;
import fr.colline.monatis.comptes.model.Compte;
import fr.colline.monatis.comptes.service.CompteTousTypeService;
import fr.colline.monatis.exception.ControllerErreur;
import fr.colline.monatis.exception.ControllerException;
import fr.colline.monatis.exception.ServiceTechniqueException;

@RestController
@RequestMapping("/monatis/comptes")
@Transactional
public class CompteController {

	@Autowired private CompteTousTypeService compteTousTypeService;

	@GetMapping("/all")
	public List<CompteResponseDto> getAllComptes() 
			throws ControllerException {

		try {
			List<CompteResponseDto> resultat = new ArrayList<>();
			Sort tri = Sort.by("identifiant");
			List<Compte> liste = compteTousTypeService.rechercherTous(tri);
			for ( Compte compte : liste ) {
				resultat.add(CompteDtoMapper.modelToDetailedResponseDto(compte));
			}
			return resultat;
		} 
		catch (ServiceTechniqueException e) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		}
	}
}

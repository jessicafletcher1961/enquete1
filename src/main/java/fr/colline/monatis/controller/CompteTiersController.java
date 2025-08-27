package fr.colline.monatis.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.colline.monatis.controller.dto.CompteTiersRequestDto;
import fr.colline.monatis.controller.dto.CompteTiersResponseDto;
import fr.colline.monatis.controller.dto.mapper.CompteTiersDtoMapper;
import fr.colline.monatis.exception.ControllerErreur;
import fr.colline.monatis.exception.ControllerException;
import fr.colline.monatis.exception.ServiceFonctionnelleException;
import fr.colline.monatis.exception.ServiceTechniqueException;
import fr.colline.monatis.model.CompteTiers;
import fr.colline.monatis.service.CompteTiersService;

@RestController
@RequestMapping("/monatis/comptes/tiers")
@Transactional
public class CompteTiersController {

	@Autowired private CompteTiersService compteTiersService;

	@GetMapping("/all")
	public List<CompteTiersResponseDto> getAllComptesTiers() 
			throws ControllerException {

		try {
			List<CompteTiersResponseDto> resultat = new ArrayList<>();
			Sort tri = Sort.by("identifiant");
			List<CompteTiers> liste = compteTiersService.rechercherTous(tri);
			for ( CompteTiers compteTiers : liste ) {
				resultat.add(CompteTiersDtoMapper.detailedModelToResponseDto(compteTiers));
			}
			return resultat;
		} 
		catch (ServiceTechniqueException e) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		}
	}

	@GetMapping("/get/{identifiant}")
	public CompteTiersResponseDto getCompteTiersParIdentifiant(
			@PathVariable(name = "identifiant") String identifiant) 
					throws ControllerException {

		Assert.hasText(identifiant, "L'identifiant du compte tiers est obligatoire");

		try {
			CompteTiers compteTiers = compteTiersService.rechercherParIdentifiant(identifiant);
			if ( compteTiers == null ) {
				throw new ControllerException(
						ControllerErreur.COMPTE_TIERS_NON_TROUVE_PAR_IDENTIFIANT,
						identifiant);
			}
			return CompteTiersDtoMapper.detailedModelToResponseDto(compteTiers);
		} 
		catch (ServiceTechniqueException e) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		}
	}

	@PostMapping("/new")
	public CompteTiersResponseDto creerCompteTiers(
			@RequestBody CompteTiersRequestDto dto) 
					throws ControllerException {

		try {
			CompteTiers compteTiers = new CompteTiers();
			compteTiers = requestDtoToModel(compteTiers, dto);
			compteTiers = compteTiersService.creerCompte(compteTiers);
			return CompteTiersDtoMapper.detailedModelToResponseDto(compteTiers);
		} 
		catch (ServiceTechniqueException e) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		} 
		catch (ServiceFonctionnelleException e) {
			throw new ControllerException(
					e,
					ControllerErreur.COMPTE_TIERS_CREATION_PROBLEME,
					dto.identifiant);
		}
	}

	@PutMapping("/mod/{identifiant}")
	public CompteTiersResponseDto modifierCompteTiers(
			@PathVariable(name = "identifiant") String identifiantInitial,
			@RequestBody CompteTiersRequestDto dto) 
					throws ControllerException {

		Assert.hasText(identifiantInitial, "L'identifiant du compte interne est obligatoire");

		try {
			CompteTiers compteTiers = compteTiersService.rechercherParIdentifiant(identifiantInitial);
			if ( compteTiers == null ) {
				throw new ControllerException(
						ControllerErreur.COMPTE_TIERS_NON_TROUVE_PAR_IDENTIFIANT,
						identifiantInitial);
			}
			compteTiers = requestDtoToModel(compteTiers, dto);
			compteTiers = compteTiersService.modifierCompte(compteTiers);
			return CompteTiersDtoMapper.detailedModelToResponseDto(compteTiers);
		}
		catch ( ServiceTechniqueException e ) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		} 
		catch (ServiceFonctionnelleException e) {
			throw new ControllerException(
					e,
					ControllerErreur.COMPTE_TIERS_MODIFICATION_PROBLEME,
					identifiantInitial);
		}
	}

	@DeleteMapping("/del/{identifiant}")
	public void supprimerCompteTiers(
			@PathVariable(name = "identifiant") String identifiantInitial) 
					throws ControllerException {

		Assert.hasText(identifiantInitial, "L'identifiant du compte tiers est obligatoire");

		try {
			CompteTiers compteTiers = compteTiersService.rechercherParIdentifiant(identifiantInitial);
			if ( compteTiers == null ) {
				throw new ControllerException(
						ControllerErreur.COMPTE_TIERS_NON_TROUVE_PAR_IDENTIFIANT,
						identifiantInitial);
			}
			compteTiersService.supprimerCompte(compteTiers.getId());
		}
		catch ( ServiceTechniqueException e ) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		} 
		catch (ServiceFonctionnelleException e) {
			throw new ControllerException(
					e,
					ControllerErreur.COMPTE_TIERS_SUPPRESSION_PROBLEME,
					identifiantInitial);
		}
	}

	private CompteTiers requestDtoToModel(
			CompteTiers compteTiers,
			CompteTiersRequestDto dto) {

		if ( dto.identifiant != null ) compteTiers.setIdentifiant(dto.identifiant);
		if ( dto.libelle != null ) compteTiers.setLibelle(dto.libelle);

		return compteTiers;
	}
}

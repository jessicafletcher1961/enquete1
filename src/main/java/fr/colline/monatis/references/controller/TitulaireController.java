package fr.colline.monatis.references.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.colline.monatis.exception.ControllerErreur;
import fr.colline.monatis.exception.ControllerException;
import fr.colline.monatis.exception.ServiceFonctionnelleException;
import fr.colline.monatis.exception.ServiceTechniqueException;
import fr.colline.monatis.references.controller.dto.titulaires.TitulaireRequestDto;
import fr.colline.monatis.references.controller.dto.titulaires.TitulaireBasicResponseDto;
import fr.colline.monatis.references.controller.dto.titulaires.TitulaireDetailedResponseDto;
import fr.colline.monatis.references.controller.mapper.TitulaireDtoMapper;
import fr.colline.monatis.references.model.Titulaire;
import fr.colline.monatis.references.service.TitulaireService;

@RestController
@RequestMapping("/monatis/references/titulaire")
@Transactional
public class TitulaireController {

	@Autowired private TitulaireService titulaireService;

	@GetMapping("/all")
	public List<TitulaireBasicResponseDto> getAllTitulaires() 
			throws ControllerException {

		try {
			List<TitulaireBasicResponseDto> resultat = new ArrayList<>();
			Sort tri = Sort.by("nom");
			List<Titulaire> liste = titulaireService.rechercherTous(tri);
			for ( Titulaire titulaire : liste ) {
				resultat.add(TitulaireDtoMapper.modelToBasicResponseDto(titulaire));
			}
			return resultat;
		} 
		catch (ServiceTechniqueException e) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		}
	}

	@GetMapping("/get/{nom}")
	public TitulaireDetailedResponseDto getTitulaireParNom(@PathVariable(name = "nom") String nom) 
			throws ControllerException {

		nom = verifierNom(nom);

		try {
			Titulaire titulaire = titulaireService.rechercherParNom(nom);
			if ( titulaire == null ) {
				throw new ControllerException(
						ControllerErreur.TITULAIRE_NON_TROUVE_PAR_NOM,
						nom);
			}
			return TitulaireDtoMapper.modelToDetailedResponseDto(titulaire);
		} 
		catch (ServiceTechniqueException e) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		}
	}

	@PostMapping("/new")
	public TitulaireDetailedResponseDto creerTitulaire(@RequestBody TitulaireRequestDto dto) 
			throws ControllerException {

		try {
			Titulaire titulaire = new Titulaire();
			titulaire = creationRequestDtoToModel(titulaire, dto);
			titulaire = titulaireService.creerReference(titulaire);
			return TitulaireDtoMapper.modelToDetailedResponseDto(titulaire);
		} 
		catch (ServiceTechniqueException e) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		} 
		catch (ServiceFonctionnelleException e) {
			throw new ControllerException(
					e,
					ControllerErreur.TITULAIRE_CREATION_PROBLEME,
					dto.nom);
		}
	}

	@PutMapping("/mod/{nom}")
	public TitulaireDetailedResponseDto modifierTitulaire(
			@PathVariable(name = "nom") String nom,
			@RequestBody TitulaireRequestDto dto) 
					throws ControllerException {

		nom = verifierNom(nom);
		
		try {
			Titulaire titulaire = titulaireService.rechercherParNom(nom);
			if ( titulaire == null ) {
				throw new ControllerException(
						ControllerErreur.TITULAIRE_NON_TROUVE_PAR_NOM,
						nom);
			}
			titulaire = modificationRequestDtoToModel(titulaire, dto);
			titulaire = titulaireService.modifierReference(titulaire);
			return TitulaireDtoMapper.modelToDetailedResponseDto(titulaire);
		} 
		catch (ServiceTechniqueException e) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		} 
		catch (ServiceFonctionnelleException e) {
			throw new ControllerException(
					e,
					ControllerErreur.TITULAIRE_MODIFICATION_PROBLEME,
					nom);
		}
	}

	@DeleteMapping("/del/{nom}")
	public void supprimerTitulaire(@PathVariable(name = "nom") String nom) 
					throws ControllerException {

		nom = verifierNom(nom);

		try  {
			Titulaire titulaire = titulaireService.rechercherParNom(nom);
			if ( titulaire == null ) {
				throw new ControllerException(
						ControllerErreur.TITULAIRE_NON_TROUVE_PAR_NOM,
						nom);
			}
			titulaireService.supprimerReference(titulaire.getId());
		} 
		catch (ServiceTechniqueException e) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		} 
		catch (ServiceFonctionnelleException e) {
			throw new ControllerException(
					e,
					ControllerErreur.TITULAIRE_SUPPRESSION_PROBLEME,
					nom);
		}
	}
	
	private Titulaire creationRequestDtoToModel(
			Titulaire titulaire,
			TitulaireRequestDto dto) 
					throws ControllerException {
		
		titulaire.setNom(verifierNom(dto.nom));
		titulaire.setLibelle(verifierLibelle(dto.libelle));
		
		return titulaire;
	}

	private Titulaire modificationRequestDtoToModel(
			Titulaire titulaire,
			TitulaireRequestDto dto) 
					throws ControllerException {
		
		if ( dto.nom != null ) titulaire.setNom(verifierNom(dto.nom));
		if ( dto.libelle != null ) titulaire.setLibelle(verifierLibelle(dto.libelle));
		
		return titulaire;
	}
	
	private String verifierNom(String nom) throws ControllerException {
		
		if ( nom == null || nom.isBlank() ) { 
			throw new ControllerException(
					ControllerErreur.TITULAIRE_NOM_OBLIGATOIRE);
		}
		return nom;
	}
	
	private String verifierLibelle(String libelle) {
		
		if ( libelle == null || libelle.isBlank() ) {

			libelle = null;
		}
		return libelle;
	}
}

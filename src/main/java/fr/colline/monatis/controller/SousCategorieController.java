package fr.colline.monatis.controller;

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

import fr.colline.monatis.controller.dto.SousCategorieRequestDto;
import fr.colline.monatis.controller.dto.SousCategorieResponseDto;
import fr.colline.monatis.controller.dto.mapper.SousCategorieDtoMapper;
import fr.colline.monatis.exception.ControllerErreur;
import fr.colline.monatis.exception.ControllerException;
import fr.colline.monatis.exception.ServiceFonctionnelleException;
import fr.colline.monatis.exception.ServiceTechniqueException;
import fr.colline.monatis.model.Categorie;
import fr.colline.monatis.model.SousCategorie;
import fr.colline.monatis.service.CategorieService;
import fr.colline.monatis.service.SousCategorieService;

@RestController
@RequestMapping("/monatis/references/souscategorie")
@Transactional
public class SousCategorieController {

	@Autowired private SousCategorieService sousCategorieService;
	@Autowired private CategorieService categorieService;

	@GetMapping("/all")
	public List<SousCategorieResponseDto> getAllSousCategorie() 
			throws ControllerException {

		try {
			List<SousCategorieResponseDto> resultat = new ArrayList<>();
			Sort tri = Sort.by("nom");
			List<SousCategorie> liste = sousCategorieService.rechercherTous(tri);
			for ( SousCategorie sousCategorie : liste ) {
				resultat.add(SousCategorieDtoMapper.detailedModelToResponseDto(sousCategorie));
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
	public SousCategorieResponseDto getSousCategorieParNom(@PathVariable(name = "nom") String nom) 
			throws ControllerException {

		nom = verifierNom(nom);

		try {
			SousCategorie sousCategorie = sousCategorieService.rechercherParNom(nom);
			if ( sousCategorie == null ) {
				throw new ControllerException(
						ControllerErreur.SOUS_CATEGORIE_NON_TROUVEE_PAR_NOM,
						nom);
			}
			return SousCategorieDtoMapper.detailedModelToResponseDto(sousCategorie);
		} 
		catch (ServiceTechniqueException e) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		}
	}

	@PostMapping("/new")
	public SousCategorieResponseDto creerSousCategorie(@RequestBody SousCategorieRequestDto dto) 
			throws ControllerException {

		try {
			SousCategorie sousCategorie = new SousCategorie();
			sousCategorie = creationRequestDtoToModel(sousCategorie, dto);
			sousCategorie = sousCategorieService.creerReference(sousCategorie);
			return SousCategorieDtoMapper.detailedModelToResponseDto(sousCategorie);
		} 
		catch (ServiceTechniqueException e) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		} 
		catch (ServiceFonctionnelleException e) {
			throw new ControllerException(
					e,
					ControllerErreur.SOUS_CATEGORIE_CREATION_PROBLEME,
					dto.nom);
		}
	}

	@PutMapping("/mod/{nom}")
	public SousCategorieResponseDto modifierSousCategorie(
			@PathVariable(name = "nom") String nom,
			@RequestBody SousCategorieRequestDto dto) 
					throws ControllerException {

		nom = verifierNom(nom);

		try {
			SousCategorie sousCategorie = sousCategorieService.rechercherParNom(nom);
			if ( sousCategorie == null ) {
				throw new ControllerException(
						ControllerErreur.SOUS_CATEGORIE_NON_TROUVEE_PAR_NOM,
						nom);
			}
			sousCategorie = modificationRequestDtoToModel(sousCategorie, dto);
			sousCategorie = sousCategorieService.modifierReference(sousCategorie);
			return SousCategorieDtoMapper.detailedModelToResponseDto(sousCategorie);
		} 
		catch (ServiceTechniqueException e) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		} 
		catch (ServiceFonctionnelleException e) {
			throw new ControllerException(
					e,
					ControllerErreur.SOUS_CATEGORIE_MODIFICATION_PROBLEME,
					nom);
		}
	}

	@DeleteMapping("/del/{nom}")
	public void supprimerSousCategorie(@PathVariable(name = "nom") String nom) 
			throws ControllerException {

		nom = verifierNom(nom);

		try {
			SousCategorie sousCategorie = sousCategorieService.rechercherParNom(nom);
			if ( sousCategorie == null ) {
				throw new ControllerException(
						ControllerErreur.SOUS_CATEGORIE_NON_TROUVEE_PAR_NOM,
						nom);
			}
			sousCategorieService.supprimerReference(sousCategorie.getId());
		} 
		catch (ServiceTechniqueException e) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		} 
		catch (ServiceFonctionnelleException e) {
			throw new ControllerException(
					e,
					ControllerErreur.SOUS_CATEGORIE_SUPPRESSION_PROBLEME,
					nom);
		}
	}

	private SousCategorie creationRequestDtoToModel(
			SousCategorie sousCategorie,
			SousCategorieRequestDto dto) 
					throws ControllerException {

		sousCategorie.setNom(verifierNom(dto.nom));
		sousCategorie.setLibelle(verifierLibelle(dto.libelle));
		sousCategorie.changerCategorie(verifierCategorieEnregistree(dto.nomCategorie));

		return sousCategorie;
	}

	private SousCategorie modificationRequestDtoToModel(
			SousCategorie sousCategorie,
			SousCategorieRequestDto dto) 
					throws ControllerException {

		if ( dto.nom != null ) sousCategorie.setNom(verifierNom(dto.nom));
		if ( dto.libelle != null ) sousCategorie.setLibelle(verifierLibelle(dto.libelle));
		if ( dto.nomCategorie != null ) sousCategorie.changerCategorie(verifierCategorieEnregistree(dto.nomCategorie));

		return sousCategorie;
	}

	private String verifierNom(String nom) throws ControllerException {

		if ( nom == null || nom.isBlank() ) { 
			throw new ControllerException(
					ControllerErreur.SOUS_CATEGORIE_NOM_OBLIGATOIRE);
		}
		return nom;
	}

	private String verifierLibelle(String libelle) {

		return libelle;
	}

	private Categorie verifierCategorieEnregistree(String nomCategorie) 
			throws ControllerException {

		if ( nomCategorie == null 
				|| nomCategorie.isBlank() ) {
			throw new ControllerException(
					ControllerErreur.SOUS_CATEGORIE_NOM_CATEGORIE_OBLIGATOIRE);
		}

		try {
			Categorie categorie = categorieService.rechercherParNom(nomCategorie);
			if ( categorie == null ) {
				throw new ControllerException(
						ControllerErreur.CATEGORIE_NON_TROUVEE_PAR_NOM, 
						nomCategorie);
			}
			return categorie;
		} 
		catch (ServiceTechniqueException e) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		}
	}
}
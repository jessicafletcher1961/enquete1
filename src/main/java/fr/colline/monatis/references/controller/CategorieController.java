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
import fr.colline.monatis.references.controller.dto.categories.CategorieDetailedResponseDto;
import fr.colline.monatis.references.controller.dto.categories.CategorieRequestDto;
import fr.colline.monatis.references.controller.mapper.CategorieDtoMapper;
import fr.colline.monatis.references.model.Categorie;
import fr.colline.monatis.references.service.CategorieService;

@RestController
@RequestMapping("/monatis/references/categorie")
@Transactional
public class CategorieController {

	@Autowired private CategorieService categorieService;

	@GetMapping("/all")
	public List<CategorieDetailedResponseDto> getAllCategories() 
			throws ControllerException {

		try {
			List<CategorieDetailedResponseDto> resultat = new ArrayList<>();
			Sort tri = Sort.by("nom");
			List<Categorie> liste = categorieService.rechercherTous(tri);
			for ( Categorie categorie : liste ) {
				resultat.add(CategorieDtoMapper.modelToDetailedResponseDto(categorie));
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
	public CategorieDetailedResponseDto getCategorieParNom(@PathVariable String nom) 
			throws ControllerException {

		nom = verifierNom(nom);

		try {
			Categorie categorie = categorieService.rechercherParNom(nom);
			if ( categorie == null ) {
				throw new ControllerException(
						ControllerErreur.CATEGORIE_NON_TROUVEE_PAR_NOM,
						nom);
			}
			return CategorieDtoMapper.modelToDetailedResponseDto(categorie);
		} 
		catch (ServiceTechniqueException e) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		}
	}

	@PostMapping("/new")
	public CategorieDetailedResponseDto creerCategorie(
			@RequestBody CategorieRequestDto dto) 
					throws ControllerException {

		try {
			Categorie categorie = new Categorie();
			categorie = creationRequestDtoToModel(categorie, dto);
			categorie = categorieService.creerReference(categorie);
			return CategorieDtoMapper.modelToDetailedResponseDto(categorie);
		} 
		catch (ServiceTechniqueException e) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		} 
		catch (ServiceFonctionnelleException e) {
			throw new ControllerException(
					e,
					ControllerErreur.CATEGORIE_CREATION_PROBLEME,
					dto.nom);
		}
	}

	@PutMapping("/mod/{nom}")
	public CategorieDetailedResponseDto modifierCategorie(
			@PathVariable(name = "nom") String nom,
			@RequestBody CategorieRequestDto dto) 
					throws ControllerException {

		nom = verifierNom(nom);
		
		try {
			Categorie categorie = categorieService.rechercherParNom(nom);
			if ( categorie == null ) {
				throw new ControllerException(
						ControllerErreur.CATEGORIE_NON_TROUVEE_PAR_NOM,
						nom);
			}
			categorie = modificationRequestDtoToModel(categorie, dto);
			categorie = categorieService.modifierReference(categorie);
			return CategorieDtoMapper.modelToDetailedResponseDto(categorie);
		} 
		catch (ServiceTechniqueException e) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		} 
		catch (ServiceFonctionnelleException e) {
			throw new ControllerException(
					e,
					ControllerErreur.CATEGORIE_MODIFICATION_PROBLEME,
					nom);
		}
	}

	@DeleteMapping("/del/{nom}")
	public void supprimerCategorie(
			@PathVariable String nom) 
					throws ControllerException {

		nom = verifierNom(nom);

		try  {
			Categorie categorie = categorieService.rechercherParNom(nom);
			if ( categorie == null ) {
				throw new ControllerException(
						ControllerErreur.CATEGORIE_NON_TROUVEE_PAR_NOM,
						nom);
			}
			categorieService.supprimerReference(categorie.getId());
		} 
		catch (ServiceTechniqueException e) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		} 
		catch (ServiceFonctionnelleException e) {
			throw new ControllerException(
					e,
					ControllerErreur.CATEGORIE_SUPPRESSION_PROBLEME,
					nom);
		}
	}
	
	private Categorie creationRequestDtoToModel(
			Categorie categorie,
			CategorieRequestDto dto) 
					throws ControllerException {
		
		categorie.setNom(verifierNom(dto.nom));
		categorie.setLibelle(verifierLibelle(dto.libelle));
		
		return categorie;
	}
	
	private Categorie modificationRequestDtoToModel(
			Categorie categorie,
			CategorieRequestDto dto) 
					throws ControllerException {
		
		if ( dto.nom != null ) categorie.setNom(verifierNom(dto.nom));
		if ( dto.libelle != null ) categorie.setLibelle(verifierLibelle(dto.libelle));
		
		return categorie;
	}
	
	private String verifierNom(String nom) throws ControllerException {
		
		if ( nom == null || nom.isBlank() ) { 
			throw new ControllerException(
					ControllerErreur.CATEGORIE_NOM_OBLIGATOIRE);
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

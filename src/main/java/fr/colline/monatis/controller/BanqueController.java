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

import fr.colline.monatis.controller.dto.BanqueRequestDto;
import fr.colline.monatis.controller.dto.BanqueResponseDto;
import fr.colline.monatis.controller.dto.mapper.BanqueDtoMapper;
import fr.colline.monatis.exception.ControllerErreur;
import fr.colline.monatis.exception.ControllerException;
import fr.colline.monatis.exception.ServiceFonctionnelleException;
import fr.colline.monatis.exception.ServiceTechniqueException;
import fr.colline.monatis.model.Banque;
import fr.colline.monatis.service.BanqueService;

@RestController
@RequestMapping("/monatis/references/banque")
@Transactional
public class BanqueController {

	@Autowired private BanqueService banqueService;

	@GetMapping("/all")
	public List<BanqueResponseDto> getAllBanques() 
			throws ControllerException {

		try {
			List<BanqueResponseDto> resultat = new ArrayList<>();
			Sort tri = Sort.by("nom");
			List<Banque> liste = banqueService.rechercherTous(tri);
			for ( Banque banque : liste ) {
				resultat.add(BanqueDtoMapper.detailedModelToResponseDto(banque));
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
	public BanqueResponseDto getBanqueParNom(
			@PathVariable(name = "nom") String nom) 
					throws ControllerException {

		nom = verifierNom(nom);

		try {
			Banque banque = banqueService.rechercherParNom(nom);
			if ( banque == null ) {
				throw new ControllerException(
						ControllerErreur.BANQUE_NON_TROUVEE_PAR_NOM,
						nom);
			}
			return BanqueDtoMapper.detailedModelToResponseDto(banque);
		} 
		catch (ServiceTechniqueException e) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		}
	}

	@PostMapping("/new")
	public BanqueResponseDto creerBanque(@RequestBody BanqueRequestDto dto) 
			throws ControllerException {

		try {
			Banque banque = new Banque();
			banque = creationRequestDtoToModel(banque, dto);
			banque = banqueService.creerReference(banque);
			return BanqueDtoMapper.detailedModelToResponseDto(banque);
		} 
		catch (ServiceTechniqueException e) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		} 
		catch (ServiceFonctionnelleException e) {
			throw new ControllerException(
					e,
					ControllerErreur.BANQUE_CREATION_PROBLEME,
					dto.nom);
		}
	}

	@PutMapping("/mod/{nom}")
	public BanqueResponseDto modifierBanque(
			@PathVariable(name = "nom") String nom,
			@RequestBody BanqueRequestDto dto) 
					throws ControllerException {

		nom = verifierNom(nom);
		
		try {
			Banque banque = banqueService.rechercherParNom(nom);
			if ( banque == null ) {
				throw new ControllerException(
						ControllerErreur.BANQUE_NON_TROUVEE_PAR_NOM,
						nom);
			}
			banque = modificationRequestDtoToModel(banque, dto);
			banque = banqueService.modifierReference(banque);
			return BanqueDtoMapper.detailedModelToResponseDto(banque);
		} 
		catch (ServiceTechniqueException e) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		} 
		catch (ServiceFonctionnelleException e) {
			throw new ControllerException(
					e,
					ControllerErreur.BANQUE_MODIFICATION_PROBLEME,
					nom);
		}
	}

	@DeleteMapping("/del/{nom}")
	public void supprimerBanque(@PathVariable(name = "nom") String nom) 
			throws ControllerException {

		nom = verifierNom(nom);

		try  {
			Banque banque = banqueService.rechercherParNom(nom);
			if ( banque == null ) {
				throw new ControllerException(
						ControllerErreur.BANQUE_NON_TROUVEE_PAR_NOM,
						nom);
			}
			banqueService.supprimerReference(banque.getId());
		} 
		catch (ServiceTechniqueException e) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		} 
		catch (ServiceFonctionnelleException e) {
			throw new ControllerException(
					e,
					ControllerErreur.BANQUE_SUPPRESSION_PROBLEME,
					nom);
		}
	}

	private Banque creationRequestDtoToModel(
			Banque banque,
			BanqueRequestDto dto) 
					throws ControllerException {

		banque.setNom(verifierNom(dto.nom));
		banque.setLibelle(verifierLibelle(dto.libelle));

		return banque;
	}
	
	private Banque modificationRequestDtoToModel(
			Banque banque,
			BanqueRequestDto dto) 
					throws ControllerException {

		if ( dto.nom != null ) banque.setNom(verifierNom(dto.nom));
		if ( dto.libelle != null ) banque.setLibelle(verifierLibelle(dto.libelle));

		return banque;
	}
	
	private String verifierNom(String nom) throws ControllerException {
		
		if ( nom == null || nom.isBlank() ) { 
			throw new ControllerException(
					ControllerErreur.BANQUE_NOM_OBLIGATOIRE);
		}
		return nom;
	}
	
	private String verifierLibelle(String libelle) {
		
		return libelle;
	}
}

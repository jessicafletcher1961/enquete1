package fr.colline.monatis.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import fr.colline.monatis.controller.dto.CompteInterneRequestDto;
import fr.colline.monatis.controller.dto.CompteInterneResponseDto;
import fr.colline.monatis.controller.dto.mapper.CompteInterneDtoMapper;
import fr.colline.monatis.exception.ControllerErreur;
import fr.colline.monatis.exception.ControllerException;
import fr.colline.monatis.exception.ServiceFonctionnelleException;
import fr.colline.monatis.exception.ServiceTechniqueException;
import fr.colline.monatis.model.Banque;
import fr.colline.monatis.model.CompteInterne;
import fr.colline.monatis.model.Titulaire;
import fr.colline.monatis.service.BanqueService;
import fr.colline.monatis.service.CompteInterneService;
import fr.colline.monatis.service.TitulaireService;
import fr.colline.monatis.typologie.TypeCompteInterne;

@RestController
@RequestMapping("/monatis/comptes/interne")
@Transactional
public class CompteInterneController {

	@Autowired private CompteInterneService compteInterneService;
	@Autowired private BanqueService banqueService;
	@Autowired private TitulaireService titulaireService;
	
	@GetMapping("/all")
	public List<CompteInterneResponseDto> getAllComptesInternes() 
			throws ControllerException {

		try {
			List<CompteInterneResponseDto> resultat = new ArrayList<>();
			Sort tri = Sort.by("identifiant");
			List<CompteInterne> liste = compteInterneService.rechercherTous(tri);
			for ( CompteInterne compteInterne : liste ) {
				resultat.add(CompteInterneDtoMapper.detailedModelToResponseDto(compteInterne));
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
	public CompteInterneResponseDto getCompteInterneParIdentifiant(@PathVariable(name = "identifiant") String identifiant) 
			throws ControllerException {

		identifiant = verifierIdentifiant(identifiant);

		try {
			CompteInterne compteInterne = compteInterneService.rechercherParIdentifiant(identifiant);
			if ( compteInterne == null ) {
				throw new ControllerException(
						ControllerErreur.COMPTE_INTERNE_NON_TROUVE_PAR_IDENTIFIANT,
						identifiant);
			}
			return CompteInterneDtoMapper.detailedModelToResponseDto(compteInterne);
		} 
		catch (ServiceTechniqueException e) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		}
	}

	@PostMapping("/new")
	public CompteInterneResponseDto creerCompteInterne(@RequestBody CompteInterneRequestDto dto) 
			throws ControllerException {

		try {
			CompteInterne compteInterne = new CompteInterne();
			compteInterne = creationRequestDtoToModel(compteInterne, dto);
			compteInterne = compteInterneService.creerCompte(compteInterne);
			return CompteInterneDtoMapper.detailedModelToResponseDto(compteInterne);
		} 
		catch (ServiceTechniqueException e) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		} 
		catch (ServiceFonctionnelleException e) {
			throw new ControllerException(
					e,
					ControllerErreur.COMPTE_INTERNE_CREATION_PROBLEME,
					dto.identifiant);
		}
	}

	@PutMapping("/mod/{identifiant}")
	public CompteInterneResponseDto modifierCompteInterne(
			@PathVariable(name = "identifiant") String identifiant,
			@RequestBody CompteInterneRequestDto dto) 
					throws ControllerException {

		identifiant = verifierIdentifiant(identifiant);

		try {
			CompteInterne compteInterne = compteInterneService.rechercherParIdentifiant(identifiant);
			if ( compteInterne == null ) {
				throw new ControllerException(
						ControllerErreur.COMPTE_INTERNE_NON_TROUVE_PAR_IDENTIFIANT,
						identifiant);
			}
			compteInterne = modificationRequestDtoToModel(compteInterne, dto);
			compteInterne = compteInterneService.modifierCompte(compteInterne);
			return CompteInterneDtoMapper.detailedModelToResponseDto(compteInterne);
		}
		catch ( ServiceTechniqueException e ) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		} 
		catch (ServiceFonctionnelleException e) {
			throw new ControllerException(
					e,
					ControllerErreur.COMPTE_INTERNE_MODIFICATION_PROBLEME,
					identifiant);
		}
	}

	@DeleteMapping("/del/{identifiant}")
	public void supprimerCompteInterne(@PathVariable(name = "identifiant") String identifiant) 
					throws ControllerException {

		identifiant = verifierIdentifiant(identifiant);

		try {
			CompteInterne compteInterne = compteInterneService.rechercherParIdentifiant(identifiant);
			if ( compteInterne == null ) {
				throw new ControllerException(
						ControllerErreur.COMPTE_INTERNE_NON_TROUVE_PAR_IDENTIFIANT,
						identifiant);
			}
//			compteInterne.changerBanque(null);
//			compteInterne.getTitulaires().clear();
//			compteInterne.changerTitulaires(compteInterne.getTitulaires());
			compteInterneService.supprimerCompte(compteInterne.getId());
		}
		catch ( ServiceTechniqueException e ) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		} 
		catch (ServiceFonctionnelleException e) {
			throw new ControllerException(
					e,
					ControllerErreur.COMPTE_INTERNE_SUPPRESSION_PROBLEME,
					identifiant);
		}
	}
	
	private CompteInterne creationRequestDtoToModel(
			CompteInterne compteInterne,
			CompteInterneRequestDto dto) 
					throws ControllerException {

		compteInterne.setIdentifiant(verifierIdentifiant(dto.identifiant));
		compteInterne.setLibelle(verifierLibelle(dto.libelle));
		compteInterne.setDateSoldeInitial(verifierDateSoldeInitial(dto.dateSoldeInitial));
		compteInterne.setMontantSoldeInitialEnCentimes(verifierMontantSoldeInitial(dto.montantSoldeInitialEnCentimes));
		compteInterne.setTypeCompteInterne(verifierTypeCompteInterne(dto.typeCompteInterne));
		compteInterne.changerBanque(verifierBanqueEnregistree(dto.nomBanque));
		compteInterne.changerTitulaires(verifierTitulairesEnregistres(dto.nomsTitulaires));

		return compteInterne;
	}
	
	private CompteInterne modificationRequestDtoToModel(
			CompteInterne compteInterne,
			CompteInterneRequestDto dto) 
					throws ControllerException {

		if ( dto.identifiant != null ) compteInterne.setIdentifiant(verifierIdentifiant(dto.identifiant));
		if ( dto.libelle != null ) compteInterne.setLibelle(verifierLibelle(dto.libelle));
		if ( dto.dateSoldeInitial != null ) compteInterne.setDateSoldeInitial(verifierDateSoldeInitial(dto.dateSoldeInitial));
		if ( dto.montantSoldeInitialEnCentimes != null ) compteInterne.setMontantSoldeInitialEnCentimes(verifierMontantSoldeInitial(dto.montantSoldeInitialEnCentimes));
		if ( dto.typeCompteInterne != null ) compteInterne.setTypeCompteInterne(verifierTypeCompteInterne(dto.typeCompteInterne));
		if ( dto.nomBanque != null ) compteInterne.changerBanque(verifierBanqueEnregistree(dto.nomBanque));
		if ( dto.nomsTitulaires != null ) compteInterne.changerTitulaires(verifierTitulairesEnregistres(dto.nomsTitulaires));

		return compteInterne;
	}

	private String verifierIdentifiant(String identifiant) throws ControllerException {
		
		if ( identifiant == null || identifiant.isBlank() ) { 
			throw new ControllerException(
					ControllerErreur.COMPTE_INTERNE_IDENTIFIANT_OBLIGATOIRE);
		}
		return identifiant;
	}

	private String verifierLibelle(String libelle) {

		return libelle;
	}

	private Timestamp verifierDateSoldeInitial(Timestamp dateSoldeInitial) {
		
		return dateSoldeInitial;
	}

	private Long verifierMontantSoldeInitial(Long montantSoldeInitialEnCentimes) {

		return montantSoldeInitialEnCentimes;
	}

	private TypeCompteInterne verifierTypeCompteInterne(String codeTypeCompteInterne) throws ControllerException {
		
		if ( codeTypeCompteInterne == null 
				|| codeTypeCompteInterne.isBlank() ) {
			throw new ControllerException(
					ControllerErreur.COMPTE_INTERNE_TYPE_COMPTE_OBLIGATOIRE);
		}
		
		TypeCompteInterne typeCompteInterne = TypeCompteInterne.findByCode(codeTypeCompteInterne);
		if ( typeCompteInterne == null ) {
			throw new ControllerException(
					ControllerErreur.COMPTE_INTERNE_TYPE_COMPTE_NON_TROUVE_PAR_CODE,
					codeTypeCompteInterne);
			
		}
		return typeCompteInterne;
	}

	private Banque verifierBanqueEnregistree(String nomBanque) 
			throws ControllerException {

		if ( nomBanque == null 
				|| nomBanque.isBlank() ) {
			throw new ControllerException(
					ControllerErreur.COMPTE_INTERNE_BANQUE_OBLIGATOIRE);
		}
		
		try {
			Banque banque = banqueService.rechercherParNom(nomBanque);
			if ( banque == null ) {
				throw new ControllerException(
						ControllerErreur.BANQUE_NON_TROUVEE_PAR_NOM, 
						nomBanque);
			}
			return banque;
		} 
		catch (ServiceTechniqueException e) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		}
	}

	private Set<Titulaire> verifierTitulairesEnregistres(List<String> nomsTitulaires) 
			throws ControllerException {

		Set<Titulaire> titulaires = new HashSet<>();

		if ( nomsTitulaires == null ) {
			throw new ControllerException(
					ControllerErreur.COMPTE_INTERNE_TABLEAU_NOM_TITULAIRE_OBLIGATOIRE);
		}
		
		if ( nomsTitulaires.size() == 0 ) {
			
			throw new ControllerException(
					ControllerErreur.COMPTE_INTERNE_AU_MOINS_UN_TITULAIRE_REQUIS);
		}

		for ( String nomTitulaire : nomsTitulaires ) {
			try {
				Titulaire titulaire = titulaireService.rechercherParNom(nomTitulaire);
				if ( titulaire == null ) {
					throw new ControllerException(
							ControllerErreur.TITULAIRE_NON_TROUVE_PAR_NOM, 
							nomTitulaire);
				}
				titulaires.add(titulaire);
			} 
			catch (ServiceTechniqueException e) {
				throw new ControllerException(
						e,
						ControllerErreur.ERREUR_TECHNIQUE);
			}
		}

		return titulaires;
	}
}

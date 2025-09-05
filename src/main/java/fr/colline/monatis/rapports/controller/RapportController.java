package fr.colline.monatis.rapports.controller;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.colline.monatis.comptes.model.CompteInterne;
import fr.colline.monatis.comptes.service.CompteInterneService;
import fr.colline.monatis.exception.ControllerErreur;
import fr.colline.monatis.exception.ControllerException;
import fr.colline.monatis.exception.ServiceTechniqueException;
import fr.colline.monatis.rapports.controller.dto.RapportCompteInterneDto;
import fr.colline.monatis.rapports.controller.mapper.RapportCompteInterneDtoMapper;
import fr.colline.monatis.rapports.service.RapportService;

@RestController
@RequestMapping("/monatis/rapports")
@Transactional
public class RapportController {

	@Autowired private CompteInterneService compteInterneService;
	@Autowired private RapportService rapportService;

	@GetMapping("/comptes/interne/all")
	public List<RapportCompteInterneDto> getAllRapportCompteInterne(
			@RequestParam(name = "dateDebut", required = false) Timestamp paramDateDebut,
			@RequestParam(name = "dateFin", required = false) Timestamp paramDateFin)
					throws ControllerException {
		try {
			Timestamp dateFin = paramDateFin == null ? Timestamp.from(Instant.now()) : paramDateFin;

			List<RapportCompteInterneDto> resultat = new ArrayList<>();

			Sort tri = Sort.by("identifiant");
			List<CompteInterne> liste = compteInterneService.rechercherTous(tri);
			for ( CompteInterne compteInterne : liste ) {
				Timestamp dateDebut = paramDateDebut == null ? compteInterne.getDateSoldeInitial() : paramDateDebut;
				resultat.add(RapportCompteInterneDtoMapper.detailedModelToResponseDto(
						rapportService.completerCompteInterne(
								compteInterne,
								dateDebut,
								dateFin)));
			}
			return resultat;
		} 
		catch (ServiceTechniqueException e) {
			throw new ControllerException(
					e,
					ControllerErreur.ERREUR_TECHNIQUE);
		}
	}

	//	@GetMapping("/comptes/interne/get/{identifiant}/solde")
	//	public List<RapportEcritureResponseDto> getCompteSolde(
	//			@PathVariable(name = "identifiant") String identifiant,
	//			@RequestBody RapportRequestDto dto) 
	//					throws ControllerException {
	//
	//	}
	//
	//	@GetMapping("/categorie/{nom}")
	//	@GetMapping("/souscategorie/{nom}")
	//	@GetMapping("/beneficiaire/{nom}")
	//	@GetMapping("/titulaire/{nom}")
	//	@GetMapping("/banque/{nom}")

}

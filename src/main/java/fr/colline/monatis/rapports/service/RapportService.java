package fr.colline.monatis.rapports.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.colline.monatis.comptes.model.CompteInterne;
import fr.colline.monatis.operations.model.Operation;
import fr.colline.monatis.operations.service.OperationService;
import fr.colline.monatis.rapports.model.RapportCompteInterne;

@Service public class RapportService {

	@Autowired private OperationService operationService;
	
	public RapportCompteInterne completerCompteInterne(CompteInterne compteInterne, Timestamp dateDebut, Timestamp dateFin) {

		RapportCompteInterne rapport = new RapportCompteInterne();
		
		rapport.setCompteInterne(compteInterne);
		rapport.setDateSoldeInitial(dateDebut);
		rapport.setDateSoldeFinal(dateFin);
		
		Long montantSoldeInitialEnCentimes;
		if ( compteInterne.getDateSoldeInitial().before(dateDebut) ) {
			RapportCompteInterne rapportAnterieur = completerCompteInterne(
					compteInterne, 
					compteInterne.getDateSoldeInitial(), 
					dateDebut);
			montantSoldeInitialEnCentimes = 
					compteInterne.getMontantSoldeInitialEnCentimes() 
					+ rapportAnterieur.getMontantTotalRecetteEnCentimes() 
					- rapportAnterieur.getMontantTotalDepenseEnCentimes();
		} else {
			montantSoldeInitialEnCentimes = compteInterne.getMontantSoldeInitialEnCentimes();
		}
		rapport.setMontantSoldeInitialEnCentimes(montantSoldeInitialEnCentimes);		

		Long montantTotalDepenseEnCentimes = 0L;
		List<Operation> listeOperationDepense = operationService.rechercherOperationDepenseParCompteIdEntreDateDebutEtDateFin(
				compteInterne.getId(),
				dateDebut,
				dateFin);
		for ( Operation operation : listeOperationDepense) {
			montantTotalDepenseEnCentimes += operation.getMontantTotalEnCentimes();
			// Les dépenses apparaîtront en négatif dans la liste des opérations à la fin
			operation.setMontantTotalEnCentimes(Math.subtractExact(0, operation.getMontantTotalEnCentimes()));	
		}
		rapport.setMontantTotalDepenseEnCentimes(montantTotalDepenseEnCentimes);
		
		Long montantTotalRecetteEnCentimes = 0L;
		List<Operation> listeOperationRecette = operationService.rechercherOperationRecetteParCompteIdEntreDateDebutEtDateFin(
				compteInterne.getId(),
				dateDebut,
				dateFin);
		for ( Operation operation : listeOperationRecette) {
			montantTotalRecetteEnCentimes += operation.getMontantTotalEnCentimes();
		}
		rapport.setMontantTotalRecetteEnCentimes(montantTotalRecetteEnCentimes);
		
		List<Operation> listeOperation = new ArrayList<>();
		listeOperation.addAll(listeOperationDepense);
		listeOperation.addAll(listeOperationRecette);
		Collections.sort(listeOperation, 
				(o1, o2) -> {
					return o2.getDateValeur().compareTo(o1.getDateValeur());
				});
		rapport.setListeOperation(listeOperation);

		Long montantSoldeFinalEnCentimes = 
				montantSoldeInitialEnCentimes
				+ montantTotalRecetteEnCentimes
				- montantTotalDepenseEnCentimes;
		rapport.setMontantSoldeFinalEnCentimes(montantSoldeFinalEnCentimes);

		return rapport;
	}
}

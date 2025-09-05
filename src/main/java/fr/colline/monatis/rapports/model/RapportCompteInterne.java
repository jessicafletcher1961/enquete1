package fr.colline.monatis.rapports.model;

import java.sql.Timestamp;
import java.util.List;

import fr.colline.monatis.comptes.model.CompteInterne;
import fr.colline.monatis.operations.model.Operation;

public class RapportCompteInterne {

	private CompteInterne compteInterne;
	
	private Timestamp dateSoldeInitial;
	
	private Long montantSoldeInitialEnCentimes;

	private Long montantTotalRecetteEnCentimes;
	
	private Long montantTotalDepenseEnCentimes;
	
	private Timestamp dateSoldeFinal;
	
	private Long montantSoldeFinalEnCentimes;
	
	private List<Operation> listeOperation;
	
	public CompteInterne getCompteInterne() {
		return compteInterne;
	}

	public void setCompteInterne(CompteInterne compteInterne) {
		this.compteInterne = compteInterne;
	}

	public Timestamp getDateSoldeInitial() {
		return dateSoldeInitial;
	}

	public void setDateSoldeInitial(Timestamp dateSoldeInitial) {
		this.dateSoldeInitial = dateSoldeInitial;
	}

	public Long getMontantSoldeInitialEnCentimes() {
		return montantSoldeInitialEnCentimes;
	}

	public void setMontantSoldeInitialEnCentimes(Long montantSoldeInitialEnCentimes) {
		this.montantSoldeInitialEnCentimes = montantSoldeInitialEnCentimes;
	}

	public Long getMontantTotalRecetteEnCentimes() {
		return montantTotalRecetteEnCentimes;
	}

	public void setMontantTotalRecetteEnCentimes(Long montantTotalRecetteEnCentimes) {
		this.montantTotalRecetteEnCentimes = montantTotalRecetteEnCentimes;
	}

	public Long getMontantTotalDepenseEnCentimes() {
		return montantTotalDepenseEnCentimes;
	}

	public void setMontantTotalDepenseEnCentimes(Long montantTotalDepenseEnCentimes) {
		this.montantTotalDepenseEnCentimes = montantTotalDepenseEnCentimes;
	}

	public Timestamp getDateSoldeFinal() {
		return dateSoldeFinal;
	}

	public void setDateSoldeFinal(Timestamp dateSoldeFinal) {
		this.dateSoldeFinal = dateSoldeFinal;
	}

	public Long getMontantSoldeFinalEnCentimes() {
		return montantSoldeFinalEnCentimes;
	}

	public void setMontantSoldeFinalEnCentimes(Long montantSoldeFinalEnCentimes) {
		this.montantSoldeFinalEnCentimes = montantSoldeFinalEnCentimes;
	}

	public List<Operation> getListeOperation() {
		return listeOperation;
	}

	public void setListeOperation(List<Operation> listeOperation) {
		this.listeOperation = listeOperation;
	}
}

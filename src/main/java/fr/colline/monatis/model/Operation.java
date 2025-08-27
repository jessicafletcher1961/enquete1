package fr.colline.monatis.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Operation {

	@Id
	@GeneratedValue(generator = "gen_seq_operation", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "gen_seq_operation", sequenceName = "seq_operation", allocationSize = 1)
	private Long id;

	@Column(length = 30, nullable = false)
	private String numero;

	private Timestamp dateValeur;

	@Column(length = 240)
	private String libelle;

	@Column(nullable = false) 
	private Long montantTotalEnCentimes;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "compte_recette_id")
	private Compte compteRecette;

	@ManyToOne(optional = false)
	@JoinColumn(name = "compte_depense_id")
	private Compte compteDepense;
	
	@OneToMany(
			mappedBy = "operation",
			cascade = CascadeType.ALL,
			fetch = FetchType.EAGER)
	private Set<DetailOperation> detailsOperation = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getDateValeur() {
		return dateValeur;
	}

	public void setDateValeur(Timestamp date) {
		this.dateValeur = date;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Long getMontantTotalEnCentimes() {
		return montantTotalEnCentimes;
	}

	public void setMontantTotalEnCentimes(Long montantTotalOperation) {
		this.montantTotalEnCentimes = montantTotalOperation;
	}

	public Compte getCompteRecette() {
		return compteRecette;
	}

	public void setCompteRecette(Compte compte) {
		this.compteRecette = compte;
	}

	public Compte getCompteDepense() {
		return compteDepense;
	}

	public void setCompteDepense(Compte compte) {
		this.compteDepense = compte;
	}

	public Set<DetailOperation> getDetailsOperation() {
		return detailsOperation;
	}

	public void setDetailsOperation(Set<DetailOperation> detailsOperation) {
		this.detailsOperation = detailsOperation;
	}

	public Operation() {}

	public Operation(
			Timestamp dateValeur, 
			String numero, 
			String libelle,
			Long montantTotalOperation) {

		this.dateValeur = dateValeur;
		this.numero = numero;
		this.libelle = libelle;
		this.montantTotalEnCentimes = montantTotalOperation; 
	}

	@Override
	public int hashCode() {
		return Objects.hash(libelle, 
				montantTotalEnCentimes,
				compteDepense.getId(), 
				compteRecette.getId(), 
				dateValeur, 
				id,
				numero);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Operation other = (Operation) obj;
		return Objects.equals(libelle, other.libelle)
				&& Objects.equals(montantTotalEnCentimes, other.montantTotalEnCentimes)
				&& Objects.equals(compteDepense.getId(), other.compteDepense.getId())
				&& Objects.equals(compteRecette.getId(), other.compteRecette.getId())
				&& Objects.equals(dateValeur, other.dateValeur)
				&& Objects.equals(id, other.id)
				&& Objects.equals(numero, other.numero);
	}
}

package fr.colline.monatis.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.SequenceGenerator;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Reference {

	@Id
	@GeneratedValue(generator = "gen_seq_reference", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "gen_seq_reference", sequenceName = "seq_reference", allocationSize = 1)
	private Long id;

	@Column(length = 30, nullable = false, unique = true)
	private String nom;

	@Column(length = 240, nullable = true)
	private String libelle;

	public Reference() {}
	
	public Reference (String nom, String commentaire) {
		this.setNom(nom);
		this.setLibelle(commentaire);
	} 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public int hashCode() {
		return Objects.hash(libelle, id, nom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reference other = (Reference) obj;
		return Objects.equals(libelle, other.libelle) 
				&& Objects.equals(id, other.id)
				&& Objects.equals(nom, other.nom);
	}
}

package fr.colline.monatis.model;

import jakarta.persistence.Entity;

@Entity
public class Beneficiaire extends Reference {

	public Beneficiaire() {}
	
	public Beneficiaire(String nom, String commentaire) {
		
		super(nom, commentaire);
	}
}

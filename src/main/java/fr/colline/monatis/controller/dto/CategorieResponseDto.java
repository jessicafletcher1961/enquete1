package fr.colline.monatis.controller.dto;

import java.io.Serializable;
import java.util.List;

public class CategorieResponseDto implements Serializable {

	private static final long serialVersionUID = -3904845026454862739L;

	public Long id;
	public String nom;
	public String libelle;

	public List<SousCategorieResponseDto> sousCategories;
}

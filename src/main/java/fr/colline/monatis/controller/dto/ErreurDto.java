package fr.colline.monatis.controller.dto;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

public class ErreurDto implements Serializable {

	private static final long serialVersionUID = 3235704918495067520L;

	public String typeLibelle;
	public String code;
	public String libelle;

	public HttpStatus status;
	public ErreurDto cause;
}

package fr.colline.monatis.exception;

import fr.colline.monatis.typologie.TypeErreur;

public class ServiceFonctionnelleException 
extends Exception
implements MonatisExceptionInterface {

	private static final long serialVersionUID = -8440036373059191857L;

	private ServiceFonctionnelleErreur erreur;
	
	private Object[] values;
	
	private Throwable initCause;

	@Override
	public TypeErreur getType() {
		return TypeErreur.FONCTIONNELLE;
	}
	
	@Override
	public String getCode() {
		return erreur.getCode();
	}

	@Override
	public String getPattern() {
		return erreur.getPattern();
	}

	@Override
	public Object[] getValues() {
		return values;
	}
	
	@Override
	public Throwable getCause() {
		return initCause;
	}

	public ServiceFonctionnelleException(
			ServiceFonctionnelleErreur erreur,
			Object...values) {

		super(erreur.getMessage(values));

		this.erreur = erreur;
		this.values = values;
		this.initCause = null;
	}

	public ServiceFonctionnelleException(
			Throwable t,
			ServiceFonctionnelleErreur erreur,
			Object...values) {

		super(erreur.getMessage(values), t);
		
		this.erreur = erreur;
		this.values = values;
		this.initCause = t;
	}
}

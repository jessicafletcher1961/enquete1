package fr.colline.monatis.exception;

import fr.colline.monatis.typologie.TypeErreur;

public class ServiceTechniqueException 
extends Exception 
implements MonatisExceptionInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2109647702750720299L;

	private ServiceTechniqueErreur erreur;
	
	private Object[] values;
	
	private Throwable initCause;
	
	@Override
	public Object[] getValues() {
		return values;
	}
	
	@Override
	public Throwable getCause() {
		return initCause;
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
	public TypeErreur getType() {
		return erreur.getType();
	}
 
	public ServiceTechniqueException(
			ServiceTechniqueErreur erreur,
			Object...values) {

		super(erreur.getMessage(values));
		
		this.erreur = erreur;
		this.values = values;
		this.initCause = null;
	}

	public ServiceTechniqueException(
			Throwable t,
			ServiceTechniqueErreur erreur,
			Object...values) {

		super(erreur.getMessage(values), t);

		this.erreur = erreur;
		this.values = values;
		this.initCause = t;
	}
}

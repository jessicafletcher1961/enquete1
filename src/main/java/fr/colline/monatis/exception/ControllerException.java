package fr.colline.monatis.exception;

import org.springframework.http.HttpStatus;

import fr.colline.monatis.typologie.TypeErreur;

public class ControllerException 
extends Exception
implements MonatisExceptionInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7072464117498728945L;

	private ControllerErreur erreur;
	
	private Object[] values;
	
	private Throwable cause;
	
	@Override
	public TypeErreur getType() {
		return erreur.getType();
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
		return cause;
	}

	public HttpStatus getStatus() {
		return erreur.getStatus();
	}
	
	public ControllerException(
			ControllerErreur erreur,
			Object...values) {

		super(erreur.getMessage(values));

		this.erreur = erreur;
		this.values = values;
		this.cause = null;
	}
	
	public ControllerException(
			Throwable initCause,
			ControllerErreur erreur,
			Object...values) {

		super(erreur.getMessage(values), initCause);

		this.erreur = erreur;
		this.values = values;
		this.cause = initCause;
	}
}

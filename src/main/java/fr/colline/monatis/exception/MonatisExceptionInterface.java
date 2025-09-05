package fr.colline.monatis.exception;

public interface MonatisExceptionInterface {

	public TypeErreur getType();
	
	public String getCode();
	
	public String getPattern();
	
	public Object[] getValues();
	
	public Throwable getCause();
	
	public String getMessage();
}

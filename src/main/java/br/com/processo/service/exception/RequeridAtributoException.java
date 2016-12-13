package br.com.processo.service.exception;

public class RequeridAtributoException extends RegrasException{

	private static final long serialVersionUID = 1L;
	
	public RequeridAtributoException(String msg) {
		super(msg);
	}

	public RequeridAtributoException(String msg, Throwable cause) {
		super(msg, cause);
	}

}

package br.com.processo.service.exception;

public class RegrasException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public RegrasException(String msg) {
		super(msg);
	}
	
	public RegrasException(String msg, Throwable cause) {
		super(msg, cause);
	}

}

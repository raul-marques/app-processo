package br.com.processo.service.exception;

public class IlegalCompetenciaProcessoParaVaraException extends RegrasException{

	private static final long serialVersionUID = 1L;

	public IlegalCompetenciaProcessoParaVaraException(String msg) {
		super(msg);
	}
	
	public IlegalCompetenciaProcessoParaVaraException(String msg, Throwable cause) {
		super(msg, cause);
	}

}

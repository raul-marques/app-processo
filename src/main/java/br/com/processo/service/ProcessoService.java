package br.com.processo.service;

import br.com.processo.domain.Processo;
import br.com.processo.service.exception.RegrasException;

public interface ProcessoService {
	Processo buscarProcesso(Integer numProcesso);
	
	Processo cadastrarProcesso(Processo processo) throws RegrasException;
}

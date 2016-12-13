package br.com.processo.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;

import br.com.processo.dao.ProcessoDAO;
import br.com.processo.domain.ClasseProcessual;
import br.com.processo.domain.ClasseProcessualCompetencia;
import br.com.processo.domain.Comarca;
import br.com.processo.domain.Competencia;
import br.com.processo.domain.Processo;
import br.com.processo.domain.Vara;
import br.com.processo.service.ProcessoService;
import br.com.processo.service.exception.IlegalCompetenciaProcessoParaVaraException;
import br.com.processo.service.exception.RegrasException;
import br.com.processo.service.exception.RequeridAtributoException;

public class ProcessoServiceImpl implements ProcessoService{
	public static final String MSG_COMPETENCIA_CLASSE_PROCESSUAL_INVALIDA_VARA = "Competência(s) informadas na Classe Processual não são compativeis com a Vara.";
	public static final String MSG_CLASSE_PROCESSUAL_COMPETENCIA_REQUERIDO = "Nenhuma competência selecionada para Classe Processual.";
	public static final String MSG_PROCESSO_VARA_REQUERIDO = "Nenhuma Vara foi atribuida ao Processo.";
	public static final String MSG_COMARCA_REQUERIDO = "Nenhuma Comarca informada.";
	
	
	@Inject
	private ProcessoDAO processoDAO;
	
	public Processo buscarProcesso(Integer numProcesso) {
		return processoDAO.buscarProcessoById(numProcesso);
	}

	public Processo cadastrarProcesso(Processo processo) throws RegrasException {
		atribuiVaraIdealAoProcesso(processo);
		if(validarInclusaoProcesso(processo)){
			return processoDAO.cadastrarProcesso(processo);
		}
		return processo;
	}

	
	private boolean validarInclusaoProcesso(Processo processo) throws RegrasException {
		Comarca comarca = processo.getComarca();
		Vara vara = processo.getVara();
		ClasseProcessual classeProcessual = processo.getClasseProcessual();
		List<ClasseProcessualCompetencia> competenciasProcesso = classeProcessual.getCompetencias();
		
		if(comarca == null || comarca.getId()==null){
			throw new RequeridAtributoException(MSG_COMARCA_REQUERIDO);
		}
		
		if(competenciasProcesso == null || competenciasProcesso.isEmpty()){
			throw new RequeridAtributoException(MSG_CLASSE_PROCESSUAL_COMPETENCIA_REQUERIDO);
		}
		
		if(vara == null || vara.getId() == null){
			throw new RequeridAtributoException(MSG_PROCESSO_VARA_REQUERIDO);
		}

		return true;
	}
	
	public List<Vara> getVaraCompetenciaCompativel(List<Vara> varasComarca, List<ClasseProcessualCompetencia> competenciasClassProcessual) throws IlegalCompetenciaProcessoParaVaraException{
//		List<Vara> result = varasComarca.stream()
//										 .filter(vara -> vara.getCompetencias()
//										 .containsAll(competenciasClassProcessual)).collect(Collectors.toList());
//		
//		if(result.isEmpty()) throw new IlegalCompetenciaProcessoParaVaraException(MSG_COMPETENCIA_CLASSE_PROCESSUAL_INVALIDA_VARA);
		ClasseProcessualCompetencia classeProcessualCompetencia = new ClasseProcessualCompetencia();
		classeProcessualCompetencia.getCompetencia();
		
		Set<Competencia> competenciasCandidatas = competenciasClassProcessual.stream()
				.map(ClasseProcessualCompetencia::getCompetencia)
				.collect(Collectors.toSet());

		List<Vara> result = varasComarca.stream().filter(cv -> cv.getListCompetencia() != null && cv.getListCompetencia().containsAll(competenciasCandidatas)).collect(Collectors.toList());

		if(result.isEmpty()) throw new IlegalCompetenciaProcessoParaVaraException(MSG_COMPETENCIA_CLASSE_PROCESSUAL_INVALIDA_VARA);
		
		return result;
	}
	
	public Vara getVaraMenoProcesso(List<Vara> varas){
		
		int menorValorCorrente = 0;
		Vara varaComMenorQtdProcessos = null;
		
		for (Vara vara : varas) {
			if(varaComMenorQtdProcessos == null){
				menorValorCorrente = vara.getQuantidadeProcessos();
				varaComMenorQtdProcessos = vara;
				continue;
			}
			if(vara.getQuantidadeProcessos()<=menorValorCorrente){
				menorValorCorrente = vara.getQuantidadeProcessos();
				varaComMenorQtdProcessos = vara;
			}
		}
		
		return varaComMenorQtdProcessos;
	}
	
	private void atribuiVaraIdealAoProcesso(Processo processo) throws IlegalCompetenciaProcessoParaVaraException{
		Comarca comarca = processo.getComarca();
		ClasseProcessual classeProcessual = processo.getClasseProcessual();
		if(comarca == null || comarca.getId() == null){
			return;
		}
		if(classeProcessual == null || classeProcessual.getCompetencias() == null || classeProcessual.getCompetencias().isEmpty()){
			return;
		}
		if(comarca.getVaras() == null || comarca.getVaras().isEmpty()){
			return;
		}
		
		List<Vara> varaCompetenciaCompativel = getVaraCompetenciaCompativel(comarca.getVaras(), classeProcessual.getCompetencias());
		processo.setVara(getVaraMenoProcesso(varaCompetenciaCompativel));
	}
	
	

}

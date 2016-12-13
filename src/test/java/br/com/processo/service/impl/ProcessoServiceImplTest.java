package br.com.processo.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.com.processo.domain.ClasseProcessual;
import br.com.processo.domain.ClasseProcessualCompetencia;
import br.com.processo.domain.Comarca;
import br.com.processo.domain.Competencia;
import br.com.processo.domain.Processo;
import br.com.processo.domain.Vara;
import br.com.processo.domain.VaraCompetencia;
import br.com.processo.service.ProcessoService;
import br.com.processo.service.exception.IlegalCompetenciaProcessoParaVaraException;
import br.com.processo.service.exception.RegrasException;
import br.com.processo.service.exception.RequeridAtributoException;

public class ProcessoServiceImplTest {

	private ProcessoService processoService;
	private List<Competencia> competenciasQuartaVaraCivel = new ArrayList<Competencia>();
	private List<Competencia> competenciasSetimaVaraCivel = new ArrayList<Competencia>();
	private List<Vara> varasComarcaCuiaba = new ArrayList<Vara>();
	
	private Comarca comarcaDeCuiaba = new Comarca(1L, "Cuiaba");
	private final Competencia competenciaFamilia = new Competencia(1L, "Familia");
	private final Competencia competenciaFazenda = new Competencia(2L, "Fazenda");
	private final Competencia competenciaDiretoria = new Competencia(3L, "Diretoria");
	private final Competencia competenciaJuizadoEspecialCivil = new Competencia(4L, "Juizado Especial Civil");
	
	private Vara quartaVara = new Vara(1L, "Quarta Vara Civel");
	private Vara setimaVara = new Vara(2L, "Setima Vara Civel");
	
	private List<ClasseProcessualCompetencia> classeProcessualCompetenciasQuartaVaraCivel = new ArrayList<ClasseProcessualCompetencia>();
	private List<ClasseProcessualCompetencia> classeProcessualCompetenciasSetimaVaraCivel = new ArrayList<ClasseProcessualCompetencia>();
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Before
    public void setUp() {
		processoService = new ProcessoServiceImpl();
		competenciasQuartaVaraCivel.add(competenciaDiretoria);
		competenciasQuartaVaraCivel.add(competenciaFamilia);
		competenciasQuartaVaraCivel.add(competenciaJuizadoEspecialCivil);
		
		competenciasSetimaVaraCivel.add(competenciaFazenda);
		
		classeProcessualCompetenciasQuartaVaraCivel.add(new ClasseProcessualCompetencia(1L, competenciaFamilia));
		classeProcessualCompetenciasQuartaVaraCivel.add(new ClasseProcessualCompetencia(2L, competenciaFazenda));
		
		classeProcessualCompetenciasSetimaVaraCivel.add(new ClasseProcessualCompetencia(3L, competenciaDiretoria));
		
		
		Set<VaraCompetencia> varaCompetencias = new HashSet<VaraCompetencia>();
		varaCompetencias.add(new VaraCompetencia(1L, quartaVara, competenciaFamilia));
		varaCompetencias.add(new VaraCompetencia(2L, quartaVara, competenciaFazenda));
		
		quartaVara.setCompetencias(varaCompetencias);
		
		varasComarcaCuiaba.add(quartaVara);
    }
	
	@Test
	public void deveLancarExceptionSeComarcaNaoForInformado() throws RegrasException{
		expectedException.expect(RequeridAtributoException.class);
		expectedException.expectMessage(ProcessoServiceImpl.MSG_COMARCA_REQUERIDO);
		
		
		ClasseProcessual classeProcessual = new ClasseProcessual(1L, this.classeProcessualCompetenciasQuartaVaraCivel);
		Processo processo = new Processo(1L, 3321, classeProcessual, null);
		
		processoService.cadastrarProcesso(processo);
	}
	
	@Test
	public void deveLancarExceptionSeCompetenciaDaClasseProcessualNaoInformado() throws RegrasException{
		expectedException.expect(RequeridAtributoException.class);
		expectedException.expectMessage(ProcessoServiceImpl.MSG_CLASSE_PROCESSUAL_COMPETENCIA_REQUERIDO);
		
		ClasseProcessual classeProcessual = new ClasseProcessual(1L, null);
		Processo processo = new Processo(1L, 3321, classeProcessual, this.comarcaDeCuiaba);
		
		processoService.cadastrarProcesso(processo);
	}
	
	@Test(expected = IlegalCompetenciaProcessoParaVaraException.class)
	public void deveLancarExeceptionSeNaoEncontrarVaraCompativelAsCompetencias() throws RegrasException{

		this.comarcaDeCuiaba.setVaras(varasComarcaCuiaba);
		ClasseProcessual classeProcessual = new ClasseProcessual(1L, this.classeProcessualCompetenciasSetimaVaraCivel);
		Processo processo = new Processo(1L, 3321, classeProcessual, this.comarcaDeCuiaba);
		
		processoService.cadastrarProcesso(processo);
	}


}

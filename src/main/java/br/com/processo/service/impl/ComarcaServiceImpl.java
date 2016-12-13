package br.com.processo.service.impl;

import javax.inject.Inject;

import br.com.processo.dao.ComarcaDAO;
import br.com.processo.domain.Comarca;
import br.com.processo.service.ComarcaService;

public class ComarcaServiceImpl implements ComarcaService{
	
	@Inject
	private ComarcaDAO comarcaDAO;
	
	public Comarca buscarComarca(Long id) {
		return comarcaDAO.buscarComarcaById(id);
	}

}

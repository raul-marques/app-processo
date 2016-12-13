package br.com.processo.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.processo.domain.Processo;

public class ProcessoDAO extends AbstractDAO<Processo> {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager entityManager;
	
	
	public Processo buscarProcessoById(Integer numProcesso){
		try {
			Query query  = entityManager.createQuery("from Processo where numProcesso = :numProcesso");
			query.setParameter("numProcesso", numProcesso);
			return (Processo) query.getSingleResult();
		} catch (NoResultException e) {
			return getNoResultObject();
		}
	}
	
	public Processo cadastrarProcesso(Processo processo){
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(processo);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		}
		return processo;
	}

	
}

package br.com.processo.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.processo.domain.Comarca;

public class ComarcaDAO extends AbstractDAO<Comarca> {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager entityManager;
	
	
	public Comarca buscarComarcaById(Long id){
		try {
			Query query  = entityManager.createQuery("from Comarca comarca left join fetch comarca.varas where comarca.id = :id");
			query.setParameter("id", id);
			return (Comarca) query.getSingleResult();
		} catch (NoResultException e) {
			return getNoResultObject();
		}
	}

	
}

package br.com.processo.dao;

import java.io.Serializable;

import br.com.processo.domain.IEntity;

public abstract class AbstractDAO<T extends IEntity> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public T getNoResultObject() {
		return null;
	}
	
}

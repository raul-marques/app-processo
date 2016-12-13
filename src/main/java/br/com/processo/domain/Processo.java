package br.com.processo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="processo")
@SequenceGenerator(name="processo_seq", sequenceName="processo_id_processo_seq", allocationSize=1)
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property="@id", scope = Processo.class)
public class Processo implements IEntity {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="processo_seq")
	private Long id;
	
	@Generated(GenerationTime.INSERT)
	@Column(name="num_processo", insertable=false, columnDefinition="serial")
	private Integer numProcesso;
	
	@ManyToOne
	private ClasseProcessual classeProcessual;
	
	@ManyToOne
	private Comarca comarca;
	
	@ManyToOne
	private Vara vara;
	
	public Processo() {
	}


	public Processo(Long id, Integer numProcesso, ClasseProcessual classeProcessual, Comarca comarca) {
		super();
		this.id = id;
		this.numProcesso = numProcesso;
		this.classeProcessual = classeProcessual;
		this.comarca = comarca;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumProcesso() {
		return numProcesso;
	}

	public void setNumProcesso(Integer numProcesso) {
		this.numProcesso = numProcesso;
	}

	public ClasseProcessual getClasseProcessual() {
		return classeProcessual;
	}

	public void setClasseProcessual(ClasseProcessual classeProcessual) {
		this.classeProcessual = classeProcessual;
	}

	public Comarca getComarca() {
		return comarca;
	}

	public void setComarca(Comarca comarca) {
		this.comarca = comarca;
	}

	public Vara getVara() {
		return vara;
	}

	public void setVara(Vara vara) {
		this.vara = vara;
	}
	
}

package br.com.processo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="classe_processual_competencia")
@SequenceGenerator(name="classe_processual_competencia_seq", sequenceName="classe_processual_competencia_id_classe_processual_competencia_seq", allocationSize=1)
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property="@id", scope = ClasseProcessualCompetencia.class)
public class ClasseProcessualCompetencia implements IEntity{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="classe_processual_competencia_seq")
	private Long id;
	
	@ManyToOne
	private Competencia competencia;
	
	@ManyToOne
	private ClasseProcessual classeProcessual;
	
	public ClasseProcessualCompetencia() {
	}
	
	public ClasseProcessualCompetencia(Long id, Competencia competencia) {
		super();
		this.id = id;
		this.competencia = competencia;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Competencia getCompetencia() {
		return competencia;
	}

	public void setCompetencia(Competencia competencia) {
		this.competencia = competencia;
	}

	public ClasseProcessual getClasseProcessual() {
		return classeProcessual;
	}

	public void setClasseProcessual(ClasseProcessual classeProcessual) {
		this.classeProcessual = classeProcessual;
	}
	
	
	
}

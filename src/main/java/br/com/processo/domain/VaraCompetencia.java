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
@Table(name="vara_competencia")
@SequenceGenerator(name="vara_competencia_seq", sequenceName="vara_competencia_id_vara_competencia_seq", allocationSize=1)
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property="@id", scope = VaraCompetencia.class)
public class VaraCompetencia implements IEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="vara_competencia_seq")
	private Long id;
	
	@ManyToOne
	private Vara vara;
	
	@ManyToOne
	private Competencia competencia;
	
	public VaraCompetencia() {
	}
	
	public VaraCompetencia(Long id, Vara vara, Competencia competencia) {
		super();
		this.id = id;
		this.vara = vara;
		this.competencia = competencia;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Vara getVara() {
		return vara;
	}

	public void setVara(Vara vara) {
		this.vara = vara;
	}

	public Competencia getCompetencia() {
		return competencia;
	}

	public void setCompetencia(Competencia competencia) {
		this.competencia = competencia;
	}
	
}

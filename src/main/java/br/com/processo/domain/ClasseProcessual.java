package br.com.processo.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="classe_processual")
@SequenceGenerator(name="classe_processual_seq", sequenceName="classe_processual_id_classe_processual_seq", allocationSize=1)
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property="@id", scope = ClasseProcessual.class)
public class ClasseProcessual implements IEntity{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="classe_processual_seq")
	private Long id;
	
	@OneToMany(mappedBy="classeProcessual")
	private List<ClasseProcessualCompetencia> competencias;
	
	public ClasseProcessual() {
	}
	
	public ClasseProcessual(Long id, List<ClasseProcessualCompetencia> competencias) {
		super();
		this.id = id;
		this.competencias = competencias;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<ClasseProcessualCompetencia> getCompetencias() {
		return competencias;
	}

	public void setCompetencias(List<ClasseProcessualCompetencia> competencias) {
		this.competencias = competencias;
	}
	
}

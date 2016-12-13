package br.com.processo.domain;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="vara")
@SequenceGenerator(name="vara_seq", sequenceName="vara_id_vara_seq", allocationSize=1)
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property="@id", scope = Vara.class)
public class Vara implements IEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="vara_seq")
	private Long id;
	
	@Column
	private String descricao;
	
	@OneToMany(mappedBy="vara")
	private Set<VaraCompetencia> competencias;
	
	@OneToMany(mappedBy="vara", fetch=FetchType.EAGER)
	private Set<Processo> processos;
	
	@ManyToOne
	private Comarca comarca;

	public Vara() {
	}
	
	public Vara(Long id, String descricao){
		super();
		this.id = id;
		this.descricao = descricao;
	}
	
	public Vara(Long id, String descricao, Set<VaraCompetencia> competencias){
		super();
		this.id = id;
		this.descricao = descricao;
		this.competencias = competencias;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Set<VaraCompetencia> getCompetencias() {
		return competencias;
	}

	public void setCompetencias(Set<VaraCompetencia> competencias) {
		this.competencias = competencias;
	}

	public Set<Processo> getProcessos() {
		return processos;
	}

	public void setProcessos(Set<Processo> processos) {
		this.processos = processos;
	}

	public Comarca getComarca() {
		return comarca;
	}

	public void setComarca(Comarca comarca) {
		this.comarca = comarca;
	}
	
	public Integer getQuantidadeProcessos(){
		return processos == null ? 0 : processos.size();
	}
	
	public List<Competencia> getListCompetencia(){
		if(this.getCompetencias() == null){
			return null;
		}
		return this.getCompetencias().stream().map(VaraCompetencia::getCompetencia).collect(Collectors.toList());
	}
	
}

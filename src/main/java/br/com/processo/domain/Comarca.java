package br.com.processo.domain;

import java.util.List;

import javax.persistence.Column;
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
@Table(name="comarca")
@SequenceGenerator(name="comarca_seq", sequenceName="comarca_id_comarca_seq", allocationSize=1)
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property="@id", scope = Comarca.class)
public class Comarca implements IEntity{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="comarca_seq")
	private Long id;
	
	@Column 
	private String descricao;

	@OneToMany(mappedBy="comarca")
	private List<Vara> varas;
	
	public Comarca() {
	}
	
	public Comarca(Long id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
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

	public List<Vara> getVaras() {
		return varas;
	}

	public void setVaras(List<Vara> varas) {
		this.varas = varas;
	}
	
	
}

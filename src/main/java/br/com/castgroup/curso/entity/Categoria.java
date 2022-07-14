package br.com.castgroup.curso.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;




@Table(name = "TB_categoria")
@Entity
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idcategoria")
	private Integer idcategoria;
	
	@Column(name="categoria")
	private String categoria;
	
	public Categoria() {
		// TODO Auto-generated constructor stub
	}

	public Integer getIdcategoria() {
		return idcategoria;
	}

	public void setIdcategoria(Integer idcategoria) {
		this.idcategoria = idcategoria;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Categoria(Integer idcategoria) {
		super();
		this.idcategoria = idcategoria;
	}

	public Categoria(Integer idcategoria, String categoria) {
		super();
		this.idcategoria = idcategoria;
		this.categoria = categoria;
	}

	
	
	
}



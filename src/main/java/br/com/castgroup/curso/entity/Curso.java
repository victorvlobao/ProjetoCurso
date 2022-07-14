package br.com.castgroup.curso.entity;



import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Table(name = "TB_curso")
@Entity
public class Curso{

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcurso")
	private Integer idcurso;
	
	@Column(name = "descricao", nullable = false)
	private String descricao;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataInicio;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataTermino;
	
    @Column(name = "quantidadeAlunos")
	private Integer quantidadeAlunos;
    
	@ManyToOne
	@JoinColumn(name="categoria")
	private Categoria categoria;	
}

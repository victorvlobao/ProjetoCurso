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


import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.Data;

@Data
@Table(name = "TB_curso")
@Entity
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcurso")
	private Integer idcurso;
	
	@Column(name = "descricao", nullable = false)
	private String descricao;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate dataInicio;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate dataTermino;
	
	private Integer quantidadeAlunos;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="fk_categoria")
	private Categoria categoria;
	
}
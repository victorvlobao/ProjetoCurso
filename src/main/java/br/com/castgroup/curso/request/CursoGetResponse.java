package br.com.castgroup.curso.request;

import java.time.LocalDate;


import br.com.castgroup.curso.entity.Categoria;
import lombok.Data;

@Data
public class CursoGetResponse {

	private Integer idcurso;
	private String descricao;
	private LocalDate dataInicio;
	private LocalDate dataTermino;
	private Integer quantidadeAlunos;
	private Categoria categoria;
	
}
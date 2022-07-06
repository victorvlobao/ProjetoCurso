package br.com.castgroup.curso.controllers;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.castgroup.curso.entity.Curso;
import br.com.castgroup.curso.repository.RepositoryCurso;
import br.com.castgroup.curso.request.CursoGetResponse;
import br.com.castgroup.curso.request.CursoPostRequest;
import br.com.castgroup.curso.request.CursoPutRequest;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CursoController {
	
	@Autowired
	RepositoryCurso repository;

	private static final String ENDPOINT = "curso";
	
	
	@RequestMapping(value = ENDPOINT, method = RequestMethod.POST)
	public ResponseEntity<String> post(@RequestBody CursoPostRequest request) {
		try {
			Curso curso = new Curso();

			curso.setDescricao(request.getDescricao());
			curso.setDataInicio(request.getDataInicio());
			curso.setDataTermino(request.getDataTermino());
			curso.setQuantidadeAlunos(request.getQuantidadeAlunos());
			curso.setCategoria(request.getCategoria());
			
			repository.save(curso);

			return ResponseEntity.status(HttpStatus.OK).body("Curso cadastrado");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro" + e.getMessage());
		}
	}
	
	
	
	@RequestMapping(value = ENDPOINT, method = RequestMethod.GET)
	public ResponseEntity<List<CursoGetResponse>> get() {

		List<CursoGetResponse> response = new ArrayList<CursoGetResponse>();

		for (Curso curso : repository.findAll()) {

			CursoGetResponse var = new CursoGetResponse();

			var.setIdcurso(curso.getIdcurso());
			var.setDescricao(curso.getDescricao());
			var.setDataInicio(curso.getDataInicio());
			var.setDataTermino(curso.getDataTermino());
			var.setQuantidadeAlunos(curso.getQuantidadeAlunos());
			var.setCategoria(curso.getCategoria());
			
			response.add(var);
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	
	@RequestMapping(value = ENDPOINT + "/{idcurso}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable("idcurso") Integer idcurso) {
		try {
			Optional<Curso> item = repository.findById(idcurso);

			if (item.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Curso não encontrado");

			} else {
				Curso curso = item.get();
				repository.delete(curso);
				return ResponseEntity.status(HttpStatus.OK).body("Curso excluido");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro :" + e.getMessage());
		}

	}
	
	
	@RequestMapping(value = ENDPOINT, method = RequestMethod.PUT)
	public ResponseEntity<String> put(@RequestBody CursoPutRequest request) {
		try {

			Optional<Curso> item = repository.findById(request.getIdcurso());

			if (item.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Curso não encontrado");
			} else {

				Curso curso = item.get();

				curso.setDescricao(request.getDescricao());
				curso.setDataInicio(request.getDataInicio());
				curso.setDataTermino(request.getDataTermino());
				curso.setQuantidadeAlunos(request.getQuantidadeAlunos());
				curso.setCategoria(request.getCategoria());
				

				repository.save(curso);

				return ResponseEntity.status(HttpStatus.OK).body("Atualizado");
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro :" + e.getMessage());
		}

	}
	
}

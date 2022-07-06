package br.com.castgroup.curso.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.castgroup.curso.entity.Curso;
import br.com.castgroup.curso.repository.RepositoryCurso;

@RestController
@RequestMapping(value="/curso")
public class CursoResources {

	@Autowired
	private RepositoryCurso repository;
	
	@GetMapping
	public ResponseEntity<List<Curso>> finAll(){
		List<Curso> result = repository.findAll();
		return ResponseEntity.ok(result);
	}
	
	
}

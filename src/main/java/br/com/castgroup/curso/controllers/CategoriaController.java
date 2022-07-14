package br.com.castgroup.curso.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.castgroup.curso.entity.Categoria;
import br.com.castgroup.curso.service.CategoriaService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {

	@Autowired
	CategoriaService service;
	
	@GetMapping("/categoria")
	public ResponseEntity<List<Categoria>>listagem(){
		return ResponseEntity.ok(service.listar());
	}
}

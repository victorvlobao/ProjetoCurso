package br.com.castgroup.curso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.castgroup.curso.entity.Categoria;
import br.com.castgroup.curso.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository repository;
	
	
	public List<Categoria>listar(){
		return repository.findAll();
	}
}

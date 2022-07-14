package br.com.castgroup.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.castgroup.curso.entity.Categoria;

@Repository
@RequestMapping
public interface CategoriaRepository extends 
	JpaRepository<Categoria, Integer>,
	RevisionRepository<Categoria,Integer,Integer>{

}

package br.com.castgroup.curso.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.castgroup.curso.entity.Curso;

@Repository
public interface RepositoryCurso extends CrudRepository<Curso, Integer>{

}

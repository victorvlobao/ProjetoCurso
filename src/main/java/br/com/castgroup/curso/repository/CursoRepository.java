package br.com.castgroup.curso.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.castgroup.curso.entity.Curso;

@Repository
public interface CursoRepository extends 
	JpaRepository<Curso, Integer>,
	RevisionRepository<Curso,Integer,Integer>{

	/*List<Curso> findByDescricao(String descricao);*/
	
	@Query("select c from Curso c where descricao like Concat('%',:valor,'%')")
	List<Curso> getDescricao(@Param("valor")String valor);
	
	@Query(value = "from Curso c where c.dataInicio BETWEEN :startDate AND :endDate")
    public List<Curso> getAllBetweenDates(@Param("startDate")LocalDate startDate,@Param("endDate")LocalDate endDate);
	
}

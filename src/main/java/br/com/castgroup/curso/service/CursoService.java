package br.com.castgroup.curso.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.castgroup.curso.Application;
import br.com.castgroup.curso.entity.Curso;
import br.com.castgroup.curso.repository.CursoRepository;

@Service
public class CursoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	@Autowired
	CursoRepository repository;

	@PersistenceContext
	EntityManager entity;

	// -----------------------------------------------------------------------------------------BUSCA
	// FUNCIONANDO
	// Busca todos

	public List<Curso> busca() {
		return repository.findAll();
	}

	// Busca por Id
	public ResponseEntity<Optional<Curso>> buscaById(Integer idCurso) {
		Optional<Curso> item = repository.findById(idCurso);
		Curso curso = item.get();
		curso.setDescricao(curso.getDescricao());
		LOGGER.info("Curso Buscado por ID com Sucesso");
		return ResponseEntity.status(HttpStatus.OK).body(item);

	}

	/*
	 * // Busca por descricao public ResponseEntity<List<Curso>>
	 * buscaByDescricao(String descricao) { List<Curso> response = new
	 * ArrayList<>(); for (Curso curso : repository.getDescricao(descricao)) { Curso
	 * var = new Curso(); var.setIdcurso(curso.getIdcurso());
	 * var.setDescricao(curso.getDescricao());
	 * var.setDataInicio(curso.getDataInicio());
	 * var.setDataTermino(curso.getDataTermino());
	 * var.setQuantidadeAlunos(curso.getQuantidadeAlunos());
	 * var.setCategoria(curso.getCategoria()); response.add(var); }
	 * LOGGER.info("Curso Buscado por Descrição com Sucesso"); return
	 * ResponseEntity.status(HttpStatus.OK).body(response); }
	 */
	public List<Curso> consultar(String descricao, LocalDate dataInicio, LocalDate dataTermino) {
		System.out.println(dataInicio);
		CriteriaBuilder criteria = entity.getCriteriaBuilder();
		CriteriaQuery<Curso> criteriaQuery = criteria.createQuery(Curso.class);

		Root<Curso> curso = criteriaQuery.from(Curso.class);
		List<Predicate> predList = new ArrayList<>();

		if (descricao != "") {
			Predicate descricaoPredicate = criteria.equal(curso.get("descricao"), descricao);
			predList.add(descricaoPredicate);
		}

		if (dataInicio != null) {
			Predicate dataInicioPredicate = criteria.greaterThanOrEqualTo(curso.get("dataInicio"), dataInicio);
			predList.add(dataInicioPredicate);
		}

		if (dataTermino != null) {
			Predicate dataTerminoPredicate = criteria.lessThanOrEqualTo(curso.get("dataTermino"), dataTermino);
			predList.add(dataTerminoPredicate);
		}

		Predicate[] predicateArray = new Predicate[predList.size()];

		predList.toArray(predicateArray);

		criteriaQuery.where(predicateArray);

		TypedQuery<Curso> query = entity.createQuery(criteriaQuery);

		return query.getResultList();

	}

	// -----------------------------------------------------------------------------------------CADASTRA
	// FUNCIONANDO
	public void cadastrar(Curso curso) {
		validaData1(curso);
		validaData2(curso);
		validaData3(curso);
		repository.save(curso);
	}

	public void validaData1(Curso curso) {
		if (curso.getDataInicio().isBefore(LocalDate.now())) {
			throw new RuntimeException("Data de Início Inválida");
		}
	}

	public void validaData2(Curso curso) {
		if (curso.getDataInicio().isAfter(curso.getDataTermino())) {
			throw new RuntimeException("Datas Incorretas");
		}
	}

	// validação de período
	private void validaData3(Curso curso) {
		if (curso.getDataInicio().isAfter(curso.getDataTermino())) {
			throw new RuntimeException(" não permitido");
		}

		List<Curso> buscacurso = repository.getAllBetweenDates(curso.getDataInicio(), curso.getDataTermino());
		if (buscacurso.size() > 0) {
			throw new RuntimeException("Este período já possui cursos");
		}

	}

	// -----------------------------------------------------------------------------------------ATUALIZA
	// FUNCIONANDO

	public void putCurso(Curso curso) {
		repository.save(curso);
	}

	// -----------------------------------------------------------------------------------------DELETA
	// FUNCIONANDO

	public void delete(Integer idcurso) {
		validaDelete(idcurso);
		repository.deleteById(idcurso);
	}

	public void validaDelete(Integer idcurso) {
		Optional<Curso> curso = repository.findById(idcurso);
		Curso c = curso.get();

		if (c.getDataTermino().isBefore(LocalDate.now())) {
			throw new RuntimeException("Não é possível excluir este curso, já realizado.");
		}
	}
}

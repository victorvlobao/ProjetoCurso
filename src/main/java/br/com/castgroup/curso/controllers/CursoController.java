package br.com.castgroup.curso.controllers;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.castgroup.curso.Application;
import br.com.castgroup.curso.entity.Curso;
import br.com.castgroup.curso.service.CursoService;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value="/curso")
public class CursoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
	
	@Autowired
	CursoService service;


	//-----------------------------------------------------------------------------------------CADASTRA FUNCIONANDO
	
	@ApiOperation("Método Post")
	@PostMapping
	public ResponseEntity<String> post(@RequestBody Curso curso) {
		try {
			service.cadastrar(curso);
			LOGGER.info("Curso Cadastrado com Sucesso");
			return ResponseEntity.ok().body("Cadastrado com Sucesso");
		}catch(Exception e) {
			LOGGER.error("Erro ao cadastrar.");
			
			return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
		}
	}
		
	//-----------------------------------------------------------------------------------------BUSCA FUNCIONANDO

	/*@GetMapping
	public List<Curso> get() {
		LOGGER.info("Cursos Buscados com Sucesso");
		return service.busca();
		
	}*/
	
	@GetMapping("/{idcurso}")
	public ResponseEntity<Optional<Curso>> getByID(@PathVariable("idcurso")Integer idCurso){
		LOGGER.info("Cursos Buscados por ID com Sucesso");
		return service.buscaById(idCurso);
	}
	/*
	@GetMapping("/descricao")
	public ResponseEntity<List<Curso>> getByDescricao(String descricao){
		LOGGER.info("Cursos Buscados por Descrição com Sucesso");
		return service.buscaByDescricao(descricao);
	}
	*/
	
	@GetMapping
	public ResponseEntity<List<Curso>> listarTudo(@RequestParam(required = false) String descricao,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataTermino) {
		List<Curso> curso = service.consultar(descricao, dataInicio, dataTermino);

		return ResponseEntity.ok().body(curso);
	}
	

	//----------------------------------------------------------------------------------------DELETAR 
	
	@DeleteMapping("/{idcurso}")
	public ResponseEntity<String> delete(@PathVariable Integer idcurso) {
		try {
			service.delete(idcurso);
			LOGGER.info("Curso Excluído com Sucesso");
			return ResponseEntity.status(HttpStatus.OK).body("Curso Excluido com Sucesso");
		}catch (Exception e){
			LOGGER.error("Erro ao Excluir.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Você não pode excluir, Curso já realizado");	  
		}
	}


	//-----------------------------------------------------------------------------------------ATUALIZAR FUNCIONANDO
	
	@PutMapping
	public ResponseEntity<String> put(@RequestBody Curso curso){
		service.putCurso(curso);
		LOGGER.info("Curso Atualizado com Sucesso");
		return ResponseEntity.status(HttpStatus.OK).body("Curso Atualizado com Sucesso");
	}	
	
	//-----------------------------------------------------------------------------------------AMOSTRA DE TESTE
	/*
	 * 
	 *@GetMapping("/descricao") public ResponseEntity<List<CursoGetResponse>>
	 * buscar(String descricao){ List<CursoGetResponse> response = new
	 * ArrayList<>();
	 * 
	 * for (Curso curso : repository.getDescricao(descricao)) { CursoGetResponse var
	 * = new CursoGetResponse();
	 * 
	 * var.setIdcurso(curso.getIdcurso()); var.setDescricao(curso.getDescricao());
	 * var.setDataInicio(curso.getDataInicio());
	 * var.setDataTermino(curso.getDataTermino());
	 * var.setQuantidadeAlunos(curso.getQuantidadeAlunos());
	 * var.setCategoria(curso.getCategoria());
	 * 
	 * response.add(var);
	 * 
	 * } return ResponseEntity.status(HttpStatus.OK).body(response); } 
	 *
	 *
	 *
	 *
	 *
	 * @PostMapping(value = ENDPOINT) public ResponseEntity<String>
	 * post( @RequestBody CursoPostRequest request) { Curso curso = new Curso();
	 * curso.setDescricao(request.getDescricao());
	 * curso.setDataInicio(request.getDataInicio());
	 * curso.setDataTermino(request.getDataTermino());
	 * curso.setQuantidadeAlunos(request.getQuantidadeAlunos());
	 * curso.setCategoria(request.getCategoria());
	 * if(LocalDate.now().isAfter(curso.getDataInicio()) ||
	 * curso.getDataInicio().isAfter(curso.getDataTermino()) ) { return
	 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERRO"); }
	 * 
	 * 
	 * else { curso.setDescricao(request.getDescricao());
	 * curso.setDataInicio(request.getDataInicio());
	 * curso.setDataTermino(request.getDataTermino());
	 * curso.setQuantidadeAlunos(request.getQuantidadeAlunos());
	 * curso.setCategoria(request.getCategoria()); repository.save(curso); return
	 * ResponseEntity.status(HttpStatus.OK).body("Curso cadastrado com sucesso"); }
	 * }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * /* //busca todos
	 * 
	 * @GetMapping(value = ENDPOINT) public ResponseEntity<List<CursoGetResponse>>
	 * get() {
	 * 
	 * List<CursoGetResponse> response = new ArrayList<CursoGetResponse>();
	 * 
	 * for (Curso curso : repository.findAll()) {
	 * 
	 * CursoGetResponse var = new CursoGetResponse();
	 * 
	 * var.setIdcurso(curso.getIdcurso()); var.setDescricao(curso.getDescricao());
	 * var.setDataInicio(curso.getDataInicio());
	 * var.setDataTermino(curso.getDataTermino());
	 * var.setQuantidadeAlunos(curso.getQuantidadeAlunos());
	 * var.setCategoria(curso.getCategoria());
	 * 
	 * response.add(var); } return
	 * ResponseEntity.status(HttpStatus.OK).body(response); }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * /*@GetMapping(value = "/date/{dataInicio}/{dataTermino}") public
	 * ResponseEntity<?> buscarPorData(
	 * 
	 * @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate dataInicio,
	 * 
	 * @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate dataTermino){
	 * List<CursoGetResponse> response = new ArrayList<>();
	 * 
	 * for (Curso curso :
	 * repository.FindByDataInicioBetween(dataInicio,dataTermino)) {
	 * CursoGetResponse var = new CursoGetResponse();
	 * 
	 * var.setIdcurso(curso.getIdcurso()); var.setDescricao(curso.getDescricao());
	 * var.setDataInicio(curso.getDataInicio());
	 * var.setDataTermino(curso.getDataTermino());
	 * var.setQuantidadeAlunos(curso.getQuantidadeAlunos());
	 * var.setCategoria(curso.getCategoria());
	 * 
	 * response.add(var);
	 * 
	 * } return ResponseEntity.status(HttpStatus.OK).body(response); }
	 */

	/*
	 * @DeleteMapping(value = ENDPOINT + "/{idcurso}") public ResponseEntity<String>
	 * delete(@PathVariable("idcurso") Integer idcurso) {
	 * 
	 * 
	 * Optional<Curso> item = repository.findById(idcurso);
	 * 
	 * if(item.isEmpty()) { return
	 * ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi encontrado");
	 * }else { Curso curso = item.get();
	 * if(LocalDate.now().isBefore(curso.getDataTermino())) {
	 * repository.delete(curso); return
	 * ResponseEntity.status(HttpStatus.OK).body("Curso excluído com sucesso");
	 * }else { return ResponseEntity.status(HttpStatus.BAD_REQUEST).
	 * body("Você não pode excluir este curso"); } } }
	 */

	

	/*
	 * @PutMapping(value = ENDPOINT) public ResponseEntity<String> put(@RequestBody
	 * CursoPutRequest request) { try {
	 * 
	 * Optional<Curso> item = repository.findById(request.getIdcurso());
	 * 
	 * if (item.isEmpty()) { return
	 * ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Curso não encontrado"); }
	 * else {
	 * 
	 * Curso curso = item.get();
	 * 
	 * curso.setDescricao(request.getDescricao());
	 * curso.setDataInicio(request.getDataInicio());
	 * curso.setDataTermino(request.getDataTermino());
	 * curso.setQuantidadeAlunos(request.getQuantidadeAlunos());
	 * curso.setCategoria(request.getCategoria());
	 * 
	 * 
	 * repository.save(curso);
	 * 
	 * return ResponseEntity.status(HttpStatus.OK).body("Atualizado"); }
	 * 
	 * } catch (Exception e) { return
	 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro :" +
	 * e.getMessage()); }
	 * 
	 * }
	 */
}

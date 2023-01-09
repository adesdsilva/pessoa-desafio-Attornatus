package br.com.setecolinas.pessoasmanager.pessoa.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.setecolinas.pessoasmanager.pessoa.model.Pessoa;
import br.com.setecolinas.pessoasmanager.pessoa.service.PessoaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/pessoas")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> buscarPessoaId(@PathVariable("id") Long id){
		return ResponseEntity.ok().body(pessoaService.buscarPessoaId(id));
	}
	
	@GetMapping("/{id}/listarEnderecosPessoa")
	public ResponseEntity<String> listarEnderecosPessoa(@PathVariable("id") Long id){
		return ResponseEntity.ok().body(pessoaService.buscarPessoaId(id).getEnderecos().toString());
	}
	
	@GetMapping("/listarPessoas")
	public ResponseEntity<List<Pessoa>> listarPessoas() {
		return ResponseEntity.ok().body(pessoaService.listarPessoas());
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> inserirPessoa(@Valid @RequestBody Pessoa p) {
		Pessoa pessoa = pessoaService.inserirPessoa(p);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(pessoa.getId()).toUri();
		return ResponseEntity.created(location).body(pessoa);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> alterarPessoa(@Valid @RequestBody Pessoa p) {
		pessoaService.alterarPessoa(p);
		return ResponseEntity.noContent().build();
	}
	
}

package br.com.setecolinas.pessoasmanager.pessoa.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.setecolinas.pessoasmanager.pessoa.exceptions.BadRequestException;
import br.com.setecolinas.pessoasmanager.pessoa.exceptions.NotFoundException;
import br.com.setecolinas.pessoasmanager.pessoa.model.Pessoa;
import br.com.setecolinas.pessoasmanager.pessoa.repository.PessoaRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Pessoa buscarPessoaId(Long id) {
		return pessoaRepository.findById(id).orElseThrow(
				() -> new NotFoundException("Objeto nao encontrado! Id:" + id + ", Tipo: " + Pessoa.class.getName()));
	}
	
	public List<Pessoa> listarPessoas() {
		return pessoaRepository.findAll();
	}

	public Pessoa inserirPessoa(Pessoa p) {
		Objects.requireNonNull(p);
		boolean exists = pessoaRepository.existsByNome(p.getNome());
		if (exists) {
			throw new BadRequestException("Ja existe uma Pessoa cadastrada com o Nome informado!");
		}
		
		return pessoaRepository.save(p);
	}

	public void alterarPessoa(Pessoa pessoaUpdated) {
		Objects.requireNonNull(pessoaUpdated);
		boolean exists = pessoaRepository.existsById(pessoaUpdated.getId());
		if (!exists) {
			throw new BadRequestException("Nao foi possivel alterar a Pessoa! Id inexistente");
		}
		Pessoa p = new Pessoa();
		p.update(pessoaUpdated);	
		pessoaRepository.save(p);
	}
	
}

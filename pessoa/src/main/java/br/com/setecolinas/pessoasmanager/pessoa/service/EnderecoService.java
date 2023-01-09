package br.com.setecolinas.pessoasmanager.pessoa.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.setecolinas.pessoasmanager.pessoa.exceptions.BadRequestException;
import br.com.setecolinas.pessoasmanager.pessoa.exceptions.NotFoundException;
import br.com.setecolinas.pessoasmanager.pessoa.model.Endereco;
import br.com.setecolinas.pessoasmanager.pessoa.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Endereco buscarEnderecoId(Long id) {
		return enderecoRepository.findById(id).orElseThrow(
				() -> new NotFoundException("Objeto nao encontrado! Id:" + id + ", Tipo: " + Endereco.class.getName()));
	}
	
	public List<Endereco> listarEnderecos() {
		return enderecoRepository.findAll();
	}

	public Endereco inserirEndereco(Endereco e) {
		Objects.requireNonNull(e);
		boolean exists = enderecoRepository.existsByCep(e.getCep());
		if (exists) {
			throw new BadRequestException("Ja existe um Endereço cadastrado com o CEP informado!");
		}
		
		return enderecoRepository.save(e);
	}

	public void alterarEndereco(Endereco enderecoUpdated) {
		Objects.requireNonNull(enderecoUpdated);
		boolean exists = enderecoRepository.existsById(enderecoUpdated.getId());
		if (!exists) {
			throw new BadRequestException("Nao foi possivel alterar o Endereco! Id inexistente");
		}
		Endereco end = new Endereco();
		end.update(enderecoUpdated);
		enderecoRepository.save(end);
	}
}

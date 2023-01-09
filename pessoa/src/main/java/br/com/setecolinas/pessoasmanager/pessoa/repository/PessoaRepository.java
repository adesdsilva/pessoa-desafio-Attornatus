package br.com.setecolinas.pessoasmanager.pessoa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.setecolinas.pessoasmanager.pessoa.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	public Optional<Pessoa> findByNome(String nome);

	public boolean existsByNome(String nome);

	public boolean existsById(Long id);
	
	
}

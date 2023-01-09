package br.com.setecolinas.pessoasmanager.pessoa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.setecolinas.pessoasmanager.pessoa.model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	public Optional<Endereco> findByTipo(String tipo);

	public boolean existsByCep(String cep);
}

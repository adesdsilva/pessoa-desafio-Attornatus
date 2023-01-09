package br.com.setecolinas.pessoasmanager.pessoa.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "pessoa")
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pessoa_id")
	private Long id;
	
    @Column(length = 100, nullable = false)
	private String nome;
	
    @Column(name = "data_nascimento")
    @JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data_Nascimento;
	
    @OneToMany(mappedBy = "pessoa")
	private List<Endereco> enderecos;
	
	public Pessoa() {
		//JPA/Hibernate
	}

	public Pessoa(Long id, String nome, LocalDate data_Nascimento, List<Endereco> enderecos) {
		
		Objects.requireNonNull(nome);
		Objects.requireNonNull(data_Nascimento);
		Objects.requireNonNull(enderecos);
		
		this.id = id;
		this.nome = nome;
		this.data_Nascimento = data_Nascimento;
		this.enderecos = enderecos;
	}
	
	public void update(Pessoa pessoaUpdated) {
		Objects.requireNonNull(pessoaUpdated);
		
		this.nome = pessoaUpdated.nome;
		this.data_Nascimento = pessoaUpdated.data_Nascimento;
		for (Endereco endereco : this.enderecos) {
			for (Endereco end : pessoaUpdated.enderecos) {
				endereco.update(end);
			}
		}
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public LocalDate getData_Nascimento() {
		return data_Nascimento;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setData_Nascimento(LocalDate data_Nascimento) {
		this.data_Nascimento = data_Nascimento;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	
	
	
}

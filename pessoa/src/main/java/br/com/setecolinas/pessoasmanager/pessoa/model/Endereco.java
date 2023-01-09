package br.com.setecolinas.pessoasmanager.pessoa.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "endereco")
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "equipe_id")
	private Long id;

	@Column(length = 10, nullable = false)
	private String cep;

	@Column(length = 100, nullable = false)
	private String logradouro;

	@Column(length = 6, nullable = false)
	private String numero;

	@Column(length = 25, nullable = false)
	private String cidade;

	@ManyToOne(optional = false)
	@JoinColumn(name = "pessoa_id", nullable = false, foreignKey = @ForeignKey(name = "fk_endereco_pessoa"))
	private Pessoa pessoa;

    @Column(length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
	private TipoEndereco tipo;

	public Endereco() {
		// JPA/Hibernate
	}

	public Endereco(Long id, String cep, String logradouro, String numero, String cidade, Pessoa pessoa) {
		Objects.requireNonNull(cep);
		Objects.requireNonNull(logradouro);
		Objects.requireNonNull(numero);
		Objects.requireNonNull(cidade);

		this.id = id;
		this.cep = cep;
		this.logradouro = logradouro;
		this.numero = numero;
		this.cidade = cidade;
		this.pessoa = pessoa;
	}

	public void update(Endereco enderecoUpdated) {
		Objects.requireNonNull(enderecoUpdated);

		this.cep = enderecoUpdated.cep;
		this.logradouro = enderecoUpdated.logradouro;
		this.numero = enderecoUpdated.numero;
		this.cidade = enderecoUpdated.cidade;
	}

	public Long getId() {
		return id;
	}

	public String getCep() {
		return cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public String getCidade() {
		return cidade;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public TipoEndereco getTipo() {
		return tipo;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public void setTipo(TipoEndereco tipo) {
		this.tipo = tipo;
	}

	
}

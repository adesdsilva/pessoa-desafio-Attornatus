package br.com.setecolinas.pessoasmanager.pessoa.config;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.setecolinas.pessoasmanager.pessoa.model.Endereco;
import br.com.setecolinas.pessoasmanager.pessoa.model.Pessoa;
import br.com.setecolinas.pessoasmanager.pessoa.model.TipoEndereco;
import br.com.setecolinas.pessoasmanager.pessoa.repository.EnderecoRepository;
import br.com.setecolinas.pessoasmanager.pessoa.repository.PessoaRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	@Override
	public void run(String... args) throws Exception {		
		Pessoa p1 = new Pessoa();
		Pessoa p2 = new Pessoa();
		
		TipoEndereco tipo1 = TipoEndereco.PRINCIPAL;
		TipoEndereco tipo2 = TipoEndereco.SECUNDARIO;
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.format("11/05/1995");
		
		Endereco e1 = new Endereco();
		e1.setCep("55290-000");
		e1.setCidade("Garanhuns");
		e1.setLogradouro("Rua da Palma");
		e1.setNumero("123");
		e1.setPessoa(p1);
		e1.setTipo(tipo1);
		
		Endereco e2 = new Endereco();
		e2.setCep("55299-120");
		e2.setCidade("Garanhuns");
		e2.setLogradouro("Rua dos poetas");
		e2.setNumero("875");
		e2.setPessoa(p1);
		e2.setTipo(tipo2);
		
		p1.setData_Nascimento(LocalDate.now());
		p1.setNome("Adelino Santos");
		p1.getEnderecos().add(e1);
		
		p2.setData_Nascimento(LocalDate.parse("11/10/2000"));
		p2.setNome("Adelino Santos");
		p2.getEnderecos().add(e1);
		p2.getEnderecos().add(e2);
			
		pessoaRepository.saveAll(Arrays.asList(p1, p2));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
	}
	
}

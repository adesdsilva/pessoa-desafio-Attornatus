package br.com.setecolinas.pessoasmanager.pessoa.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.setecolinas.pessoasmanager.pessoa.model.Endereco;
import br.com.setecolinas.pessoasmanager.pessoa.model.Pessoa;
import br.com.setecolinas.pessoasmanager.pessoa.model.TipoEndereco;
import br.com.setecolinas.pessoasmanager.pessoa.repository.PessoaRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PessoaController.class })
public class PessoaControllerTest {

	// URL base para acesso desse controlador
	private final String BASE_URL = "/api/v1/pessoas";

	// Instância do ObjectMapper para trabalhar com JSON
	private ObjectMapper objectMapper;

	@Autowired
	private PessoaController restController;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PessoaRepository pessoaRepository;

	@Before(value = "")
	public void setUp() {
		objectMapper = new ObjectMapper();
		mockMvc = MockMvcBuilders.standaloneSetup(restController).build();
	}

	@Test
	public void buscar_id_1L() throws Exception {

		Pessoa pessoa = new Pessoa();
		TipoEndereco tipo1 = TipoEndereco.PRINCIPAL;
		TipoEndereco tipo2 = TipoEndereco.SECUNDARIO;
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.format("11/05/1995");
		
		Endereco e1 = new Endereco();
		e1.setCep("55290-000");
		e1.setCidade("Garanhuns");
		e1.setLogradouro("Rua da Palma");
		e1.setNumero("123");
		e1.setPessoa(pessoa);
		e1.setTipo(tipo1);
		
		Endereco e2 = new Endereco();
		e2.setCep("55299-120");
		e2.setCidade("Garanhuns");
		e2.setLogradouro("Rua dos poetas");
		e2.setNumero("875");
		e2.setPessoa(pessoa);
		e2.setTipo(tipo2);
		
		pessoa.setData_Nascimento(LocalDate.now());
		pessoa.setNome("Adelino Santos");
		pessoa.getEnderecos().add(e1);
		//setar valor do ID para 1L;
		
		when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));

		mockMvc.perform(get(BASE_URL + "/1")).andExpect((ResultMatcher) content().contentType(org.springframework.http.MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.nome", is("Teste"))).andExpect(jsonPath("$.valor", is(10.0)));

		verify(pessoaRepository, times(1)).findById(1L);
	}
	
	@Test
    public void buscar_id_404() throws Exception {
        mockMvc.perform(get(BASE_URL + "/2")).andExpect(status().isNotFound());
    }
	
	@Test
    public void criar_200() throws Exception {

		Pessoa pessoa = new Pessoa();
		TipoEndereco tipo1 = TipoEndereco.PRINCIPAL;
		TipoEndereco tipo2 = TipoEndereco.SECUNDARIO;
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.format("11/05/1995");
		
		Endereco e1 = new Endereco();
		e1.setCep("55298-000");
		e1.setCidade("Garanhuns");
		e1.setLogradouro("Rua da Alegrie");
		e1.setNumero("15");
		e1.setPessoa(pessoa);
		e1.setTipo(tipo1);
		
		Endereco e2 = new Endereco();
		e2.setCep("55292-180");
		e2.setCidade("Garanhuns");
		e2.setLogradouro("Rua das Flores");
		e2.setNumero("75");
		e2.setPessoa(pessoa);
		e2.setTipo(tipo2);
		
		pessoa.setData_Nascimento(LocalDate.now());
		pessoa.setNome("Teste pessoa");
		pessoa.getEnderecos().add(e1);

        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);

        mockMvc.perform(post(BASE_URL)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1L)))
                .andExpect(jsonPath("$.nome", is("Teste pessoa")))
                .andExpect(jsonPath("$.data_nascimento", is(LocalDate.now())));

        verify(pessoaRepository, times(1)).save(any(Pessoa.class));

    }
	
	@Test
    public void atualizar_200() throws Exception {

		Pessoa pessoa = new Pessoa();
		TipoEndereco tipo1 = TipoEndereco.PRINCIPAL;
		TipoEndereco tipo2 = TipoEndereco.SECUNDARIO;
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.format("11/05/1995");
		
		Endereco e1 = new Endereco();
		e1.setCep("55290-000");
		e1.setCidade("Garanhuns");
		e1.setLogradouro("Rua da Palma");
		e1.setNumero("123");
		e1.setPessoa(pessoa);
		e1.setTipo(tipo1);
		
		Endereco e2 = new Endereco();
		e2.setCep("55299-120");
		e2.setCidade("Garanhuns");
		e2.setLogradouro("Rua dos poetas");
		e2.setNumero("875");
		e2.setPessoa(pessoa);
		e2.setTipo(tipo2);
		
		pessoa.setData_Nascimento(LocalDate.now());
		pessoa.setNome("Adelino Santos");
		pessoa.getEnderecos().add(e1);

        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);

        mockMvc.perform(put(BASE_URL + "/1L")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1L)));
    }

    @Test
    public void deletar_200() throws Exception {

    	Pessoa pessoa = new Pessoa();
		TipoEndereco tipo1 = TipoEndereco.PRINCIPAL;
		TipoEndereco tipo2 = TipoEndereco.SECUNDARIO;
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.format("11/05/1995");
		
		Endereco e1 = new Endereco();
		e1.setCep("55290-000");
		e1.setCidade("Garanhuns");
		e1.setLogradouro("Rua da Palma");
		e1.setNumero("123");
		e1.setPessoa(pessoa);
		e1.setTipo(tipo1);
		
		Endereco e2 = new Endereco();
		e2.setCep("55299-120");
		e2.setCidade("Garanhuns");
		e2.setLogradouro("Rua dos poetas");
		e2.setNumero("875");
		e2.setPessoa(pessoa);
		e2.setTipo(tipo2);
		
		pessoa.setData_Nascimento(LocalDate.now());
		pessoa.setNome("Adelino Santos");
		pessoa.getEnderecos().add(e1);

        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));

        mockMvc.perform(delete(BASE_URL + "/1L"))
                .andExpect(status().isOk());

        verify(pessoaRepository, times(1)).deleteById(1L);
    }
    

}

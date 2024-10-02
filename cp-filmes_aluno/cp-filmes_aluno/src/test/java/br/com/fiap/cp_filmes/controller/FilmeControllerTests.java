package br.com.fiap.cp_filmes.controller;


import br.com.fiap.cp_filmes.dto.FilmeDTO;
import br.com.fiap.cp_filmes.service.FilmeService;
import br.com.fiap.cp_filmes.service.exception.ResourceNotFoundException;
import br.com.fiap.cp_filmes.tests.FilmeFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FilmeController.class)
public class FilmeControllerTests {


    @Autowired
    private MockMvc mockMvc; //para chamar o endpoint
    //controller tem dependência do service
    //dependência mockada
    @MockBean
    private FilmeService service;
    private FilmeDTO filmeDTO;

    //Converter para JSON o objeto Java e enviar na requisção
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() throws Exception {



        //criando um PagamentoDTO
        filmeDTO = FilmeFactory.createFilmeDTO();

        //Listando PagamentoDTO
        List<FilmeDTO> list = List.of(filmeDTO);

        //simulando comportamento do service - findAll
        when(service.findAll()).thenReturn(list);

    }

    @Test
    public void findAllShouldReturnListPagamentoDTO() throws Exception {

        //chamando uma requisição com o método get em /pagamentos
        ResultActions result = mockMvc.perform(get("/filmes")
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }


}

package br.com.fiap.cp_filmes.controller;


import br.com.fiap.cp_filmes.dto.FilmeDTO;
import br.com.fiap.cp_filmes.tests.FilmeFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional


public class FilmeControllerIT {

    @Autowired
    private MockMvc mockMvc;
    private Long existingId;
    private Long nonExistingId;
    private FilmeDTO filmeDTO;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() throws Exception {
        existingId = 1L;
        nonExistingId = 50L;
        filmeDTO = FilmeFactory.createFilmeDTO();
    }


    @Test
    public void updateShouldUpdateAndReturnFilmeDTOWhenIdExists() throws Exception {

        String jsonBody = objectMapper.writeValueAsString(filmeDTO);
        mockMvc.perform(put("/filmes/{id}", existingId)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.titulo").exists())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void updateShoulReturnNotFoundWhenIdDoesNotExist() throws Exception {

        String jsonBody = objectMapper.writeValueAsString(filmeDTO);
        mockMvc.perform(put("/filmes/{id}", nonExistingId)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }


}

package br.com.fiap.cp_filmes.service;

import br.com.fiap.cp_filmes.dto.FilmeDTO;
import br.com.fiap.cp_filmes.model.Filme;
import br.com.fiap.cp_filmes.repository.FilmeRepository;
import br.com.fiap.cp_filmes.service.exception.ResourceNotFoundException;
import br.com.fiap.cp_filmes.tests.FilmeFactory;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
@ExtendWith(SpringExtension.class)

public class FilmeServiceTests {


    @InjectMocks
    private FilmeService service;

    @Mock
    private FilmeRepository repository;

    private Long existingId;
    private Long nonExistingId;

    private Filme filme;
    private FilmeDTO filmeDTO;

    @BeforeEach
    void setup() throws Exception {
        existingId = 1L;
        nonExistingId = 10L;
        //próximos testes
        filme = FilmeFactory.createFilme();
        filmeDTO = new FilmeDTO(filme);
        //simulação do comportamento
        // findById
        Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(filme));
        Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());

    }



    @Test
    public void findByIdShouldReturnFilmeDTOWhenIdExists(){
        FilmeDTO result = service.findById(existingId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getId(), existingId);
        Assertions.assertEquals(result.getTitulo(), filme.getTitulo());
        Assertions.assertEquals(result.getClassificacao(), filme.getClassificacao());
        Assertions.assertEquals(result.getAnoDeLancamento(), filme.getAnoDeLancamento());
        Assertions.assertEquals(result.getGenero(), filme.getGenero());
    }

    @Test
    public void findByIdShouldReturnResourceNotFoundExceptionWhenIdDoesNotExists(){
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.findById(nonExistingId);
        });
    }


}

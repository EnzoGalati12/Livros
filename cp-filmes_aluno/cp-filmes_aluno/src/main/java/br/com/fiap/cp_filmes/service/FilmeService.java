package br.com.fiap.cp_filmes.service;

import br.com.fiap.cp_filmes.dto.FilmeDTO;
import br.com.fiap.cp_filmes.model.Filme;
import br.com.fiap.cp_filmes.repository.FilmeRepository;
import br.com.fiap.cp_filmes.service.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmeService {
    @Autowired
    private FilmeRepository repository;

    @Transactional(readOnly = true)
    public List<FilmeDTO> findAll() {
        return repository.findAll().stream().map(FilmeDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public FilmeDTO findById(Long id) {
        Filme entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado! Id: " + id)
        );

        return new FilmeDTO(entity);
    }



    @Transactional
    public FilmeDTO update(Long id, FilmeDTO dto) {
        try {
            Filme entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new FilmeDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado! Id: " + id);
        }
    }



    private void copyDtoToEntity(FilmeDTO dto, Filme entity) {

      //  entity.setId(dto.getId());
        entity.setTitulo(dto.getTitulo());
        entity.setClassificacao(dto.getClassificacao());
        entity.setGenero(dto.getGenero());
        entity.setAnoDeLancamento(dto.getAnoDeLancamento());
    }
}

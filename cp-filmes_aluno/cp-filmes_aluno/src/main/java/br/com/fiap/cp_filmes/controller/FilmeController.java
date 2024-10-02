package br.com.fiap.cp_filmes.controller;

import br.com.fiap.cp_filmes.dto.FilmeDTO;
import br.com.fiap.cp_filmes.model.Filme;
import br.com.fiap.cp_filmes.service.FilmeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/filmes")

public class FilmeController {

    @Autowired
    private FilmeService service;

    @GetMapping
    public ResponseEntity<List<FilmeDTO>> findAll() {

        List<FilmeDTO> dto = service.findAll();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmeDTO> findById(@PathVariable Long id) {
        FilmeDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }



    @PutMapping("/{id}")
    public ResponseEntity<FilmeDTO> update(@PathVariable @NotNull Long id,
                                           @RequestBody @Valid FilmeDTO dto ){
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }



}

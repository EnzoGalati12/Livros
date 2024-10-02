package br.com.fiap.cp_filmes.repository;

import br.com.fiap.cp_filmes.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
}

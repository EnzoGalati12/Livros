package br.com.fiap.cp_filmes.tests;
import br.com.fiap.cp_filmes.dto.FilmeDTO;
import br.com.fiap.cp_filmes.model.Filme;

public class FilmeFactory {

    public static Filme createFilme(){

        return new Filme(1L, "Back to the Future", 1985, "Aventura", "12 anos");
    }

    public static FilmeDTO createFilmeDTO(){
        Filme filme = createFilme();
        return new FilmeDTO(filme);
    }

}

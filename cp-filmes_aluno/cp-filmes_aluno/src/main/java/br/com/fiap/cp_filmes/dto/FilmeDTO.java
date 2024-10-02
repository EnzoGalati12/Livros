package br.com.fiap.cp_filmes.dto;


import br.com.fiap.cp_filmes.model.Filme;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class FilmeDTO {



    private long id;
    @NotNull(message = "Campo é obrigatório")
    private String titulo;

    @NotNull(message = "Campo  é obrigatório")
    @Positive
    private int anoDeLancamento;

    @NotNull(message = "Campo  é obrigatório")
    private String genero;

    @NotNull(message = "Campo  é obrigatório")
    private String classificacao;

    public FilmeDTO(Filme entity){
        id = entity.getId();
        titulo= entity.getTitulo();
        anoDeLancamento = entity.getAnoDeLancamento();
        classificacao = entity.getClassificacao();
        genero= entity.getGenero();

    }

}

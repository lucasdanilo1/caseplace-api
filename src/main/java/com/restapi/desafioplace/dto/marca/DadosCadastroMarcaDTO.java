package com.restapi.desafioplace.dto.marca;

import com.restapi.desafioplace.dto.modelo.DadosCadastroModeloDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DadosCadastroMarcaDTO(

        @NotBlank
        String nome,
        @NotNull
        Integer codDenatran,
        List<DadosCadastroModeloDTO> modelos

) {
}

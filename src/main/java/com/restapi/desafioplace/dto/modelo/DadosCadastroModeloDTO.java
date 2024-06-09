package com.restapi.desafioplace.dto.modelo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroModeloDTO(

        @NotBlank
        String nome,
        @NotNull
        Integer ano,
        @NotNull
        Long marcaId
){
}

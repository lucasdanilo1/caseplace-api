package com.restapi.desafioplace.dto.modelo;

public record FiltrosModeloDTO(

        String nome,
        Integer ano,
        Boolean ativo,
        Long marcaId

) {
}

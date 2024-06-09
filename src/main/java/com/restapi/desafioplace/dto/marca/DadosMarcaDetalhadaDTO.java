package com.restapi.desafioplace.dto.marca;

import com.restapi.desafioplace.model.Marca;

public record DadosMarcaDetalhadaDTO(

        Long id,
        String nome,
        Integer codDenatran,
        Boolean ativo
) {

    public DadosMarcaDetalhadaDTO(Marca marca){
        this(marca.getId(),
                marca.getNome(),
                marca.getCodDenatran(),
                marca.isAtivo());
    }

}

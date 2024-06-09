package com.restapi.desafioplace.dto.modelo;

import com.restapi.desafioplace.model.Modelo;

public record DadosListagemModeloDTO(

        Long id,
        String nome,
        Integer ano,
        Boolean ativo,
        Long marcaId

) {

    public DadosListagemModeloDTO(Modelo modelo){
        this(modelo.getId(), modelo.getNome(), modelo.getAno(), modelo.isAtivo(), modelo.getId());
    }

}

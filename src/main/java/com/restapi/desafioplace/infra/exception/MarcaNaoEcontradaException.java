package com.restapi.desafioplace.infra.exception;

import lombok.Getter;

@Getter
public class MarcaNaoEcontradaException extends RuntimeException {

    private final Long marcaId;

    public MarcaNaoEcontradaException(Long marcaId){
        this.marcaId = marcaId;
    }

}

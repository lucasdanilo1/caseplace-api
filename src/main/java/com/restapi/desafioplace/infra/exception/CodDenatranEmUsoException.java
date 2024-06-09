package com.restapi.desafioplace.infra.exception;

import lombok.Getter;

@Getter
public class CodDenatranEmUsoException extends RuntimeException {

    private final Integer codigo;

    public CodDenatranEmUsoException(Integer codigo){
        this.codigo = codigo;
    }

}

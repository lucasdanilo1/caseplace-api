package com.restapi.desafioplace.infra.exception;

import lombok.Getter;

@Getter
public class MarcaCadastradaException extends RuntimeException {

    private final String nomeMarca;

    public MarcaCadastradaException(String nomeMarca){
        this.nomeMarca = nomeMarca;
    }

}

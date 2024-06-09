package com.restapi.desafioplace.infra.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MarcaCadastradaException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Object> MarcaCadastrada(
            MarcaCadastradaException ex,
            WebRequest request) {
        final String errorMessage = "A marca inserida '" + ex.getNomeMarca() + "' já está cadastrada.";
        return buildErrorResponse(
                ex,
                errorMessage,
                HttpStatus.CONFLICT,
                request);
    }

    @ExceptionHandler(CodDenatranEmUsoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Object> CodDenatranEmUso(
            CodDenatranEmUsoException ex,
            WebRequest request) {
        final String errorMessage = "Já existe uma Marca associada ao código '" + ex.getCodigo() + "'";
        return buildErrorResponse(
                ex,
                errorMessage,
                HttpStatus.CONFLICT,
                request);
    }

    @ExceptionHandler(MarcaNaoEcontradaException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> MarcaNaoEcontradaException(
            MarcaNaoEcontradaException ex,
            WebRequest request) {
        final String errorMessage = "Marca não encontrada";
        return buildErrorResponse(
                ex,
                errorMessage,
                HttpStatus.NOT_FOUND,
                request);
    }

    @ExceptionHandler(ModeloNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> ModeloNaoEncontradoException(
            ModeloNaoEncontradoException ex,
            WebRequest request) {
        final String errorMessage = "Modelo não encontrado";
        return buildErrorResponse(
                ex,
                errorMessage,
                HttpStatus.NOT_FOUND,
                request);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleAllUncaughtException(
            Exception ex,
            WebRequest request) {
        final String errorMessage = "Um erro desconhecido ocorreu";
        return buildErrorResponse(
                ex,
                errorMessage,
                HttpStatus.INTERNAL_SERVER_ERROR,
                request);
    }

    @Override
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException methodArgumentNotValidException,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Erro de validação");
        for (FieldError fieldError : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
            errorResponse.addValidationError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ResponseEntity.unprocessableEntity().body(errorResponse);
    }

    private ResponseEntity<Object> buildErrorResponse(
            Exception exception,
            String message,
            HttpStatus httpStatus,
            WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), message);
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }

}

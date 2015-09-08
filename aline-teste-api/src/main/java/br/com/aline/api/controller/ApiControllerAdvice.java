package br.com.aline.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.aline.api.exception.CepNotFoundException;
import br.com.aline.api.exception.InvalidCepException;
import br.com.aline.api.model.ErrorInfo;

/**
 * Classe com o controller advice para as exceptions
 * 
 * @author aline.dias
 *
 */
@ControllerAdvice
public class ApiControllerAdvice {

    @ResponseBody
    @ExceptionHandler(CepNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorInfo cepNotFoundException(CepNotFoundException ex) {
        return new ErrorInfo(ex.getMessage());
    }
    
    @ResponseBody
    @ExceptionHandler(InvalidCepException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorInfo invalidCepException(InvalidCepException ex) {
        return new ErrorInfo(ex.getMessage());
    }
    
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorInfo methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return new ErrorInfo(ex.getLocalizedMessage());
    }
}

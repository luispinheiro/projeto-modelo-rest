package com.lespit.modelorest.infraestructure.web;

import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lespit.modelorest.domain.task.DuplicateTaskException;

//Para o Spring conhecer a classe
@RestControllerAdvice
public class WebRequestExceptionHandler {

	//Dizer para o Spring que esse métdodo trata uma exceção
	//É uma boa prática retornar o erro 400 BAD_REQUEST
	@ExceptionHandler
	//Preciso dizer o método HTTP que ele irá retornar
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	//Para passar o tipo de exceção que eu quero recuperar 
	//O objeto RepositoryConstraintViolationException tem o método getErrors 
	//passo como parâmetro para fromValidationError
	public RestResponseError handleException(RepositoryConstraintViolationException e) {
		return RestResponseError.fromValidationError(e.getErrors());
	}
	
	//Dizer para o Spring que esse métdodo trata uma exceção
	//É uma boa prática retornar o erro 400 BAD_REQUEST
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	//Para passar o tipo de exceção que eu quero recuperar 
	//O objeto DuplicateTaskException tem o método getMessage 
	//passo como parâmetro para fromMessage
	//3º
	public RestResponseError handleException(DuplicateTaskException e) {
		return RestResponseError.fromMessage(e.getMessage());
	}
}

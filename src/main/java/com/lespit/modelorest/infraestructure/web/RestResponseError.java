package com.lespit.modelorest.infraestructure.web;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

//Retorno de erro de uma requisição rest, utilizar o contrutor privado
public class RestResponseError {
	
	private String error;

	private RestResponseError() {
		
	}

	public String getError() {
		return error;
	}
	
	//Importar a interface Errors org.springframework.validation
	public static RestResponseError fromValidationError(Errors errors) {
		RestResponseError resp = new RestResponseError();
		StringBuilder sb = new StringBuilder();
		
		//Uma lista de ObjectError o append para um ponto e dar espaço entre as mensagens
		for (ObjectError error : errors.getAllErrors()) {
			sb.append(error.getDefaultMessage()).append(". ");
		}
		
		resp.error = sb.toString();
		return resp;
	}
	
	//Retorna a mensagem de duplicação e não o erro
	//4º
	public static RestResponseError fromMessage(String message) {
		RestResponseError resp = new RestResponseError();
		resp.error = message;
		return resp;
	}
	
}

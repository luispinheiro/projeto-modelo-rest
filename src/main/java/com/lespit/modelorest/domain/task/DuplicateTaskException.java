package com.lespit.modelorest.domain.task;

@SuppressWarnings("serial")
//Enviar uma mensagem no momento de savar ou atualizar a descrição duplicada
//1º
public class DuplicateTaskException extends Exception {

	public DuplicateTaskException(String message) {
		super(message);
	}

	
}

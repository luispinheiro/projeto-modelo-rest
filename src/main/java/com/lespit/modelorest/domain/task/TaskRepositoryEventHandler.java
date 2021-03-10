package com.lespit.modelorest.domain.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

//Spring reconhecer a classe
@Component
//Escolher as classes de validação de negócio, irá atuar antes de chamar o método save do repositório
@RepositoryEventHandler(Task.class)
//Tratar eventos de repositório.
//2º
public class TaskRepositoryEventHandler {

	private TaskRepository taskRepository;

	@Autowired
	//O Autowired pelo construtor tem algumas vantagens
	public TaskRepositoryEventHandler(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}
	
	//Chamar antes de salvar os dados com o Put
	@HandleBeforeSave
	//Chamar antes de atualizar o salvamento dos dados com o Post
	@HandleBeforeCreate
	public void handle(Task task) throws DuplicateTaskException {
		
		//Chama as tasks do método findByDescription do repositório
		Task taskDB = taskRepository.findByDescription(task.getDescription());
		boolean duplicate = false;
		
		//Se a descrição não existe contia para o próximo if
		if (taskDB != null) {
			//Se existe um outro ID com a mesma descrição
			//Se esta atualizando precisa saber se o ID é diferente do ID da TaskDB que esta sendo atualizada
			if ((task.getId() == null || task.getId() == 0) && task.getDescription().equals(taskDB.getDescription())) {
				duplicate = true;
			}
			
			if (task.getId() != null && task.getId() > 0 && !task.getId().equals(taskDB.getId())) {
				duplicate = true;
			}
		}
		
		if (duplicate) {
			throw new DuplicateTaskException("Já existe uma tarefa com esta descrição");
		}
	}

}

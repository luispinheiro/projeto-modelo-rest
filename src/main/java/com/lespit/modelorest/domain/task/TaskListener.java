package com.lespit.modelorest.domain.task;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PrePersist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.lespit.modelorest.domain.user.AppUser;
import com.lespit.modelorest.domain.user.AppUserRepository;

@Component
public class TaskListener {

	//Atributo para a chamada estática de insejeção do repositório na clssse listener
	private static AppUserRepository appUserRepository;
	
	//Prepersist chama a Task quando estiver pronta para ser salva no banco de dados Post e Put assim ela insere o usuário na tarefa
	@PrePersist
	public void onPrePersistHandler(Task task) {
		if (task.getAppUser() == null) {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			AppUser appUser = appUserRepository.findByUsername(username);
			
			if (appUser == null) {
				throw new EntityNotFoundException("O usuário " + username + " não foi encontrado");
			}
			
			task.setAppUser(appUser);
		}
	}
	
	//Método para a chamada estática de insejeção do repositório
	@Autowired
	public void init(AppUserRepository appUserRepository) {
		TaskListener.appUserRepository = appUserRepository;
	}
}

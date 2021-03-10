package com.lespit.modelorest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.lespit.modelorest.domain.task.Task;

@SpringBootApplication
public class ModeloRestApplication implements RepositoryRestConfigurer {

	//Interface Logger biblioteca org.slf4j.Logger
	//Classe biblioteca LoggerFactore org.slf4j.Logger
	//Os níveis do maior para o menor
	//menos logs para o mais logs
	//Fatal Error Warn Info Debug Trace
	//Padrão do Spring Boot é info
	private static final Logger logger =LoggerFactory.getLogger(ModeloRestApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(ModeloRestApplication.class, args);
		logger.info("Modelo Rest bluetask em ação!");
	}
	
	//Método para externalizar e mostrar os ids de Task na selação dos dados
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry corsRegistry) {
		config.exposeIdsFor(Task.class);

		corsRegistry.addMapping("/**")
		.allowedOrigins("*")
		.allowedMethods("GET","POST","PUT","DELETE");

		logger.info("Repository CORS setub.. OK");
	}
	
	//Métodos abaixo para mostrar um array de erros, para criar um método de validação quando necessário
	//Deixar mais amigável para o usuário
	//cuidado para importar a validação org.springframework.validation
	@Bean
	public Validator validator() {
		return new LocalValidatorFactoryBean();
	}
	
	//Configura eventos de validação antes de salvar os atualizar os dados	
	@Override
	public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener vrel) {
		Validator validator = validator();
		vrel.addValidator("beforeCreate", validator);
		vrel.addValidator("beforeSave", validator);

	}

}

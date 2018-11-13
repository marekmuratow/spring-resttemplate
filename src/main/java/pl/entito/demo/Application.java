package pl.entito.demo;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
	public static void main(String[] args) {

		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		CustomRestTemplateRunner restTemplateRunner = context.getBean(CustomRestTemplateRunner.class);
		
		restTemplateRunner.getForEntityBookFromJson();
		restTemplateRunner.getForEntityBookListFromJson();
		restTemplateRunner.getForObjectBookFromJson();
		restTemplateRunner.getForObjectBookListFromJson();

		restTemplateRunner.postForLocationBook();
		restTemplateRunner.postForEntityBook();

		restTemplateRunner.exchange();

		restTemplateRunner.options();

		context.close();
	}
}

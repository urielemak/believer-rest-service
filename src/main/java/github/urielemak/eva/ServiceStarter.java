package github.urielemak.eva;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
@SpringBootApplication
public class ServiceStarter {

	@Autowired
	private Environment environment;

	@Bean
	public OpenAPI openApi() {
	    return new OpenAPI()
		.info(new Info()
			.title(env("application.name"))
			.description(env("application.description"))
			.version(env("application.version"))
			.contact(new Contact()
			    .name(env("developer"))
			    .url(env("developer.site"))
			    .email(env("developer.email")))
			.termsOfService("TOC")
			.license(new License()
			    .name(env("application.license"))
			    .url(env("application.license.url"))));
	}

	/*@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}*/

	public String env(String property) {
		return this.environment.getProperty(property);
	}

	public static void main(String[] args) {
		SpringApplication.run(ServiceStarter.class, args);
	}

}

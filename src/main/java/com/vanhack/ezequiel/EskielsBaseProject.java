package com.vanhack.ezequiel;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication(scanBasePackages = { "com.vanhack.ezequiel" })
public class EskielsBaseProject extends WebMvcConfigurerAdapter {

	final static org.slf4j.Logger logger = LoggerFactory.getLogger(EskielsBaseProject.class);

	public static void main(String[] args) {
		logger.info("Application Started");
		SpringApplication.run(EskielsBaseProject.class, args);
	}

	// This function returns the data of the application in the swagger page
	@Bean
	public Docket docket()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage(getClass().getPackage().getName()))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(generateApiInfo());
	}

	// Defines which data will be displayed by swagger
	private ApiInfo generateApiInfo()
	{
		return new ApiInfo("Ezequiel's SkipTheDishes Challenge", "This is a project that I have created during the SkipTheDishes Interview Fair on Saturday (17/03).", "Version 1.0 - Starter",
				"urn:tos", "eskiel.sj@gmail.com", "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");
	}


}
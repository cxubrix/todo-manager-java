package lv.rcs.todo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxSwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				// should be ignored by default, bug?
				.ignoredParameterTypes(AuthenticationPrincipal.class)				
				// only our package
				.select().apis(RequestHandlerSelectors.basePackage("lv.rcs.todo")) 
				.paths(PathSelectors.any()).build();
	}

}

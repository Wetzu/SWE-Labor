package rummy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;

//@Autowired
//private ThymeleafProperties properties;
//
//@Value("${spring.thymeleaf.templates_root:}")
//private String templatesRoot;
//
//@Bean
//public ClassLoaderTemplateResolver secondaryTemplateResolver() {
//		ClassLoaderTemplateResolver secondaryTemplateResolver = new ClassLoaderTemplateResolver();
//		secondaryTemplateResolver.setPrefix("templates-2/");
//		secondaryTemplateResolver.setSuffix(".html");
//		secondaryTemplateResolver.setTemplateMode(TemplateMode.HTML);
//		secondaryTemplateResolver.setCharacterEncoding("UTF-8");
//		secondaryTemplateResolver.setOrder(1);
//		secondaryTemplateResolver.setCheckExistence(true);
//
//		return secondaryTemplateResolver;
//		}

@SpringBootApplication
@EnableAutoConfiguration(exclude = { ErrorMvcAutoConfiguration.class })
public class Main {
	public static void main(final String[] args) {
		SpringApplication.run(Main.class, args);
	}
}
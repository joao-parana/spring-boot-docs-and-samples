package sample.web.staticcontent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class SampleWebMvcApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SampleWebMvcApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SampleWebMvcApplication.class, args);
	}
}

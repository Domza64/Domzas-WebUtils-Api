package xyz.domza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DomzasWebUtilsApplication {

	public static void main(String[] args) {

		// TODO - Load application.properties from external file
		/*
			Check if external config file (application.properties) exits, if not, create it from template
			in some default location like users home /webspaceConfig...
			Then and exit application with message to populate required fields and rerun app

			If file exists add --spring.config.location=file:/path/to/config/ to args
		*/
		SpringApplication.run(DomzasWebUtilsApplication.class, args);
	}
}

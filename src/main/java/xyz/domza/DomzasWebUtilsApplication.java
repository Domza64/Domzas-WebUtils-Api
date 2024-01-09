package xyz.domza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import xyz.domza.utils.ApplicationPropertiesManager;

@SpringBootApplication
public class DomzasWebUtilsApplication {

	public static void main(String[] args) {
		boolean configExists = ApplicationPropertiesManager.checkConfig();

		if (configExists) {
			// Get config location
			String homeDirectory = System.getProperty("user.home");
			String configLocation = "--spring.config.location=file:" + homeDirectory + "/webspace-config/application.properties";

			// Merge the original args with the additional arguments
			String[] allArgs = new String[args.length + 1];
			System.arraycopy(args, 0, allArgs, 0, args.length);
			allArgs[args.length] = configLocation;

			// Run the Spring Boot application with allArgs
			SpringApplication.run(DomzasWebUtilsApplication.class, allArgs);
		}
	}
}

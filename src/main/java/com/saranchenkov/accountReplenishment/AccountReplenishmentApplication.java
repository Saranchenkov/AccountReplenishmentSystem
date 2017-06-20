package com.saranchenkov.accountReplenishment;

import com.saranchenkov.accountReplenishment.configuration.JpaConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages = {"com.saranchenkov.accountReplenishment"})
public class AccountReplenishmentApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(AccountReplenishmentApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AccountReplenishmentApplication.class);
	}
}

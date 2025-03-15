package com.oparin.busbus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@ComponentScan("com.oparin.busbus")
public class BusbusApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusbusApplication.class, args);
	}
	@GetMapping("/")
	public String showHomePage() {
		return "product"; // Предполагается, что ваш файл HTML называется "index.html" и находится в папке resources/templates
	}

}



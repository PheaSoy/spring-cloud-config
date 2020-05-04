package com.example.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sound.sampled.BooleanControl;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication

public class BookServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(BookServiceApplication.class, args);
	}


	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	class Book {
		String title;
		double totalPrice;
		double price;
		String author;
	}

	@RestController
	@RefreshScope
	class BooleanControl {

		@Autowired
		BookConfig bookConfig;

		@Value("${tax:0.0}")
		double tax;

		@GetMapping("/books")
		Book getBook(){
			double price=bookConfig.getPrice();
			double taxPrice= (price*tax)/100;
			double totalPrice=taxPrice+price;

			return new Book(bookConfig.getTitle(),totalPrice,price,"Jose Long");
		}
	}
	@Configuration
	@ConfigurationProperties(prefix = "book")
	//@RefreshScope
	@Data
	class BookConfig {
		String title;
		double price;
	}
}

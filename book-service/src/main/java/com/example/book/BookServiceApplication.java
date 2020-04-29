package com.example.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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
		@Value("${tax:0.0}")
		double tax;

		@GetMapping("/books")
		Book getBook(){
			double price=10.0;
			double taxPrice= (price*tax)/100;
			double totalPrice=taxPrice+price;

			return new Book("Cloud-Native Java",totalPrice,price,"Jose Long");
		}
	}
}

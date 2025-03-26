package com.anstay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AnstayApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnstayApplication.class, args);
		System.out.println("Đã chạy xong ....");
	}
}
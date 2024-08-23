package com.preflame.oneorder;

import java.net.InetAddress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OneorderApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneorderApplication.class, args);
		printAddress();
	}

	public static void printAddress() {
		try {
			InetAddress addr = InetAddress.getLocalHost();
			System.out.println("How to Connect");
			System.out.println("This Application has 3 displays");
			System.out.println("User: /order/***");
			System.out.println("Regi: /master/***");
			System.out.println("Kitchen: /kitchen/***");
			System.out.println();
			System.out.println("Connection Address: " + addr.getHostAddress() + ":8080");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}

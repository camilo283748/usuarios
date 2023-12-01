package com.bci.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class UsuariosApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;


	@Override
	public void run(String... args) throws Exception {

			//String passwordBCrypt = passwordEncoder.encode("contraseña123);
			//System.out.println("pass " + passwordBCrypt);
	}

	public static void main(String[] args) {
		SpringApplication.run(UsuariosApplication.class, args);

	}



}


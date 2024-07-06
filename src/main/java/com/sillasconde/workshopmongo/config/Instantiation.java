package com.sillasconde.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.sillasconde.workshopmongo.domain.User;
import com.sillasconde.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	
	@Autowired
	private UserRepository repo;
	
	@Override
	public void run(String... args) throws Exception {
		
		repo.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		User rios = new User(null, "Alex Rios", "alexrios@gmail.com");
		User carter = new User(null, "Carter Red", "carterRed@outlook.com");
		
		repo.saveAll(Arrays.asList(maria,alex,bob,rios,carter));
		
	}

}

package com.sillasconde.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sillasconde.workshopmongo.domain.User;
import com.sillasconde.workshopmongo.dto.UserDTO;
import com.sillasconde.workshopmongo.repository.UserRepository;
import com.sillasconde.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	
	public List<User> findAll() {

		return repo.findAll();
	}

	public User findById(String id) {

		Optional<User> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insert(User obj) {

		return repo.insert(obj);
	}
	
	public void deleteById(String id) {
		// Case not found, throw exception
		findById(id);
		repo.deleteById(id);
	}
	
	public User update(User newObj) {
		// Case not found, throw exception
		//findById(newObj.getId());
		
		User prevObj = repo.findById(newObj.getId()).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
		updateData(prevObj, newObj);

		return repo.save(prevObj);
	}
	
	
	public void updateData(User prevObj, User newObj) {
	
		prevObj.setName(newObj.getName());
		prevObj.setEmail(newObj.getEmail());

	}
	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(),objDto.getName(),objDto.getEmail());
	}
}

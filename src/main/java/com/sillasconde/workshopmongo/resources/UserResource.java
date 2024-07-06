package com.sillasconde.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sillasconde.workshopmongo.domain.User;
import com.sillasconde.workshopmongo.dto.UserDTO;
import com.sillasconde.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	//@RequestMapping(method=RequestMethod.GET);
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		
		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
		
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		
		User obj = service.findById(id);
		
		return ResponseEntity.ok().body(new UserDTO(obj));
		
	}
	
	@PostMapping()
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto) {
		// returns empty response
		// header with user created
		User obj = service.fromDTO(objDto);
		service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		// created: 201 code
		return ResponseEntity.created(uri).build();	
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable String id) {
		
		service.deleteById(id);
		
		return ResponseEntity.noContent().build();		
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable String id) {
		// returns empty response
		// header with user created
		User obj = service.fromDTO(objDto);
		obj.setId(id);
		
		service.update(obj);
		// created: 201 code
		return ResponseEntity.noContent().build();
	}

}

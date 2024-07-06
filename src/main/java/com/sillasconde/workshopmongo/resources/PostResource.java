package com.sillasconde.workshopmongo.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sillasconde.workshopmongo.domain.Post;
import com.sillasconde.workshopmongo.resources.util.URL;
import com.sillasconde.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService service;

	// @RequestMapping(method=RequestMethod.GET);
	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id) {

		Post obj = service.findById(id);

		return ResponseEntity.ok().body(obj);

	}

	
	@GetMapping(value = "/titlesearch")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue = "") String text) {

		text = URL.decodeText(text);
		
		List<Post> list = service.findByTitle(text); 

		return ResponseEntity.ok().body(list);

	}
	
	@GetMapping(value = "/fullsearch")
	public ResponseEntity<List<Post>> fullSearch(
			@RequestParam(value="text", defaultValue = "") String text,
			@RequestParam(value="text", defaultValue = "") String minDate,
			@RequestParam(value="text", defaultValue = "") String maxDate) {

		text = URL.decodeText(text);
		Date min = URL.decodeDate(minDate, new Date(0L));
		Date max = URL.decodeDate(maxDate, new Date());
		
		List<Post> list = service.fullSearch(text,min,max); 

		return ResponseEntity.ok().body(list);

	}

}

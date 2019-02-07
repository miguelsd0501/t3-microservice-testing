package com.t3.microservice.books.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.http.ResponseEntity;

import com.t3.microservice.books.models.entities.Book;
import com.t3.microservice.books.models.services.IBookService;

@RestController
@RequestMapping("/api")
public class BookController {
	
	@Autowired
	IBookService bookService;
	
	@GetMapping("/books")
    public List<Book> index() {
        return bookService.findAll();
    }
	
	@GetMapping("/books/{id}")
	public Book show(@PathVariable Long id) {
		return bookService.findById(id);
	}
	
	@PostMapping("/books")
	public ResponseEntity<?> create(@Valid @RequestBody Book book, BindingResult result) {
		Map<String, Object> response = new HashMap<>();
        Book bookNew = null;

        // validando datos de entrada
        if(result.hasErrors()) {

            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        // realizando el procesamiento de los datps
        try {
        	bookNew = bookService.save(book);
        } catch (DataAccessException e) {
            response.put("message", "Failed to insert information in database");
            response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // devolviendo una respuesta
        response.put("menssage", "Book created successfully");
        response.put("book", bookNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}

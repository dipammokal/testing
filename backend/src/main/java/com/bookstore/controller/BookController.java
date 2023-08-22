package com.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.dto.AddBookDTO;
import com.bookstore.dto.OnlyBookDTO;
import com.bookstore.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@PostMapping("/add")
	public ResponseEntity<?> addBook(@RequestBody AddBookDTO bookDTO) {
		return ResponseEntity.status(HttpStatus.OK).body(bookService.addBook(bookDTO));
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateBook(@RequestBody OnlyBookDTO detachedBook) {
		return ResponseEntity.status(HttpStatus.OK).body(bookService.updateBook(detachedBook));
	}
	
	@GetMapping("/getall")
	public List<OnlyBookDTO> getAllBook() {
		return bookService.getAllBooks();
	}
	
	@GetMapping("/get/{id}")
	public AddBookDTO getBook(@PathVariable Long id) {
		return bookService.getBook(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.FOUND).body(bookService.deleteBook(id));
	}
	
}

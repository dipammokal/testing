package com.bookstore.service;



import java.util.List;

import com.bookstore.dto.AddBookDTO;

import com.bookstore.dto.ApiResponse;
import com.bookstore.dto.OnlyBookDTO;

public interface BookService {

	ApiResponse addBook(AddBookDTO bookDTO);

	ApiResponse updateBook(OnlyBookDTO detachedBook);

	List<OnlyBookDTO> getAllBooks();

	AddBookDTO getBook(Long id);


	ApiResponse deleteBook(Long id);


}

package com.bookstore.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.bookstore.custom_exceptions.ResourceNotFoundException;
import com.bookstore.dto.AddBookDTO;
import com.bookstore.dto.ApiResponse;
import com.bookstore.dto.BookDTO;
import com.bookstore.dto.OnlyBookDTO;

import com.bookstore.entities.Book;
import com.bookstore.entities.Review;
import com.bookstore.repository.AuthorRepository;
import com.bookstore.repository.BookRepository;

@Service
@Transactional
public class BookServiceImpl implements BookService{
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public ApiResponse addBook(AddBookDTO bookDTO) {
		Book book = mapper.map(bookDTO, Book.class);
		bookRepository.save(book);
		ApiResponse apiResponse = new ApiResponse("Book Added Successfully!!");
		return apiResponse;
	}

	@Override

	public ApiResponse updateBook(OnlyBookDTO detachedBook) {
		Book book = bookRepository.findById(detachedBook.getId()).orElseThrow(() -> new ResourceNotFoundException("Something went wrong!"));
		book.setIsbn(detachedBook.getIsbn());
		book.setTitle(detachedBook.getTitle());
		book.setDescription(detachedBook.getDescription());
		book.setCategory(detachedBook.getCategory());
		book.setPrice(detachedBook.getPrice());
		book.setDiscountedPrice(detachedBook.getDiscountedPrice());
		book.setAuthor(authorRepository.findById(detachedBook.getAuthorId()).orElseThrow(() -> new ResourceNotFoundException("Author id invalid!")));
		book.setQuantity(detachedBook.getQuantity());
		book.setImagePath(detachedBook.getImagePath());
		bookRepository.save(book);
		return new ApiResponse("Book updated successfully!");
	}

	@Override
	public List<OnlyBookDTO> getAllBooks() {
		List<OnlyBookDTO> bookList = new ArrayList<>();
//		bookRepository.findAll().forEach(i -> bookList.add(new OnlyBookDTO(i.getId(), i.getIsbn(), i.getTitle(), i.getDescription(), i.getCategory(), i.getPrice(), i.getDiscountedPrice(), i.getAuthor(), i.getQuantity(), i.getImagePath())));
		bookRepository.findAll().forEach(i -> bookList.add(new OnlyBookDTO(i.getId(), i.getIsbn(), i.getTitle(), i.getDescription(), i.getCategory(), i.getPrice(), i.getDiscountedPrice(), i.getAuthor().getId(), i.getQuantity(), i.getImagePath())));
		return bookList;
	}

	@Override
	public AddBookDTO getBook(Long id) {
		Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid Book ID!!"));
		BookDTO bookDTO = new BookDTO(book.getId(), book.getIsbn(), book.getTitle(), book.getDescription(), book.getCategory(), book.getPrice(), book.getDiscountedPrice(), book.getAuthor().getId(), book.getQuantity(), book.getImagePath(), new ArrayList<Review>());
		
		return null;
	}

	@Override
	public ApiResponse deleteBook(Long id) {
		Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid Book ID!!"));
		bookRepository.delete(book);
		return new ApiResponse("Book Deleted Successfully!!");
	}





//	@Override
//	public BookDTO getBookDetails(Long bookId) {
//		Book book = bookRepository.findById(bookId).
//		orElseThrow(() -> new ResourceNotFoundException("Invalid Book ID !!!"));
//		return mapper.map(book, BookDTO.class);
//	}

	
	
	
	
}

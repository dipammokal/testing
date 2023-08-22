package com.bookstore.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.bookstore.custom_exceptions.ResourceNotFoundException;
import com.bookstore.dto.ApiResponse;
import com.bookstore.dto.GetReviewDTO;
import com.bookstore.dto.ReviewDTO;
import com.bookstore.dto.UserReviewDTO;
import com.bookstore.entities.Book;
import com.bookstore.entities.Review;
import com.bookstore.entities.User;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.ReviewRepository;
import com.bookstore.repository.UserRepository;


@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {
	

	private ModelMapper mapper;
	private UserRepository userRepository;
	private BookRepository bookRepository;
	private ReviewRepository reviewRepository;
	


	@Override
	public ApiResponse createReview(Long userId,Long bookId,ReviewDTO reviewDTO)  
	{
		
		
		reviewDTO.setCreatedAt(LocalDateTime.now());
		Review review = mapper.map(reviewDTO,Review.class);
		User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("invalid user id!"));
		Book book=bookRepository.findById(bookId).orElseThrow(()-> new ResourceNotFoundException("invalid book id!"));
		review.setBook(book);
		review.setUser(user);
		reviewRepository.save(review);
		ApiResponse apiResponse=new ApiResponse("Review saved");
		return apiResponse;
	}

	@Override
	public List<GetReviewDTO> getAllReview(Long bookId)
	{
		
		Book book=bookRepository.findById(bookId).orElseThrow(()-> new ResourceNotFoundException("invalid book id!"));
		List<Review>review=book.getReviews();
		
		List<GetReviewDTO> getReviewDTO=new ArrayList<>();
		
		GetReviewDTO getReview;
		
		
	
		
		review.forEach(i -> getReviewDTO.add(new GetReviewDTO(i.getRating(),i.getReview(),new UserReviewDTO(i.getUser().getFirstName(), i.getUser().getLastName()))));
		return getReviewDTO;
		

		
	}
	
	
}

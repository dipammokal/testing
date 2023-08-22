package com.bookstore.dto;

import java.util.ArrayList;
import java.util.List;

import com.bookstore.entities.BookCategory;
import com.bookstore.entities.Review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

	private Long id;
	private long isbn;
	private String title;
	private String description;
	private BookCategory category;
	private double price;
	private double discountedPrice;
	private long authorId;
	private int quantity;
	private String imagePath;
	private List<Review> reviews = new ArrayList<>();
	
}

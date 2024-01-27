package com.demo.LibraryManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.LibraryManagement.Exception.ResourceNotFoundException;
import com.demo.LibraryManagement.entity.Book;
import com.demo.LibraryManagement.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookRepository bookRepository;

	@Override
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public Book addBook(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public Optional<Book> getBookById(Long bookID) {
		Optional<Book> book = bookRepository.findById(bookID);
		if(book.isPresent()) {
			return book;
		} else {
			throw new ResourceNotFoundException("Book not exist with id:" + bookID );
		}
	}

	@Override
	public String deleteById(Long id) {
		 Optional<Book> book = bookRepository.findById(id);
		 if(book.isPresent()) {
			 bookRepository.deleteById(id);
			 return "Book deleted Successfully!";
		 } else {
			 return "Book with provided ID not found";
		 }
	}

	@Override
	public Book updateBook(Book book) {
		return bookRepository.save(book);
	}

}

package com.demo.LibraryManagement.service;

import java.util.List;
import java.util.Optional;

import com.demo.LibraryManagement.entity.Book;



public interface BookService {

	List<Book> getAllBooks();

	Book addBook(Book book);

	Optional<Book> getBookById(Long bookID);

	String deleteById(Long id);

	Book updateBook(Book book);

}

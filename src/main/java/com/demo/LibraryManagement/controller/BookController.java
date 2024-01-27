package com.demo.LibraryManagement.controller;

import java.util.List;
import java.util.Optional;

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

import com.demo.LibraryManagement.Exception.ResourceNotFoundException;
import com.demo.LibraryManagement.entity.Book;
import com.demo.LibraryManagement.service.BookService;

@RestController
@RequestMapping("api/books")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping
	public ResponseEntity<List<Book>> getAllBooks(){
		List<Book> booklist = bookService.getAllBooks();
		return new ResponseEntity<>(booklist, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable("id") Long id ){
	
		Optional<Book> bookVal = bookService.getBookById(id);
		return new ResponseEntity<>(bookVal.get(),HttpStatus.OK);
		
	}
	
	@PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return new ResponseEntity<>(bookService.addBook(book),HttpStatus.CREATED);
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
		Optional<Book> updateBook;

			updateBook = bookService.getBookById(id);
			if(updateBook.isPresent()) {
				Book bookToUpdated = updateBook.get();
				bookToUpdated.setTitle(book.getTitle());
				bookToUpdated.setAuthor(book.getAuthor());
				bookToUpdated.setPublicationYear(book.getPublicationYear());
				bookToUpdated.setIsbn(book.getIsbn());
				return new ResponseEntity<>(bookService.updateBook(bookToUpdated),HttpStatus.OK);
			}else {
				throw new ResourceNotFoundException("Book not exist with id:" + id);
		    }
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        String msg = bookService.deleteById(id);
        return msg;
    }

}

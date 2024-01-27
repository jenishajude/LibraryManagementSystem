package com.demo.LibraryManagement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.LibraryManagement.Exception.ResourceNotFoundException;
import com.demo.LibraryManagement.entity.Book;
import com.demo.LibraryManagement.repository.BookRepository;
import com.demo.LibraryManagement.service.BookServiceImpl;

@SpringBootTest
public class BookServiceImplTest {
	
	@Mock
	BookRepository bookRepository;
	
	@InjectMocks
	BookServiceImpl bookServiceImpl;
	
	@Test
	public void getAllBooksTest() {
		Mockito.when(bookServiceImpl.getAllBooks()).thenReturn(createBookListStub());
		List<Book> bookList = bookServiceImpl.getAllBooks();
		assertThat(bookList).isNotNull();
        assertThat(bookList.size()).isEqualTo(2);	
	}
	
	@Test
	public void addBookTest() {
		Book book = createBookStubData();
		Mockito.when(bookRepository.save(Mockito.any(Book.class))).thenReturn(book);
		Book bookObj = bookServiceImpl.addBook(book);
		assertEquals(bookObj.getId(), book.getId());
	}
	
	@Test
	public void getBookByIdTest() {
		when(bookRepository.findById(1L)).thenReturn(createBookStub());
		Optional<Book> book = bookServiceImpl.getBookById(1L);
		assertEquals(book.get().getId(), 1L);
		assertEquals(book.get().getTitle(), "War and Peace");
		assertEquals(book.get().getAuthor(), "Tolstoy");
	}
	
	@Test
	public void getBookByIdExceptionTest() {

		when(bookRepository.findById(1L)).thenReturn(Optional.empty());
		ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> bookServiceImpl.getBookById(1L));
	    assertEquals(exception.getMessage(), "Book not exist with id:1");
	}

	@Test
	public void deleteByIdTest() {
		when(bookRepository.findById(1L)).thenReturn(createBookStub());
		Mockito.doNothing().when(bookRepository).deleteById(1L);
		bookServiceImpl.deleteById(1L);
		Mockito.verify(bookRepository, Mockito.times(1)).deleteById(1L);
	}
	
	@Test
	public void deleteByIdNotFoundTest() {
		when(bookRepository.findById(1L)).thenReturn(Optional.empty());
		Mockito.doNothing().when(bookRepository).deleteById(1L);
		bookServiceImpl.deleteById(1L);
		Mockito.verify(bookRepository, Mockito.times(0)).deleteById(1L);
	}

	@Test
	public void updateBookTest() {
		Book book = createBookStubData();
		Mockito.when(bookRepository.save(Mockito.any(Book.class))).thenReturn(book);
		Book bookObj = bookServiceImpl.updateBook(book);
		assertEquals(bookObj.getId(), book.getId());	
	}
	
	private List<Book> createBookListStub() {
		List<Book> bookList = new ArrayList<>();
		bookList.add(new Book(1L,"War and Peace","Tolstoy","2019","878-1478-910-899-1"));
		bookList.add(new Book(2L,"The India Story","Bimal Jalal","2009","971-1478-0-78-2"));
		return bookList;
	}
	
	private Book createBookStubData(){
		Book bookStub = new Book(1L,"War and Peace","Tolstoy","2019","878-1478-910-899-1");
		return bookStub;	
	}
	
	private Optional<Book> createBookStub(){
		Book bookStub = new Book(1L,"War and Peace","Tolstoy","2019","878-1478-910-899-1");
		return Optional.of(bookStub);
	}

}

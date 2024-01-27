package com.demo.LibraryManagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.LibraryManagement.Exception.ResourceNotFoundException;
import com.demo.LibraryManagement.entity.Book;
import com.demo.LibraryManagement.entity.BorrowingRecords;
import com.demo.LibraryManagement.entity.Patron;
import com.demo.LibraryManagement.repository.BookRepository;
import com.demo.LibraryManagement.repository.BorrowingRecordsRepository;
import com.demo.LibraryManagement.repository.PatronRepository;
import com.demo.LibraryManagement.service.BorrowingRecordsServiceImpl;



@SpringBootTest
public class BorrowingRecordsServiceImplTest {
	
	@Mock
	BorrowingRecordsRepository borrowingRecordsRepository;
	
	@Mock
	PatronRepository patronRepository;
	
	@Mock
	BookRepository bookRepository;
	
	@InjectMocks
	BorrowingRecordsServiceImpl borrowingRecordsServiceImpl;
	
	@Test
	public void borrowBookTest() {
		when(bookRepository.findById(1L)).thenReturn(createBookStub());
		when(patronRepository.findById(1L)).thenReturn(createPatronStub());
		BorrowingRecords record = createBorrowingRecordsStub();
		Mockito.when(borrowingRecordsRepository.save(Mockito.any(BorrowingRecords.class))).thenReturn(record);
		BorrowingRecords recordsObj = borrowingRecordsServiceImpl.borrowBook(1L,1L);
		assertEquals(recordsObj.getBorrowedBy().getName(), "John");
		assertEquals(recordsObj.getBorrowedBook().getTitle(), "War and Peace");
	}
	
	@Test
	public void updateReturnDateTest(){
		when(bookRepository.findById(1L)).thenReturn(createBookStub());
		when(patronRepository.findById(1L)).thenReturn(createPatronStub());
		Book book =  createBookStubData();
		Patron patron = createPatronStubData();
		when(borrowingRecordsRepository.findByBorrowedByAndBorrowedBook(patron, book))
	    .thenReturn(new BorrowingRecords(1L, true, patron, book, "2024-01-25", ""));
		BorrowingRecords recordsObj = borrowingRecordsServiceImpl.updateReturnDate(1L,1L);
		Mockito.when(borrowingRecordsRepository.save(Mockito.any(BorrowingRecords.class))).thenReturn(recordsObj);
		assertEquals(recordsObj.getBorrowedBy().getName(), "John");
		assertEquals(recordsObj.getBorrowedBook().getTitle(), "War and Peace");
	}
	
	@Test
	public void borrowBookExceptionTest() {
		when(patronRepository.findById(1L)).thenReturn(Optional.empty());
		when(bookRepository.findById(1L)).thenReturn(Optional.empty());
	    assertThrows(ResourceNotFoundException.class, () -> borrowingRecordsServiceImpl.borrowBook(1L,1L));
	}
	
	@Test
	public void updateReturnDateExceptionTest() {
		when(patronRepository.findById(1L)).thenReturn(Optional.empty());
		when(bookRepository.findById(1L)).thenReturn(Optional.empty());
	    assertThrows(ResourceNotFoundException.class, () -> borrowingRecordsServiceImpl.updateReturnDate(1L,1L));
	}

	private BorrowingRecords createBorrowingRecordsStub() {
		Book book = createBookStubData();
		Patron patron = createPatronStubData();
		BorrowingRecords records = new BorrowingRecords(1L, true,patron, book, "2024-01-25", "");
		return records;
	}

	private Optional<Book> createBookStub(){
		Book bookStub = new Book(1L,"War and Peace","Tolstoy","2019","878-1478-910-899-1");
		return Optional.of(bookStub);	
	}
	private Book createBookStubData(){
		Book bookStub = new Book(1L,"War and Peace","Tolstoy","2019","878-1478-910-899-1");
		return bookStub;	
	}
	
	private Patron createPatronStubData(){
		Patron patronStub = new Patron(1L,"John","0987656789");
		return patronStub;	
	} 
	
	private Optional<Patron> createPatronStub(){
		Patron patronStub = new Patron(1L,"John","0987656789");
		return Optional.of(patronStub);
	} 
}

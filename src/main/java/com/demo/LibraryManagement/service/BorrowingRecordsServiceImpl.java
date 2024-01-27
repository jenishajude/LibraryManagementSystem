package com.demo.LibraryManagement.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.LibraryManagement.Exception.ResourceNotFoundException;
import com.demo.LibraryManagement.entity.Book;
import com.demo.LibraryManagement.entity.BorrowingRecords;
import com.demo.LibraryManagement.entity.Patron;
import com.demo.LibraryManagement.repository.BookRepository;
import com.demo.LibraryManagement.repository.BorrowingRecordsRepository;
import com.demo.LibraryManagement.repository.PatronRepository;

@Service
public class BorrowingRecordsServiceImpl implements BorrowingRecordsService{


	@Autowired
	private BorrowingRecordsRepository borrowingRecordsRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private PatronRepository patronRepository;
	
	@Override
	public BorrowingRecords borrowBook(Long bookId, Long patronId) {
		 Book book = bookRepository.findById(bookId).orElse(null);
	     Patron patron = patronRepository.findById(patronId).orElse(null);
	     BorrowingRecords borrowingRecords = new BorrowingRecords();
	        if (book != null && patron != null) {
        	
	        	borrowingRecords.setBorrowedBook(book);
	        	borrowingRecords.setBorrowedBy(patron);
	        	borrowingRecords.setBorrowed(true);
	        	
	        	Date currentDate = new Date();
	        	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        	String currentDateTime = dateFormat.format(currentDate);
	        	
	        	borrowingRecords.setBorrowDate(currentDateTime);
	        	borrowingRecords.setReturnDate("");
	        	
	            return borrowingRecordsRepository.save(borrowingRecords);
	        } else {
	        	throw new ResourceNotFoundException("Either Patron or Book not found for the ID value provided");
	        }
			
	}

	@Override
	public BorrowingRecords updateReturnDate(Long bookId, Long patronId) {
		Book book = bookRepository.findById(bookId).orElse(null);
	    Patron patron = patronRepository.findById(patronId).orElse(null);
	    
	    BorrowingRecords borrowingRecords = borrowingRecordsRepository.findByBorrowedByAndBorrowedBook(patron, book);
	    if (book != null && patron != null) {
	    	Date currentDate = new Date();
	    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    	String currentDateTime = dateFormat.format(currentDate);
 
	    	borrowingRecords.setReturnDate(currentDateTime);
	    	borrowingRecords.setBorrowed(false);
	    	borrowingRecordsRepository.save(borrowingRecords);
	    	return borrowingRecords;
	    }else {
        	throw new ResourceNotFoundException("Either Patron or Book not found for the ID value provided");
	    }
	}
		
}

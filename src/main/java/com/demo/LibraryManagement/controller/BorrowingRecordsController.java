package com.demo.LibraryManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.LibraryManagement.Exception.ResourceNotFoundException;
import com.demo.LibraryManagement.entity.BorrowingRecords;
import com.demo.LibraryManagement.service.BorrowingRecordsService;

@RestController
@RequestMapping("api/")
public class BorrowingRecordsController {
	
	@Autowired
	private BorrowingRecordsService borrowingRecordsService;
	
	 @PostMapping("borrow/{bookId}/patron/{patronId}")
	 public ResponseEntity<BorrowingRecords> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) {
		 BorrowingRecords borrowedBook = borrowingRecordsService.borrowBook(bookId, patronId);
	     return new ResponseEntity<>(borrowedBook,HttpStatus.OK);
	    }
	 
	 @PutMapping("return/{bookId}/patron/{patronId}")
	 public ResponseEntity<BorrowingRecords> returnBook(@PathVariable Long bookId, @PathVariable Long patronId) {
		 BorrowingRecords returnedBook = borrowingRecordsService.updateReturnDate(bookId, patronId);
		 return new ResponseEntity<>(returnedBook,HttpStatus.OK);
	  }
	 	

}

package com.demo.LibraryManagement.service;


import com.demo.LibraryManagement.entity.BorrowingRecords;

public interface BorrowingRecordsService {

	

	BorrowingRecords borrowBook(Long bookId, Long patronId);

	BorrowingRecords updateReturnDate(Long bookId, Long patronId);

}

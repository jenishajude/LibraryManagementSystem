package com.demo.LibraryManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.LibraryManagement.entity.Book;
import com.demo.LibraryManagement.entity.BorrowingRecords;
import com.demo.LibraryManagement.entity.Patron;

public interface BorrowingRecordsRepository extends JpaRepository<BorrowingRecords, Long>{

	//BorrowingRecords findByBorrowedBy_IdAndBorrowedBook_Id( Long patronId,Long bookId);

	BorrowingRecords findByBorrowedByAndBorrowedBook(Patron patron, Book book);

}

package com.demo.LibraryManagement.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
public class BorrowingRecords {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private boolean borrowed;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Patron borrowedBy;
    
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book borrowedBook;
    private String borrowDate;
    private String returnDate;
    
    
	
	public BorrowingRecords() {
		super();
	}
	public BorrowingRecords(Long id, boolean borrowed, Patron borrowedBy, Book borrowedBook, String borrowDate,
			String returnDate) {
		super();
		Id = id;
		this.borrowed = borrowed;
		this.borrowedBy = borrowedBy;
		this.borrowedBook = borrowedBook;
		this.borrowDate = borrowDate;
		this.returnDate = returnDate;
	}
    
	public boolean isBorrowed() {
		return borrowed;
	}
	public void setBorrowed(boolean borrowed) {
		this.borrowed = borrowed;
	}
	public Patron getBorrowedBy() {
		return borrowedBy;
	}
	public void setBorrowedBy(Patron borrowedBy) {
		this.borrowedBy = borrowedBy;
	}
	public Book getBorrowedBook() {
		return borrowedBook;
	}
	public void setBorrowedBook(Book borrowedBook) {
		this.borrowedBook = borrowedBook;
	}
	public String getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(String borrowDate) {
		this.borrowDate = borrowDate;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}


}

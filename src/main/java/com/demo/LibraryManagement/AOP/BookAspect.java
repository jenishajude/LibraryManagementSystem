package com.demo.LibraryManagement.AOP;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.demo.LibraryManagement.entity.Book;

@Aspect
@Component
public class BookAspect {
	
	@Before(value = "execution(* com.demo.LibraryManagement.controller.BookController.*(..))")
	public void beforeAspect(JoinPoint joinPoint) {
		System.out.println("Request to Book Controller " + joinPoint.getSignature() +" started at " + new Date());
	}
	
	@After(value = "execution(* com.demo.LibraryManagement.controller.BookController.*(..))")
	public void afterAspect(JoinPoint joinPoint) {
		System.out.println("Request to Book Controller " + joinPoint.getSignature() +" ended at " + new Date());
	}
	
	@Before(value = "execution(* com.demo.LibraryManagement.service.BookService.*(..))")
	public void beforeAspectForService(JoinPoint joinPoint) {
		System.out.println("Request to Book Service " + joinPoint.getSignature() +" started at " + new Date());
	}
	
	@After(value = "execution(* com.demo.LibraryManagement.service.BookService.*(..))")
	public void afterAspectForService(JoinPoint joinPoint) {
		System.out.println("Request to Book Service " + joinPoint.getSignature() +" ended at " + new Date());
	}
	
	@AfterReturning(value = "execution(* com.demo.LibraryManagement.service.BookService.getAllBooks())", returning = "book")
	public void afterReturningGetAllBooksAspect(JoinPoint joinPoint, List<Book> book) {
		System.out.println("Business logic to getAllBooks ran successfully at "+ new Date());
	}
	
	@AfterReturning(value = "execution(* com.demo.LibraryManagement.service.BookService.getBookById(..))", returning = "book")
	public void afterReturningGetBookByIdAspect(JoinPoint joinPoint, Optional<Book> book) {
		System.out.println("Business logic to getBookById ran successfully at "+ new Date() + " and the book title is " + book.get().getTitle());
	}
	
	@AfterReturning(value = "execution(* com.demo.LibraryManagement.service.BookService.addBook(..))", returning = "book")
	public void afterReturningAddBookAspect(JoinPoint joinPoint, Book book) {
		System.out.println("Business logic to addBook ran successfully and the book is saved with id: " + book.getId());
	}
	
	@AfterReturning(value = "execution(* com.demo.LibraryManagement.service.BookService.updateBook(..))", returning = "book")
	public void afterReturningUpdateBookAspect(JoinPoint joinPoint, Book book) {
		System.out.println("Business logic to updateBook ran successfully for the book id: " + book.getId());
	}
	
	@AfterReturning(value = "execution(* com.demo.LibraryManagement.service.BookService.deleteById(..))")
	public void afterReturningDeleteByIdAspect(JoinPoint joinPoint) {
		System.out.println("Business logic to deleteBook ran successfully");
	}
	
	@AfterThrowing(value = "execution(* com.demo.LibraryManagement.service.BookService.getBookById(..))", throwing = "exception")
	public void afterThrowingGetBookByIdAspect(JoinPoint joinPoint, Exception exception) {
		System.out.println("Business logic to getBookById threw an exception: "+ exception.getMessage());
	}
	
}

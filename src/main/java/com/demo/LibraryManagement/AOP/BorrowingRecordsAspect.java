package com.demo.LibraryManagement.AOP;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.demo.LibraryManagement.entity.BorrowingRecords;

@Aspect
@Component
public class BorrowingRecordsAspect {
	
	@Before(value = "execution(* com.demo.LibraryManagement.controller.BorrowingRecordsController.*(..))")
	public void beforeAspect(JoinPoint joinPoint) {
		System.out.println("Request to BorrowingRecords Controller " + joinPoint.getSignature() +" started at " + new Date());
	}
	
	@After(value = "execution(* com.demo.LibraryManagement.controller.BorrowingRecordsController.*(..))")
	public void afterAspect(JoinPoint joinPoint) {
		System.out.println("Request to BorrowingRecords Controller " + joinPoint.getSignature() +" ended at " + new Date());
	}
	
	@Before(value = "execution(* com.demo.LibraryManagement.service.BorrowingRecordsService.*(..))")
	public void beforeAspectForService(JoinPoint joinPoint) {
		System.out.println("Request to BorrowingRecords Service " + joinPoint.getSignature() +" started at " + new Date());
	}
	
	@After(value = "execution(* com.demo.LibraryManagement.service.BorrowingRecordsService.*(..))")
	public void afterAspectForService(JoinPoint joinPoint) {
		System.out.println("Request to BorrowingRecords Service " + joinPoint.getSignature() +" ended at " + new Date());
	}

	@AfterReturning(value = "execution(* com.demo.LibraryManagement.service.BorrowingRecordsService.borrowBook(..))", returning = "borrowingRecords")
	public void afterReturningBorrowBookAspect(JoinPoint joinPoint, BorrowingRecords borrowingRecords) {
		System.out.println("Business logic to borrowBook ran successfully! "+  borrowingRecords.getBorrowedBook().getTitle() + " is borrowed by "+ borrowingRecords.getBorrowedBy().getName());
	}
	
	@AfterReturning(value = "execution(* com.demo.LibraryManagement.service.BorrowingRecordsService.updateReturnDate(..))", returning = "borrowingRecords")
	public void afterReturningUpdateReturnDateAspect(JoinPoint BorrowingRecords, BorrowingRecords borrowingRecords) {
		System.out.println("Business logic to UpdateReturnDate ran successfully and updated for book id: " + borrowingRecords.getBorrowedBook().getId());
	}
	
	@AfterThrowing(value = "execution(* com.demo.LibraryManagement.service.BorrowingRecordsService.borrowBook(..))", throwing = "exception")
	public void afterThrowingBorrowBookAspect(JoinPoint joinPoint, Exception exception) {
		System.out.println("Business logic to borrowBook threw an exception: "+ exception.getMessage());
	}
	
	@AfterThrowing(value = "execution(* com.demo.LibraryManagement.service.BorrowingRecordsService.updateReturnDate(..))", throwing = "exception")
	public void afterThrowingUpdateReturnDateAspect(JoinPoint BorrowingRecords, Exception exception) {
		System.out.println("Business logic to UpdateReturnDate threw an exception: "+ exception.getMessage());
	}

}

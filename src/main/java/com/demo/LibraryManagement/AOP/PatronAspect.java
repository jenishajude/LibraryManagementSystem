package com.demo.LibraryManagement.AOP;

import java.util.Date;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.demo.LibraryManagement.entity.Patron;

@Aspect
@Component
public class PatronAspect {
	
	@Before(value = "execution(* com.demo.LibraryManagement.controller.PatronController.*(..))")
	public void beforeAspect(JoinPoint joinPoint) {
		System.out.println("Request to Patron Controller " + joinPoint.getSignature() +" started at " + new Date());
	}
	
	@After(value = "execution(* com.demo.LibraryManagement.controller.PatronController.*(..))")
	public void afterAspect(JoinPoint joinPoint) {
		System.out.println("Request to Patron Controller " + joinPoint.getSignature() +" ended at " + new Date());
	}
	
	@Before(value = "execution(* com.demo.LibraryManagement.service.PatronService.*(..))")
	public void beforeAspectForService(JoinPoint joinPoint) {
		System.out.println("Request to Patron Service " + joinPoint.getSignature() +" started at " + new Date());
	}
	
	@After(value = "execution(* com.demo.LibraryManagement.service.PatronService.*(..))")
	public void afterAspectForService(JoinPoint joinPoint) {
		System.out.println("Request to Patron Service " + joinPoint.getSignature() +" ended at " + new Date());
	}
	
	@AfterReturning(value = "execution(* com.demo.LibraryManagement.service.PatronService.getAllPatronss())", returning = "patron")
	public void afterReturningGetAllPatronsAspect(JoinPoint joinPoint, List<Patron> patron) {
		System.out.println("Business logic to getAllPatrons ran successfully at "+ new Date());
	}
	
	@AfterReturning(value = "execution(* com.demo.LibraryManagement.service.PatronService.getPatronById(..))", returning = "patron")
	public void afterReturningGetPatronByIdAspect(JoinPoint joinPoint, Patron patron) {
		System.out.println("Business logic to getPatronById ran successfully at "+ new Date() + " and the patron title is " + patron.getName());
	}
	
	@AfterReturning(value = "execution(* com.demo.LibraryManagement.service.PatronService.addPatron(..))", returning = "patron")
	public void afterReturningAddPatronAspect(JoinPoint joinPoint, Patron patron) {
		System.out.println("Business logic to addPatron ran successfully and the patron is saved with id: " + patron.getId());
	}
	
	@AfterReturning(value = "execution(* com.demo.LibraryManagement.service.PatronService.updatePatron(..))", returning = "patron")
	public void afterReturningUpdatePatronAspect(JoinPoint joinPoint, Patron patron) {
		System.out.println("Business logic to updatePatron ran successfully for the patron id: " + patron.getId());
	}
	
	@AfterReturning(value = "execution(* com.demo.LibraryManagement.service.PatronService.deleteById(..))")
	public void afterReturningDeleteByIdAspect(JoinPoint joinPoint) {
		System.out.println("Business logic to deletePatron ran successfully");
	}
	
	@AfterThrowing(value = "execution(* com.demo.LibraryManagement.service.PatronService.getPatronById(..))", throwing = "exception")
	public void afterThrowingGetPatronByIdAspect(JoinPoint joinPoint, Exception exception) {
		System.out.println("Business logic to getPatronById threw an exception: "+ exception.getMessage());
	}
	
}

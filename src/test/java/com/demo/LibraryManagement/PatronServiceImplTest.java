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
import com.demo.LibraryManagement.entity.Patron;
import com.demo.LibraryManagement.repository.PatronRepository;
import com.demo.LibraryManagement.service.PatronServiceImpl;

@SpringBootTest
public class PatronServiceImplTest {
	
	@Mock
	PatronRepository patronRepository;
	
	@InjectMocks
	PatronServiceImpl patronServiceImpl;
	
	@Test
	public void getAllPatronsTest() {
		Mockito.when(patronServiceImpl.getAllPatronss()).thenReturn(createPatronListStub());
		List<Patron> patronList = patronServiceImpl.getAllPatronss();
		assertThat(patronList).isNotNull();
        assertThat(patronList.size()).isEqualTo(2);	
	}
	
	@Test
	public void addPatronTest() {
		Patron patron = createPatronStubData();
		Mockito.when(patronRepository.save(Mockito.any(Patron.class))).thenReturn(patron);
		Patron patronObj = patronServiceImpl.addPatron(patron);
		assertEquals(patronObj.getId(), patron.getId());
	}
	
	@Test
	public void getPatronByIdTest() {
		when(patronRepository.findById(1L)).thenReturn(createPatronStub());
		Optional<Patron> patron = patronServiceImpl.getPatronById(1L);
		assertEquals(patron.get().getId(), 1L);
		assertEquals(patron.get().getName(), "John");
		assertEquals(patron.get().getContactInfo(), "0987656789");
	}
	
	@Test
	public void getPatronByIdExceptionTest() {
		when(patronRepository.findById(1L)).thenReturn(Optional.empty());
		ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> patronServiceImpl.getPatronById(1L));
	    assertEquals(exception.getMessage(), "Patron not exist with id:1");
	}
	
	@Test
	public void deleteByIdTest() {
		Mockito.doNothing().when(patronRepository).deleteById(1L);
		patronServiceImpl.deleteById(1L);
		Mockito.verify(patronRepository, Mockito.times(1)).deleteById(1L);
	}

	@Test
	public void updatePatronTest() {
		Patron patron = createPatronStubData();
		Mockito.when(patronRepository.save(Mockito.any(Patron.class))).thenReturn(patron);
		Patron patronObj = patronServiceImpl.updatePatron(patron);
		assertEquals(patronObj.getId(), patron.getId());
		
	}
	
	private List<Patron> createPatronListStub() {
		List<Patron> patronList = new ArrayList<>();
		patronList.add(new Patron(1L,"John","0987656789"));
		patronList.add(new Patron(2L,"Amy","9087656789"));
		return patronList;
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

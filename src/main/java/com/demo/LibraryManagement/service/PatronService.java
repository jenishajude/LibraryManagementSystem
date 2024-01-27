package com.demo.LibraryManagement.service;

import java.util.List;
import java.util.Optional;

import com.demo.LibraryManagement.entity.Patron;

public interface PatronService {

	List<Patron> getAllPatronss();

	Optional<Patron> getPatronById(Long patronID);

	Patron addPatron(Patron patron);

	Patron updatePatron(Patron updatePatron);

	void deleteById(Long id);

}

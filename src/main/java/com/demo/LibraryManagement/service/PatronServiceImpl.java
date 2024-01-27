package com.demo.LibraryManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.LibraryManagement.Exception.ResourceNotFoundException;
import com.demo.LibraryManagement.entity.Patron;
import com.demo.LibraryManagement.repository.PatronRepository;

@Service
public class PatronServiceImpl implements PatronService {

	@Autowired
	private PatronRepository patronRepository;

	@Override
	public List<Patron> getAllPatronss() {
		return patronRepository.findAll();
	}

	@Override
	public Optional<Patron> getPatronById(Long patronID) {
		Optional<Patron> patron = patronRepository.findById(patronID);
		if(patron.isPresent()) {
			return patron;
		}else {
			throw new ResourceNotFoundException("Patron not exist with id:" + patronID);
		}
	}

	@Override
	public Patron addPatron(Patron patron) {
		return patronRepository.save(patron);
	}

	@Override
	public Patron updatePatron(Patron updatePatron) {
		return patronRepository.save(updatePatron);
	}

	@Override
	public void deleteById(Long id) {
		patronRepository.deleteById(id);	
	}

}

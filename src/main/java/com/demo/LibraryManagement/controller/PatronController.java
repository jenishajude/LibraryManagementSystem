package com.demo.LibraryManagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.LibraryManagement.Exception.ResourceNotFoundException;
import com.demo.LibraryManagement.entity.Patron;
import com.demo.LibraryManagement.service.PatronService;


@RestController
@RequestMapping("api/patrons")
public class PatronController {

	@Autowired
	private PatronService patronService;
	
	
	@GetMapping
	public ResponseEntity<List<Patron>> getAllPatronss(){
		List<Patron> patronlist = patronService.getAllPatronss();
		return new ResponseEntity<>(patronlist, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Patron> getPatronById(@PathVariable("id") Long id ){
			Optional<Patron> patronVal = patronService.getPatronById(id);
			return new ResponseEntity<>(patronVal.get(),HttpStatus.OK);
		
	}
	
	@PostMapping
    public ResponseEntity<Patron> addPatron(@RequestBody Patron patron) {
        return new ResponseEntity<>(patronService.addPatron(patron),HttpStatus.CREATED);
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<Patron> updatepatron(@PathVariable Long id, @RequestBody Patron patron) {
		Optional<Patron> updatePatron = patronService.getPatronById(id);;
		if(updatePatron.isPresent()) {
			Patron patronToUpdated = updatePatron.get();
			patronToUpdated.setName(patron.getName());
			patronToUpdated.setContactInfo(patron.getContactInfo());
			return new ResponseEntity<>(patronService.updatePatron(patronToUpdated),HttpStatus.OK);
		}else {
			throw new ResourceNotFoundException("Patron not exist with id:" + id);
		}
    }

    @DeleteMapping("/{id}")
    public String deletePatron(@PathVariable Long id) {
        patronService.deleteById(id);
        return "Patron deleted successfully";
    } 
}

package com.demo.LibraryManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.LibraryManagement.entity.Patron;

public interface PatronRepository extends JpaRepository<Patron, Long>{

}

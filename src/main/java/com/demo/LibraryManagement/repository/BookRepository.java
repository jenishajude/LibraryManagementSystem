package com.demo.LibraryManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.LibraryManagement.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}

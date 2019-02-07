package com.t3.microservice.books.models.services;

import java.util.List;

import com.t3.microservice.books.models.entities.Book;

public interface IBookService {
	
    public List<Book> findAll();

    public Book findById(Long id);

    public Book save(Book book);

    public void delete(Long id);
}

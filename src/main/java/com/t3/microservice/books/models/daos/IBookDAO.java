package com.t3.microservice.books.models.daos;

import org.springframework.data.repository.CrudRepository;

import com.t3.microservice.books.models.entities.Book;

public interface IBookDAO extends CrudRepository<Book, Long>{

}

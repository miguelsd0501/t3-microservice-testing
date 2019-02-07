package com.t3.microservice.books.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.t3.microservice.books.models.daos.IBookDAO;
import com.t3.microservice.books.models.entities.Book;

@Service
public class BookService implements IBookService {
	
	@Autowired
	IBookDAO bookDao;

	@Override
	@Transactional(readOnly = true)
	public List<Book> findAll() {
		return (List<Book>) bookDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Book findById(Long id) {
		return bookDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Book save(Book book) {
		return bookDao.save(book);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		bookDao.deleteById(id);
	}

}

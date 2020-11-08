package com.example.library.services;


import com.example.library.entities.Book;
import com.example.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Book getBookById(Integer id) {
        return bookRepository.findById(id).get();
    }

    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }
}

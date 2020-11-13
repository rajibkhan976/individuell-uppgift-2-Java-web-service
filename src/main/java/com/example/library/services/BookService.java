package com.example.library.services;


import com.example.library.entities.Book;
import com.example.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBook(String name, String author, boolean sortByGenre) {
        List<Book> books = bookRepository.findAll();

        if (name != null) {
            books = books.stream()
                    .filter(book -> book.getName().equalsIgnoreCase(name))
                    .collect(Collectors.toList());
        }

        if (author != null) {
            books = books.stream()
                    .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                    .collect(Collectors.toList());
        }

        if (sortByGenre) {
            books.sort(Comparator.comparing(Book::getGenre));
        }
        return books;
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Book getBookById(Integer id) {
        if (!bookRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Could not find the book by id %i.", id));
        }
        return bookRepository.findById(id).get();
    }

    public void deleteBook(Integer id) {
        if (!bookRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Could not find the book by id %i.", id));
        }
        bookRepository.deleteById(id);
    }
}

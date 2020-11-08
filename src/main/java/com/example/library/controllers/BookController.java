package com.example.library.controllers;


import com.example.library.entities.Book;
import com.example.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/v1/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("")
    public ResponseEntity<List<Book>> findAllBooks() {
        return ResponseEntity.ok(bookService.getAllBook());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable Integer id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Book> addBook(@Validated @RequestBody Book book) {
        return ResponseEntity.ok(bookService.saveBook(book));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBookById(@Validated @RequestBody Book book, @PathVariable Integer id) {
        try {
            Book existsBook = bookService.getBookById(id);
            book.setId(id);
            bookService.saveBook(book);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBookById(@PathVariable Integer id) {
        bookService.deleteBook(id);
    }
}

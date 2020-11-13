package com.example.library.controllers;


import com.example.library.entities.Book;
import com.example.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/v1/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Secured({"ROLE_ADMIN", "ROLE_EDITOR", "ROLE_USER"})
    @GetMapping("")
    public ResponseEntity<List<Book>> findAllBooks(@RequestParam(required = false) String name, @RequestParam(required = false) String author, @RequestParam(required = false) boolean sortByGenre) {

        return ResponseEntity.ok(bookService.getAllBook(name, author, sortByGenre));
    }

    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    @GetMapping("/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable Integer id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/")
    public ResponseEntity<Book> addBook(@Validated @RequestBody Book book) {
        return ResponseEntity.ok(bookService.saveBook(book));
    }

    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
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

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBookById(@PathVariable Integer id) {
        bookService.deleteBook(id);
    }
}

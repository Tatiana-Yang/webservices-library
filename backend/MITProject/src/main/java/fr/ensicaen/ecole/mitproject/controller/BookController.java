package fr.ensicaen.ecole.mitproject.controller;

import fr.ensicaen.ecole.mitproject.dto.BookDto;
import fr.ensicaen.ecole.mitproject.entity.Author;
import fr.ensicaen.ecole.mitproject.entity.Book;
import fr.ensicaen.ecole.mitproject.exception.WrongIDException;
import fr.ensicaen.ecole.mitproject.repositories.AuthorRepository;
import fr.ensicaen.ecole.mitproject.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("spring/book")
public class BookController {

    public BookService bookservice;
    public AuthorRepository authorRepository;

    public BookController(@Autowired BookService bookservice){
        this.bookservice = bookservice;
    }

    @GetMapping(value="/allBooks", produces ="application/json")
    public Collection<Book> getAll(){
        return bookservice.list();
    }

    @PostMapping(value = "/bookById", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> searchBookID(@RequestBody Book book) throws WrongIDException {
        if(book == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Book boo = bookservice.getById(book.getId_book());
        return ResponseEntity.status(HttpStatus.OK).body(boo);
    }

    @PostMapping(value = "/bookByTitle", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Book>> searchBookTitle(@RequestBody Book book) {
        if(book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        ArrayList<Book> boo = bookservice.getByTitle(book.getTitle());
        if(boo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(boo);
    }

    @PostMapping(value = "/bookByYear", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Book>> searchBookYear(@RequestBody Book book) {
        if(book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        ArrayList<Book> boo = bookservice.getByYear(book.getPublication_year());
        if(boo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(boo);
    }

    @PostMapping(value = "/add", produces = "application/json")
    public BookDto add(@RequestBody BookDto bookDto){
        bookservice.add(bookDto);
        return bookDto;
    }

    @DeleteMapping("/delete-{id}")
    public ResponseEntity<Book> delete(@PathVariable String id) throws WrongIDException {
        Book book = bookservice.getById(Long.parseLong(id));
        if(book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        bookservice.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }



}

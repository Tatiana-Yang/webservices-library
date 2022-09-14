package fr.ensicaen.ecole.mitproject.service;

import fr.ensicaen.ecole.mitproject.dto.BookDto;
import fr.ensicaen.ecole.mitproject.entity.Author;
import fr.ensicaen.ecole.mitproject.entity.Book;
import fr.ensicaen.ecole.mitproject.entity.Publisher;
import fr.ensicaen.ecole.mitproject.exception.WrongIDException;
import fr.ensicaen.ecole.mitproject.repositories.AuthorRepository;
import fr.ensicaen.ecole.mitproject.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public Collection<Book> list(){
        return bookRepository.findAll();
    }

    public Book add(BookDto bookDto){
        Book b = toBook(bookDto);
        bookRepository.save(b);
        return b;
    }

    public Book getById(long id) throws WrongIDException {
        Optional<Book> book = bookRepository.findById(id);
        if (!book.isPresent()) throw WrongIDException.builder(String.valueOf(id), Book.class);
        return book.get();
    }

    public ArrayList<Book> getByTitle(String title) {
        ArrayList<Book> book = bookRepository.searchBookByPartialTitle(title);
        if (!book.isEmpty())
            return book;
        else
            return null;
    }

    public ArrayList<Book> getByYear(int year) {
        ArrayList<Book> book = bookRepository.searchBookByYear(year);
        if (!book.isEmpty())
            return book;
        else
            return null;
    }

    public Optional<Book> delete(String id) {
        long idLong = Long.parseLong(id);
        Book book = bookRepository.getById(idLong);
        bookRepository.delete(book);
        return Optional.of(book);
    }


    public Book toBook(BookDto bookDto){
        Book book = new Book();
        long id = list().size() + 1;
        book.setId_book(id);
        book.setDescription(bookDto.description);
        book.setTitle(bookDto.title);
        book.setPublication_year(bookDto.publication_year);
        book.setState(bookDto.state);
        book.setId_publisher(bookDto.id_publisher);
        List<Author> authors = book.getAuthors();
        for(Long l : bookDto.id_authors){
            Optional <Author> a = authorRepository.findById(l);
            if(a.isPresent()) {
                authors.add(a.get());
                book.setId_author(String.valueOf(l));
            }
        }
        book.setAuthors(authors);
        return book;
    }

    /*
    public long count() {
        return bookRepository.count();
    }
    */

}

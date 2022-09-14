package fr.ensicaen.ecole.mitproject.controller;

import fr.ensicaen.ecole.mitproject.entity.Author;
import fr.ensicaen.ecole.mitproject.entity.Publisher;
import fr.ensicaen.ecole.mitproject.exception.WrongIDException;
import fr.ensicaen.ecole.mitproject.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("spring/author")
public class AuthorController {


    public AuthorService authorService;

    public AuthorController( @Autowired AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(value = "/allAuthors", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Author> getAll() {
        return authorService.list();
    }

    @PostMapping(value = "/authorById", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Author> searchAuthorID(@RequestBody Author author) throws WrongIDException {
        if(author == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Author auth = authorService.getById(author.getId_author());
        return ResponseEntity.status(HttpStatus.OK).body(auth);
    }

    @PostMapping(value = "/authorByName", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Author>> searchAuthorName(@RequestBody Author author) {
        if(author == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        ArrayList<Author> auth = authorService.getByName(author.getName());
        if(auth == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(auth);
    }

    @PostMapping(value = "/addByForm")
    public ResponseEntity<Author> addForm(@RequestBody Author author) throws WrongIDException {
        Author auth;
        if(author == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        if(authorService.getByName(author.getName()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        auth = authorService.add(author.getName());
        if(author.getName() == null || author.getName().length() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(auth);
    }

    @DeleteMapping("/delete-{id}")
    public ResponseEntity<Author> delete(@PathVariable String id) throws WrongIDException {
        Author author = authorService.getById(Long.parseLong(id));
        if(author == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        authorService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(author);
    }
}


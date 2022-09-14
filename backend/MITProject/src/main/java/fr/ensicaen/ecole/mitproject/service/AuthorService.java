package fr.ensicaen.ecole.mitproject.service;

import fr.ensicaen.ecole.mitproject.entity.Author;
import fr.ensicaen.ecole.mitproject.entity.Publisher;
import fr.ensicaen.ecole.mitproject.exception.WrongIDException;
import fr.ensicaen.ecole.mitproject.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Collection<Author> list() {
        return authorRepository.findAll();
    }


    public Author add(Author author){
        return authorRepository.save(author);
    }

    public Author getById(long id_author) throws WrongIDException {
        Optional<Author> author = authorRepository.findById(id_author);
        if (author.isEmpty()) throw WrongIDException.builder(String.valueOf(id_author), Author.class);
        return author.get();
    }

    public ArrayList<Author> getByName(String name) {
        ArrayList<Author> author = authorRepository.searchAuthorByPartialName(name);
        if (!author.isEmpty())
            return author;
        else
            return null;
    }

    public Optional<Author> delete(String id) {
        long idLong = Long.parseLong(id);
        Author author = authorRepository.getById(idLong);
        authorRepository.delete(author);
        return Optional.of(author);
    }

    public Author add(String name){
        Author auth = new Author();
        auth.setName(name);
        authorRepository.save(auth);
        return auth;
    }
}

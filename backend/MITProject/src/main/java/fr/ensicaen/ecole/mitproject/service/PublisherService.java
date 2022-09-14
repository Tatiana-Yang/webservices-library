package fr.ensicaen.ecole.mitproject.service;
import fr.ensicaen.ecole.mitproject.entity.Publisher;
import fr.ensicaen.ecole.mitproject.exception.AlreadyInDatabaseException;
import fr.ensicaen.ecole.mitproject.exception.WrongIDException;
import fr.ensicaen.ecole.mitproject.repositories.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class PublisherService {
    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public Publisher add(Publisher publisher) throws AlreadyInDatabaseException {
        Optional<Publisher> exists = publisherRepository.searchByPublisherName(publisher.getName());
        if (!exists.isEmpty())
            throw AlreadyInDatabaseException.builder("same name", "name=" + publisher.getName());
        else
            return publisherRepository.save(publisher);
    }

    public Collection<Publisher> list() {
        return publisherRepository.findAll();
    }


    public Publisher getById(long id_author) throws WrongIDException {
        Optional<Publisher> publisher = publisherRepository.findById(id_author);
        if (!publisher.isPresent()) throw WrongIDException.builder(String.valueOf(id_author), Publisher.class);
        return publisher.get();
    }

    public ArrayList<Publisher> getByName(String name) {
        ArrayList<Publisher> publisher = publisherRepository.searchPublisherByPartialName(name);
        if (!publisher.isEmpty())
            return publisher;
        else
            return null;
    }

    public Optional<Publisher> delete(String id) {
        long idLong = Long.parseLong(id);
        Publisher publisher = publisherRepository.getById(idLong);
        publisherRepository.delete(publisher);
        return Optional.of(publisher);
    }

    public Publisher add(String name){
        Publisher pub = new Publisher();
        pub.setName(name);
        publisherRepository.save(pub);
        return pub;
    }
}

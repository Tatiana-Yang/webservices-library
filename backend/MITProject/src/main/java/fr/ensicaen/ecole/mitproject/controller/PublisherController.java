package fr.ensicaen.ecole.mitproject.controller;

import fr.ensicaen.ecole.mitproject.entity.Publisher;
import fr.ensicaen.ecole.mitproject.exception.WrongIDException;
import fr.ensicaen.ecole.mitproject.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("spring/publisher")
public class PublisherController {

    private PublisherService publisherService;

    public PublisherController(@Autowired PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping(value = "/allPublishers", produces = "application/json")
    public Collection<Publisher> getAll() {
        return publisherService.list();
    }

    @PostMapping(value = "/publisherByName", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Publisher>> searchPublisherName(@RequestBody Publisher publisher) {
        if(publisher == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        ArrayList<Publisher> publish = publisherService.getByName(publisher.getName());
        if(publish == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(publish);
    }

    @PostMapping(value = "/publisherById", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Publisher> searchPublisherID(@RequestBody Publisher publisher) throws WrongIDException {
        if(publisher == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Publisher publish = publisherService.getById(publisher.getId_publisher());
        if(publish == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(publish);
    }

    @PostMapping(value = "/addByForm")
    public ResponseEntity<Publisher> addForm(@RequestBody Publisher publisher) throws WrongIDException {
        Publisher publish;
        if(publisher == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        if(publisherService.getByName(publisher.getName()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        publish = publisherService.add(publisher.getName());
        if(publisher.getName() == null || publisher.getName().length() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(publish);
    }

    @DeleteMapping("/delete-{id}")
    public ResponseEntity<Publisher> delete(@PathVariable String id) throws WrongIDException {
        Publisher publisher = publisherService.getById(Long.parseLong(id));
        if(publisher == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        publisherService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(publisher);
    }

}

package fr.ensicaen.ecole.mitproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity(name = "Author")
@Table(name="Author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_author", nullable = false)
    private Long id_author;

    @NaturalId(mutable = false)
    @Column(unique = true, nullable = false, length = 64)
    String name;

    @ManyToMany(fetch = FetchType.EAGER,
            mappedBy="authors")
    private List<Book> books;

    public Author(){

    }

    public Long getId_author() {
        return id_author;
    }

    public String getName() {
        return name;
    }

    public void setId_author(Long id_author) {
        this.id_author = id_author;
    }

    public void setName(String name) {
        this.name = name;
    }
}

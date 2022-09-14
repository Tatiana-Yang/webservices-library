package fr.ensicaen.ecole.mitproject.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="Book")
@Table(name="Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_book", nullable = false)
    private Long id_book;

    @Column(name = "title")
    private String title;

    @Column(name = "publication_year")
    private int publication_year;

    @Column(name = "description")
    private String description;

    @Column(name = "state")
    private int state;

    @Column(name = "id_publisher")
    private int id_publisher;

    @Column(name = "id_author")
    private String id_author;

//    @ManyToMany(fetch = FetchType.EAGER)
//            @joinColumns = @JoinColumn (name= "id_book", referencedColumnName = "id_book"),
//            inverseJoinColumns = @JoinColumn (name = "id_author", referencedColumnName =  "id_author"))
//    private List<Author> authors;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="id_author")
    private List<Author> authors = new ArrayList<>();

    public Long getId_book() {
        return id_book;
    }

    public void setId_book(Long id_book) {
        this.id_book = id_book;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublication_year() {
        return publication_year;
    }

    public void setPublication_year(int publication_year) {
        this.publication_year = publication_year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getId_publisher() {
        return id_publisher;
    }

    public void setId_publisher(int id_publisher) {
        this.id_publisher = id_publisher;
    }

    public String getId_author() {
        return id_author;
    }

    public void setId_author(String id_author) {
        if(this.id_author== null){
            this.id_author = id_author;
        }
        else{
            this.id_author += "," + id_author;
        }
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }
}

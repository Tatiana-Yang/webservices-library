package fr.ensicaen.ecole.mitproject.entity;

import javax.persistence.*;
import java.util.List;

@Entity//(name="Publisher")
@Table(name="Publisher")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_publisher", nullable = false)
    private long id_publisher;

    @Column
    private String name;

    public Publisher(){

    }

    public long getId_publisher() {
        return id_publisher;
    }

    public void setId_publisher(long id_publisher) {
        this.id_publisher = id_publisher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

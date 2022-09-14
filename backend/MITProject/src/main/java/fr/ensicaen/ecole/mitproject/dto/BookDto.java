package fr.ensicaen.ecole.mitproject.dto;

import java.util.ArrayList;

public class BookDto {
    public String title;
    public int publication_year;
    public String description;
    public int state;
    public int id_publisher;
    public ArrayList<Long> id_authors;
}
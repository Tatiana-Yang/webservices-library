package fr.ensicaen.ecole.mitproject.repositories;

import fr.ensicaen.ecole.mitproject.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    /*
     ** find a book by partial title
     */
    @Query(value = "SELECT b FROM Book b WHERE lower(b.title) LIKE lower(concat('%', :partial_title, '%'))")
    ArrayList<Book> searchBookByPartialTitle(@Param("partial_title") String title);

    /*
     ** find a book by year of publication
     */
    @Query(value = "SELECT b FROM Book b WHERE b.publication_year = :year")
    ArrayList<Book> searchBookByYear(@Param("year") int year);

}

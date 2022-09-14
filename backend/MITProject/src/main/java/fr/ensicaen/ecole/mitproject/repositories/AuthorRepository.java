package fr.ensicaen.ecole.mitproject.repositories;

import fr.ensicaen.ecole.mitproject.entity.Author;
import fr.ensicaen.ecole.mitproject.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{
    /*
     ** count authors
     *
    @Query(value = "SELECT COUNT(Author.id_author) FROM Author")
    Optional<Author> countAuthors();*/

    /*
     ** find an author by name
     */
    @Query(value = "SELECT a FROM Author a WHERE lower(a.name) LIKE lower(:name)")
    Optional<Author> searchByAuthorName(@Param("name") String Name);

    /*
     ** find an author by partial name
     */
    //@Query(value = "SELECT a FROM Author a WHERE lower(a.name) LIKE lower(concat('%', :partial_name, '%'))")
    //Optional<Author> searchAuthorByPartialName(@Param("partial_name") String name);

    /*
     ** find an author by partial name
     */
    @Query(value = "SELECT a FROM Author a WHERE lower(a.name) LIKE lower(concat('%', :partial_name, '%'))")
    ArrayList<Author> searchAuthorByPartialName(@Param("partial_name") String name);
}


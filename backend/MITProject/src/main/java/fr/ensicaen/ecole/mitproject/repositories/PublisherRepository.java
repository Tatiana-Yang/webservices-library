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
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    /*
     ** find a publisher by name
     */
    @Query(value = "SELECT p FROM Publisher p WHERE lower(p.name) LIKE lower(:name)")
    Optional<Publisher> searchByPublisherName(@Param("name") String Name);

    /*
     ** find a publisher by partial name
     */
    @Query(value = "SELECT p FROM Publisher p WHERE lower(p.name) LIKE lower(concat('%', :partial_name, '%'))")
    ArrayList<Publisher> searchPublisherByPartialName(@Param("partial_name") String name);

}

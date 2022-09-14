package fr.ensicaen.ecole.mitproject.repositories;

import fr.ensicaen.ecole.mitproject.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    /*
     ** find a loan by user
     */
    @Query(value = "SELECT l FROM Loan l WHERE lower(l.id_user) LIKE lower(concat('%', :id_user, '%'))")
    ArrayList<Loan> searchLoanByUserId(@Param("id_user") String id_user);

}

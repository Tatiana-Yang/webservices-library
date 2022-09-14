package fr.ensicaen.ecole.mitproject.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name="Loan")
@Table(name="Loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_loan", nullable = false)
    private Long id_loan;

    @Column(name = "id_user")
    private String id_user;

    @Column(name = "id_book")
    private Long id_book;

    @Column(name = "date_borrowing")
    private Date date_borrowing;

    @Column(name = "date_return")
    private Date date_return;

    @Column(name = "borrowing_days")
    private int borrowing_days;


    public Long getId_loan() {
        return id_loan;
    }

    public void setId_loan(Long id_loan) {
        this.id_loan = id_loan;
    }

    public String getId_user() { return  id_user; }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public Long getId_book() {
        return id_book;
    }

    public void setId_book(Long id_book) {
        this.id_book = id_book;
    }

    public Date getDate_borrowing() {
        return date_borrowing;
    }

    public void setDate_borrowing(Date date_borrowing) {
        this.date_borrowing = date_borrowing;
    }

    public Date getDate_return() {
        return date_return;
    }

    public void setDate_return(Date date_return) {
        this.date_return = date_return;
    }

    public int getBorrowing_days() {
        return borrowing_days;
    }

    public void setBorrowing_days(int borrowing_days) {
        this.borrowing_days = borrowing_days;
    }
}

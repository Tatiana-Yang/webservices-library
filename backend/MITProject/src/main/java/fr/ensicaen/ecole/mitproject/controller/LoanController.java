package fr.ensicaen.ecole.mitproject.controller;

import fr.ensicaen.ecole.mitproject.dto.LoanDto;
import fr.ensicaen.ecole.mitproject.entity.Loan;
import fr.ensicaen.ecole.mitproject.exception.WrongIDException;
import fr.ensicaen.ecole.mitproject.repositories.AuthorRepository;
import fr.ensicaen.ecole.mitproject.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("spring/loan")
public class LoanController {

    public LoanService loanservice;
    public AuthorRepository authorRepository;

    public LoanController(@Autowired LoanService loanservice){
        this.loanservice = loanservice;
    }

    @GetMapping(value="/allLoans", produces ="application/json")
    public Collection<Loan> getAll(){
        return loanservice.list();
    }

    @PostMapping(value = "/loanById", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Loan> searchLoanID(@RequestBody Loan loan) throws WrongIDException {
        if(loan == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Loan loa = loanservice.getById(loan.getId_loan());
        return ResponseEntity.status(HttpStatus.OK).body(loa);
    }

    @PostMapping(value = "/loanByUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Loan>> searchLoanUser(@RequestBody Loan loan) {
        if(loan == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        ArrayList<Loan> loa = loanservice.getByUser(loan.getId_user());
        if(loa == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(loa);
    }

    @PostMapping(value = "/add", produces = "application/json")
    public LoanDto add(@RequestBody LoanDto loanDto){
        loanservice.add(loanDto);
        return loanDto;
    }

    @DeleteMapping("/delete-{id}")
    public ResponseEntity<Loan> delete(@PathVariable String id) throws WrongIDException {
        Loan loan = loanservice.getById(Long.parseLong(id));
        if(loan == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        loanservice.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(loan);
    }



}

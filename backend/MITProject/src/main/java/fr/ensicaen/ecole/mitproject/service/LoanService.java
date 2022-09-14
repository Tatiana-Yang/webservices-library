package fr.ensicaen.ecole.mitproject.service;

import fr.ensicaen.ecole.mitproject.dto.LoanDto;
import fr.ensicaen.ecole.mitproject.entity.Loan;
import fr.ensicaen.ecole.mitproject.exception.WrongIDException;
import fr.ensicaen.ecole.mitproject.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LoanService {
    @Autowired
    private LoanRepository loanRepository;

    public Collection<Loan> list(){
        return loanRepository.findAll();
    }

    public Loan add(LoanDto loanDto){
        Loan l = toLoan(loanDto);
        loanRepository.save(l);
        return l;
    }

    public Loan getById(long id) throws WrongIDException {
        Optional<Loan> loan = loanRepository.findById(id);
        if (!loan.isPresent()) throw WrongIDException.builder(String.valueOf(id), Loan.class);
        return loan.get();
    }

    public ArrayList<Loan> getByUser(String id_user) {
        ArrayList<Loan> loan = loanRepository.searchLoanByUserId(id_user);
        if (!loan.isEmpty())
            return loan;
        else
            return null;
    }

    public Optional<Loan> delete(String id) {
        long idLong = Long.parseLong(id);
        Loan loan = loanRepository.getById(idLong);
        loanRepository.delete(loan);
        return Optional.of(loan);
    }


    public Loan toLoan(LoanDto loanDto){
        Loan loan = new Loan();
        long id = list().size() + 1;
        loan.setId_loan(id);
        loan.setId_user(loanDto.id_user);
        loan.setId_book(loanDto.id_book);

        Date date_borrowing = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date_borrowing);
        c.add(Calendar.DATE, loanDto.borrowing_days);
        Date date_return = c.getTime();

        loan.setDate_borrowing(date_borrowing);
        loan.setDate_return(date_return);
        loan.setBorrowing_days(loanDto.borrowing_days);
        return loan;
    }

    /*
    public long count() {
        return loanRepository.count();
    }
    */

}

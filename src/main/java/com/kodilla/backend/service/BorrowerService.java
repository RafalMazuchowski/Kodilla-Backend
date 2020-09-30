package com.kodilla.backend.service;

import com.kodilla.backend.domain.Borrower;
import com.kodilla.backend.domain.repository.BorrowerDao;
import com.kodilla.backend.exceptions.BorrowerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowerService {

    @Autowired
    private BorrowerDao borrowerDao;

    public List<Borrower> getAllBorrowers() {
        return borrowerDao.findAll();
    }

    public Borrower getBorrower(Long id) {
        return borrowerDao.findById(id).orElseThrow(BorrowerNotFoundException::new);
    }

    public Borrower saveBorrower(Borrower borrower) {
        return borrowerDao.save(borrower);
    }

    public void deleteBorrower(long id) {
        borrowerDao.deleteById(id);
    }
}

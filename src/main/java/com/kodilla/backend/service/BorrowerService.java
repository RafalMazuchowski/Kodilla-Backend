package com.kodilla.backend.service;

import com.kodilla.backend.domain.Borrower;
import com.kodilla.backend.domain.dto.BorrowerDto;
import com.kodilla.backend.domain.repository.BorrowerDao;
import com.kodilla.backend.exceptions.BorrowerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
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

    public Borrower updateBorrower(BorrowerDto borrowerDto, Long id) {
        Borrower borrower = borrowerDao.findById(id).orElseThrow(BorrowerNotFoundException::new);
        return borrowerDao.save(borrower);
    }

    public void deleteBorrower(long id) {
        borrowerDao.deleteById(id);
    }
}

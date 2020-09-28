package com.kodilla.backend.domain;

import com.kodilla.backend.domain.repository.BorrowerDao;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BorrowerTest {
    @Autowired
    private BorrowerDao borrowerDao;

    @Transactional
    @Test
    public void testSaveAndFindAll() {
        //Given
        int initialNumberOfBorrowers = borrowerDao.findAll().size();
        Borrower borrower1 = new Borrower(16L, "Adam", "Ford", Date.valueOf("2001-04-20"));
        Borrower borrower2 = new Borrower(17L, "Adam", "Ford", Date.valueOf("2001-04-20"));

        //When
        borrowerDao.save(borrower1);
        borrowerDao.save(borrower2);
        List<Borrower> borrowers = borrowerDao.findAll();
        int numberOfBorrowers = borrowers.size();

        //Then
        assertEquals(initialNumberOfBorrowers + 2, numberOfBorrowers);
        assertTrue(borrowers.contains(borrower1));
        assertTrue(borrowers.contains(borrower2));

        //CleanUp
        borrowerDao.delete(borrower1);
        borrowerDao.delete(borrower1);
    }

    @Transactional
    @Test
    public void testFindById() {
        //Given
        Borrower borrower1 = new Borrower(16L, "Adam", "Ford", Date.valueOf("2001-04-20"));
        borrowerDao.save(borrower1);

        //When
        Borrower foundBorrower = borrowerDao.findById(borrower1.getBorrowerId()).get();

        //Then
        assertEquals(borrower1, foundBorrower);

        //CleanUp
        borrowerDao.delete(borrower1);
    }

    @Transactional
    @Test
    public void testDelete() {
        //Given
        int initialNumberOfBorrowers = borrowerDao.findAll().size();
        Borrower borrower1 = new Borrower(16L, "Adam", "Ford", Date.valueOf("2001-04-20"));
        borrowerDao.save(borrower1);

        //When
        borrowerDao.delete(borrower1);
        List<Borrower> borrowers = borrowerDao.findAll();
        int numberOfBorrowers = borrowers.size();

        //Then
        assertEquals(initialNumberOfBorrowers, numberOfBorrowers);
        assertFalse(borrowers.contains(borrower1));
    }
}
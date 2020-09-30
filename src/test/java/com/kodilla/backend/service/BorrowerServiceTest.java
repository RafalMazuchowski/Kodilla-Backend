package com.kodilla.backend.service;

import com.kodilla.backend.domain.Borrower;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BorrowerServiceTest {

    @Autowired
    private BorrowerService borrowerService;

    @Transactional
    @Test
    public void testGetAllBorrowers() {
        //Given
        int initialNumberOfBorrowers = borrowerService.getAllBorrowers().size();
        Borrower borrower1 = new Borrower(16L, "Adam", "Ford", Date.valueOf("2001-04-20"));
        Borrower borrower2 = new Borrower(17L, "Piotr", "Potter", Date.valueOf("2001-04-20"));

        //When
        borrowerService.saveBorrower(borrower1);
        borrowerService.saveBorrower(borrower2);
        List<Borrower> borrowers = borrowerService.getAllBorrowers();
        int numberOfBorrowers = borrowers.size();

        //Then
        assertEquals(initialNumberOfBorrowers + 2, numberOfBorrowers);
        assertEquals(borrower1.getFirstName(), "Adam");
        assertEquals(borrower2.getFirstName(), "Piotr");

        //CleanUp
        borrower1.setBorrowerId(borrowers.get(initialNumberOfBorrowers).getBorrowerId());
        borrower2.setBorrowerId(borrowers.get(initialNumberOfBorrowers + 1).getBorrowerId());
        borrowerService.deleteBorrower(borrower1.getBorrowerId());
        borrowerService.deleteBorrower(borrower2.getBorrowerId());
    }

    @Transactional
    @Test
    public void testGetBorrower() {
        //Given
        Borrower borrower1 = new Borrower(16L, "Adam", "Ford", Date.valueOf("2001-04-20"));
        borrowerService.saveBorrower(borrower1);
        borrower1.setBorrowerId(borrowerService.getAllBorrowers().get(borrowerService.getAllBorrowers().size() - 1).getBorrowerId());

        //When
        Borrower foundBorrower = borrowerService.getBorrower(borrower1.getBorrowerId());

        //Then
        assertEquals(borrower1, foundBorrower);

        //CleanUp
        borrowerService.deleteBorrower(borrower1.getBorrowerId());
    }

    @Transactional
    @Test
    public void testDeleteBorrower() {
        //Given
        int initialNumberOfBorrowers = borrowerService.getAllBorrowers().size();
        Borrower borrower1 = new Borrower(16L, "Adam", "Ford", Date.valueOf("2001-04-20"));
        borrowerService.saveBorrower(borrower1);
        borrower1.setBorrowerId(borrowerService.getAllBorrowers().get(borrowerService.getAllBorrowers().size() - 1).getBorrowerId());

        //When
        borrowerService.deleteBorrower(borrower1.getBorrowerId());
        List<Borrower> borrowers = borrowerService.getAllBorrowers();
        int numberOfBorrowers = borrowers.size();

        //Then
        assertEquals(initialNumberOfBorrowers, numberOfBorrowers);
        assertFalse(borrowers.contains(borrower1));
    }
}
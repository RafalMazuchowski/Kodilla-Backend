package com.kodilla.backend.mapper;

import com.kodilla.backend.domain.Borrower;
import com.kodilla.backend.domain.dto.BorrowerDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BorrowerMapperTest {

    @Autowired
    BorrowerMapper borrowerMapper;

    @Test
    void mapToBorrower() {
        //Given
        BorrowerDto borrowerDto = new BorrowerDto(1L, "Adam", "Ford", Date.valueOf("2001-04-20"));

        //When
        Borrower mappingResult = borrowerMapper.mapToBorrower(borrowerDto);

        //Then
        assertEquals(Optional.of(1L), Optional.ofNullable(mappingResult.getBorrowerId()));
        assertEquals("Adam", mappingResult.getFirstName());
        assertEquals("Ford", mappingResult.getLastName());
        assertEquals(Date.valueOf("2001-04-20"), mappingResult.getRegistration());
    }

    @Test
    void mapToBorrowerDto() {
        //Given
        Borrower borrower = new Borrower(1L, "Adam", "Ford", Date.valueOf("2001-04-20"));

        //When
        BorrowerDto mappingResult = borrowerMapper.mapToBorrowerDto(borrower);

        //Then
        assertEquals(Optional.of(1L), Optional.ofNullable(mappingResult.getBorrowerId()));
        assertEquals("Adam", mappingResult.getFirstName());
        assertEquals("Ford", mappingResult.getLastName());
        assertEquals(Date.valueOf("2001-04-20"), mappingResult.getRegistration());
    }

    @Test
    public void mapToBorrowerDtoList() {
        //Given
        Borrower borrower = new Borrower(1L, "Adam", "Ford", Date.valueOf("2001-04-20"));
        List<Borrower> borrowerList = new ArrayList<>();
        borrowerList.add(borrower);

        //When
        List<BorrowerDto> mappingResult = borrowerMapper.mapToBorrowerDtoList(borrowerList);

        //Then
        assertEquals(Optional.of(1L), Optional.ofNullable(mappingResult.get(0).getBorrowerId()));
        assertEquals("Adam", mappingResult.get(0).getFirstName());
        assertEquals("Ford", mappingResult.get(0).getLastName());
        assertEquals(Date.valueOf("2001-04-20"), mappingResult.get(0).getRegistration());
    }
}
package com.kodilla.backend.mapper;

import com.kodilla.backend.domain.Borrower;
import com.kodilla.backend.domain.dto.BorrowerDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BorrowerMapper {

    public Borrower mapToBorrower(BorrowerDto borrowerDto) {
        return new Borrower(borrowerDto.getBorrowerId(),
                borrowerDto.getFirstName(),
                borrowerDto.getLastName(),
                borrowerDto.getRegistration());
    }

    public BorrowerDto mapToBorrowerDto(Borrower borrower) {
        return new BorrowerDto(borrower.getBorrowerId(),
                borrower.getFirstName(),
                borrower.getLastName(),
                borrower.getRegistration());
    }

    public List<BorrowerDto> mapToBorrowerDtoList(List<Borrower> borrowerList) {
        return borrowerList.stream()
                .map(b -> new BorrowerDto(b.getBorrowerId(),
                        b.getFirstName(),
                        b.getLastName(),
                        b.getRegistration()))
                .collect(Collectors.toList());
    }
}

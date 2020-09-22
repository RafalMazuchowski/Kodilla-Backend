package com.kodilla.backend.mapper;

import com.kodilla.backend.domain.Rent;
import com.kodilla.backend.domain.dto.RentDto;
import com.kodilla.backend.domain.enums.Rental;
import com.kodilla.backend.domain.repository.BorrowerDao;
import com.kodilla.backend.domain.repository.CarDao;
import com.kodilla.backend.exceptions.BorrowerNotFoundException;
import com.kodilla.backend.exceptions.CarNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RentMapper {

    @Autowired
    private BorrowerDao borrowerDao;

    @Autowired
    private CarDao carDao;

    public Rent mapToRent(RentDto rentDto) {
        return new Rent(
                rentDto.getRentId(),
                borrowerDao.findById(rentDto.getBorrowerId())
                        .orElseThrow(BorrowerNotFoundException::new),
                carDao.findById(rentDto.getCarId())
                        .orElseThrow(CarNotFoundException::new),
                rentDto.getDistance(),
                Rental.valueOf(rentDto.getPlaceOfRent()),
                Rental.valueOf(rentDto.getPlaceOfReturn()),
                rentDto.getCarReturned(),
                rentDto.getPrice());
    }

    public RentDto mapToRentDto(Rent rent) {
        return new RentDto(
                rent.getRentId(),
                rent.getBorrower().getBorrowerId(),
                rent.getCar().getCarId(),
                rent.getDistance(),
                rent.getPlaceOfRent().toString(),
                rent.getPlaceOfReturn().toString(),
                rent.getCarReturned(),
                rent.getPrice());
    }

    public List<RentDto> mapToRentDtoList(List<Rent> rentList) {
        return rentList.stream()
                .map(r -> new RentDto(
                        r.getRentId(),
                        r.getBorrower().getBorrowerId(),
                        r.getCar().getCarId(),
                        r.getDistance(),
                        r.getPlaceOfRent().toString(),
                        r.getPlaceOfReturn().toString(),
                        r.getCarReturned(),
                        r.getPrice()))
                .collect(Collectors.toList());
    }
}

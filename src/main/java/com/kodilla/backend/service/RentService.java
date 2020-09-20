package com.kodilla.backend.service;

import com.kodilla.backend.domain.Rent;
import com.kodilla.backend.domain.dto.RentDto;
import com.kodilla.backend.domain.repository.RentDao;
import com.kodilla.backend.exceptions.RentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class RentService {

    @Autowired
    private RentDao rentDao;

    public List<Rent> getAllRents() {
        return rentDao.findAll();
    }

    public Rent getRent(Long id) {
        return rentDao.findById(id).orElseThrow(RentNotFoundException::new);
    }

    public Rent saveRent(Rent rent) {
        return rentDao.save(rent);
    }

    public Rent updateRent(RentDto rentDto, Long id) {
        Rent rent = rentDao.findById(id).orElseThrow(RentNotFoundException::new);
        return rentDao.save(rent);
    }

    public void deleteRent(long id) {
        rentDao.deleteById(id);
    }
}

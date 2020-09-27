package com.kodilla.backend.service;

import com.kodilla.backend.domain.Rent;
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
        Rent rent = rentDao.findById(id).orElseThrow(RentNotFoundException::new);
/*        if (rent.getPrice() == null) {
            //price_counting
            rent.setPrice(1000);
        } else {*/
            return rent;
      //  }
    }

    public Rent saveRent(Rent rent) {
        return rentDao.save(rent);
    }

    public void deleteRent(long id) {
        rentDao.deleteById(id);
    }
}

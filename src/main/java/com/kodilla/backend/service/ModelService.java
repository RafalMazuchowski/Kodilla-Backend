package com.kodilla.backend.service;

import com.kodilla.backend.domain.Model;
import com.kodilla.backend.domain.repository.ModelDao;
import com.kodilla.backend.exceptions.ModelNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ModelService {

    @Autowired
    private ModelDao modelDao;

    public List<Model> getAllModels() {
        return modelDao.findAll();
    }

    public Model getModel(Long id) {
        return modelDao.findById(id).orElseThrow(ModelNotFoundException::new);
    }

    public Model saveModel(Model model) {
        return modelDao.save(model);
    }

    public void deleteModel(long id) {
        modelDao.deleteById(id);
    }
}

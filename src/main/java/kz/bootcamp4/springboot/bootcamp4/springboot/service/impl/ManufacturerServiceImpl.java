package kz.bootcamp4.springboot.bootcamp4.springboot.service.impl;

import kz.bootcamp4.springboot.bootcamp4.springboot.model.Manufacturer;
import kz.bootcamp4.springboot.bootcamp4.springboot.repository.ManufacturerRepository;
import kz.bootcamp4.springboot.bootcamp4.springboot.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    @Autowired
    private ManufacturerRepository manufacturerRepository;
    @Override
    public List<Manufacturer> getManufacturers(){
        return manufacturerRepository.findAll();
    }
}

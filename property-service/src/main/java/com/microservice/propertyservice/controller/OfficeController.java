package com.microservice.propertyservice.controller;

import com.microservice.propertyservice.exception.InvalidInputException;
import com.microservice.propertyservice.exception.ResourceNotFoundException;
import com.microservice.propertyservice.model.Office;
import com.microservice.propertyservice.model.value_object.ResponseTemplateVO;
import com.microservice.propertyservice.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("offices")
public class OfficeController {

    private final OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @GetMapping("/all")
    public List<Office> getAllOffices() {
        return officeService.getAllOffices();
    }

    @GetMapping("/info/{id}")
    public ResponseTemplateVO getOfficeWithTenant(@PathVariable("id") Long id) {
        ResponseTemplateVO office = officeService.getOfficeWithTenant(id);
        if (office.getProperty() == null)
            throw new ResourceNotFoundException("Office with id " + id + " not found.");

        return office;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOffice(@RequestBody Office office) throws InvalidInputException {
        if (office.getName() == null || office.getPeopleCapacity() == null || office.getSquareMeter() == null) {
            throw new InvalidInputException("Name, capacity and square meters are required fields.");
        }
        officeService.createOffice(office);
    }

    @PutMapping("/edit/{id}")
    public void updateOffice(@PathVariable("id") Long id, @RequestBody Office office) {
        office.setId(id);
        if (office.getName() == null || office.getPeopleCapacity() == null || office.getSquareMeter() == null) {
            throw new InvalidInputException("Name, capacity and square meters are required fields.");
        }
        officeService.updateOffice(office);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOffice(@PathVariable("id") Long id) {
        officeService.deleteOffice(id);
    }
}
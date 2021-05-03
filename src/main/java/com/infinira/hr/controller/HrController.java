package com.infinira.hr.controller;

import com.infinira.hr.model.Employee;
import com.infinira.hr.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;


@RestController
public class HrController {
    @Autowired
    private HrService service;
    
	@PostMapping("/create")
    public int create(@RequestBody Employee employee){
        return service.createEmployee(employee);
    }
    @GetMapping("/get/{id}")
    public Employee get(@PathVariable int id){
        return service.getEmployee(id);
    }
    
    @GetMapping("/getAll")
    public List getAll(){
        return service.getAllEmployees();
    }
    
    @PutMapping("/update")
    public void update(@RequestBody Employee employee){
        service.updateEmployee(employee);
    }
    
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id){
        service.deleteEmployee(id);
    }

}


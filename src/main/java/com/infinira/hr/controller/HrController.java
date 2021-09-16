package com.infinira.hr.controller;

import com.infinira.hr.model.Employee;
import com.infinira.hr.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;


@RestController
public class HrController {
    @Autowired
    private HrService service;

    @RequestMapping(value = "/Employee", method = RequestMethod.POST) 
    public int create(@RequestBody Employee employee){
        return service.createEmployee(employee);
    }

    @GetMapping("/Employee/{id}")
    public Employee get(@PathVariable int id){
        return service.getEmployee(id);
    }
    
    @PutMapping("/Employee")
    public void update(@RequestBody Employee employee){
        service.updateEmployee(employee);
    }
    
    @DeleteMapping("/Employee/{id}")
    public void delete(@PathVariable int id){
        service.deleteEmployee(id);
    }
    
    @GetMapping("/Employees")
    public List<Employee> getAll(){
        return service.getAllEmployees();
    }
        

}


package com.infinira.hr.controller;

import com.infinira.hr.model.Employee;
import com.infinira.hr.service.HrService;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;


@RestController
public class HrController {

    private HrService service;
    
    public int create(@RequestBody Employee employee){
        return service.createEmployee(employee);
    }

    public Employee get(@PathVariable int id){
        return service.getEmployee(id);
    }
    
    public List getAll(){
        return service.getAllEmployees();
    }
    
    public void update(@RequestBody Employee employee){
        service.updateEmployee(employee);
    }
    
    public void delete(@PathVariable int id){
        service.deleteEmployee(id);
    }

}


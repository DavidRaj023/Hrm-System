package com.infinira.hr.controller;

import com.infinira.hr.model.Employee;
import com.infinira.hr.service.HrService;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;


@RestController
public class HrController {

    private HrService service;
    
    public int create(Employee employee){
        return service.createEmployee(employee);
    }

    public Employee get(int id){
        return service.getEmployee(id);
    }
    
    public List getAll(){
        return service.getAllEmployees();
    }
    
    public void update(Employee employee){
        service.updateEmployee(employee);
    }
    
    public void delete(int id){
        service.deleteEmployee(id);
    }

}


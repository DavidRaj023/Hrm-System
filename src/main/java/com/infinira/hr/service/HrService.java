package com.infinira.hr.service;
import com.infinira.hr.model.Employee;
import com.infinira.hr.dao.EmployeeDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class HrService implements HrInterface {
	
    public int createEmployee( Employee employee ){
        return new EmployeeDao().create(employee);
    }
    
    public Employee getEmployee( int employeeId ){
        return new EmployeeDao().get(employeeId);
    }
    
    public void updateEmployee( Employee employee ){
        new EmployeeDao().update(employee);
    }
    
    public void deleteEmployee( int employeeId ){
        new EmployeeDao().delete(employeeId);
    }
    
    public List<Employee> getAllEmployees(){
        return new EmployeeDao().getAll();
    }
}
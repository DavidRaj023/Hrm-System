package com.infinira.hr.service;
import com.infinira.hr.model.Employee;
import java.util.List;

public interface HrInterface {
    public int createEmployee( Employee employee );
    public Employee getEmployee( int employeeId );
    public void updateEmployee( Employee employee );
    public void deleteEmployee( int employeeId );
    public List<Employee> getAllEmployees();
}
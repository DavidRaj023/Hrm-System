package com.infinira.hr.test;
import com.infinira.hr.util.ResourceService;
import com.infinira.hr.util.HrException;
import com.infinira.hr.model.Employee;
import com.infinira.hr.model.Gender;
import com.infinira.hr.model.MaritalStatus;
import com.infinira.hr.model.EmployeeStatus;
import com.infinira.hr.service.HrInterface;
import com.infinira.hr.service.HrService;
import java.io.FileInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

public class HrTest{
    
    private static byte[] byteArray(File file){
        byte[] array = new byte[(int) file.length()];
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            fis.read(array);
            return array;
        } catch (Throwable th){
            throw new HrException("Failed to read a document", th);
        } finally{
            if (fis != null){
                try {
                    fis.close();
                } catch (Throwable th){ }
            }
        }
    }
    
    private int create(Employee employee){
        employee.setFirstName("KavinKumar");
        employee.setMiddleName("E");
        employee.setLastName("Raj");
        employee.setFatherName("Edwin");
        
        try {
            employee.setDob(new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2000"));
        } catch (Throwable th){
            throw new HrException("Failed to parse a given DOB", th);
        } 
        employee.setGender(Gender.m);
        employee.setMaritalStatus(MaritalStatus.single);
        employee.setUid("8978675645");
        employee.setEmail("benjamindavid023@gmail");
        employee.setPhone("9578821821");
        employee.setBloodGroup("b+");
        
        try {
            employee.setDoj(new SimpleDateFormat("dd/MM/yyyy").parse("02/10/2001"));
        } catch (Throwable th){
            throw new HrException("Failed to parse a given DOJ", th);
        } 

        employee.setAddressLine1("1/10-4 Periyar Street");
        employee.setAddressLine2("Malaiyappa Nagar");
        employee.setCity("Trichy");
        employee.setState("Tamil Nadu");
        employee.setPostalCode("620010");
        employee.setCountry("India");
        employee.setTitle("Trainee");
        employee.setResume(byteArray(new File("E:/David Raj.E.pdf")));
        employee.setPhoto(byteArray(new File("E:/David.jpg")));
        employee.setEmployeeStatus(EmployeeStatus.active);
		return new HrService().createEmployee(employee); 

    }
    
    private Employee get(int empId) {
		return new HrService().getEmployee(empId);
    }
    
    private List getAll() {
        return new HrService().getAllEmployees();
    }
    
    private void update(Employee employee, int empId){
        employee.setEmpId(empId);
        employee.setFirstName("Davidu");
        employee.setMiddleName("E");
        employee.setLastName("Raj");
        employee.setFatherName("Edwin");
        try {
            employee.setDob(new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2000"));
        } catch (Throwable th){
            throw new HrException("Failed to parse a given DOB", th);
        } 
        employee.setGender(Gender.m);
        employee.setMaritalStatus(MaritalStatus.single);
        employee.setUid("8978675645");
        employee.setEmail("benjamindavid023@gmail");
        employee.setPhone("9578821821");
        employee.setBloodGroup("b+");
        try {
            employee.setDoj(new SimpleDateFormat("dd/MM/yyyy").parse("02/10/2001"));
        } catch (Throwable th){
            throw new HrException("Failed to parse a given DOJ", th);
        } 
        employee.setAddressLine1("1/10-4 Periyar Street");
        employee.setAddressLine2("Malaiyappa Nagar");
        employee.setCity("Trichy");
        employee.setState("TamilNadu");
        employee.setPostalCode("620010");
        employee.setCountry("India");
        employee.setTitle("Trainee");
        employee.setResume(byteArray(new File("E:/David Raj.E.pdf")));
        employee.setPhoto(byteArray(new File("E:/David.jpg")));
        employee.setEmployeeStatus(EmployeeStatus.active);
		new HrService().updateEmployee(employee);
    }
    
    private void delete(int empId) {
		new HrService().deleteEmployee(empId);      
    }
    
    public static void main(String[] args) throws Exception {
        Employee employee = new Employee();
        HrTest test = new HrTest();
        
        int EmployeeID = test.create(employee);
        Employee getEmployee = test.get(146);
        test.update(employee, 150);
        test.delete(147);
        List<Employee> ls = test.getAll();
    }
    
}

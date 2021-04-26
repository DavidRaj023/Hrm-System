package com.infinira.hr.dao;
import com.infinira.hr.model.Employee;
import com.infinira.hr.util.DatabaseService;
import com.infinira.hr.util.ResourceService;
import com.infinira.hr.util.HrException;
import com.infinira.hr.model.Gender;
import com.infinira.hr.model.MaritalStatus;
import com.infinira.hr.model.EmployeeStatus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.io.InputStream;


public class EmployeeDao {
	//Insert
    public int create(Employee employee) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseService.getInstance().getConnection();
            pstmt = conn.prepareStatement(INSERT_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            setValues(pstmt, employee);
            pstmt.executeUpdate();      
            rs = pstmt.getGeneratedKeys();
            int generatedKey = 0;           
            if (rs.next()) {
               generatedKey = rs.getInt(1);
               return generatedKey;
            } else {
                throw new HrException(ResourceService.getInstance().getMessage("HR-00006", employee.getFirstName()));
            }
            
        } catch (Throwable th) {
            throw new HrException(ResourceService.getInstance().getMessage("HR-00001", employee.getFirstName()), th);
        } finally {
            DatabaseService.getInstance().closeResources(rs, pstmt, conn);
        }
    }
    
    //Select
    public Employee get(int employeeId) {
		if (employeeId <= 0){
			throw new HrException(ResourceService.getInstance().getMessage("HR-00007"));
		}
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;        
        Employee employee = new Employee();
        try {
            conn = DatabaseService.getInstance().getConnection();
            pstmt = conn.prepareStatement(SELECT_QUERY + employeeId);
            rs = pstmt.executeQuery();
            rs.next();
            getValues(rs, employee);
            return employee;
        } catch (Throwable th) {
            throw new HrException(ResourceService.getInstance().getMessage("HR-00002", employee.getFirstName()), th);
        } finally {
            DatabaseService.getInstance().closeResources(rs, pstmt, conn);
        }
    }

    //Update
    public void update(Employee employee) {
		if (employee.getEmpId() <= 0){
			throw new HrException(ResourceService.getInstance().getMessage("HR-00007"));
		}
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DatabaseService.getInstance().getConnection();
            pstmt = conn.prepareStatement(UPDATE_QUERY + employee.getEmpId());
            setValues(pstmt,employee);
            pstmt.executeUpdate();      
        }
        catch (Throwable th) {
            throw new HrException(ResourceService.getInstance().getMessage("HR-00003", employee.getEmpId()), th);
        } finally {
            DatabaseService.getInstance().closeResources(null, pstmt, conn);
        }
    }

    //Delete
    public void delete(int employeeId) {
		if (employeeId <= 0){
			throw new HrException(ResourceService.getInstance().getMessage("HR-00007"));
		}
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            DatabaseService db = DatabaseService.getInstance();
            conn = db.getConnection();
            pstmt = conn.prepareStatement(DELETE_QUERY+ employeeId);
            pstmt.executeUpdate();
        } catch (Throwable th) {
            throw new HrException(ResourceService.getInstance().getMessage("HR-00004", employeeId), th);
        } finally {
            DatabaseService.getInstance().closeResources(null, pstmt, conn);
        }
    }

    // Get All
    public List<Employee> getAll() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Employee> ls = new ArrayList<Employee>();
        try {
            DatabaseService db = DatabaseService.getInstance();
            conn = db.getConnection();
            pstmt = conn.prepareStatement(SELECTALL_QUERY);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                Employee employee = new Employee();
                getValues(rs, employee);
                ls.add(employee);
            }
        }
        catch (Throwable th) {
            throw new HrException(ResourceService.getInstance().getMessage("HR-00005"), th);
        } finally {
            DatabaseService.getInstance().closeResources(rs, pstmt, conn);
        }
        return ls;
    }
    
    private void setValues(PreparedStatement ps, Employee employee) throws Exception {
        ps.setString(1, employee.getFirstName());
        ps.setString(2, employee.getMiddleName());
        ps.setString(3, employee.getLastName());
        ps.setString(4, employee.getFatherName());
        ps.setDate(5, new java.sql.Date(employee.getDob().getTime()));
        ps.setObject(6, employee.getGender(),Types.OTHER);
        ps.setObject(7, employee.getMaritalStatus(),Types.OTHER);
        ps.setString(8, employee.getUid());
        ps.setString(9, employee.getEmail());
        ps.setString(10, employee.getPhone());
        ps.setString(11, employee.getBloodGroup());
        ps.setDate(12, new java.sql.Date(employee.getDoj().getTime()));
        ps.setString(13, employee.getAddressLine1());
        ps.setString(14, employee.getAddressLine2());
        ps.setString(15, employee.getCity());
        ps.setString(16, employee.getState());
        ps.setString(17, employee.getPostalCode());
        ps.setString(18, employee.getCountry());
        ps.setString(19, employee.getTitle());
        ps.setBytes(20, employee.getResume());
        ps.setBytes(21, employee.getPhoto());
        ps.setObject(22, employee.getEmployeeStatus(),Types.OTHER);
    }

    private Employee getValues(ResultSet rs, Employee employee) throws Exception {
        employee.setFirstName(rs.getString("first_name"));
        employee.setMiddleName(rs.getString("middle_name"));
        employee.setLastName(rs.getString("last_name"));
        employee.setFatherName(rs.getString("father_name"));
        employee.setDob(rs.getDate("dob"));
        employee.setGender(Gender.valueOf(rs.getString("gender")));
        employee.setMaritalStatus(MaritalStatus.valueOf(rs.getString("marital_status")));
        employee.setUid(rs.getString("uid"));
        employee.setEmail(rs.getString("email"));
        employee.setPhone(rs.getString("phone"));
        employee.setBloodGroup(rs.getString("blood_group"));
        employee.setDoj(rs.getDate("doj"));
        employee.setAddressLine1(rs.getString("address_line1"));
        employee.setAddressLine2(rs.getString("address_line2"));
        employee.setCity(rs.getString("city"));
        employee.setState(rs.getString("state"));
        employee.setPostalCode(rs.getString("postal_code"));
        employee.setCountry(rs.getString("country"));
        employee.setTitle(rs.getString("title"));
        employee.setResume(rs.getBytes("resume"));
        employee.setPhoto(rs.getBytes("photo"));
        employee.setEmployeeStatus(EmployeeStatus.valueOf(rs.getString("status")));
        return employee;
    }
    
    
    private final String INSERT_QUERY = "INSERT INTO employee(first_name, middle_name, last_name, father_name, dob, gender, marital_status, uid, email, phone, blood_group, doj, address_line1, address_line2, city, state, postal_code, country, title, resume, photo, status)VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String SELECT_QUERY = "SELECT emp_id, first_name, middle_name, last_name, father_name, dob, gender, marital_status, uid, email, phone, blood_group, doj, address_line1, address_line2, city, state, postal_code, country, title, resume, photo, status FROM employee where emp_id=";
    private final String UPDATE_QUERY = "UPDATE employee SET first_name=?, middle_name=?, last_name=?, father_name=?, dob=?, gender=?, marital_status=?, uid=?, email=?, phone=?, blood_group=?, doj=?, address_line1=?, address_line2=?, city=?, state=?, postal_code=?, country=?, title=?, resume=?, photo=?, status=? WHERE emp_id =";
    private final String DELETE_QUERY = "delete from employee where emp_id=";
    private final String SELECTALL_QUERY = "SELECT emp_id, first_name, middle_name, last_name, father_name, dob, gender, marital_status, uid, email, phone, blood_group, doj, address_line1, address_line2, city, state, postal_code, country, title, resume, photo, status FROM employee";
}



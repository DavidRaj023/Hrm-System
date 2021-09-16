package com.infinira.hr.util;

import java.text.SimpleDateFormat;
import org.junit.jupiter.api.TestInfo;
import com.infinira.hr.model.Employee;
import com.infinira.hr.model.EmployeeStatus;
import com.infinira.hr.model.Gender;
import com.infinira.hr.model.MaritalStatus;
import io.restassured.response.Response;

public class AbstractEmployee {
	public static Employee generateModelObj() {
		Employee employee = new Employee();
		employee.setFirstName("David42");
		employee.setMiddleName("E");
		employee.setLastName("Raj");
		employee.setFatherName("Edwin");

		try {
			employee.setDob(new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2000"));
		} catch (Throwable th) {
			throw new RuntimeException("Failed to parse a given DOB", th);
		}
		employee.setGender(Gender.m);
		employee.setMaritalStatus(MaritalStatus.single);
		employee.setUid("8978675645");
		employee.setEmail("benjamindavid023@gmail");
		employee.setPhone("9578821821");
		employee.setBloodGroup("b+");

		try {
			employee.setDoj(new SimpleDateFormat("dd/MM/yyyy").parse("02/10/2001"));
		} catch (Throwable th) {
			throw new RuntimeException("Failed to parse a given DOJ", th);
		}

		employee.setAddressLine1("1/10-4 Periyar Street");
		employee.setAddressLine2("Malaiyappa Nagar");
		employee.setCity("Trichy");
		employee.setState("Tamil Nadu");
		employee.setPostalCode("620010");
		employee.setCountry("India");
		employee.setTitle("Trainee");
		employee.setEmployeeStatus(EmployeeStatus.active);
		return employee;
	}
	
	public static Employee generateModelObj(int empId) {
		Employee employee = generateModelObj();
		employee.setEmpId(empId);
		return employee;
	}

	public void checkStatusCode(Response response) {
		if (response.getStatusCode() != 200) {
			throw new RuntimeException(response.jsonPath().getString("trace"));
		}
	}

	public void validateResponse(Response response) {
		if (response.asString() == null || response.asString().isEmpty()) {
			throw new RuntimeException(INVALID_RESPONSE);
		}
	}
	
	public String getMessage(String key, Object... params) {
		if (key == null || key.isEmpty()) {
            return NULL_KEY;
        }
		return key;
		
	}
	
	public static void beforeAll() {
		System.out.println("This method should be executed before all the test");
	}
	
	public static void afterAll() {
		System.out.println("This method should be execute after all the test");
	}

	public void beforeEach(TestInfo testInfo) {
		System.out.println(testInfo.getTags() + " Test Started");
	}
	
	public void afterEach(TestInfo testInfo) {
		System.out.println(testInfo.getTags() + " Test completed");
	}
	
	protected static final String INVALID_RESPONSE = "Response is empty or null";
	protected static final String HR_URL = (String) HrUtil.hrUrl;
	protected static final String EMPLOYEE_URL = HR_URL+ "Employee/";
	protected static final String GETALL_EMPLOYEES = HR_URL+ "Employees";
	protected static final String UPDATEFAILED_MESSAGE = "Failed to update a employee {0}";
	protected static final String DELETEFAILED_MESSAGE = "Failed to remove an employee: {0}";
	protected static final String GETALLFAILED_MESSAGE = "Failed to read all data";
	protected static final String GETID_FAILED = "Failed to get a id for created employee";
	protected static final String PARSING_FAILED = "Input String cannot be parsed to Integer. {0}";
	protected static final String NULL_KEY = "Key can not be null or Empty";
	protected static final String OS_ARCH = "os.arch";
	protected static final String ARCH_VALUE = ".*64.*";
	protected static final String SYS_USER = "user.name";
	protected static final String OS_NAME = "Linux";

	
	
}

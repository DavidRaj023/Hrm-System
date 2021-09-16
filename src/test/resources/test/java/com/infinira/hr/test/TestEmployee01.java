package com.infinira.hr.test;

import java.text.MessageFormat;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import com.infinira.hr.util.AbstractEmployee;
import com.infinira.hr.util.RestAssuredClient;
import io.restassured.response.Response;
import static org.testng.Assert.assertTrue;


@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
@DisplayNameGeneration(DisplayNameGenerator.IndicativeSentences.class)
@Tag("Test01")
public class TestEmployee01 extends AbstractEmployee {
	
	private ThreadLocal<Integer> employeeId = new ThreadLocal<Integer>();
	
	@Test
	@Order(1)
	@DisplayName("Create")
	@Tag("create")
	public void createEmployee() {
		String employeeJsonData = RestAssuredClient.convertPojoToJson(generateModelObj());
		Response response = RestAssuredClient.post(EMPLOYEE_URL, employeeJsonData);
		
		if (response.getStatusCode() != 200 && response.getStatusCode() != 201) {
			throw new RuntimeException(response.jsonPath().getString("trace"));
		}
		
		try {
			employeeId.set(Integer.parseInt(response.asString()));
			System.out.println(employeeId.get());
		} catch(Throwable th){
			throw new RuntimeException(MessageFormat.format(PARSING_FAILED, employeeId.get()));
		}		
		assertTrue(employeeId.get() > 0, MessageFormat.format(GETID_FAILED, employeeJsonData));
	}

	@Test
	@Order(2)
	@DisplayName("Get")
	@Tag("get")
	public void getEmployee() {
		String restUrl = EMPLOYEE_URL + employeeId.get();
		Response response = RestAssuredClient.get(restUrl);
		checkStatusCode(response);
		validateResponse(response);
		
		assertEquals(generateModelObj().getFirstName(), response.jsonPath().getString("firstName"));
	}

	@Test
	@Order(3)
	@DisplayName("Update")
	@Tag("update")
	public void updateEmployee() {
		String employeeJsonData = RestAssuredClient.convertPojoToJson(generateModelObj(employeeId.get()));
		Response response = RestAssuredClient.put(EMPLOYEE_URL, employeeJsonData);
		checkStatusCode(response);
		
		assertEquals(MessageFormat.format(UPDATEFAILED_MESSAGE, employeeId), response.getStatusCode(), 200);
	}

	@Test
	@Order(4)
	@DisplayName("Delete")
	@Tag("delete")
	public void deleteEmployee() {
		String restUrl = EMPLOYEE_URL + employeeId.get();
		Response response = RestAssuredClient.delete(restUrl);
		checkStatusCode(response);
		
		assertEquals(MessageFormat.format(DELETEFAILED_MESSAGE, employeeId), response.getStatusCode(), 200);
	}

	@Test
	@Order(5)
	@DisplayName("GetAll")
	@Tag("getAll")
	public void getAllEmployees() {
		Response response = RestAssuredClient.get(GETALL_EMPLOYEES);
		checkStatusCode(response);
		validateResponse(response);
		
		assertEquals(GETALLFAILED_MESSAGE, response.getStatusCode(), 200);
		assertTrue(response.jsonPath().getList("id").size() > 0, GETALLFAILED_MESSAGE);
	}
	
	@BeforeEach
	public void beforeEachTest(TestInfo testInfo) {
		beforeEach(testInfo);
	}
	
	@AfterEach
	public void afterEachTest(TestInfo testInfo) {
		afterEach(testInfo);
	}
	
	@BeforeAll
	public void beforeAllTest(TestInfo testInfo) {
		beforeAll();
	}
	
	@AfterAll
	public void afterAllTest(TestInfo testInfo) {
		afterAll();
	}
	
}

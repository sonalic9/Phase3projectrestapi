package RestApi;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class EndToEndTest {
	public static int id;
	Response response;
	List<String> all_names;
	List<Integer> all_ids;
	int Initial_Total_Employees;
	int Total_Employees_End;
	@Test
	public void TC01_getallEmployee() {
		response = Methods.getAllEmployee();
		// System.out.println(response.asString());
		// i) Validate response code is 200

		int actual_response = response.statusCode();

		Assert.assertEquals(actual_response, 200);

		// ii)Validate that number of employees are 3

		JsonPath jobject = response.jsonPath();
		all_ids = jobject.get("id");
		all_names = jobject.get("names");
		Initial_Total_Employees= all_ids.size();
		// Assert.assertEquals(all_ids.size(),28);
	}

	@Test
	public void TC02_postEmployee() {
		response = Methods.createEmployee("pankaj", "McDonald", 10000, "john.pan@gmail.com");
		System.out.println(response.asString());

		// i) Validate response code is 201
		int actual_response = response.statusCode();

		Assert.assertEquals(actual_response, 201);

		JsonPath jobject = response.jsonPath();
		// ii) Validate John is in the response

		String empname = jobject.get("firstName");
		id = jobject.get("id");
		Assert.assertEquals(empname, "pankaj");

		System.out.println("Employeename is" + empname + " " + "emplyee id is " + id);

	}

	@Test
	public void TC03_updateEmployee() {
		response = Methods.update_employee("Tommy", id, "McDonald", 10000, "john.san@gmail.com");
		System.out.println(response.asString());
		int actual_response = response.statusCode();
		Assert.assertEquals(actual_response, 200);
		// JsonPath jobject = response.jsonPath();
		// String empname = jobject.get("firstName");
		// Assert.assertEquals(empname, "Tommy");
	}

	@Test
	public void TC04_getSingleEmployee() {
		response = Methods.getSingleEmployee(id);
		int actual_response = response.statusCode();
		Assert.assertEquals(actual_response, 200);
		JsonPath jobject = response.jsonPath();
		String empname = jobject.get("firstName");

		Assert.assertEquals(empname, "Tommy");

	}

	@Test
	public void TC05_deleteEmployee() {
		response = Methods.deleteEmployee(id);
		int actual_response = response.statusCode();
		Assert.assertEquals(actual_response, 200);

	}

	@Test
	public void TC06_getSingleEmployee() {
		response = Methods.getSingleEmployee(id);
		int actual_response = response.statusCode();
		Assert.assertEquals(actual_response, 400);

	}

	@Test
	public void TC07_allgetEmployee() {
		response = Methods.getAllEmployee();
		System.out.println(response.asString());
		// i) Validate response code is 200

		int actual_response = response.statusCode();
		
		  Assert.assertEquals(actual_response, 200);
		  JsonPath jobject = response.jsonPath();
			all_ids = jobject.get("id");
			all_names = jobject.get("names");
			Total_Employees_End= all_ids.size();
			Assert.assertEquals(Total_Employees_End, Initial_Total_Employees);
		 
	}
}

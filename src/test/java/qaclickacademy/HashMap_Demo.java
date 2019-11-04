package qaclickacademy;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class HashMap_Demo {
	
	@Test
	public void getTest() {
		// TODO Auto-generated method stub
				
				//Create <HashMap> object
				HashMap<String,Object> map=new HashMap<String, Object>();
				map.put("name", "Learn HashMap using Rest API");
				map.put("isbn", "142970279");
				map.put("aisle", "007");
				map.put("author", "Purush");
				
				/*HashMap<String,Object> map1=new HashMap<String, Object>();
				map.put("lat", "23");
				map.put("lng", "86");
				map.put("location", map);*/
		// Base URI or Host
				
				//Task 1 - Grab the Response
				RestAssured.baseURI = "http://216.10.245.166";
				
				Response res=given()
				.header("Content-Type","application/json")
				.body(map)

				.when()
				.post("/Library/Addbook.php")
				.then().assertThat().statusCode(200)
				.extract().response();
				
				String responseString=res.asString();
				System.out.println(responseString);
				
				//Task 2 - Grab the Place ID from the Response
				JsonPath js=new JsonPath(responseString);
				String bookid=js.get("ID");
				System.out.println(bookid);
				
				//Task 3 - Perform Delete operation using Place ID
				given()
				.header("Content-Type","application/json")
				.body("{\r\n" + 
						"    \"ID\":" +"\""+bookid+"\"\r\n" +
						"}")
				.when()
				.post("/Library/DeleteBook.php")
				.then().assertThat().statusCode(200);
				

	}

}

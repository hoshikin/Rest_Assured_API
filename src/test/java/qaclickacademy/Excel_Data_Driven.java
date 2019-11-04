package qaclickacademy;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.Data_Driven_Demo;

public class Excel_Data_Driven {
	
	@Test
	public void getTest() throws IOException {
		// TODO Auto-generated method stub
				
				//Create object of Data_Driven_Demo from Resources package
				Data_Driven_Demo dd=new Data_Driven_Demo();
				ArrayList data=dd.getData("RestAddbook");
				
				//Get data from above ArrayLlist object data and send it down to HashMap
				HashMap<String,Object> map=new HashMap<String, Object>();
				map.put("name", data.get(1));
				map.put("isbn", data.get(2));
				map.put("aisle", data.get(3));
				map.put("author", data.get(4));
				
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


package qaclickacademy;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import qaclickacademy.resources;
import qaclickacademy.payLoad;


public class post_delete_JSON {
	
public Properties prop=new Properties();
	
	@BeforeTest
	public void getData() throws IOException {

		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//env.properties");
		prop.load(fis);
	}
	
	@Test
	public void getTest() {
		// TODO Auto-generated method stub

		// Base URI or Host
				
				//Task 1 - Grab the Response
				RestAssured.baseURI = prop.getProperty("QACLICKURL");
				Response res=given()
				.queryParam("key", prop.getProperty("QACLICKKEY"))
				.body(resources.getResourcesJson()).

				when()
				.post(payLoad.getPayLoad())
				.then().assertThat().statusCode(200).and().contentType(ContentType.JSON)
				.body("status", equalTo("OK"))
				.extract().response();
				
				String responseString=res.asString();
				System.out.println(responseString);
				
				//Task 2 - Grab the Place ID from the Response
				JsonPath js=new JsonPath(responseString);
				String placeid=js.get("place_id");
				System.out.println(placeid);
				
				//Task 3 - Perform Delete operation using Place ID
				given()
				.queryParam("key", prop.getProperty("QACLICKKEY"))
				.body("{\r\n" + 
						"    \"place_id\":" +"\""+placeid+"\"\r\n" +
						"}")
				.when()
				.post("/maps/api/place/delete/json")
				.then().assertThat().statusCode(200).and().contentType(ContentType.JSON)
				.body("status", equalTo("OK"));
				

	}
}

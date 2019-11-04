package qaclickacademy;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.Properties;

public class json_advanced {
	
public Properties prop=new Properties();
	
	@BeforeTest
	public void getData() throws IOException {

		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//env.properties");
		prop.load(fis);
	}
	
	@Test
	public void getTest() {
		// TODO Auto-generated method stub
		
		//Base uRI or Host
		
		RestAssured.baseURI=prop.getProperty("GOOGLEMAPURL");
		
		Response res=given().
		param("key", prop.getProperty("GOOGLEMAPKEY")).
		param("radius","500").
		//Below code is used to enable Logging
		//param("location", prop.getProperty("GOOGLELOCATION")).log().all().
		param("location", prop.getProperty("GOOGLELOCATION")).
		when().
		get("maps/api/place/nearbysearch/json").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		body("results[0].name", equalTo("Sydney")).and().
		body("results[1].user_ratings_total", equalTo(314)).and().
		header("Server","scaffolding on HTTPServer2").
		extract().response();
		
		JsonPath js=reusableMethods.rawToJson(res);
		int count=js.get("results.size()");
		//System.out.println(i);
		for(int i=0; i<count; i++) {
			
			String results=js.get("results["+i+"].name");
			System.out.println(results);
		}
		System.out.println(count);
		
	}
}

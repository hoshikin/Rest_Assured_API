/*package qaclickacademy;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class get_test {
	
	
	@Test
	public void getTest() {
		// TODO Auto-generated method stub
		
		//Base uRI or Host
		
		RestAssured.baseURI="https://maps.googleapis.com";
		
		given().
		param("key","AIzaSyDZxdV2xMWTmLxPBjfoJfMk9ksjyNVA5iY").
		param("radius","500").
		param("location","-33.8670522,151.1957362").
		when().
		get("maps/api/place/nearbysearch/json").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		body("results[0].name", equalTo("Sydney")).and().
		body("results[1].user_ratings_total", equalTo(313)).and().
		header("Server","scaffolding on HTTPServer2");
		
		
	}
}
*/
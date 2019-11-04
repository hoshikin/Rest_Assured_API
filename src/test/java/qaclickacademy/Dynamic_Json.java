/*package qaclickacademy;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import qaclickacademy.payLoad;
import qaclickacademy.resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import qaclickacademy.payLoad;

public class Dynamic_Json {
	
	@Test(dataProvider="BooksProvider")
	public void addBook(String isbn, String aisle) {
		// TODO Auto-generated method stub
		
		//Base uRI or Host
		
		RestAssured.baseURI = "http://216.10.245.166";
		Response res=given()
		.header("Content-Type","application/json")
		.body(payLoad.AddBook(isbn, aisle))
		.when()
		.post("/Library/Addbook.php")
		.then().assertThat().statusCode(200)
		.extract().response();
		
		JsonPath js=reusableMethods.rawToJson(res);
		String id=js.get("ID");
		System.out.println(id);

	}
	
	@DataProvider(name="BooksProvider")
	public Object[][] getData() {
		return new Object[][] { {"comic4", "200"}, {"thriller4", "300"}, {"fiction4", "400"} };
	}

}
*/
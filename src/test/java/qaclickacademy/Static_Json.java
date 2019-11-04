/*package qaclickacademy;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import qaclickacademy.payLoad;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Static_Json {
	
	@Test
	public void addBook() throws IOException {
		// TODO Auto-generated method stub
		
		//Base uRI or Host
		
		RestAssured.baseURI = "http://216.10.245.166";
		Response res=given()
		.header("Content-Type","application/json")
		.body(GenerateStringFromSourceJson("C:\\Users\\Purush\\postdata.json"))
		.when()
		.post("/Library/Addbook.php")
		.then().assertThat().statusCode(200)
		.extract().response();
		
		JsonPath js=reusableMethods.rawToJson(res);
		String id=js.get("ID");
		System.out.println(id);

	}
	

	public static String GenerateStringFromSourceJson(String path) throws IOException {
		
		return new String(Files.readAllBytes(Paths.get(path)));

	}

}
*/
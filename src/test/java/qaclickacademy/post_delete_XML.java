package qaclickacademy;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

//import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import qaclickacademy.resources;
import qaclickacademy.payLoad;


public class post_delete_XML {
	
public Properties prop=new Properties();
	
	@BeforeTest
	public void getData() throws IOException {

		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//env.properties");
		prop.load(fis);
	}
	
	@Test
	public void getTest() throws IOException {
		// TODO Auto-generated method stub

		// Base URI or Host
				
				//Task 1 - Grab the Response
				String postdata=GenerateStringFromSourceXML("C:\\Users\\Purush\\postdata.xml");
				RestAssured.baseURI = prop.getProperty("QACLICKURL");
				Response res=given()
				.queryParam("key", prop.getProperty("QACLICKKEY"))
				.body(postdata).

				when()
				.post(payLoad.getPayLoadXml())
				.then().assertThat().statusCode(200).and().contentType(ContentType.XML)
				.extract().response();
				
				String responseString=res.asString();
				System.out.println(responseString);
				
				//Task 2 - Grab the Place ID from the Response
				//XmlPath x=new XmlPath(responseString);
				XmlPath x=new XmlPath(responseString);
				String placeid=x.get("response.place_id");
				System.out.println(placeid);
				
				//Task 3 - Perform Delete operation using Place ID
				given()
				.queryParam("key", "qaclick123")
				.body("{\r\n" + 
						"    \"place_id\":" +"\""+placeid+"\"\r\n" +
						"}")
				.when()
				.post("/maps/api/place/delete/xml")
				.then().assertThat().statusCode(200); 
				

	}
	
public static String GenerateStringFromSourceXML(String path) throws IOException {
		
		return new String(Files.readAllBytes(Paths.get(path)));

	}
}

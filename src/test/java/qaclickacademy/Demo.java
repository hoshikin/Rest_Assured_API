package qaclickacademy;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Demo {

public Properties prop=new Properties();
	
	@BeforeTest
	public void getData() throws IOException {

		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//env.properties");
		prop.load(fis);
	}
		
		@Test
		public void test() {
			// TODO Auto-generated method stub
			
			//Base uRI or Host
			
			RestAssured.baseURI= prop.getProperty("GOOGLEMAPURL");
			
			given().
					param("location", prop.getProperty("GOOGLELOCATION")).
					param("radius","500").
					param("key", prop.getProperty("AIzaSyDZxdV2xMWTmLxPBjfoJfMk9ksjyNVA5iY")).
					when().
					get("maps/api/place/nearbysearch/json").
					then().assertThat().statusCode(200);

		}

	}



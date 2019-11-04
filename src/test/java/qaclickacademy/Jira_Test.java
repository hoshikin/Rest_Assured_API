package qaclickacademy;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Jira_Test {
	
public Properties prop=new Properties();
	
	@BeforeTest
	public void getData() throws IOException {

		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//env.properties");
		prop.load(fis);
	}
	
	@Test
	public void getJiraTest() throws InterruptedException {
		// TODO Auto-generated method stub
		
		System.out.println("JIRA TEST - PENDING");
		System.out.println(prop.getProperty("JIRAUSERNAME"));
		System.out.println(prop.getProperty("PASSWORD_JIRA"));
		/*System.setProperty("webdriver.chrome.driver", "C://Users//Purush//Downloads//chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// code to get the code value to be passed to Rest API
		driver.get(
				"https://id.atlassian.com/login");
		
		//Thread.sleep(5000);
		
		driver.findElement(By.cssSelector("[type='email']")).sendKeys(prop.getProperty("USERNAME"));
		driver.findElement(By.cssSelector("[type='email']")).sendKeys(Keys.ENTER);

		Thread.sleep(5000);

		driver.findElement(By.cssSelector("[type='password']")).sendKeys(prop.getProperty("PASSWORD_JIRA"));
		driver.findElement(By.cssSelector("[type='password']")).sendKeys(Keys.ENTER);

		Thread.sleep(5000);

		String url = driver.getCurrentUrl();
		System.out.println("My URL: "+url);
		driver.quit();

		//String partialcode = url.split("code=")[1];
		//String code = partialcode.split("&scope")[0];
		String code = url.split("token=")[1];
		System.out.println("My Code :"+code);
		
		
		String response = given().urlEncodingEnabled(false)
				.queryParams("token", code)
				.queryParams("oauth_consumer_key","PXcDwXmL2ol2eyJ9dbF4sQyrwDWKLicY")
				.queryParams("oauth_signature_method", "HMAC-SHA1")
				.queryParams("oauth_signature", "W7ZFEuORNQTIp2AjCCpa1HNfWoQ")
				
				// .queryParam("scope",
				// "email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email")
				
				//.when().log().all()
				.when()
				.post("https://purushy2k.atlassian.net/jira/software/projects/RA/boards/1/backlog").asString();

				//System.out.println(response);

				JsonPath js1 = new JsonPath(response);
				String reqid = js1.getString("REQUEST ID");
				//System.out.println("Request ID: "+reqid);
				
				String r2 = given().contentType("application/json").
				queryParams("access_token", accessToken).expect().defaultParser(Parser.JSON)
				.when()
				.get("https://rahulshettyacademy.com/getCourse.php")
				.asString();
				
				System.out.println(r2);
*/		
}
	
}

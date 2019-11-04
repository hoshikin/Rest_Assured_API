package qaclickacademy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import googleAPI.Api;
import googleAPI.WebAutomation;
import googleAPI.getCourse;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class OAuth_Demo<web> {
	
	private static Logger log=LogManager.getLogger(OAuth_Demo.class.getName());
	public Properties prop=new Properties();
	
	@BeforeTest
	public void getData() throws IOException {
		
		//Below code is to set the path of env.properties variables into your current project directory
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//env.properties");
		prop.load(fis);
	}

	@Test
	public void getOAuthData() throws InterruptedException {
		// TODO Auto-generated method stub
		
		String [] coursetitles = {"Selenium Webdriver Java","Cypress","Protractor"};

		System.setProperty("webdriver.chrome.driver", "C://Users//Purush//Downloads//chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// code to get the code value to be passed to Rest API
		driver.get(prop.getProperty("GOOGLEURL"));
		
		//Below code logs the info into Log4j output file
		log.info("User Name: "+prop.getProperty("GOOGLEUSERNAME"));
		
		driver.findElement(By.cssSelector("[type='email']")).sendKeys(prop.getProperty("GOOGLEUSERNAME"));
		driver.findElement(By.cssSelector("[type='email']")).sendKeys(Keys.ENTER);

		Thread.sleep(5000);

		driver.findElement(By.cssSelector("[type='password']")).sendKeys(prop.getProperty("GOOGLEPASSWORD"));
		driver.findElement(By.cssSelector("[type='password']")).sendKeys(Keys.ENTER);

		Thread.sleep(5000);

		String url = driver.getCurrentUrl();
		//Below code logs the info into Log4j output file
		log.info(url);
		driver.quit();

		String partialcode = url.split("code=")[1];
		String code = partialcode.split("&scope")[0];
		//System.out.println(code);

		String response = given().urlEncodingEnabled(false)
						.queryParams("code", code)
						.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
						.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
						.queryParams("grant_type", "authorization_code")
						.queryParams("state", "verifyfjdss")
						.queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")
						.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
						
						// .queryParam("scope",
						// "email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email")
						
						.when().log().all()
						.post("https://www.googleapis.com/oauth2/v4/token").asString();

						// System.out.println(response);
		
						JsonPath jsonPath = new JsonPath(response);
						String accessToken = jsonPath.getString("access_token");
						//Below code logs the info into Log4j output file
						log.info("Access Token: "+accessToken);
						
						//Below code calls the pojo Class file getCourses.java (which  Serializes and Deserealizes Java code to JSon)
						getCourse gc = given().
						queryParams("access_token", accessToken).contentType("application/json").expect().defaultParser(Parser.JSON).
						when().
						get("https://rahulshettyacademy.com/getCourse.php")
						.as(getCourse.class);
						
						//Below code logs the info into Log4j output file
						log.info(gc.getUrl());
						log.info(gc.getInstructor());
						log.info(gc.getLinkedIn());
						
						//code to get List of Courses
						List <Api> apicourses=gc.getCourses().getApi();
						for(int i=0; i<apicourses.size(); i++) {
							if(apicourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
								System.out.println(apicourses.get(i).getPrice());
							}
						}
						
						ArrayList <String> a=new ArrayList<String>();
						List <WebAutomation> w=gc.getCourses().getWebAutomation();
						for(int i=0; i<w.size(); i++) {
							a.add(w.get(i).getCourseTitle());
						}
						System.out.println(a);
						
						List <String> expectedList= Arrays.asList(coursetitles);
						Assert.assertTrue(a.equals(expectedList));
						System.out.println("COURSE TITLE - TEST PASSED");
	}

}

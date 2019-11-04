package qaclickacademy;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class reusableMethods {
	
	public static XmlPath rawToXml(Response r) {
		String responseString=r.asString();
		XmlPath x=new XmlPath(responseString);
		return x;

	}
	
	public static JsonPath rawToJson(Response r) {
		String responseString=r.asString();
		JsonPath x=new JsonPath(responseString);
		return x;

		
	}
	
	public static String jiraSessionId() {
		
		RestAssured.baseURI = "https://purushy2k.atlassian.net";
		Response res=given().
		header("Content-Type","application/json").
		body("{\r\n" + 
				"    \"self\": \"https://purushy2k.atlassian.net/rest/api/latest/user?username=admin\",\r\n" + 
				"    \"name\": \"admin\"\r\n" + 
				"}").
		when().
		post("rest/auth/1/session").
		then().assertThat().statusCode(200).
		extract().response();
		
		JsonPath js=reusableMethods.rawToJson(res);
		String sessionid=js.get("session.value");
		return sessionid;
	}

}

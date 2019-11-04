package qaclickacademy;

public class payLoad {
	
	public static String getPayLoad() {
		String str="/maps/api/place/add/json";
		return str;
	}
	
	public static String getPayLoadXml() {
		String str1="/maps/api/place/add/xml";
		return str1;
	}
	
	public static String AddBook(String isbn, String aisle) {
		String str3="{\r\n" + 
				"   \"name\": \"Learn Appium Automation with Java\",\r\n" + 
				"   \"isbn\": \""+isbn+"\",\r\n" + 
				"   \"aisle\": \""+aisle+"\",\r\n" + 
				"   \"author\": \"John foe\"\r\n" + 
				"}";
		return str3;
	}
}

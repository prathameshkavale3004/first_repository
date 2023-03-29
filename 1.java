import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class FieldLocator //DO NOT Change the class Name
{
	JSONArray address;
	public static WebDriver driver;
	
	public static WebDriver createDriver() //DO NOT change the method signature
	{
	   driver=DriverSetup.getWebDriver();
	   driver.get("http://webapps.tekstac.com/AddressBook/");
	   return driver;
	   //Implement code to create Driver from DriverSetup and set to 'static' driver variable. Return the driver.
	}
	
	public JSONArray ReadFile(String fileName)   //DO NOT change the method signature
    {
        try{
            JSONParser parser=new JSONParser();
            FileReader reader=new FileReader(fileName);
            Object obj=parser.parse(reader);
            JSONObject obj1=(JSONObject)obj;
            JSONArray address1=(JSONArray)obj1.get("Addresses");
            address=address1;
        }catch(Exception e){
            System.out.println(e);
        }
            return address;
            //Implement code to read and return addresses as JSON array 
    }
    
    public String getNickName(int id) {
	
        JSONObject nickname=(JSONObject)address.get(id);
        String nname=(String)nickname.get("NickName");
        return nname;
        //Implement code to return nickname from address
    }

	public String getContactName(int id) {
		JSONObject contactname=(JSONObject)address.get(id);
		String cname=(String)contactname.get("ContactName");
		return cname;
		//Implement code to return contactname from address
	}

	public String getCity(int id) {
		JSONObject city=(JSONObject)address.get(id);
		String cityname=(String)city.get("City");
		return cityname;
		//Implement code to return city from address
	}

	public String getType(int id) {
		JSONObject type=(JSONObject)address.get(id);
		String typ=(String)type.get("Type");
		return typ;
		//Implement code to return type from address
	}

	public String getMessage() {
		driver.findElement(By.id("nickname")).sendKeys(getNickName(1));
		driver.findElement(By.id("contact")).sendKeys(getContactName(1));
		driver.findElement(By.id("company")).sendKeys("Cricket");
		driver.findElement(By.id("city")).sendKeys(getCity(1));
		driver.findElement(By.id("country")).sendKeys("India");
		driver.findElement(By.id("type")).sendKeys(getType(1));
		driver.findElement(By.id("add")).click();
		String msg="Registered Successfully";
		return msg;
		    //Implement code to submit form with values got from json and return a success message.
	}

	public static void main(String[] args) {
		FieldLocator pagLocator=new FieldLocator();
	    FieldLocator.createDriver();
	    pagLocator.ReadFile("AddressBook.json");
	    pagLocator.getMessage();
	    FieldLocator.driver.close();//Implement the required code
	    //Close the driver
	}

}  

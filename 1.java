import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;

import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class UserDetails {

    //use the below variable declarations
	public static WebDriver driver;
	public static JSONArray details;
	
	public static WebDriver createDriver() {    //Do not change the class name
		driver=DriverSetup.getWebDriver();
		driver.get("http://webapps.tekstac.com/FormRegistration/UserRegistration.html");
		return driver;
		/* Create a driver. Assign it to static variable 'driver' and return it */
	    /* navigate to 'http://webapps.tekstac.com/FormRegistration/UserRegistration.html'  */
	}
	
	public JSONArray readFile(String fileName) {   //Do not change the method signature
	   //Retrieve the json file name passed as 'fileName' parameter. Parse the json, store it in 'details' array and return it
	   try{
	       JSONParser parser=new JSONParser();
	       FileReader reader=new FileReader(fileName);
	       Object obj=parser.parse(reader);
	       JSONObject jobj=(JSONObject)obj;
	       details=(JSONArray)jobj.get("Userdetails");
	   }catch(Exception e)
	   {
	}finally{
	    }
	    return details;
	}

	public String getName(int id) {   //Do not change the method signature
		//Parse the json to get 'Name' value. Return it.
        //Parameter 'id' is the id in the Registration.json
        JSONObject name=(JSONObject)details.get(id);
        String nname=(String)name.get("Name");
        return nname;
	}

	public String getEmail(int id) {   //Do not change the method signature
		//Parse the json to get 'Email' value. Return it.
        //Parameter 'id' is the id in the Registration.json
        JSONObject email=(JSONObject)details.get(id);
        String eemail=(String)email.get("Email");
        return eemail;
	}

	
	
	public String getPhone(int id) {     //Do not change the method signature   
		//Parse the json to get 'Phone' value. Return it.
        //Parameter 'id' is the id in the Registration.json
        JSONObject phone=(JSONObject)details.get(id);
        String pphone=(String)phone.get("Phone");
        return pphone;
	}

	
	public String getAge(int id) {         //Do not change the method signature
		JSONObject age=(JSONObject)details.get(id);
		String aage=(String)age.get("Age");
		return aage;
		//Parse the json to get 'Age' value. Return it.
        //Parameter 'id' is the id in the Registration.json
	}

	
	public String getPassword(int id) {      //Do not change the method signature
	//Parse the json to get 'Password' value. Return it.
        //Parameter 'id' is the id in the Registration.json
        JSONObject pass=(JSONObject)details.get(id);
        String ppass=(String)pass.get("Password");
        return ppass;
	}

	
	public String getHobbies(int id) {        //Do not change the method signature
		//Parse the json to get 'Hobbies' value. Return it.
        //Parameter 'id' is the id in the Registration.json
        JSONObject hobbies=(JSONObject)details.get(id);
        String hhobbies=(String)hobbies.get("Hobbies");
        return hhobbies;
	}

	
	public String getGender(int id) {          //Do not change the method signature
		//Parse the json to get 'Gender' value. Return it.
        //Parameter 'id' is the id in the Registration.json
        JSONObject gender=(JSONObject)details.get(id);
        String ggender=(String)gender.get("Gender");
        return ggender;
	}

	public String getCity(int id) {          //Do not change the method signature
		//Parse the json to get 'City' value. Return it.
        //Parameter 'id' is the id in the Registration.json
        JSONObject city=(JSONObject)details.get(id);
        String ccity=(String)city.get("City");
        return ccity;
	}

	public String getAddress(int id) {         //Do not change the method signature
		//Parse the json to get 'Address' value. Return it.
        //Parameter 'id' is the id in the Registration.json
        JSONObject address=(JSONObject)details.get(id);
        String aaddress=(String)address.get("Address");
        return aaddress;
	}

	public String getMessage() {           //Do not change the method signature
		//Find the web elements in the page. Assign the respective values from json to the form.
        //Submit the form 
        //Locate the 'Registered Successfully' message and return it
        Select cCity=new Select(driver.findElement(By.id("city")));
        driver.findElement(By.xpath("//input[@type='reset']")).click();
        driver.findElement(By.id("uname")).sendKeys(getName(1));
        driver.findElement(By.id("uemail")).sendKeys(getEmail(1));
        driver.findElement(By.id("phone")).sendKeys(getPhone(1));
        driver.findElement(By.id("age")).sendKeys(getAge(1));
        driver.findElement(By.id("pass")).sendKeys(getPassword(1));
        if(getHobbies(1).equals("Cricket"))
        {
            driver.findElement(By.id("option1")).click();
            
        }
        else 
        {
            driver.findElement(By.id("option2")).click();
        }
        if(getGender(1).equals("Male"))
        {
            driver.findElement(By.xpath("//input[@id='male']/parent::label")).click();
        }
        else{
            driver.findElement(By.xpath("//input[@id='female']/parent::label")).click();
        }
        cCity.selectByVisibleText(getCity(1));
        driver.findElement(By.name("address")).sendKeys(getAddress(1));
        driver.findElement(By.id("submit")).click();
        String msg=driver.findElement(By.xpath("/html/body/h2")).getText();
        return msg;
	}

	public static void main(String[] args) {
		UserDetails fieldLocator = new UserDetails();
		//Add required code
		createDriver();
		fieldLocator.readFile("Registration.json");
		String str=fieldLocator.getMessage();
		System.out.println(str);
		driver.close();
	}

}

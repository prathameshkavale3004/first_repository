import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;



public class UserDetails{      //Do not change the class name
    
    //use this variable declaration
    public static WebDriver driver;
    public static Document doc;
    public static XPath xpath;
    
    public static WebDriver createDriver(){   //Do not change the method signature
	
	    /* Create a driver. Assign it to static variable 'driver' and return it */
	    /* navigate to 'http://webapps.tekstac.com/FormRegistration/UserRegistration.html'  */
	    
	    driver=DriverSetup.getWebDriver();
	    driver.get("http://webapps.tekstac.com/FormRegistration/UserRegistration.html");
	    
	    return driver;
	}
    
    public XPath ReadFile(String fileName,String id){      //Do not change the method signature
        //Retrieve the xml file name passed as 'fileName' parameter. Parse the xml and return the xPath
        //Parameter 'id' is the id in the Userdetails.xml
        try{
            DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
            DocumentBuilder builder=factory.newDocumentBuilder();
            doc=builder.parse(fileName);
        } catch(Exception e){
           System.out.println(e); 
        }
        doc.getDocumentElement().normalize();
        XPathFactory xPathFactory=XPathFactory.newInstance();
        xpath=xPathFactory.newXPath();
        return xpath;
    }
    
    public  Node getName(int id){   //Do not change the method signature
        //Parse the xml to get 'Name' element. Return its node
        //Parameter 'id' is the id in the Userdetails.xml
        Node node=null;
        try{
            node=(Node)xpath.compile("//User[@id="+id+"]/Name").evaluate(doc,XPathConstants.NODE);
            
        }catch(Exception e)
        {
            System.out.println(e);
        }
        return node;
    }
    
    public  Node getEmail(int id){   //Do not change the method signature
        //Parse the xml to get 'Email' element. Return its node
        //Parameter 'id' is the id in the Userdetails.xml
        Node node=null;
        try{
            node=(Node)xpath.compile("//User[@id="+id+"]/Email").evaluate(doc,XPathConstants.NODE);
        
            }catch(Exception e)
            {
                System.out.println(e);
            }
            return node;
    }
    
    public  Node getPhone(int id){    //Do not change the method signature
        //Parse the xml to get 'Phone' element. Return its node
        //Parameter 'id' is the id in the Userdetails.xml
        Node node=null;
        try{
            node=(Node)xpath.compile("//User[@id="+id+"]/Phone").evaluate(doc,XPathConstants.NODE);
            
        }catch(Exception e)
        {
            System.out.println(e);
        }
        return node;
    }
    
    public  Node getAge(int id){        //Do not change the method signature
        //Parse the xml to get 'Age' element. Return its node
        //Parameter 'id' is the id in the Userdetails.xml
        Node node=null;
        try{
            node=(Node)xpath.compile("//User[@id="+id+"]/Age").evaluate(doc,XPathConstants.NODE);
            
        }catch(Exception e)
        {
            System.out.println(e);
        }
        return node;
    }
    
    
    public  Node getPassword(int id){    //Do not change the method signature
        //Parse the xml to get 'Password' element. Return its node
        //Parameter 'id' is the id in the Userdetails.xml
        Node node=null;
        try{
            node=(Node)xpath.compile("//User[@id="+id+"]/Password").evaluate(doc,XPathConstants.NODE);
            
        } catch(Exception e)
        {
            System.out.println(e);
        }
        return node;
    }
    
    
    
    public  Node getHobbies(int id){    //Do not change the method signature
        //Parse the xml to get 'Hobbies' element. Return its node
        //Parameter 'id' is the id in the Userdetails.xml
        Node node=null;
        try{
            node=(Node)xpath.compile("//User[@id="+id+"]/Hobbies").evaluate(doc,XPathConstants.NODE);
            
        }catch(Exception e)
        {
            System.out.println(e);
        }
        return node;
    }
    
    
    public  Node getGender(int id){    //Do not change the method signature
        //Parse the xml to get 'Gender' element. Return its node
        //Parameter 'id' is the id in the Userdetails.xml
        Node node=null;
        try{
            node=(Node)xpath.compile("//User[@id="+id+"]/Gender").evaluate(doc,XPathConstants.NODE);
            
        }catch(Exception e)
        {
            System.out.println(e);
        }
        return node;
    }
    
    
    public  Node getCity(int id){        //Do not change the method signature
        //Parse the xml to get 'City' element. Return its node
        //Parameter 'id' is the id in the Userdetails.xml
        Node node=null;
        try{
            node=(Node)xpath.compile("//User[@id="+id+"]/City").evaluate(doc,XPathConstants.NODE);
            
        }catch(Exception e)
        {
            System.out.println(e);
        }
        return node;
    }
    
    
    public  Node getAddress(int id){       //Do not change the method signature
       //Parse the xml to get 'Address' element. Return its node
        //Parameter 'id' is the id in the Userdetails.xml
        Node node=null;
        try{
            node=(Node)xpath.compile("//User[@id="+id+"]/Address").evaluate(doc,XPathConstants.NODE);
            
        }catch(Exception e)
        {
            System.out.println(e);
        }
        return node;
    }
    
    
      public  String  getMessage(){  //Do not change the method signature
        
        //Find the web elements in the page. Assign the respective values from xml to the form.
        //Submit the form 
        //Locate the 'Registered Successfully' message and return it
        driver.findElement(By.id("uname")).sendKeys(getName(1).getTextContent());
        driver.findElement(By.id("uemail")).sendKeys(getEmail(1).getTextContent());
        driver.findElement(By.id("phone")).sendKeys(getPhone(1).getTextContent());
        driver.findElement(By.id("age")).sendKeys(getAge(1).getTextContent());
        driver.findElement(By.id("pass")).sendKeys(getPassword(1).getTextContent());
        driver.findElement(By.xpath("//input[@id='option1']/parent::label")).click();
        driver.findElement(By.xpath("//input[@id='male']/parent::label")).click();
        Select city=new Select(driver.findElement(By.id("city")));
        city.selectByVisibleText(getCity(1).getTextContent());
        driver.findElement(By.name("address")).sendKeys(getAddress(1).getTextContent());
        driver.findElement(By.id("submit")).click();
        String msg=driver.findElement(By.tagName("h2")).getText();
        return msg;
        
      }

    public static void main(String[] args){
	    UserDetails pagLocator=new UserDetails();
	    createDriver();
	    pagLocator.ReadFile("UserDetails.xml","1");
	    System.out.println(pagLocator.getMessage());
	    driver.close();
	    //Add required code here
	} 
}

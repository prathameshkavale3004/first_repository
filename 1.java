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



public class ShipmentDetail{      //Do not change the class name
    
    //use this variable declaration
    public static WebDriver driver;
    public Document doc;
    public XPath xpath;
    
    
    public static WebDriver createDriver(){   //Do not change the method signature
	
	    /* Create a driver. Assign it to static variable 'driver' and return it */
	    /* navigate to 'https://webapps.tekstac.com/Handling_Reg_Expression/'  */
	    driver=DriverSetup.getWebDriver();
	    driver.get("https://webapps.tekstac.com/Handling_Reg_Expression/");
	    return driver;
	}
    
    public XPath ReadFile(String fileName,String id){      //Do not change the method signature
        //Retrieve the xml file name passed as 'fileName' parameter. Parse the xml and return the xPath
        //Parameter 'id' is the id in the ShipmentDetail.xml
        try{
            String fname,lname,uname,pass=null;
            String projectPath=System.getProperty("user.dir");
            System.out.println(projectPath);
            
            File fxmlFile=new File(projectPath+File.separator+fileName);
            System.out.println(projectPath+File.separator+fileName);
            
            DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
            DocumentBuilder builder=factory.newDocumentBuilder();
            doc=builder.parse(fxmlFile);
            
            XPathFactory xPathFactory=XPathFactory.newInstance();
            xpath=xPathFactory.newXPath();
        } catch(Exception e)
        {
            e.printStackTrace();
        }
        return xpath;
    }
    
    public  Node getUserId(int id){   //Do not change the method signature
        //Parse the xml to get 'UserId' element. Return its node
        //Parameter 'id' is the id in the ShipmentDetail.xml
        String UuseridEx=String.format("/ShipmentDetails/ShipmentDetail[@id='"+id+"']/UserId");
        Node node1=null;
        try{
            node1=(Node)xpath.compile(UuseridEx).evaluate(doc,XPathConstants.NODE);
        }catch(Exception e){}
        String Uuserid=node1!=null?(node1.getTextContent()):"cannot read the test case xml file ";
        return node1;
        }
    
    
    
    
      public  String  getMessage(){  //Do not change the method signature
        
        //Find the web elements in the page. Assign the respective values from xml to the form.
        //Submit the form 
        //Locate the 'Name : Shamili
        //Shipment Id : SHIP1236
        //Phone Number : 9224158877
        //E-mail: shamili93@gamil.com' message and return it
        driver.findElement(By.name("userId")).sendKeys(getUserId(1).getTextContent());
        driver.findElement(By.id("track")).click();
        String message=driver.findElement(By.xpath("//*[@id=\"result\"]")).getText();
        return message;
        
      }

    public static void main(String[] args){
	   ShipmentDetail pagLocator=new ShipmentDetail();
	   pagLocator.createDriver();
	   pagLocator.ReadFile("ShipmentDetail.xml","1");
	   String message=pagLocator.getMessage();
	   System.out.println("Message: " +message);
	   driver.close();
	   //Add required code here
	} 
}

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



public class CustomerRegistration{      //Do not change the class name
    
    //use this variable declaration
    public static WebDriver driver;
    public XPath xPath;
    public Document doc;
    
    public static WebDriver createDriver(){   //Do not change the method signature
	
	    /* Create a driver. Assign it to static variable 'driver' and return it */
	    /* navigate to 'https://webapps.tekstac.com/CustomerRegistration_4284/'  */
	    driver=DriverSetup.getWebDriver();
	    driver.get("https://webapps.tekstac.com/CustomerRegistration_4284");
	    return driver;
	    
	}
    
    public XPath ReadFile(String fileName,String id){      //Do not change the method signature
        //Retrieve the xml file name passed as 'fileName' parameter. Parse the xml and return the xPath
        //Parameter 'id' is the id in the CustomerRegistration.xml
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
            xPath=xPathFactory.newXPath();
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return xPath;
    }
    
    public  Node getName(int id){   //Do not change the method signature
        //Parse the xml to get 'Name' element. Return its node
        //Parameter 'id' is the id in the CustomerRegistration.xml
        String UnameEx=String.format("/CustomerDetails/Customer[@id='"+id+"']/Name");
        Node node1=null;
        try{
            node1=(Node)xPath.compile(UnameEx).evaluate(doc,XPathConstants.NODE);
            
        }catch(Exception e)
        {
            
        }
        String uname=node1!=null?(node1.getTextContent()):"cannot read the case xml file";
        return node1;
    }
    
    public  Node getPhone(int id){    //Do not change the method signature
        //Parse the xml to get 'Phone' element. Return its node
        //Parameter 'id' is the id in the CustomerRegistration.xml
        String UphoneEx=String.format("/CustomerDetails/Customer[@id='"+id+"']/Phone");
        Node node1=null;
        try{
            node1=(Node)xPath.compile(UphoneEx).evaluate(doc,XPathConstants.NODE);
            
        }catch(Exception e)
        {
    }
    String uphone=node1!=null?(node1.getTextContent()):"cannot read the case xml file";
    return node1;
    }
    
    public  Node getDOB(int id){        //Do not change the method signature
        //Parse the xml to get 'DOB' element. Return its node
        //Parameter 'id' is the id in the CustomerRegistration.xml
        String UDOBEX=String.format("/CustomerDetails/Customer[@id='"+id+"']/DOB");
        Node node1=null;
        try{
            node1=(Node)xPath.compile(UDOBEX).evaluate(doc,XPathConstants.NODE);
            
        }catch(Exception e)
        {
            
        }
        String udob=node1!=null?(node1.getTextContent()):"cannot read the case xml file";
        return node1;
    }
    
    
    public  Node getGender(int id){    //Do not change the method signature
        //Parse the xml to get 'Gender' element. Return its node
        //Parameter 'id' is the id in the CustomerRegistration.xml
        String UgenderEx=String.format("/CustomerDetails/Customer[@id='"+id+"']/Gender");
        Node node1=null;
        try{
            node1=(Node)xPath.compile(UgenderEx).evaluate(doc,XPathConstants.NODE);
            
        }catch(Exception e)
        {
            
        }
        String ugender=node1!=null?(node1.getTextContent()):"cannot read the case xml file";
        return node1;
    }
    
    
    public  Node getAddress(int id){       //Do not change the method signature
       //Parse the xml to get 'Address' element. Return its node
        //Parameter 'id' is the id in the CustomerRegistration.xml
        String UaddrEx=String.format("/CustomerDetails/Customer[@id='"+id+"']/Address");
        Node node1=null;
        try{
            node1=(Node)xPath.compile(UaddrEx).evaluate(doc,XPathConstants.NODE);
            
        }catch(Exception e)
        {
            
        }
        String uaddr=node1!=null?(node1.getTextContent()):"cannot read the case xml file";
        return node1;
    }
    
    
      public  String  getMessage(){  //Do not change the method signature
        
        //Find the web elements in the page. Assign the respective values from xml to the form.
        //Submit the form 
        //Locate the 'Registered Successfully' message and return it
        driver.findElement(By.id("name")).sendKeys(getName(1).getTextContent());
        driver.findElement(By.id("mobile")).sendKeys(getPhone(1).getTextContent());
        driver.findElement(By.id("dob")).sendKeys(getDOB(1).getTextContent());
        
        WebElement radio1=driver.findElement(By.id("female"));
        WebElement radio2=driver.findElement(By.id("male"));
        radio2.click();
        
        driver.findElement(By.id("address")).sendKeys(getAddress(1).getTextContent());
        driver.findElement(By.id("register")).click();
        String message=driver.findElement(By.xpath("//*[@id=\"errorMessage\"]")).getText();
        return message;
        
        
      }

    public static void main(String[] args){
	    CustomerRegistration pagLocator=new CustomerRegistration();
	    //Add required code here
	    pagLocator.createDriver();
	    pagLocator.ReadFile("CustomerRegistration.xml","1");
	    String message=pagLocator.getMessage();
	    driver.close();
	} 
}

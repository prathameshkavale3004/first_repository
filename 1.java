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



public class DiscountCalculator{      //Do not change the class name
    
    //use this variable declaration
    public static WebDriver driver;
    public XPath xpath;
    public Document doc;
    
    
    public static WebDriver createDriver(){   //Do not change the method signature
	
	    /* Create a driver. Assign it to static variable 'driver' and return it */
	    /* navigate to 'https://webapps.tekstac.com/CompanyOffersDiscount/'  */
	    driver=DriverSetup.getWebDriver();
	    driver.get("https://webapps.tekstac.com/CompanyOffersDiscount/");
	    return driver;
	    
	}
    
    public XPath ReadFile(String fileName,String id){      //Do not change the method signature
        //Retrieve the xml file name passed as 'fileName' parameter. Parse the xml and return the xPath
        //Parameter 'id' is the id in the DiscountCalculator.xml
        try{
            String fname,lname,uname,pass=null;
            String projectPath=System.getProperty("user.dir");
            System.out.println(projectPath);
            File fxmlFile=new File(projectPath+File.separator+fileName);
            System.out.println(projectPath+File.separator+fileName);
            DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
            DocumentBuilder builder=null;
            builder=factory.newDocumentBuilder();
            doc=builder.parse(fxmlFile);
            XPathFactory xPathFactory=XPathFactory.newInstance();
            xpath=xPathFactory.newInstance().newXPath();
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return xpath;
    }
    
    public  Node getWeight(int id){   //Do not change the method signature
        //Parse the xml to get 'Weight' element. Return its node
        //Parameter 'id' is the id in the DiscountCalculator.xml
        String UweightEx=String.format("/Shipmentdetails/Shipmentdetail[@id='"+id+"']/Weight");
        Node node1=null;
        try{
            node1=(Node)xpath.compile(UweightEx).evaluate(doc,XPathConstants.NODE);
            
        }catch (Exception e)
        {
        }
        String uweight=node1!=null?(node1.getTextContent()):"cannot read the test case xml file";
        return node1;
    }
    
    public  Node getDistance(int id){   //Do not change the method signature
        //Parse the xml to get 'Distance' element. Return its node
        //Parameter 'id' is the id in the DiscountCalculator.xml
        String UdistanceEx=String.format("/Shipmentdetails/Shipmentdetail[@id='"+id+"']/Distance");
        Node node1=null;
        try{
            node1=(Node)xpath.compile(UdistanceEx).evaluate(doc,XPathConstants.NODE);
        
            
        }catch(Exception e)
        {
        }
        String udistance=node1!=null?(node1.getTextContent()):"cannot read the test case xml file";
        return node1;
    }
    
    
    
    public  String  getMessage(){  //Do not change the method signature
        
        //Find the web elements in the page. Assign the respective values from xml to the form.
        //Submit the form 
        //Locate the 'Datax shipping company offers discount' message and return it
        System.out.println("Inside Message");
        driver.findElement(By.id("weight")).sendKeys(getWeight(1).getTextContent());
        driver.findElement(By.id("distance")).sendKeys(getDistance(1).getTextContent());
        driver.findElement(By.id("submit")).click();
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
        String message=driver.findElement(By.xpath("//*[@id=\"result\"]")).getText();
        System.out.println("Message: "+message);
        return message;
        
     }

    public static void main(String[] args){
	    DiscountCalculator pagLocator=new DiscountCalculator();
	    WebDriver driver=pagLocator.createDriver();
	    pagLocator.ReadFile("DiscountCalculator.xml","1");
	    String message=pagLocator.getMessage();
	    driver.close();
	    //Add required code here
	} 
}

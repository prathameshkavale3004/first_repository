import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;


public class NameLocator   //DO NOT Change the class Name
{
	public static Document doc;
	public static WebDriver driver;
	public static XPath xpath;
	public static Node node;
	public static String fname=null;
	public static String lname=null;
	public static String uname=null;
	public static String pass=null;
	public static String msg=null;
	
	public static WebDriver createDriver() //DO NOT change the method signature
	{
	   driver=DriverSetup.getWebDriver();
	   return driver;
	   //Implement code to create Driver from DriverSetup, set to 'static' driver variable and return it
	}
	
	public XPath ReadFile(String xmlfileName,String id)   //DO NOT change the method signature
    {
        try{
            DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
            DocumentBuilder builder=factory.newDocumentBuilder();
            doc=builder.parse(xmlfileName);
            }catch(Exception e)
            {
                System.out.println(e);
            }
            doc.getDocumentElement().normalize();
            XPathFactory xPathFactory=XPathFactory.newInstance();
            xpath=xPathFactory.newXPath();
            try{
                node=(Node) xpath.compile("//User[@id="+id+"]").evaluate(doc,XPathConstants.NODE);
                }catch(Exception e){
                    System.out.println(e);
                    }
                    return xpath;
                    //Implement code to read and return XPath object reference
    }
    public  String getFirstName(int id){
        try{
            node=(Node) xpath.compile("//User[@id="+id+"]/Firstname").evaluate(doc,XPathConstants.NODE);
            fname=node.getTextContent();
            }catch(Exception e)
            {
                System.out.println(e);
            }
            return fname;
            }
            //Implement code to return firstname from xml    
    public  String getLastName(int id){
        try{
            node=(Node)xpath.compile("//User[@id="+id+"]/Lastname").evaluate(doc,XPathConstants.NODE);
            lname=node.getTextContent();
        }catch(Exception e)
        {
            System.out.println(e);
        }
        return lname;
        }
        //Implement code to return lastname from xml
    public  String getUserName(int id){
        try{
            node=(Node) xpath.compile("//User[@id="+id+"]/Username").evaluate(doc,XPathConstants.NODE);
            uname=node.getTextContent();
        }catch (Exception e)
        {
         System.out.println(e);
        }
         return uname;
            //Implement code to return username from xml
    }
    
    public  String getPassword(int id){
        try{
            node=(Node) xpath.compile("//User[@id="+id+"]/Password").evaluate(doc,XPathConstants.NODE);
            pass=node.getTextContent();
        }catch(Exception e)
        {
         System.out.println(e);
         }
         return pass;
         //Implement code to return passworf from xml
    }
     
     public  String  getMessage(){  
        driver.findElement(By.id("firstname")).sendKeys(getFirstName(1));
        driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(getLastName(1));
        driver.findElement(By.id("username")).sendKeys(getUserName(1));
        driver.findElement(By.id("pass")).sendKeys(getPassword(1));
        driver.findElement(By.id("reg")).click();
       
        return msg="Registered Successfully";
        //Implement code to submit form with values got from xml and return a success message.
      }   
      
      public static void main(String[] args)
	{
	    NameLocator pagLocator=new NameLocator();
	    pagLocator.createDriver();
	    pagLocator.ReadFile("Userdetails.xml","1");
	    System.out.println(pagLocator.getMessage());
	    driver.close();//Implement the required code
	    //Close the driver
	}

}  

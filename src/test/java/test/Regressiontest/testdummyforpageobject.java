package test.Regressiontest;

import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import test.Utility.BaseClass;
import test.pageobjects.Loginpage;
import test.pageobjects.SystemConfigpage;

public class testdummyforpageobject extends BaseClass {
	ExtentTest logger;
	WebDriverWait wait;
	Loginpage login=new Loginpage();
	SystemConfigpage sysconfig= new SystemConfigpage();
	
	
	
	
	//user story number
	//Test Description:This test aims to validate the login scenarios of positive cases along with standard user able to navigate to systemconfig page
	 @Test(enabled = true ,dataProvider = "Loginpositiveandnegative",groups = "regression")
	    public void LoginPositive(String username,String password,String Usertype,String templatetype,String Templatename,String Name,String Valuetype,String MandatoryDropdown,String runTestcase,String Experimentname ) throws InterruptedException, MalformedURLException
	    {
		 if(runTestcase.equalsIgnoreCase("yes")) {
		 driver=chromeinitialization();
	    	logger=extent.startTest("Create table template "+username);
	    	login.geturl(driver);
	    	wait= new WebDriverWait(driver, 180);
	    	driver.navigate().to("http://google.com");
	    	SystemConfigpage sysconfigpage=    PageFactory.initElements(driver, SystemConfigpage.class);
	    	sysconfigpage.searchbox.click();
	    	sysconfigpage.searchbox.sendKeys("testing");
	    	sysconfigpage.searchbox.sendKeys(Keys.ENTER);
	    	
	    	//sysconfigpage.googlesearchbutton.click();
	    	int sizeoflements=sysconfigpage.titile_allelement.size();
	    	System.out.println(sizeoflements);
	    	
	    	for(int i=2;i<=sizeoflements;i++) {
	    		sysconfigpage.googlecode(driver, i);
	    	}
	    	
		 }
		 
	    }
	 
	 
	 
	 
	 
	 @AfterClass(alwaysRun = true)
		public void aftmeth2() {
			
			extent.endTest(logger);
			
		}
	 
	 @AfterMethod(alwaysRun = true)
		public void closedriver() {
		 try {
			driver.close();
		 }catch(Exception e){
			 
		 }
		}
	 
	 
	 
	 @DataProvider
	  public String[][] Loginpositiveandnegative() throws IOException{
		  String[][] testOBJArray=null;
		 
				
				   
		  System.out.println("configfile reader value is :"+ConfigFileReader("runon"));
		 if(ConfigFileReader("runon").equalsIgnoreCase("local")) {
			  
				  if(ConfigFileReader("environment").equalsIgnoreCase("srv18")) {
			  testOBJArray=getdata(testdatasheetpath,"TableTemplate");
			  
				  }
				  //write for other env here
				  
			  
		 }else if(ConfigFileReader("runon").equalsIgnoreCase("jenkin")) {
			 
				 if(System.getenv("environment").equalsIgnoreCase("srv18")) {
					 System.out.println("executing with jenkin variables dataprovider in srv18");
					 testOBJArray=getdata(testdatasheetpath,"TableTemplate");
				 }
				 //write for other env here.
				  
				  
		 }
		
		   return testOBJArray;
	  }
}

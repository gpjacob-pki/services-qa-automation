package test.Regressiontest;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import test.Utility.BaseClass;
import test.pageobjects.Loginpage;
import test.pageobjects.SystemConfigpage;




public class PositivenegativeLoginScenario2 extends BaseClass {
	
	ExtentTest logger;
	WebDriverWait wait;
	Loginpage login=new Loginpage();
	SystemConfigpage sysconfig= new SystemConfigpage();
	

	
	//user story number
	//Test Description:This test aims to validate the login scenarios of positive cases along with standard user able to navigate to systemconfig page
	 @Test(enabled = true ,dataProvider = "Loginpositiveandnegative",groups = "smoke")
	    public void LoginPositive(String username,String password,String Usertype) throws InterruptedException, MalformedURLException
	    {
		 	driver2=chromeinitializationremote();
	    	logger=extent.startTest("Login Positive "+username);
	    	login.geturl(driver2);
	    	wait= new WebDriverWait(driver2, 180);
	    	login.loginSignals(driver2, logger, username, password, wait, "LoginPositive");	
	    	login.verifySystemConfigDropdown(driver2, logger, Usertype,wait);
	    	if(Usertype.equalsIgnoreCase("Systemadmin")) {
	    		login.clickonSystemconfig(driver2, logger, Usertype, wait);
	    		String winHandleBefore=sysconfig.winhandlebefore(driver2);
	    		WebDriver switcheddriver=sysconfig.windowsswitch(driver2);
	    		login.loginSignals(switcheddriver, logger, username, password, wait, "LoginPositive");
	    		sysconfig.switchbacktodefaultdriver(switcheddriver, winHandleBefore);
	    		System.out.println("Switched window title"+driver2.getTitle());
	    	}
	    	logger.log(LogStatus.PASS, "End Case: positive login successfull");
	    	
	    	
	    	
	    }
	//user story number
	//Test Description:This test aims to validate login negative Scenario
	 @Test(enabled = true ,dataProvider = "Loginpositiveandnegative",groups = "smoke")
	    public void LoginNegative(String username,String password) throws InterruptedException, MalformedURLException
	    {
		 	driver2=chromeinitializationremote();
	    	logger=extent.startTest("Login Negative "+username);
//	    	driver.get("https://srvcadmintraining18.signalsnotebook.perkinelmercloud.eu/");
	    	login.geturl(driver2);
	    	wait= new WebDriverWait(driver2, 180);
	    	login.loginSignals(driver2, logger, username, password, wait,"LoginNegative");	    	
	    	logger.log(LogStatus.PASS, "End Case: positive login successfull");    	
	    	
	    	
	    }
	 
	 
	 
	
	 
	 @AfterClass(alwaysRun = true)
		public void aftmeth2() {
			
			extent.endTest(logger);
			
		}
	 
	 @AfterMethod(alwaysRun = true)
		public void closedriver() {
			driver2.quit();
		}
	 
	  @DataProvider
	  public String[][] Loginpositiveandnegative(Method m) throws IOException{
		  String[][] testOBJArray=null;
		  System.out.println("configfile reader value is :"+ConfigFileReader("runon"));
		 if(ConfigFileReader("runon").equalsIgnoreCase("local")) {
			  if(m.getName().equals("LoginPositive") ) {
				  if(ConfigFileReader("environment").equalsIgnoreCase("srv18")) {
			  testOBJArray=getdata(testdatasheetpath,"LoginPositiveScenario_srv18");
				  }
				  //write for other env here
				  
			  }else if(m.getName().equals("LoginNegative")) {
				  testOBJArray=getdata(testdatasheetpath,"LoginNegativeScenario");
			  }
		 }else if(ConfigFileReader("runon").equalsIgnoreCase("jenkin")) {
			 if(m.getName().equals("LoginPositive")) {
				 if(System.getenv("environment").equalsIgnoreCase("srv18")) {
					 System.out.println("executing with jenkin variables dataprovider in srv18");
					 testOBJArray=getdata(testdatasheetpath,"LoginPositiveScenario_srv18");
				 }
				 //write for other env here.
				  
				  }else if(m.getName().equals("LoginNegative")) {
					  testOBJArray=getdata(testdatasheetpath,"LoginNegativeScenario");
				  }
		 }
		return testOBJArray;
	  }
	    
}

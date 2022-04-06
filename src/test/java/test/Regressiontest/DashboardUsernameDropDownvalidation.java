package test.Regressiontest;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import test.Utility.BaseClass;
import test.pageobjects.Loginpage;
import test.pageobjects.SystemConfigpage;

/**
 * Unit test for simple App.
 */
public class DashboardUsernameDropDownvalidation extends BaseClass
{
    /**
     * Rigorous Test :-)
     */
	ExtentTest logger;
	WebDriverWait wait;
	
	
	Loginpage login=new Loginpage();
	SystemConfigpage sysconfig= new SystemConfigpage();
	
	
	
	//user story number
	//Test Description:This test aims to validate the Dropdown menus of the homepage dashboard is as expected for admin and standard user
  @Test(enabled = true,dataProvider = "UserData",groups = "regression")
    public void dashboardDropDownValidation(String username,String password,String Usertype,String Dashboardicons,String userDropdown) throws InterruptedException, MalformedURLException
    {
	  	driver=chromeinitialization();
	  	
	  	logger=extent.startTest(Usertype+" dashboardDropDownValidation "+username);
	  	login.geturl(driver);
	  	wait= new WebDriverWait(driver, 180);
	  	login.loginSignals(driver, logger, username, password, wait, "LoginPositive");	
	  	login.verifySystemConfigDropdown(driver, logger, Usertype,wait);
	  	login.usernameDropDownValidation(driver, logger, Usertype, wait, userDropdown);
	  	
	    logger.log(LogStatus.PASS, "End Case: scenario successfull");
    	
    	//extent.endTest(logger);
    }
    
   
    
    
    @DataProvider
    public String[][] UserData() throws IOException{
  	  String[][] testOBJArray=null;
  	  if(ConfigFileReader("runon").equalsIgnoreCase("local")) {
  		  
  			  if(ConfigFileReader("environment").equalsIgnoreCase("srv18")) {
  		  testOBJArray=getdata(testdatasheetpath,"Dashboardiconsvalidation");
  			  }
  			  //write for other env here
  			  
  		  
  	 }else if(ConfigFileReader("runon").equalsIgnoreCase("jenkin")) {
  		 
  			 if(System.getenv("environment").equalsIgnoreCase("srv18")) {
  				 System.out.println("executing with jenkin variables dataprovider in srv18");
  				 testOBJArray=getdata(testdatasheetpath,"Dashboardiconsvalidation");
  			 }
  			 //write for other env here.
  			  
  			  
  	 }
  	return testOBJArray;
    }
    
    

    @AfterMethod(alwaysRun = true)
	public void closedriver() {
		driver.close();
	}
    
    
    @AfterClass(alwaysRun = true)
    public void aftmeth() {
    	
    	extent.endTest(logger);
    }
    
   
    
    
}

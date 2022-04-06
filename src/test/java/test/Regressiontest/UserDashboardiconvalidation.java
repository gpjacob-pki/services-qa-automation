package test.Regressiontest;

import java.io.IOException;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import test.Utility.BaseClass;
import test.pageobjects.Loginpage;
import test.pageobjects.SystemConfigpage;

public class UserDashboardiconvalidation extends BaseClass{
	ExtentTest logger;
	WebDriverWait wait;
	
	Loginpage login=new Loginpage();
	SystemConfigpage sysconfig= new SystemConfigpage();
	

	
	@AfterMethod(alwaysRun = true)
	public void closedriver() {
		driver.close();
	}
   
	
	//user story number
	//Test Description:This test aims to validate login page Dashboard icons for both standard and admin users. 
  @Test(enabled = true, dataProvider = "standardUserData",groups = "regression")
    public void UserDashboardIconValidation(String username,String password,String Usertype,String Dashboardicons,String userDropdown) throws InterruptedException, IOException
    {
	 driver=chromeinitialization();
  	logger=extent.startTest(Usertype+" user dashboard icon validation for "+username);
  	login.geturl(driver);
  	wait= new WebDriverWait(driver, 180);
  	login.loginSignals(driver, logger, username, password, wait, "LoginPositive");	
  	login.verifySystemConfigDropdown(driver, logger, Usertype,wait);
  	login.dashboardiconValidation(driver, logger, Usertype, wait, Dashboardicons);
  	
  	logger.log(LogStatus.PASS, "End Case:Scenario successful.");
    	
    }
  
  @AfterClass(alwaysRun = true)
  public void aftmeth() {
  	System.out.println("aftermethod");
  	extent.endTest(logger);
  
  	
  	
  }
  
  @DataProvider
  public String[][] standardUserData() throws IOException{
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
  
  

}

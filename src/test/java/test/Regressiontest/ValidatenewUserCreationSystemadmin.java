package test.Regressiontest;

import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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

public class ValidatenewUserCreationSystemadmin extends BaseClass{

	ExtentTest logger;
	WebDriverWait wait;
	Loginpage login=new Loginpage();
	static WebDriver switcheddriver;
	static String winHandleBefore;
	SystemConfigpage sysconfig= new SystemConfigpage();
	
	@BeforeClass (alwaysRun = true)
	public void chromeinitialize() throws MalformedURLException {
		 driver=chromeinitialization();
	}
	
	
	//user story number
	/*Test Description:This test aims to validate new user creation is successfull for a system admin user.
	In this method newuser is created and validated if its created successfully
	and also trying to edit the created users and finally deleting the newly created users.*/
	
	@Test(enabled = true ,dataProvider = "UserData",groups = "regression")
    public void newUserCreation(String username,String password,String Usertype,String firstname,String lastname,String Alias,String country,String Organization,String userrole,String Systemgroup) throws InterruptedException
    {
    	
    	logger=extent.startTest("Validate new user creation by systemadmin for "+username);
    	login.geturl(driver);
    	wait= new WebDriverWait(driver, 180);
    	login.loginSignals(driver, logger, username, password, wait, "LoginPositive");	
    	login.verifySystemConfigDropdown(driver, logger, Usertype,wait);
    	if(Usertype.equalsIgnoreCase("Systemadmin")) {
    		login.clickonSystemconfig(driver, logger, Usertype, wait);
    		 winHandleBefore=sysconfig.winhandlebefore(driver);
    		switcheddriver=sysconfig.windowsswitch(driver);
    		login.loginSignals(switcheddriver, logger, username, password, wait, "LoginPositive");
    		String email=sysconfig.userCreation(switcheddriver, wait, logger, firstname, lastname, Alias, country, Organization);
    		sysconfig.validateifUserCretaedSuccessfully(switcheddriver, logger, email, wait);
    		sysconfig.changeUserRoleAndSystemGroup(switcheddriver, logger, email, wait, userrole, Systemgroup);
    		sysconfig.verifychangedUserRole(switcheddriver, logger, email, wait, userrole);
    		login.deleteNewlyCreatedUser(switcheddriver, logger, email, wait);
    		Thread.sleep(6000);
    		//sysconfig.switchbacktodefaultdriver(switcheddriver, winHandleBefore);
    		System.out.println("Switched window title"+driver.getTitle());
    	}
    	logger.log(LogStatus.PASS, "End Case: positive login successfull");
    	
    	
    	
    }
	
	//user story number
	/*Test Description:This test aims to validate if the page is timed out once the sysadmin user set a timeout */
	
	@Test(enabled = false ,dependsOnMethods = "newUserCreation",groups = "regression")
	public void pagetimeoutset() throws InterruptedException {
		logger=extent.startTest("Pagetimeoutvalidation");
		sysconfig.pagetimeoutvalidation(switcheddriver, logger, driver);
		sysconfig.switchbacktodefaultdriver(switcheddriver, winHandleBefore);
		
	}
	
	
	
	
	
	@AfterClass(alwaysRun = true)
	public void aftmeth2() {
		System.out.println("after method facebook");
		
		driver.close();
		
	}
 
 @AfterMethod(alwaysRun = true)
	public void closedriver() {
	 extent.endTest(logger);
	}
 
 
 @DataProvider
 public String[][] UserData() throws IOException{
	  String[][] testOBJArray=null;
	  if(ConfigFileReader("runon").equalsIgnoreCase("local")) {
		  
			  if(ConfigFileReader("environment").equalsIgnoreCase("srv18")) {
		  testOBJArray=getdata(testdatasheetpath,"Usercreationandvalidation");
			  }
			  //write for other env here
			  
		  
	 }else if(ConfigFileReader("runon").equalsIgnoreCase("jenkin")) {
		 
			 if(System.getenv("environment").equalsIgnoreCase("srv18")) {
				 System.out.println("executing with jenkin variables dataprovider in srv18");
				 testOBJArray=getdata(testdatasheetpath,"Usercreationandvalidation");
			 }
			 //write for other env here.
			  
			  
	 }
	return testOBJArray;
 }
 
	
}

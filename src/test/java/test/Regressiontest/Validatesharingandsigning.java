package test.Regressiontest;

import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import test.Utility.BaseClass;
import test.pageobjects.Loginpage;
import test.pageobjects.SystemConfigpage;

public class Validatesharingandsigning extends BaseClass {
	ExtentTest logger;
	WebDriverWait wait;
	
	
	Loginpage login=new Loginpage();
	SystemConfigpage sysconfig= new SystemConfigpage();
	static String sharingAccess;
	

	//user story number
	/*Test Description:This test aims to validate if the user have access to create a new experiment
	 and Validate if the user have sharing and signing access*/
	
  @Test(enabled = true,dataProvider = "UserData",groups = "regression",dependsOnMethods = "configchecker2")
    public void dashboardDropDownValidation(String username,String password,String Usertype,String template,String description,String notebook,String users,String project,String Department,String internalfield,String Shareproject) throws InterruptedException, MalformedURLException
    {
	 driver=chromeinitialization();
  	logger=extent.startTest(Usertype+" ValidateSharingand Signing for "+username);
  	login.geturl(driver);
  	wait= new WebDriverWait(driver, 180);
  	login.loginSignals(driver, logger, username, password, wait, "LoginPositive");	
  	
  	String[] sharingaccessyesorno=sharingAccess.split(",");
  	
  	//sharing access for system admin
  	if(Usertype.equalsIgnoreCase("System Admin")) {
  	
  	login.newExperiment(driver, wait, logger, Usertype, template, description, notebook, users, project, Department, internalfield,sharingaccessyesorno[1]);
  	
  	//sharing access for standard user.
  	}else if(Usertype.equalsIgnoreCase("Standard User")) {
  	  	login.newExperiment(driver, wait, logger, Usertype, template, description, notebook, users, project, Department, internalfield,sharingaccessyesorno[0]);
  	}
  	
    logger.log(LogStatus.PASS, "End Case: scenario successfull");
    	
    	//extent.endTest(logger);
    }
    
   

	@Test(enabled = true,dataProvider = "UserData",groups = "regression")
	public void configchecker2(String username,String password,String Usertype,String template,String description,String notebook,String users,String project,String Department,String internalfield,String Shareproject) throws MalformedURLException, InterruptedException {
		if(Shareproject.equalsIgnoreCase("1")) {
		driver=chromeinitialization();
		logger=extent.startTest(Usertype+" Congig checker for validateSharing");
		login.geturl(driver);
		wait= new WebDriverWait(driver, 180);
		login.loginSignals(driver, logger, username, password, wait, "LoginPositive");	
		login.verifySystemConfigDropdown(driver, logger, Usertype,wait);
		login.clickonSystemconfig(driver, logger, Usertype, wait);
		System.out.println("systemconfig clicked");
		String winHandleBefore=sysconfig.winhandlebefore(driver);
		WebDriver switcheddriver=sysconfig.windowsswitch(driver);
		login.loginSignals(switcheddriver, logger, username, password, wait, "LoginPositive");
		
		sysconfig.clickOnuserroles(switcheddriver, wait, logger);
		
		
		
		String attribute=sysconfig.tabtoclick(switcheddriver, wait, logger, "Author");
		int numberofAccess=sysconfig.number_of_access(switcheddriver, wait, logger,attribute,"Share");
		String statu_standardusers=sysconfig.findenabledornot(switcheddriver, wait, logger, winHandleBefore, "Standard User", attribute,numberofAccess,"Share");
		String statu_systemadmin=sysconfig.findenabledornot(switcheddriver, wait, logger, winHandleBefore, "System Admin", attribute,numberofAccess,"Share");

		
		sharingAccess=statu_standardusers+","+statu_systemadmin;
		
		}else {
			
		}
	}
  
    
    
    @DataProvider
    public String[][] UserData() throws IOException{
  	  String[][] testOBJArray=null;
  	  if(ConfigFileReader("runon").equalsIgnoreCase("local")) {
  		  
  			  if(ConfigFileReader("environment").equalsIgnoreCase("srv18")) {
  		  testOBJArray=getdata(testdatasheetpath,"Experiment creation");
  			  }
  			  //write for other env here
  			  
  		  
  	 }else if(ConfigFileReader("runon").equalsIgnoreCase("jenkin")) {
  		 
  			 if(System.getenv("environment").equalsIgnoreCase("srv18")) {
  				 System.out.println("executing with jenkin variables dataprovider in srv18");
  				 testOBJArray=getdata(testdatasheetpath,"Experiment creation");
  			 }
  			 //write for other env here.
  			  
  			  
  	 }
  	return testOBJArray;
    }
    
    

    @AfterMethod(alwaysRun = true)
	public void closedriver() {
    	try {
		driver.close();
    	}catch(Exception e){
    		
    	}
	}
    
    
    @AfterClass(alwaysRun = true)
    public void aftmeth() {
    	System.out.println("aftermethod");
    	extent.endTest(logger);
    }
    
    @AfterTest
	 public void aftrtst() {
		 
		 
		 extent.flush();
	 }
    
    

}

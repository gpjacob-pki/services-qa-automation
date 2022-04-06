package test.Regressiontest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import test.Utility.BaseClass;
import test.pageobjects.ExperimentcreationPage;
import test.pageobjects.Loginpage;
import test.pageobjects.SystemConfigpage;

public class ExternalActionsValidation extends BaseClass{

	
	ExtentTest logger;
	WebDriverWait wait;
	static String sharingAccess;
	static String statu_standardusers;
	static String statu_systemadmin;
	static ArrayList ExternalActionslable;
	static ArrayList ExternalActionsstatus;
	static ArrayList applyto;
	Loginpage login=new Loginpage();
	ExperimentcreationPage expcreationpage=new ExperimentcreationPage();
	SystemConfigpage sysconfig= new SystemConfigpage();
	
	
	 @Test(enabled = true ,dataProvider = "UserData",groups = "regression",dependsOnMethods = "configchecker")
	    public void LoginPositive(String username,String password,String Usertype,String template,String description,String notebook,String users,String project,String Department,String internalfield,String Shareproject) throws MalformedURLException, InterruptedException {
	  
		
		 	driver=chromeinitialization();
	    	logger=extent.startTest("ExternalActions Validation for "+username);
	    	 
	    	login.geturl(driver);
	    	wait= new WebDriverWait(driver, 30);
	    	login.loginSignals(driver, logger, username, password, wait, "LoginPositive");	
	    	login.loginpagepopupdismiss(driver, logger, wait);
	    	login.waituuntilloginpageload(driver, logger, wait);
	    	
	    	expcreationpage.validateExternalActionsinEnduserterminal(driver, wait, logger, Usertype, template, description, notebook, users, project, Department, internalfield, Shareproject, ExternalActionslable, ExternalActionsstatus, applyto);
	    	
	    
	    	logger.log(LogStatus.PASS, "End Case: positive login successfull");
		 
		 
	    }
	 
	 
	 
	 @Test(enabled = true,dataProvider = "UserData",groups = "regression")
		public void configchecker(String username,String password,String Usertype,String template,String description,String notebook,String users,String project,String Department,String internalfield,String iteration) throws MalformedURLException, InterruptedException {
			if(iteration.equalsIgnoreCase("1")) {
			driver=chromeinitialization();
			logger=extent.startTest(Usertype+" Config checker for ExternalValidations Activation ");
			login.geturl(driver);
		
			wait= new WebDriverWait(driver, 30);
			login.loginSignals(driver, logger, username, password, wait, "LoginPositive");	
			login.verifySystemConfigDropdown(driver, logger, Usertype,wait);
			login.clickonSystemconfig(driver, logger, Usertype, wait);
			System.out.println("systemconfig clicked");
			String winHandleBefore=sysconfig.winhandlebefore(driver);
			WebDriver switcheddriver=sysconfig.windowsswitch(driver);
			login.loginSignals(switcheddriver, logger, username, password, wait, "LoginPositive");
			
			
			sysconfig.sysconfigExternalActionsclick(switcheddriver, wait, logger);
			ExternalActionslable=sysconfig.externalActionslable(switcheddriver, wait, logger, Usertype);
			ExternalActionsstatus=sysconfig.externalActionsstatus(switcheddriver, wait, logger, Usertype);
			applyto=sysconfig.ExternalActionsApplyto(switcheddriver, wait, logger, ExternalActionslable);
			
			}else {
				
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
	
}

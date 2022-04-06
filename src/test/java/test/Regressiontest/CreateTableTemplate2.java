package test.Regressiontest;

import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
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

public class CreateTableTemplate2 extends BaseClass {
	ExtentTest logger;
	WebDriverWait wait;
	Loginpage login=new Loginpage();
	SystemConfigpage sysconfig= new SystemConfigpage();
	

	
	//user story number
	//Test Description:This test aims to validate the login scenarios of positive cases along with standard user able to navigate to systemconfig page
	 @Test(enabled = true ,dataProvider = "Loginpositiveandnegative",groups = "smoke")
	    public void LoginPositive(String username,String password,String Usertype,String templatetype,String Templatename,String Name,String Valuetype,String MandatoryDropdown,String runTestcase,String Experimentname ) throws InterruptedException, MalformedURLException
	    {
		 if(runTestcase.equalsIgnoreCase("yes")) {
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
	    		sysconfig.clickOnTableTemplate(switcheddriver, wait,logger);
	    		Boolean existornot=sysconfig.validateifTableTemplateAlreadyExistsorNot(switcheddriver, wait, Templatename,logger);
	    		String Templatenamenew=Templatename+Integer.toString(randomnumgenrator());
	    		sysconfig.createtemplate(switcheddriver, wait, existornot, Templatename,Templatenamenew,logger,Name,Valuetype);
	    		sysconfig.switchbacktodefaultdriver(switcheddriver, winHandleBefore);
	    		   	login.experimenticonclick(switcheddriver, wait, logger, Experimentname);	
	    		   	login.clickonAddContent(switcheddriver, wait, logger, Experimentname);
	    		   	login.selecttemplatefromAddContent(switcheddriver, wait, logger, "Table");
	    		   	login.selectTableTemplate(switcheddriver, wait, logger, Templatename);
	    		   	login.tableTemplateValidation(switcheddriver, wait, logger, Templatename, Name);

	    	}
	    	logger.log(LogStatus.PASS, "End Case: positive login successfull");
		 }
		 else {
			 
		 }
		 
	    }
	 
	 
	 
	 
	 
	 @AfterClass(alwaysRun = true)
		public void aftmeth2() {
			
			extent.endTest(logger);
			
		}
	 
	 @AfterMethod(alwaysRun = true)
		public void closedriver() {
		 try {
			driver2.quit();
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

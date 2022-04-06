package test.Regressiontest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;

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
import test.pageobjects.SystemSettingPage;

public class SystemSetting_AutoTextValidation extends BaseClass {

	static ArrayList Autotextlist;
	static ArrayList Autotextsnippet;

	ExtentTest logger;
	WebDriverWait wait;
	Loginpage login=new Loginpage();
	SystemConfigpage sysconfig= new SystemConfigpage();
	SystemSettingPage systemsettingpage=new SystemSettingPage();
	ExperimentcreationPage expcreationpage=new ExperimentcreationPage();
	
	

	 @Test(enabled = true ,dataProvider = "UserData",groups = "regression",dependsOnMethods = "configchecker")
	    public void LoginPositive(String username,String password,String Usertype,String template,String description,String notebook,String users,String project,String Department,String internalfield,String Shareproject) throws MalformedURLException, InterruptedException {
	    //
//		 if(runTestcase.equalsIgnoreCase("yes")) {
		
		 driver=chromeinitialization();
	    	logger=extent.startTest("SystemSetting_AutotextValidation for "+username);
	    	 
	    	login.geturl(driver);
	    	wait= new WebDriverWait(driver, 180);
	    	login.loginSignals(driver, logger, username, password, wait, "LoginPositive");	
	    	login.loginpagepopupdismiss(driver, logger, wait);
	    	login.waituuntilloginpageload(driver, logger, wait);
	    	//expcreationpage.endusernotebookCreation(driver, wait, logger);
	    	//expcreationpage.validateExternalActionsinEnduserterminal(driver, wait, logger, Usertype, template, description, notebook, users, project, Department, internalfield, Shareproject, ExternalActionslable, ExternalActionsstatus, applyto);
	    	login.newExperimentcreation(driver, wait, logger, Usertype, template, description, notebook, users, project, Department, internalfield);
	    	expcreationpage.clickonAddContent(driver, wait, logger, Shareproject);
	    	expcreationpage.selecttemplatefromAddContent(driver, wait, logger, "Text");
	    	expcreationpage.clickonTextcreated(driver, wait, logger);
	    	expcreationpage.clickonPredefinedValues(driver, wait, logger);
	    	ArrayList getAutotextList_EnduserText= expcreationpage.getAutotextList_EnduserText(driver, wait, logger);
	    	Boolean trueorfalse=expcreationpage.ValidateEqualityofTwoArray(driver, wait, logger, Autotextlist, getAutotextList_EnduserText);
	    	
	    	if(trueorfalse) {
	    		logger.log(LogStatus.PASS, "Values are same in config page and end user page ");
	    		
	    	}else {
	    		
	    		logger.log(LogStatus.FAIL, "Values are not same in config page and end user page ");
	    	}
	    	
	    	expcreationpage.clickoninsertTextTemplate(driver, wait, logger);
	    	ArrayList getAutotextSnippet=expcreationpage.getpredefinedtexttemplate_EnduserText(driver, wait, logger);
	    	Boolean trueorfalse1=expcreationpage.ValidateEqualityofTwoArray(driver, wait, logger, Autotextsnippet, getAutotextSnippet);
	    	
	    	if(trueorfalse1) {
	    		logger.log(LogStatus.PASS, "Values are same in config page and end user page for snippets");
	    		
	    	}else {
	    		logger.log(LogStatus.FAIL, "values are not same in config and enduser page  for snippets");
	    		
	    	}
	    	//}
	    	logger.log(LogStatus.PASS, "End Case: positive login successfull");
		 
		 
	    }
	
	@Test(enabled = true,dataProvider = "UserData",groups = "regression")
	public void configchecker(String username,String password,String Usertype,String template,String description,String notebook,String users,String project,String Department,String internalfield,String iteration) throws MalformedURLException, InterruptedException {
		if(iteration.equalsIgnoreCase("1")) {
		driver=chromeinitialization();
		logger=extent.startTest(Usertype+" Congig checker for SystemSetting_AutotextValidation");
		login.geturl(driver);
		wait= new WebDriverWait(driver, 180);
		login.loginSignals(driver, logger, username, password, wait, "LoginPositive");	
		login.verifySystemConfigDropdown(driver, logger, Usertype,wait);
		login.clickonSystemconfig(driver, logger, Usertype, wait);
		System.out.println("systemconfig clicked");
		String winHandleBefore=sysconfig.winhandlebefore(driver);
		WebDriver switcheddriver=sysconfig.windowsswitch(driver);
		login.loginSignals(switcheddriver, logger, username, password, wait, "LoginPositive");
		System.out.println("second login done");
		sysconfig.clickOnSystemSetting(switcheddriver, wait, logger);
		Autotextlist=systemsettingpage.getAutotxt_listvalues(switcheddriver, wait, logger);
		Autotextsnippet=systemsettingpage.getsnippet_valueAutotext(switcheddriver, wait, logger);
		
		
		
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


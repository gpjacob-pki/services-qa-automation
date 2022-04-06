package test.Regressiontest;

import java.awt.AWTException;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Map;

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

public class UploadsValidation  extends BaseClass{
	
	
	
	ExtentTest logger;
	WebDriverWait wait;
	
	static Boolean encryptedcheckbox_checker;
	static Boolean allfiletypeallowedchecker;
	static Boolean whitelistfiletypeallowedchecker;
	static String[] file_names;
	static int sizeoffilenames;
	static String[] file_names_all;
	static int sizeoffilenames_all;
	static ArrayList array_filetype;
	static ArrayList array_filetype_merged;
	Loginpage login=new Loginpage();
	SystemConfigpage sysconfig= new SystemConfigpage();
	ExperimentcreationPage expcreationpage=new ExperimentcreationPage();
	
	 @Test(enabled = true ,dataProvider = "UserData",groups = "regression",dependsOnMethods = "configchecker2")
	    public void LoginPositive(String username,String password,String Usertype,String template,String description,String notebook,String users,String project,String Department,String internalfield,String Shareproject) throws MalformedURLException, InterruptedException, AWTException {
		 	
		
		 	driver=chromeinitialization();
	    	logger=extent.startTest("ExternalActions Validation for "+username);   	
	     	login.geturl(driver);
	    	wait= new WebDriverWait(driver, 40);
	    	login.loginSignals(driver, logger, username, password, wait, "LoginPositive");	
	    	login.loginpagepopupdismiss(driver, logger, wait);
	    	login.waituuntilloginpageload(driver, logger, wait);
	    	login.newExperimentcreation(driver, wait, logger, Usertype, template, description, notebook, users, project, Department, internalfield);
	    	expcreationpage.clickonAddContent(driver, wait, logger, Shareproject);
	    	expcreationpage.selecttemplatefromAddContent(driver, wait, logger, "File Upload");
	    	expcreationpage.Uploadfilepath("Testing 123.docx");
	    	if(!encryptedcheckbox_checker) {
	    		expcreationpage.Fileuploadchecker(driver, wait, logger, template, "Testing 123.docx");
	    	}else {
	    		expcreationpage.Fileencrytederrormessageuploadchecker(driver, wait, logger, template, "Testing 123.docx");
	    	}
	    	
	    	file_names=expcreationpage.filesnotallowed(driver, wait, logger);
	    	sizeoffilenames=expcreationpage.getfilenames_size(driver, wait, logger, file_names);    	
	    	
	    	for(int i=0;i<sizeoffilenames;i++) {
	    		if(allfiletypeallowedchecker) {
	    		expcreationpage.clickonAddContent(driver, wait, logger, Shareproject);
		    	expcreationpage.selecttemplatefromAddContent(driver, wait, logger, "File Upload");
		    	expcreationpage.Uploadfilepath(file_names[i]);
	    		expcreationpage.Filetypenotalloweduploadchecker(driver, wait, logger, template, file_names[i]);
	    		}
	    	}	    	
	    	
	    	if(whitelistfiletypeallowedchecker) {
	    		file_names_all=expcreationpage.filesnames(driver, wait, logger);
	    		sizeoffilenames_all=expcreationpage.getfilenames_size(driver, wait, logger, file_names_all);
	    		for(int i=0;i<sizeoffilenames_all;i++) {
		    		
		    		expcreationpage.clickonAddContent(driver, wait, logger, Shareproject);
			    	expcreationpage.selecttemplatefromAddContent(driver, wait, logger, "File Upload");
			    	expcreationpage.Uploadfilepath(file_names_all[i]);
			    	expcreationpage.whitelistfiletypechecker(driver, wait, logger, template, file_names_all[i],array_filetype_merged);
		    		//expcreationpage.Filetypenotalloweduploadchecker(driver, wait, logger, template, file_names[i]);
		    		}
		    	
	    		
	    	}
	    	
	    	
	    	Thread.sleep(8000);
	    	
	    
	    	logger.log(LogStatus.PASS, "End Case: positive login successfull");
		 
		 
	    }
	 
	 
	 
	 @Test(enabled = true,dataProvider = "UserData",groups = "regression")
		public void configchecker2(String username,String password,String Usertype,String template,String description,String notebook,String users,String project,String Department,String internalfield,String iteration) throws MalformedURLException, InterruptedException {
		 if(iteration.equalsIgnoreCase("1")) {
				driver=chromeinitialization();
				logger=extent.startTest(Usertype+" Congig checker for MaterialsActivationChecker ");
				login.geturl(driver);
				wait= new WebDriverWait(driver, 30);
				login.loginSignals(driver, logger, username, password, wait, "LoginPositive");	
				login.verifySystemConfigDropdown(driver, logger, Usertype,wait);
				login.clickonSystemconfig(driver, logger, Usertype, wait);
				System.out.println("systemconfig clicked");
				String winHandleBefore=sysconfig.winhandlebefore(driver);
				WebDriver switcheddriver=sysconfig.windowsswitch(driver);
				login.loginSignals(switcheddriver, logger, username, password, wait, "LoginPositive");
				System.out.println("second login done");
				sysconfig.clickOnSystemSetting(switcheddriver, wait, logger);
				sysconfig.clickonUploads(switcheddriver, wait, logger);
				encryptedcheckbox_checker=sysconfig.encryptfilecheckboc_Validation(switcheddriver, wait, logger);
				allfiletypeallowedchecker=sysconfig.allfiletypeallowedchecker(switcheddriver, wait, logger);
				whitelistfiletypeallowedchecker=sysconfig.whitelistfiletypeallowedchecker(switcheddriver, wait, logger);
				if(whitelistfiletypeallowedchecker) {
				array_filetype=sysconfig.whitelist_types(switcheddriver, wait, logger);
				array_filetype_merged=sysconfig.merge_Whitelist_names(switcheddriver, wait, logger, array_filetype);
				
				}
				
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

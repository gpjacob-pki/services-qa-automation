package test.Regressiontest;

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

public class InventoryValidation  extends BaseClass{
	
	
	
	ExtentTest logger;
	WebDriverWait wait;
	static String sharingAccess;
	static String statu_standardusers;
	static String statu_systemadmin;
	static ArrayList materialsActivationlable;
	static ArrayList locationtypes;
	static ArrayList containertypes;
	static ArrayList materialsActivatiostatus;
	static String statu_standardusers_AddMaterials;
	static String statu_systemadmin_AddMaterials;
	static Map location_fields;
	static Map container_fields;
	static Boolean Inventoryactivation;
	static String statu_addlocation_systemadmin;
	static String  statu_addlocation_standarduser;
	
	
	Loginpage login=new Loginpage();
	SystemConfigpage sysconfig= new SystemConfigpage();
	
	 @Test(enabled = true ,dataProvider = "UserData",groups = "regression",dependsOnMethods = "configchecker2")
	    public void LoginPositive(String username,String password,String Usertype,String template,String description,String notebook,String users,String project,String Department,String internalfield,String Shareproject) throws MalformedURLException, InterruptedException {
	  
		
		 	driver=chromeinitialization();
	    	logger=extent.startTest("ExternalActions Validation for "+username);
	    	 
	    	login.geturl(driver);
	    	wait= new WebDriverWait(driver, 40);
	    	login.loginSignals(driver, logger, username, password, wait, "LoginPositive");	
	    	login.loginpagepopupdismiss(driver, logger, wait);
	    	login.waituuntilloginpageload(driver, logger, wait);
	    	System.out.println("usertype is :"+Usertype);
	    	login.clickonnamedropdown(driver, logger, Usertype, wait);
	    	if(((Usertype.equalsIgnoreCase("System Admin"))&&(statu_systemadmin.equalsIgnoreCase("yes"))&&(Inventoryactivation))||((Usertype.equalsIgnoreCase("Standard User"))&&(statu_standardusers.equalsIgnoreCase("yes"))&&(Inventoryactivation))) {
	    	login.verifyifInventoryPresentInDropdown(driver, logger, Usertype, wait);
	    	login.clickOnInventoryDropdown(driver, logger, Usertype, wait);
	    	String winHandleBefore=sysconfig.winhandlebefore(driver);
			WebDriver switcheddriver=sysconfig.windowsswitch(driver);
			if(((Usertype.equalsIgnoreCase("System Admin"))&&(statu_addlocation_systemadmin.equalsIgnoreCase("yes")))||((Usertype.equalsIgnoreCase("Standard User"))&&(statu_addlocation_standarduser.equalsIgnoreCase("yes")))) {
			login.VerifyAddLocation(switcheddriver, logger, Usertype, wait);
			login.locationtype_Validation(switcheddriver, logger, Usertype, wait);
			login.LocationFieldValidation(switcheddriver, logger, Usertype, wait, locationtypes, location_fields);
			login.mapValidation(switcheddriver, logger, Usertype, wait, location_fields, locationtypes);
			}else {
				logger.log(LogStatus.PASS, "Location in drop down is not enabled as expected");
			}
	    	//expcreationpage.validateExternalActionsinEnduserterminal(driver, wait, logger, Usertype, template, description, notebook, users, project, Department, internalfield, Shareproject, ExternalActionslable, ExternalActionsstatus, applyto);
	    	}else if(((Usertype.equalsIgnoreCase("System Admin"))&&(statu_systemadmin.equalsIgnoreCase("no"))||(!Inventoryactivation))||((Usertype.equalsIgnoreCase("Standard User"))&&(statu_standardusers.equalsIgnoreCase("no"))||(!Inventoryactivation))) {
	    		login.verifyifInventoryNotPresentInDropdown(driver, logger, Usertype, wait);
	    	}
	    
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
				sysconfig.clickOnuserroles(switcheddriver, wait, logger);
				System.out.println("userroles clicked");
				
				
				String attribute=sysconfig.tabtoclick(switcheddriver, wait, logger, "Administration");
				
				int numberofAccess=sysconfig.number_of_access(switcheddriver, wait, logger,attribute,"Inventory App");
				
				statu_standardusers=sysconfig.findenabledornot(switcheddriver, wait, logger, winHandleBefore, "Standard User", attribute,numberofAccess,"Inventory App");
				
				System.out.println("value is:"+statu_standardusers);
				 statu_systemadmin=sysconfig.findenabledornot(switcheddriver, wait, logger, winHandleBefore, "System Admin", attribute,numberofAccess,"Inventory App");
				 
					System.out.println("value is:"+statu_systemadmin);
				 

					String attribute1=sysconfig.tabtoclick(switcheddriver, wait, logger, "Locations");
					
					int numberofAccess1=sysconfig.number_of_access(switcheddriver, wait, logger,attribute1,"Add Location");
					
					statu_addlocation_standarduser=sysconfig.findenabledornot(switcheddriver, wait, logger, winHandleBefore, "Standard User", attribute1,numberofAccess1,"Add Location");
					
					System.out.println("value is:"+statu_standardusers);
					statu_addlocation_systemadmin=sysconfig.findenabledornot(switcheddriver, wait, logger, winHandleBefore, "System Admin", attribute1,numberofAccess1,"Add Location");
					 
					 
				
				sharingAccess=statu_standardusers+","+statu_systemadmin;
//				System.out.println("sharing access is:"+sharingAccess);
				sysconfig.clickonDashDoardHomePageIcon(switcheddriver, wait, logger);
				
				sysconfig.inventoryclick(switcheddriver, wait, logger);
				
				Inventoryactivation=sysconfig.Inventoryactivation(switcheddriver, wait, logger, Usertype);
				sysconfig.Locationssidebarclick(switcheddriver, wait, logger, Usertype);				
				sysconfig.Typessidebarclick(switcheddriver, wait, logger, Usertype);
				locationtypes=sysconfig.getlistofLocationtypes(switcheddriver, wait, logger, Usertype);
				location_fields=sysconfig.getlistoflocationtypefields(switcheddriver, wait, logger, Usertype, locationtypes);
//				statu_addlocation_standarduser=sysconfig.locationactivationvalidation(switcheddriver, wait, logger, "Standard User", winHandleBefore);
//				statu_addlocation_systemadmin=sysconfig.locationactivationvalidation(switcheddriver, wait, logger, "System Admin", winHandleBefore);
				
				System.out.println("The location for standarduser is enabled :"+statu_addlocation_standarduser+" for SystemAdmin:"+statu_addlocation_systemadmin);
				
//				materialsActivatiostatus=sysconfig.materialsActivationstatus(switcheddriver, wait, logger, Usertype);
				logger.log(LogStatus.PASS, "Inventoryactivation  is enabled or not :"+Inventoryactivation);
				
				sysconfig.Containerssidebarclick(switcheddriver, wait, logger, Usertype);
				sysconfig.ContaineerTypessidebarclick(switcheddriver, wait, logger, Usertype);
				containertypes=sysconfig.getlistofContainertypes(switcheddriver, wait, logger, Usertype);
				container_fields=sysconfig.getlistofcontainertypefields(switcheddriver, wait, logger, Usertype, containertypes);
				
				System.out.println("value is:"+Inventoryactivation);
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

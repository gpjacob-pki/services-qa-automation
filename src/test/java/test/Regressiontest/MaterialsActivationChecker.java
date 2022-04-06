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
import test.pageobjects.Loginpage;
import test.pageobjects.SystemConfigpage;

public class MaterialsActivationChecker extends BaseClass{

	
	ExtentTest logger;
	WebDriverWait wait;
	static String sharingAccess;
	static String statu_standardusers;
	static String statu_systemadmin;
	static ArrayList materialsActivationlable;
	static ArrayList materialsActivatiostatus;
	static String statu_standardusers_AddMaterials;
	static String statu_systemadmin_AddMaterials;
	
	
	Loginpage login=new Loginpage();
	SystemConfigpage sysconfig= new SystemConfigpage();
	
	
	 @Test(enabled = true ,dataProvider = "UserData",groups = "regression",dependsOnMethods = "configchecker2")
	    public void LoginPositive(String username,String password,String Usertype,String template,String description,String notebook,String users,String project,String Department,String internalfield,String Shareproject) throws MalformedURLException, InterruptedException {
	    

		 	driver=chromeinitialization();
	    	logger=extent.startTest("MaterialsActivationChecker "+username);
	    	login.geturl(driver);
	    	wait= new WebDriverWait(driver, 30);
	    	login.loginSignals(driver, logger, username, password, wait, "LoginPositive");	
	    	login.loginpagepopupdismiss(driver, logger, wait);
	    	login.waituuntilloginpageload(driver, logger, wait);
	    	
	    	if(((Usertype.equalsIgnoreCase("System Admin"))&&(statu_systemadmin.equalsIgnoreCase("yes")))||((Usertype.equalsIgnoreCase("Standard user"))&&(statu_standardusers.equalsIgnoreCase("yes")))) {
	    	logger.log(LogStatus.PASS, "Material view option is enabled for "+ Usertype);
	    		login.ClickonMaterials_fromuserDashboard(driver, logger, wait);
	    	login.ClickonMaterial_dropdown(driver, logger, wait);
	    	ArrayList getlistofMaterialsDropdown= login.getlistofMaterialsDropdown(driver, logger, wait);
	    	login.verifyConfigpathanduserpage_Materials(driver, logger, wait,getlistofMaterialsDropdown,materialsActivationlable,materialsActivatiostatus);
	    	
	    	if(((Usertype.equalsIgnoreCase("System Admin"))&&(statu_systemadmin_AddMaterials.equalsIgnoreCase("yes")))||((Usertype.equalsIgnoreCase("Standard user"))&&(statu_standardusers_AddMaterials.equalsIgnoreCase("yes")))) {
	    		login.VerifyifUserIsAbleToADDMaterial(driver, logger, wait, getlistofMaterialsDropdown);
	    	}
	    	
	    	
	    	}else {
	    		System.out.println(Usertype+"Material view status is No");
	    		login.VerifynoMaterialsicon_fromuserDashboard(driver, logger, wait, Usertype);
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
			
			
			String attribute=sysconfig.tabtoclick(switcheddriver, wait, logger, "Materials");
			int numberofAccess=sysconfig.number_of_access(switcheddriver, wait, logger,attribute,"View Materials");
			statu_standardusers=sysconfig.findenabledornot(switcheddriver, wait, logger, winHandleBefore, "Standard User", attribute,numberofAccess,"View Materials");
			 statu_systemadmin=sysconfig.findenabledornot(switcheddriver, wait, logger, winHandleBefore, "System Admin", attribute,numberofAccess,"View Materials");
			 
			 int numberofAccess_AddMaterials=sysconfig.number_of_access(switcheddriver, wait, logger,attribute,"Add Materials");
				statu_standardusers_AddMaterials=sysconfig.findenabledornot(switcheddriver, wait, logger, winHandleBefore, "Standard User", attribute,numberofAccess_AddMaterials,"Add Materials");
				 statu_systemadmin_AddMaterials=sysconfig.findenabledornot(switcheddriver, wait, logger, winHandleBefore, "System Admin", attribute,numberofAccess_AddMaterials,"Add Materials");
			 

			
			sharingAccess=statu_standardusers+","+statu_systemadmin;
//			System.out.println("sharing access is:"+sharingAccess);
			sysconfig.clickonDashDoardHomePageIcon(switcheddriver, wait, logger);
			sysconfig.sysconfigmaterialsclick(switcheddriver, wait, logger);
			materialsActivationlable=sysconfig.materialsActivationlable(switcheddriver, wait, logger, Usertype);
			materialsActivatiostatus=sysconfig.materialsActivationstatus(switcheddriver, wait, logger, Usertype);
			logger.log(LogStatus.PASS, "materials lables  is"+materialsActivationlable+ " and theri status is :"+materialsActivatiostatus);
			
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

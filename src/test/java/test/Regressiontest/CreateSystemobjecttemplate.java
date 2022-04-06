package test.Regressiontest;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.commons.compress.utils.Lists;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.utils.Stream;

import test.Utility.BaseClass;
import test.pageobjects.Loginpage;
import test.pageobjects.SystemConfigpage;

public class CreateSystemobjecttemplate extends BaseClass {

	ExtentTest logger;
	WebDriverWait wait;
	Loginpage login=new Loginpage();
	SystemConfigpage sysconfig= new SystemConfigpage();
	

	
	//user story number
	//Test Description:This test aims to validate the login scenarios of positive cases along with standard user able to navigate to systemconfig page
	 @Test(enabled = true ,dataProvider = "Loginpositiveandnegative",groups = "regression")
	    public void LoginPositive(String username,String password,String Usertype,String templatetype,String Templatename,String field1,String field1YorN,String field2,String runTestcase ) throws InterruptedException, MalformedURLException
	    {
		 
		 if(runTestcase.equalsIgnoreCase("yes")) {
		 	driver=chromeinitialization();
	    	logger=extent.startTest("create sysytem object template for "+username);
	    	login.geturl(driver);
	    	wait= new WebDriverWait(driver, 180);
	    	login.loginSignals(driver, logger, username, password, wait, "LoginPositive");	
	    	login.verifySystemConfigDropdown(driver, logger, Usertype,wait);
	    	if(Usertype.equalsIgnoreCase("Systemadmin")) {
	    		login.clickonSystemconfig(driver, logger, Usertype, wait);
	    		String winHandleBefore=sysconfig.winhandlebefore(driver);
	    		WebDriver switcheddriver=sysconfig.windowsswitch(driver);
	    		login.loginSignals(switcheddriver, logger, username, password, wait, "LoginPositive");
	    		sysconfig.clickOnSystemObject(switcheddriver, wait);
	    		   		
	    		
	    		Templatename=Templatename+Integer.toString(randomnumgenrator());
	    		System.out.println("Random template name genrated is:"+Templatename);
	    		sysconfig.clickontemplatecreation(switcheddriver, wait,templatetype,Templatename);
	    		sysconfig.exptemplateFieldsValidationCreation(switcheddriver, wait,logger,field1,field1YorN,field2);
	    		
	    		
	    		
	    		sysconfig.switchbacktodefaultdriver(switcheddriver, winHandleBefore);
	    		System.out.println("Switched window title"+driver.getTitle());
	    		
	    		login.clickonNEwexperiment(switcheddriver, wait, logger,templatetype);
	    		
	    		login.SearchforNewExperimenttemplate(switcheddriver, wait, logger, Templatename);
	    		login.mandatoryOrNotFieldValidation(switcheddriver, wait, logger, field1, field1YorN);
	    		
	    		System.out.println("type is :"+templatetype+" and value is "+Templatename);
	    	}
	    	logger.log(LogStatus.PASS, "End Case: positive login successfull");
	    	
	    	
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
	
	 
	
	 
	
//	 @DataProvider
//	 public Object[][] dp() throws IOException {
//	   List<Object[]> result = Lists.newArrayList();
//	   result.addAll(Arrays.asList(Loginpositiveandnegative()));
//	   result.addAll(Arrays.asList(experimentTemplate()));
//	   return result.toArray(new String[result.size()][]);
//	 }
	 
	 
//	 public Object[][] combinedDataProvider() {
//		    // Using stream to combine the two separate data providers.
//		    return Stream.of(Loginpositiveandnegative(), experimentTemplate())
//		                 .flatMap(Arrays::stream)
//		                 .toArray(Object[][]::new);
//		}
	 
	 
	 @DataProvider
	  public String[][] Loginpositiveandnegative() throws IOException{
		  String[][] testOBJArray=null;
		 
				
				   
		  System.out.println("configfile reader value is :"+ConfigFileReader("runon"));
		 if(ConfigFileReader("runon").equalsIgnoreCase("local")) {
			  
				  if(ConfigFileReader("environment").equalsIgnoreCase("srv18")) {
			  testOBJArray=getdata(testdatasheetpath,"Experimenttemplate");
			  
				  }
				  //write for other env here
				  
			  
		 }else if(ConfigFileReader("runon").equalsIgnoreCase("jenkin")) {
			 
				 if(System.getenv("environment").equalsIgnoreCase("srv18")) {
					 System.out.println("executing with jenkin variables dataprovider in srv18");
					 testOBJArray=getdata(testdatasheetpath,"Experimenttemplate");
				 }
				 //write for other env here.
				  
				  
		 }
		
		   return testOBJArray;
	  }
	    
	 
	 
	 
//	  public Object[][] experimentTemplate() throws IOException{
//		  Object[][] testOBJArray=null;
//		  Object[][] testOBJArray2=null;
//		  System.out.println("configfile reader value is :"+ConfigFileReader("runon"));
//		 if(ConfigFileReader("runon").equalsIgnoreCase("local")) {
//			  
//				  if(ConfigFileReader("environment").equalsIgnoreCase("srv18")) {
//			 // testOBJArray=getdata(testdatasheetpath,"LoginPositiveScenario_srv18");
//			  testOBJArray2=getdata(testdatasheetpath,"Experimenttemplate");
//				  }
//				  //write for other env here
//				  
//			 
//		 }else if(ConfigFileReader("runon").equalsIgnoreCase("jenkin")) {
//			 
//				 if(System.getenv("environment").equalsIgnoreCase("srv18")) {
//					 System.out.println("executing with jenkin variables dataprovider in srv18");
//					 testOBJArray=getdata(testdatasheetpath,"LoginPositiveScenario_srv18");
//				 }
//				 //write for other env here.
//				  
//				  
//		 }
//		
//		 
//		return testOBJArray2;
//	  }
//	    
}

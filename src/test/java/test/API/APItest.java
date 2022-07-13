package test.API;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import net.bytebuddy.NamingStrategy.SuffixingRandom.BaseNameResolver.ForGivenType;
import org.testng.Assert;
import test.Utility.BaseClass;

import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.IOException;

import javax.xml.crypto.Data;

import org.hamcrest.Matchers.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.io.Files;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
public class APItest extends BaseClass {
	
	ExtentTest logger;
	static String nameofid;
	@Test(enabled = true ,dataProvider = "postAPIData",groups = "API_Test")
	
	public void apimethod(String BaseURI,String Method,String Header_Key,String Header_Value,String status_code) {

		logger=extent.startTest("Validate attrribute name of experiment");
		int statuscode=Integer.parseInt(status_code);  
		RestAssured.baseURI=BaseURI;


		String response=given().header(Header_Key,Header_Value).when().get().then().log().all().statusCode(statuscode).extract().response().asString();
		
		logger.log(LogStatus.PASS, "Response code is as expected:"+statuscode);

		
	}

	 
	 @DataProvider
	 public String[][] postAPIData() throws IOException{
		  String[][] testOBJArray=null;
				   
		  System.out.println("configfile reader value is :"+ConfigFileReader("runon"));
		 if(ConfigFileReader("runon").equalsIgnoreCase("local")) {
			  
				  if(ConfigFileReader("environment").equalsIgnoreCase("srv18")) {
			  testOBJArray=getdata(testdatasheetpath,"GetAPI");
			  
				  }
				  //write for other env here
				  
			  
		 }else if(ConfigFileReader("runon").equalsIgnoreCase("jenkin")) {
			 
				 if(System.getenv("environment").equalsIgnoreCase("srv18")) {
					 System.out.println("executing with jenkin variables dataprovider in srv18");
					 testOBJArray=getdata(testdatasheetpath,"PostAPI_AttributeCreation");
				 }
			 //write for other env here
		 }
		
		   return testOBJArray;
	  }
}

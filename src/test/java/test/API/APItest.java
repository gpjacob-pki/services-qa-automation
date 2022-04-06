package test.API;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import net.bytebuddy.NamingStrategy.SuffixingRandom.BaseNameResolver.ForGivenType;
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
	@Test(enabled = true ,dataProvider = "getAPIData",groups = "API_Test")
	
	public void apimethod(String BaseURI,String Method,String Header_Key,String Header_Value,String Validationid,String status_code) {
		logger=extent.startTest("Validate attrribute name of experiment");
		int statuscode=Integer.parseInt(status_code);  
		RestAssured.baseURI=BaseURI;
		String response=given().header(Header_Key,Header_Value).when().get().then().log().all().statusCode(statuscode).extract().response().asString();
		
		logger.log(LogStatus.PASS, "Response code is as expected:"+statuscode);
		JsonPath js=new JsonPath(response);
		int length=js.getInt("data.size()");
		
		
		
		for(int i=0;i<length;i++) {
			String experimentidActual=js.getString("data["+i+"].id");
			if(experimentidActual.equals(Validationid)) {
				
				 nameofid=js.getString("data["+i+"].attributes.name");
				 logger.log(LogStatus.PASS, "experiment id: "+experimentidActual+" name is :"+nameofid);
				 break;
			}
		}
		
		
	}
	
	
	@Test(enabled = true ,dataProvider = "postAPIData",groups = "API_Test")
	public void API_AttributelistCration(String BaseURI,String Method,String Header_Key,String Header_Value,String Validationid,String status_code,String jsonfilename) throws Exception {
		logger=extent.startTest("Create_Validate_Delete_Attribute list");
		int statuscode=Integer.parseInt(status_code);  
		String[] split=Validationid.split(",");
		
		
		//code to  create list attributes
		RestAssured.baseURI=BaseURI;
		//String response=given().header(Header_Key,Header_Value).header("Content-Type","application/vnd.api+json").body(replaceallvalue(readjsonfile(jsonfilename),"RestAssured_Test_1","RestAssured_Test_2","RestAssured_Test_3",split)).when().post().then().log().all().statusCode(statuscode).extract().response().asString();
		String response=given().header(Header_Key,Header_Value).header("Content-Type","application/vnd.api+json").body(replaceallvalue(readjsonfile(jsonfilename),"\"RestAssured_Test_1\"",split)).when().post().then().log().all().statusCode(statuscode).extract().response().asString();
		
		logger.log(LogStatus.PASS, "Response code is as expected:"+statuscode);
		JsonPath js=new JsonPath(response);
		int length=js.getInt("data.attributes.options.size()");		
		
		String attributeID=js.getString("data.attributes.id");
		logger.log(LogStatus.PASS, "Attribute id for created attribute is : "+attributeID);
		for(int i=0;i<length;i++) {
			String ValidationidActual=js.getString("data.attributes.options["+i+"]");
				
			if(ValidationidActual.equals(split[i])) {
				
				logger.log(LogStatus.PASS, "value of list matches  for "+ValidationidActual);
				
			}else {
				
				logger.log(LogStatus.FAIL, "value of list doesnot matches  for "+ValidationidActual);
			}
		}
		
		//code to validate list attribute created successfully or not
		RestAssured.baseURI=BaseURI+"/"+attributeID;
		String response2=given().header(Header_Key,Header_Value).header("Content-Type","application/vnd.api+json").when().get().then().log().all().statusCode(200).extract().response().asString();
		JsonPath js2=new JsonPath(response2);
		int length2=js2.getInt("data.attributes.options.size()");		
		
		for(int i=0;i<length;i++) {
			String ValidationidActual2=js2.getString("data.attributes.options["+i+"]");
				
			if(ValidationidActual2.equals(split[i])) {
				
				logger.log(LogStatus.PASS, "List value is created successfully  for "+ValidationidActual2);
				
			}else {
				
				logger.log(LogStatus.PASS, "List value is not created successfully  for "+ValidationidActual2);
			}
		
	}
		
		//code to delete created list attribute wit the help of attribute id.
		RestAssured.baseURI=BaseURI+"/"+attributeID;
		String response3=given().header(Header_Key,Header_Value).header("Content-Type","application/vnd.api+json").when().delete().then().log().all().statusCode(204).extract().response().asString();
		JsonPath js3=new JsonPath(response3);
		logger.log(LogStatus.PASS, "List value is created is also  deleted successfully for attribute with id:"+attributeID);
	
	}
	
	
	 @DataProvider
	  public String[][] getAPIData() throws IOException{
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
					 testOBJArray=getdata(testdatasheetpath,"GetAPI");
				 }
				 //write for other env here.
				  
				  
		 }
		
		   return testOBJArray;
	  }
	 
	 
	 
	 @DataProvider
	 public String[][] postAPIData() throws IOException{
		  String[][] testOBJArray=null;
		 
				
				   
		  System.out.println("configfile reader value is :"+ConfigFileReader("runon"));
		 if(ConfigFileReader("runon").equalsIgnoreCase("local")) {
			  
				  if(ConfigFileReader("environment").equalsIgnoreCase("srv18")) {
			  testOBJArray=getdata(testdatasheetpath,"PostAPI_AttributeCreation");
			  
				  }
				  //write for other env here
				  
			  
		 }else if(ConfigFileReader("runon").equalsIgnoreCase("jenkin")) {
			 
				 if(System.getenv("environment").equalsIgnoreCase("srv18")) {
					 System.out.println("executing with jenkin variables dataprovider in srv18");
					 testOBJArray=getdata(testdatasheetpath,"PostAPI_AttributeCreation");
				 }
				 //write for other env here.
				  
				  
		 }
		
		   return testOBJArray;
	  }
}

package test.API;
import com.mongodb.util.JSON;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

import io.restassured.response.Response;
import org.bson.io.BsonOutput;
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
	
	public void apimethod(String BaseURI,String Method,String Header_Key,String Header_Value,String status_code, String Name, String ValidationValue, String ValidationKey, String ValidationKeyValue) {

		logger=extent.startTest("Validating the "+Name+"API");

		System.out.println(Name);

		int statuscode=Integer.parseInt(status_code);

		String base = ConfigFileReader("URL")+BaseURI;

		System.out.println("The base is :"+base);
		RestAssured.baseURI=base;


		System.out.println(Name);
		Response response=given().header(Header_Key,Header_Value).when().get().then().log().all().statusCode(statuscode).extract().response();


		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, statuscode);
		logger.log(LogStatus.PASS, "Response code is as expected:"+statuscode);

         //Validating whether the string is JSON or not
         String responseBody = response.getBody().asPrettyString();
		 Assert.assertEquals(isJsonString(responseBody), true);

		 //Validating whether the response is according to the expected values
		 JsonPath jsonPath = response.jsonPath();

		System.out.println(jsonPath.getString("data.id[0]"));

		System.out.println(jsonPath.getString(ValidationKey));

		//Validating for the response which have id inside the data section
        if(jsonPath.getString("data.id[0]")!=null) {

			System.out.println("Entering the loop");
			int length = jsonPath.getInt("data.size()");


			for (int i = 0; i < length; i++) {
				String actual = jsonPath.getString("data.id["+i+"]");
				if (actual.equals(ValidationValue)) {

					logger.log(LogStatus.PASS, "The id matches with the expected" + actual);

					System.out.println("The id matches with the expected" + actual);
					break;
				}
			else {
						Assert.fail("The id is not present");
					logger.log(LogStatus.FAIL, "The id does not match with the expected");
					break;
				}
			}

		}

        //Validating for other which doesn't have 'data' field
		else if(jsonPath.getString(ValidationKeyValue)!=null){

          String actual = jsonPath.getString(ValidationKey);

		  if(actual.equals(ValidationKeyValue)){

			  logger.log(LogStatus.PASS, "The id matches with the expected" + actual);

		  }


		}

	}



	public boolean isJsonString(String response) {
		try {
			JSON.parse(response);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	 
	 @DataProvider
	 public String[][] postAPIData() throws IOException{
		  String[][] testOBJArray=null;
				   
		  System.out.println("configfile reader value is :"+ConfigFileReader("runon"));
		 if(ConfigFileReader("runon").equalsIgnoreCase("local")) {
			  
				  if(ConfigFileReader("environment").equalsIgnoreCase("srv18")) {
			  testOBJArray=getdata(testdatasheetpath,"GetAPIS");
			  
				  }
				  //write for other env here
				  
			  
		 }else if(ConfigFileReader("runon").equalsIgnoreCase("jenkin")) {
			 
				 if(System.getenv("environment").equalsIgnoreCase("srv18")) {
					 System.out.println("executing with jenkin variables dataprovider in srv18");
					 testOBJArray=getdata(testdatasheetpath,"GetAPIS");
				 }
			 //write for other env here
		 }
		
		   return testOBJArray;
	  }
}

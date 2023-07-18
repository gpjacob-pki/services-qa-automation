package test.API;


import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.mongodb.util.JSON;
import com.relevantcodes.extentreports.ExtentReports;
import io.restassured.RestAssured;
import io.restassured.internal.path.json.JSONAssertion;
import io.restassured.path.json.JsonPath;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

import io.restassured.response.Response;

import org.apache.commons.lang3.StringUtils;
import org.bson.io.BsonOutput;
import org.jsoup.helper.StringUtil;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import test.Utility.BaseClass;

import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.crypto.Data;

import org.hamcrest.Matchers.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.io.Files;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import test.Utility.FlatMapUtil;


public class APItest extends BaseClass {

    ExtentTest logger;

    ExtentReports extent = new ExtentReports("reports");


    @Test(enabled = true, dataProvider = "postAPIData", groups = "API_Tests")
	public void apimethod(String BaseURI, String Method, String Header_Key, String Header_Value, String status_code, String Name, String expectedResponse, String clientName) throws IOException {

        SoftAssert softAssert = new SoftAssert();
        

        logger = extent.startTest("Validating the " + Name + "API");

        System.out.println(Name);

        int statuscode = Integer.parseInt(status_code);

        String base = ConfigFileReader("URL") + BaseURI;

        System.out.println("The base is :" + base);

        RestAssured.baseURI = base;

        RestAssured.useRelaxedHTTPSValidation();

        System.out.println(Name);
        Response response = given().header(Header_Key, Header_Value).when().get().then().log().all().extract().response();


        int statusCode = response.getStatusCode();
        softAssert.assertEquals(statusCode, statuscode);
        logger.log(LogStatus.PASS, "The clients using this API are" +clientName);
        if(statusCode==statuscode){
        logger.log(LogStatus.PASS, "Response code is as expected: " + statusCode);}
        else if(statusCode!=statuscode){
            logger.log(LogStatus.FAIL, "The Response is not as expected, Getting error code as " +statusCode);

        }

        //Validating whether the string is JSON or not
        String responseBody = response.getBody().asPrettyString();
        softAssert.assertEquals(isJsonString(responseBody), true);
        Boolean flag = isJsonString(responseBody);

        System.out.println("The response is Json "+flag);

        Assert.assertTrue(flag, "The response is not json");

        //Comparing responses as JSON
        if (flag) {
            try {
                Gson gson = new Gson();
                Type type = new TypeToken<Map<String, Object>>() {
                }.getType();

                Map<String, Object> leftMap = gson.fromJson(responseBody, type);
                Map<String, Object> rightMap = gson.fromJson(expectedResponse, type);


                Map<String, Object> leftFlatMap = FlatMapUtil.flatten(leftMap);
                Map<String, Object> rightFlatMap = FlatMapUtil.flatten(rightMap);

                MapDifference<String, Object> difference = Maps.difference(leftFlatMap, rightFlatMap);

                System.out.println("Entries only on the actual response\n--------------------------");
                logger.log(LogStatus.INFO, "Entries only on the actual response\n--------------------------");
                difference.entriesOnlyOnLeft()
                        .forEach((key, value) -> System.out.println(key + ": " + value));
                difference.entriesOnlyOnLeft()
                        .forEach((key, value) -> logger.log(LogStatus.INFO,key + ": " + value));

                System.out.println("\n\nEntries only on the expected response\n--------------------------");
                logger.log(LogStatus.INFO,"\n\nEntries only on the expected response\n--------------------------");
                difference.entriesOnlyOnRight()
                        .forEach((key, value) -> System.out.println(key + ": " + value));
                difference.entriesOnlyOnRight()
                        .forEach((key, value) -> logger.log(LogStatus.INFO,key + ": " + value));

                System.out.println("\n\nEntries differing\n--------------------------");
                logger.log(LogStatus.INFO, "\n\nEntries differing\n--------------------------");
                difference.entriesDiffering()
                        .forEach((key, value) -> System.out.println(key + ": " + value));
                difference.entriesDiffering()
                        .forEach((key, value) -> logger.log(LogStatus.INFO,key + ": " + value));


            } catch (JsonSyntaxException e){


            }

        } else {
            softAssert.assertTrue(responseBody.equals(expectedResponse));
            String difference = StringUtils.difference(responseBody, expectedResponse);
            System.out.println("********************************************************************************");
            logger.log(LogStatus.INFO, "********************************************************************************");
            System.out.println("The difference is " + difference);
            logger.log(LogStatus.INFO, "The difference is " + difference);
        }

        extent.flush();

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
    public String[][] postAPIData() throws IOException {
        String[][] testOBJArray = null;

        System.out.println("configfile reader value is :" + ConfigFileReader("runon"));
        if (ConfigFileReader("runon").equalsIgnoreCase("local")) {

            if (ConfigFileReader("environment").equalsIgnoreCase("srv18")) {
                testOBJArray = getdata(testdatasheetpath, "CompareAPIs");

            }
            //write for other env here


        } else if (ConfigFileReader("runon").equalsIgnoreCase("jenkin")) {

            if (System.getenv("environment").equalsIgnoreCase("srv18")) {
                System.out.println("executing with jenkin variables dataprovider in srv18");
                testOBJArray = getdata(testdatasheetpath, "CompareAPIs");
            }
            //write for other env here
        }

        return testOBJArray;
    }
}

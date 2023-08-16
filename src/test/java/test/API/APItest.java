package test.API;


import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.gson.*;
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

    @Test( dataProvider = "postAPIData")
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
        //Response response = given().header(Header_Key, Header_Value).when().get().then().log().all().extract().response();
        Response response = given().header(Header_Key, ConfigFileReader(Header_Key)).when().get().then().log().all().extract().response();

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, statuscode);
        logger.log(LogStatus.PASS, "The clients using this API are " + clientName);
        if (statusCode == statuscode) {
            logger.log(LogStatus.PASS, "Response code is as expected: " + statusCode);
        } else if (statusCode != statuscode) {
            logger.log(LogStatus.FAIL, "The Response is not as expected, Getting error code as " + statusCode);

        }

        //Validating whether the string is JSON or not
        String responseBody = response.getBody().asPrettyString();
        softAssert.assertEquals(isJsonString(responseBody), true);
        Boolean flag = isJsonString(responseBody);

        System.out.println("The response is Json " + flag);

        logger.log(LogStatus.INFO, "The response is JSON");

        //Assert.assertTrue(flag, "The response is not json");

        //Comparing responses as JSON
        if (flag) {
            try {
                Gson gson = new Gson();
                JsonElement leftJsonElement = JsonParser.parseString(responseBody);
                JsonElement rightJsonElement = JsonParser.parseString(expectedResponse);

                if (leftJsonElement.isJsonObject() && rightJsonElement.isJsonObject()) {
                    JsonObject leftJsonObject = leftJsonElement.getAsJsonObject();
                    JsonObject rightJsonObject = rightJsonElement.getAsJsonObject();

                    Type type = new TypeToken<Map<String, Object>>() {
                    }.getType();

                    Map<String, Object> leftMap = gson.fromJson(responseBody, type);
                    Map<String, Object> rightMap = gson.fromJson(expectedResponse, type);


                    Map<String, Object> leftFlatMap = FlatMapUtil.flatten(leftMap);
                    Map<String, Object> rightFlatMap = FlatMapUtil.flatten(rightMap);

                    MapDifference<String, Object> difference = Maps.difference(leftFlatMap, rightFlatMap);

                    System.out.println("Entries only on the actual response\n--------------------------");
                    logger.log(LogStatus.ERROR, "Entries only on the actual response\n--------------------------");
                    difference.entriesOnlyOnLeft()
                            .forEach((key, value) -> System.out.println(key + ": " + value));
                    difference.entriesOnlyOnLeft()
                            .forEach((key, value) -> logger.log(LogStatus.INFO, key + ": " + value));

                    System.out.println("\n\nEntries only on the expected response\n--------------------------");
                    logger.log(LogStatus.ERROR, "\n\nEntries only on the expected response\n--------------------------");
                    difference.entriesOnlyOnRight()
                            .forEach((key, value) -> System.out.println(key + ": " + value));
                    difference.entriesOnlyOnRight()
                            .forEach((key, value) -> logger.log(LogStatus.INFO, key + ": " + value));

                    System.out.println("\n\nEntries differing\n--------------------------");
                    logger.log(LogStatus.INFO, "\n\nEntries differing\n--------------------------");
                    difference.entriesDiffering()
                            .forEach((key, value) -> System.out.println(key + ": " + value));
                    difference.entriesDiffering()
                            .forEach((key, value) -> logger.log(LogStatus.INFO, key + ": " + value));

                } else if (leftJsonElement.isJsonArray() && rightJsonElement.isJsonArray()) {
                    JsonArray leftJsonArray = leftJsonElement.getAsJsonArray();
                    JsonArray rightJsonArray = rightJsonElement.getAsJsonArray();

                    JsonObject leftJsonObject =  new JsonObject();
                    leftJsonObject.add("",leftJsonArray);

                    JsonObject rightJsonObject =  new JsonObject();
                    rightJsonObject.add("",rightJsonArray);

                    getDifferencesFromObject(leftJsonObject,rightJsonObject);

                    return;


                    // Handle JSON arrays comparison
                   /* for (int i = 0; i < leftJsonArray.size() && i < rightJsonArray.size(); i++) {
                        JsonElement leftElement = leftJsonArray.get(i);
                        JsonElement rightElement = rightJsonArray.get(i);
                        if(leftElement.isJsonObject() && rightElement.isJsonObject())
                        {

                        } else if (leftElement.isJsonPrimitive() && rightElement.isJsonPrimitive()) {
                            if (!leftElement.equals(rightElement)) {
                                System.out.println("Difference in array element at index " + i);
                            }
                        }

                    }

                    // Entries only on the actual response
                    leftJsonArray.forEach(leftElement -> {
                        if (!rightJsonArray.contains(leftElement)) {
                            System.out.println("Entry only in actual response: " + leftElement);
                            logger.log(LogStatus.INFO, "\n\nEntries only on the expected response\n--------------------------");
                        }
                    });

                    // Entries only on the expected response
                    rightJsonArray.forEach(rightElement -> {
                        if (!leftJsonArray.contains(rightElement)) {
                            System.out.println("Entry only in expected response: " + rightElement);
                            logger.log(LogStatus.INFO, "\n\nEntries only on the expected response\n--------------------------");
                        }
                    });*/
                } else {
                    System.out.println("Invalid JSON input");

                }
            } catch (JsonSyntaxException e) {

                System.out.println("There is some issue in the JSON");
                logger.log(LogStatus.ERROR, "Not a valid json");
            }


        } else {
            softAssert.assertTrue(responseBody.equals(expectedResponse));
            String difference = StringUtils.difference(responseBody, expectedResponse);
            System.out.println("********************************************************************************");
            logger.log(LogStatus.INFO, "********************************************************************************");
            System.out.println("The difference is " + difference);
            logger.log(LogStatus.INFO, "The difference is " + difference);
        }

    }

    public void getDifferencesFromObject(JsonObject leftJsonObject, JsonObject rightJsonObject)
    {
        Gson gson = new Gson();

        Type type = new TypeToken<Map<String, Object>>() {
        }.getType();

        Map<String, Object> leftMap = gson.fromJson(leftJsonObject, type);
        Map<String, Object> rightMap = gson.fromJson(rightJsonObject, type);


        Map<String, Object> leftFlatMap = FlatMapUtil.flatten(leftMap);
        Map<String, Object> rightFlatMap = FlatMapUtil.flatten(rightMap);

        MapDifference<String, Object> difference = Maps.difference(leftFlatMap, rightFlatMap);

        System.out.println("Entries only on the actual response\n--------------------------");
        logger.log(LogStatus.ERROR, "Entries only on the actual response\n--------------------------");
        difference.entriesOnlyOnLeft()
                .forEach((key, value) -> System.out.println(key + ": " + value));
        difference.entriesOnlyOnLeft()
                .forEach((key, value) -> logger.log(LogStatus.WARNING, key + ": " + value));

        System.out.println("\n\nEntries only on the expected response\n--------------------------");
        logger.log(LogStatus.ERROR, "\n\nEntries only on the expected response\n--------------------------");
        difference.entriesOnlyOnRight()
                .forEach((key, value) -> System.out.println(key + ": " + value));
        difference.entriesOnlyOnRight()
                .forEach((key, value) -> logger.log(LogStatus.WARNING, key + ": " + value));

        System.out.println("\n\nEntries differing\n--------------------------");
        logger.log(LogStatus.INFO, "\n\nEntries differing\n--------------------------");
        difference.entriesDiffering()
                .forEach((key, value) -> System.out.println(key + ": " + value));
        difference.entriesDiffering()
                .forEach((key, value) -> logger.log(LogStatus.INFO, key + ": " + value));
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

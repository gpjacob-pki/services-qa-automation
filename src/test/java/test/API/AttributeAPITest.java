package test.API;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.Utility.BaseClass;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.head;

public class AttributeAPITest extends BaseClass {

    ExtentTest logger;

    @Test(enabled = true, dataProvider = "postAPIData", groups = "API_Test")
    public void attributeAPIValidation(String baseURI, String headerKey, String headerValue, String expectedStatusCode, String name, String expectedResponse){

        logger = extent.startTest("Validating the attribute API response");


        String base = ConfigFileReader("URL") + baseURI;

        RestAssured.baseURI = base;

        int statuscode = Integer.parseInt(expectedStatusCode);

        Response response = given().header(headerKey, headerValue).when().get().then().log().all().statusCode(statuscode).extract().response();

        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(actualStatusCode, statuscode);
        logger.log(LogStatus.INFO, "Response code is as expected:" + actualStatusCode);

        String responseBody = response.getBody().asPrettyString();

        if(responseBody.equals(expectedResponse)){
            System.out.printf("The response matches");
            logger.log(LogStatus.PASS, "The response matches with the expected");

        }
        else{
            System.out.printf("The response didn't match");
            Assert.fail("The response didn't match");
            logger.log(LogStatus.FAIL, "The response didn't match");
        }

    }

    @DataProvider
    public String[][] postAPIData() throws IOException {
        String[][] testOBJArray = null;

        System.out.println("configfile reader value is :" + ConfigFileReader("runon"));
        if (ConfigFileReader("runon").equalsIgnoreCase("local")) {

            if (ConfigFileReader("environment").equalsIgnoreCase("srv18")) {
                testOBJArray = getdata(testdatasheetpath, "Attribute");

            }
            //write for other env here


        } else if (ConfigFileReader("runon").equalsIgnoreCase("jenkin")) {

            if (System.getenv("environment").equalsIgnoreCase("srv18")) {
                System.out.println("executing with jenkin variables dataprovider in srv18");
                testOBJArray = getdata(testdatasheetpath, "GetAPIS");
            }
            //write for other env here
        }

        return testOBJArray;
    }
}

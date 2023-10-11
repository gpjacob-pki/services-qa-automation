package test.API;


import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.mongodb.util.JSON;
import com.relevantcodes.extentreports.ExtentReports;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.path.json.JSONAssertion;
import io.restassured.path.json.JsonPath;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

import io.restassured.response.Response;

import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.bson.io.BsonOutput;
import org.jsoup.helper.StringUtil;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import test.Utility.BaseClass;

import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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

    @Test(priority = 1, dataProvider = "postAPIData")
    public void apimethod(String BaseURI, String Method, String Header_Key, String status_code, String Name, String expectedResponse, String clientName , String lastautoupdate, String isAutoUpdate, String jsonfilename, String bodyJson) throws Exception {


        SoftAssert softAssert = new SoftAssert();

        String base = ConfigFileReader("URL") + BaseURI;

        logger = extent.startTest(BaseURI);

        System.out.println(Name);

        int statuscode = Integer.parseInt(status_code);

        System.out.println("The base is :" + base);

        RestAssured.baseURI = ConfigFileReader("URL") ;

        logger.log(LogStatus.INFO,base);

        logger.log(LogStatus.INFO,"Header: " + Header_Key);

        RestAssured.useRelaxedHTTPSValidation();
        System.out.println(Name);

        Response response;

        if(!Method.equalsIgnoreCase("get")){
           if(jsonfilename.equalsIgnoreCase("Yes")) {
               response = RestAssured.given().header(Header_Key, ConfigFileReader(Header_Key))
                       .header("Content-Type", "application/vnd.api+json")
                       .header("Accept", "application/vnd.api+json")
                       .body(bodyJson).
                       when().
                       post(base).
                       then().
                       log().
                       all().extract().response();
           } else {
               bodyJson = readjsonfile(jsonfilename);
               response = postData(base, ConfigFileReader(Header_Key),new HashMap<>(), bodyJson);
           }

           int statusCode = response.getStatusCode();

           String responsebody = response.getBody().asPrettyString();

           if(statusCode==statuscode){
               logger.log(LogStatus.PASS, "The status code matches with the expected, the response code is "+statusCode);
           } else {

               System.out.println("The response code is "+statusCode);

               logger.log(LogStatus.FAIL, "The http response code is "+statusCode);
               logger.log(LogStatus.FAIL, "The error is "+responsebody);
           }
           return;
       }

        response = RestAssured.given().header(Header_Key, ConfigFileReader(Header_Key))
                .when()
                .get(BaseURI)
                .then().log().all().extract().response();

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
        RestAssured.reset();
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

                    processObjects(leftJsonObject,rightJsonObject);


                    Type type = new TypeToken<Map<String, Object>>() {
                    }.getType();

                    Map<String, Object> leftMap = gson.fromJson(leftJsonObject, type);
                    Map<String, Object> rightMap = gson.fromJson(rightJsonObject, type);


                    Map<String, Object> leftFlatMap = FlatMapUtil.flatten(leftMap);
                    Map<String, Object> rightFlatMap = FlatMapUtil.flatten(rightMap);

                    MapDifference<String, Object> difference = Maps.difference(leftFlatMap, rightFlatMap);

                    System.out.println( "----------------\t Only in actual response\t----------------------------");
                    logger.log(LogStatus.INFO, "----------------\t Only in actual response\t----------------------------");
                    Map<String,Object> differenceLeftSorted =  new HashMap<String,Object>();
                    differenceLeftSorted = removeRepeatingFlags(difference.entriesOnlyOnLeft());
                    differenceLeftSorted
                            .forEach((key, value) -> logger.log(LogStatus.WARNING, key));

                    differenceLeftSorted
                            .forEach((key, value) ->  System.out.println(key));

                    System.out.println( "----------------\t Only in Expected response\t----------------------------");
                    logger.log(LogStatus.INFO, "----------------\t Only in Expected response\t----------------------------");
                    Map<String,Object> differenceRightSorted =  new HashMap<String,Object>();
                    differenceRightSorted = removeRepeatingFlags(difference.entriesOnlyOnRight());

                    differenceRightSorted
                            .forEach((key, value) -> logger.log(LogStatus.WARNING, key));

                    differenceRightSorted
                            .forEach((key, value) ->  System.out.println(key));

                   /* System.out.println("Entries only on the actual response\n--------------------------");
                    logger.log(LogStatus.INFO, "Entries only on the actual response\n--------------------------");
                    difference.entriesOnlyOnLeft()
                            .forEach((key, value) -> System.out.println(key + ": " + value));
                    difference.entriesOnlyOnLeft()
                            .forEach((key, value) -> logger.log(LogStatus.INFO, key + ": " + value));

                    System.out.println("\n\nEntries only on the expected response\n--------------------------");
                    logger.log(LogStatus.INFO, "\n\nEntries only on the expected response\n--------------------------");
                    difference.entriesOnlyOnRight()
                            .forEach((key, value) -> System.out.println(key + ": " + value));
                    difference.entriesOnlyOnRight()
                            .forEach((key, value) -> logger.log(LogStatus.INFO, key + ": " + value));

                    System.out.println("\n\nEntries differing\n--------------------------");
                    logger.log(LogStatus.INFO, "\n\nEntries differing\n--------------------------");
                    difference.entriesDiffering()
                            .forEach((key, value) -> System.out.println(key + ": " + value));
                    difference.entriesDiffering()
                            .forEach((key, value) -> logger.log(LogStatus.INFO, key + ": " + value));*/

                }
                else if (leftJsonElement.isJsonArray() && rightJsonElement.isJsonArray()) {
                    JsonArray leftJsonArray = leftJsonElement.getAsJsonArray();
                    JsonArray rightJsonArray = rightJsonElement.getAsJsonArray();

                    JsonObject leftJsonObject =  new JsonObject();
                    leftJsonObject.add("",leftJsonArray);

                    JsonObject rightJsonObject =  new JsonObject();
                    rightJsonObject.add("",rightJsonArray);

                    processObjects(leftJsonObject,rightJsonObject);

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
            logger.log(LogStatus.WARNING, "The difference is " + difference);
        }

    }



    public void updateLatestResponses() throws IOException
    {

        FileInputStream fis= new  FileInputStream(testdatasheetpath);
        HSSFWorkbook workbook=new HSSFWorkbook(fis);
        HSSFSheet sheet= workbook.getSheet("CompareAPIs");
        int columns= sheet.getRow(0).getLastCellNum();
        int totalRow=sheet.getPhysicalNumberOfRows();

        Map<String, Integer> colMapByName = new HashMap<String, Integer>();
        if (sheet.getRow(0).cellIterator().hasNext()) {
            for (int j = 0; j < columns; j++) {
                colMapByName.put(sheet.getRow(0).getCell(j).getStringCellValue().toLowerCase(), j);
            }
        }

        int indexBaseUri =colMapByName.get("baseuri").intValue();
        int indexER =colMapByName.get("expected response").intValue();

        for(int rowNum=1;rowNum < totalRow;rowNum++) {
            HSSFRow row =sheet.getRow(rowNum);

            Boolean isautoupdate =  false;

            try {
                isautoupdate =   row.getCell(colMapByName.get("isautoupdate").intValue()).getBooleanCellValue();

            }
            catch(Exception ex){

            }

            if(!isautoupdate)
            {
               continue;
            }

            HSSFCell cellBaseUri = row.getCell(indexBaseUri);
            Cell cellER = row.getCell(indexER);
            Cell cellLastUpdate = row.getCell(colMapByName.get("lastautoupdate").intValue());
            String baseuri   =  cellBaseUri.getStringCellValue();
            System.out.print(baseuri + "\t");
            String Header_Key = row.getCell(colMapByName.get("header_key").intValue()).getStringCellValue();
            RestAssured.baseURI = ConfigFileReader("URL") ;
            Response response = RestAssured.given().header(Header_Key, ConfigFileReader(Header_Key))
                    .when()
                    .get(baseuri)
                    .then().log().all().extract().response();
            System.out.print(response.statusCode());

            String responseBody = response.getBody().asPrettyString();

            cellER.setCellValue(responseBody);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            if(cellLastUpdate == null)
            {
                cellLastUpdate =  row.createCell(colMapByName.get("lastautoupdate").intValue());
            }
            cellLastUpdate.setCellValue(dateFormat.format(date));



        }

        fis.close();

        FileOutputStream os = new FileOutputStream(testdatasheetpath);
        workbook.write(os);

        //Close the workbook and output stream
        workbook.close();
        os.close();

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
        Map<String,Object> differenceLeftSorted =  new HashMap<String,Object>();
        differenceLeftSorted = removeRepeatingFlags(difference.entriesOnlyOnLeft());

        logger.log(LogStatus.INFO, "----------------\t Only in actual response\t----------------------------");
        System.out.println( "----------------\t Only in actual response\t----------------------------");
        differenceLeftSorted
                .forEach((key, value) -> logger.log(LogStatus.INFO, key));


        differenceLeftSorted
                .forEach((key, value) ->  System.out.println(key));

        System.out.println( "----------------\t Only in Expected response\t----------------------------");
        logger.log(LogStatus.INFO, "----------------\t Only in Expected response\t----------------------------");
        Map<String,Object> differenceRightSorted =  new HashMap<String,Object>();
        differenceRightSorted = removeRepeatingFlags(difference.entriesOnlyOnRight());

        differenceRightSorted
                .forEach((key, value) -> logger.log(LogStatus.INFO, key));

        differenceRightSorted
                .forEach((key, value) ->  System.out.println(key));
        /*
        System.out.println("Entries only on the actual response\n--------------------------");
        logger.log(LogStatus.INFO, "Entries only on the actual response\n--------------------------");
        difference.entriesOnlyOnLeft()
                .forEach((key, value) -> System.out.println(key + ": " + value));
        difference.entriesOnlyOnLeft()
                .forEach((key, value) -> logger.log(LogStatus.WARNING, key + ": " + value));

        System.out.println("\n\nEntries only on the expected response\n--------------------------");
        logger.log(LogStatus.INFO, "\n\nEntries only on the expected response\n--------------------------");
        difference.entriesOnlyOnRight()
                .forEach((key, value) -> System.out.println(key + ": " + value));
        difference.entriesOnlyOnRight()
                .forEach((key, value) -> logger.log(LogStatus.WARNING, key + ": " + value));

        System.out.println("\n\nEntries differing\n--------------------------");
        logger.log(LogStatus.INFO, "\n\nEntries differing\n--------------------------");
        difference.entriesDiffering()
                .forEach((key, value) -> System.out.println(key + ": " + value));
        difference.entriesDiffering()
                .forEach((key, value) -> logger.log(LogStatus.INFO, key + ": " + value));*/
    }

    public boolean isJsonString(String response) {
        try {
            JSON.parse(response);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public  Map<String,Object> removeRepeatingFlags(Map<String,Object> lst)
    {
        Map<String,Object> differenceSorted =  new HashMap<String,Object>();
        for(String key : lst.keySet())
        {
            String[] keyList   =  key.split("/");
            String keyname =  "";
            for (String k : keyList )
            {
                if( k != null && !k.isEmpty())
                {
                    if(isNumeric(k))
                    {
                        keyname += "[]";
                    }else{

                        if(!keyname.isEmpty())
                            keyname += ">";


                        keyname += k;
                    }


                }

            }

            if(!differenceSorted.containsKey(keyname))
            {
                differenceSorted.put(keyname,key);
            }

        }
        return differenceSorted;
    }
    public void processObjects(JsonObject left, JsonObject right)
    {


        if(left.isJsonObject() && right.isJsonObject())
        {

            List<String> keys =  new ArrayList<String>();
            for(String ke : left.keySet())
            {
                keys.add(ke);
            }

            for(String ke : right.keySet())
            {
                if(!keys.contains(ke))
                  keys.add(ke);
            }

             for(String k : keys)
             {
                 if(!left.has(k) || !right.has(k))
                 {
                     continue;
                 }
                 if(left.get(k).isJsonNull() || right.get(k).isJsonNull())
                 {
                     continue;
                 }
                 if(left.get(k).isJsonArray() && right.get(k).isJsonArray())
                 {
                     ArrayList<JsonArray>  lst =   processjsonArray(left.get(k).getAsJsonArray() , right.get(k).getAsJsonArray());
                     left.remove(k);
                     left.add(k,lst.get(0));

                     right.remove(k);
                     right.add(k,lst.get(1));
                 }
             }
        }

    }

    public ArrayList<JsonArray> processjsonArray(JsonArray arr1 , JsonArray  arr2)
    {

          arr2 =   identifyMissingArrayObject(arr1,arr2);
          arr1 =   identifyMissingArrayObject(arr2,arr1);
          ArrayList<JsonArray> a =  new ArrayList<JsonArray>();
          a.add(arr1);
          a.add(arr2);
          return a;
    }

    public JsonArray identifyMissingArrayObject(JsonArray arr1, JsonArray  arr2) {
        JsonArray backupArray = arr2.deepCopy();
        JsonArray newArray = new JsonArray();
        try {
            for (JsonElement je : arr1) {
                if (je.isJsonObject()) {
                    JsonObject job = je.getAsJsonObject();
                     String key ="";
                    for(String kee : getIdentifiers())
                    {
                        if(job.keySet().contains(kee))
                        {
                            key = kee;
                            break;
                        }
                    }
                        if (job.keySet().contains(key)) {
                            if (arr2.size() > 0) {


                                 JsonObject job2 = null;
                                for(JsonElement je2: arr2)
                                {
                                    job2 = null;
                                    if(je2.getAsJsonObject().get(key).equals(job.get(key)))
                                    {
                                        job2 = je2.getAsJsonObject().deepCopy();
                                        break;
                                    }
                                }

                                if(job2 != null)
                                {
                                     newArray.add(job2);
                                }else{
                                    newArray.add(job);
                                }

                            }
                        }


                }
            }

            return newArray;

        } catch (Exception ex) {
            return backupArray;
        }


    }


    public String[] getIdentifiers()
    {
       String[] lst = {"id","eid","name","action","username","type"};
       return lst;
    }

    public  boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
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

    public Response postData(String url, String api_key,Map<String, String> headers ,String body)
    {
        RequestSpecification request = RestAssured.given();
        request.header("x-api-key", api_key);
        // Add headers
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            request.header(entry.getKey(), entry.getValue());
        }

        request.contentType(ContentType.JSON);
        request.body(body);
        Response response = request.post(url);


        return response;
    }
}

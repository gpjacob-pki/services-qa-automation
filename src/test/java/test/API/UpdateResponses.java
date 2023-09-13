package test.API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.testng.annotations.Test;
import test.Utility.BaseClass;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UpdateResponses extends BaseClass {


    @Test
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
            if(cellER == null) {
                cellER =  row.createCell(colMapByName.get("expected response").intValue());

            }
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

}

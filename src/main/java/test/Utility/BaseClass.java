package test.Utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

/**
 * Hello world!
 *
 */
public class BaseClass 
{
	
	//testdata sheet path.
	public String testdatasheetpath=System.getProperty("user.dir")+"//src//test//resources//testdata//users.xls";
	public String testdatasheetpathxlsx=System.getProperty("user.dir")+"//src//test//resources//testdata//testdata.xlsx";
	
	//driver and extent reports
	public static  ExtentTest test;
	public ChromeDriver driver;
	public RemoteWebDriver driver2;
	//extent report with time stamp
	static String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	
	
	//extent report location
	public static ExtentReports extent= new ExtentReports(System.getProperty("user.dir")+"\\reports\\Automationreports"+timeStamp+".html",true);
	WebDriverWait wait;
	
	public Properties properties;
	public final String propertyFilePath= System.getProperty("user.dir")+"//src//test//resources//XMLfiles//config.properties";

	//config file reader class 
	//here key is the value we need to pass to fetch a particular key value
	public String ConfigFileReader(String Key){
		String propvalue=null;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}		
	 propvalue=properties.getProperty(Key);
		return propvalue;
	}
	

	//initialize chrome driver.
    public ChromeDriver chromeinitialization() throws MalformedURLException {
    	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//src//test//resources//driver//chromedriver.exe");
		ChromeOptions option=new ChromeOptions();
		option.setHeadless(false);
		option.addArguments("window-size=1366,768");
		driver=new ChromeDriver(option);
    	return driver;
    }
    
    
    
    public RemoteWebDriver chromeinitializationremote() throws MalformedURLException {
    	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//src//test//resources//driver//chromedriver.exe");
		System.out.println(System.getProperty("user.dir")+"\\reports\\Automation");
		
		DesiredCapabilities dc=DesiredCapabilities.chrome();
		URL url=new URL("http://localhost:4444/wd/hub");
		RemoteWebDriver driver=new RemoteWebDriver(url,dc);
		
//		driver=new ChromeDriver();
    	return driver;
    }
    
    
    //javascript executor to wait until page load is complete.
    public static void javascriptexecutorwait(WebDriver driver) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		String ready=(String)js.executeScript("return document.readyState");
		System.out.println("page loaded completely");
	}
	
   
    //getdata code to fetch values from excelsheet and used in data provider
    public static String[][] getdata(String xls, String Tcname) throws IOException{
    	FileInputStream fis= new FileInputStream(xls);
    	HSSFWorkbook workbook=new HSSFWorkbook(fis);
    	HSSFSheet sheet= workbook.getSheet(Tcname);
    	int row=sheet.getPhysicalNumberOfRows();
    	int columns=sheet.getRow(0).getPhysicalNumberOfCells();
    	//System.out.println("num of rows and columns:"+row+":"+columns);
    	
    	String[][] data=new String[row-1][columns];
    	for(int rowNum=2;rowNum<=row;rowNum++) {
    		HSSFRow row1=sheet.getRow(rowNum-1);
    		for(int colNum=0;colNum<columns;colNum++) {
    			HSSFCell cell=row1.getCell(colNum);
    			DataFormatter formatter=new DataFormatter();
    			String value=formatter.formatCellValue(cell);
    			data[rowNum-2][colNum]=value;
    			//System.out.println("value is:"+data[rowNum-2][colNum]);
    		}
    	}
		return data;
    	
    }
    
    
    //base64 screenshot code
    public static String addScreenshot(WebDriver driver) {
    	
    	File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	String encodeBase64=null;
    	FileInputStream fileinputStramReader=null;
    	try {
    		fileinputStramReader=new FileInputStream(src);
    		byte[] bytes =new byte[(int)src.length()];
    		fileinputStramReader.read(bytes);
    		encodeBase64 =new String(Base64.getEncoder().encodeToString(bytes));
    	}
    	catch(FileNotFoundException e) {
    		e.printStackTrace();
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    	
    	return "data:image/png;base64,"+encodeBase64;
    }
    
    public static String[][] getcolumndata(String xls, String Tcname,int row) throws IOException{
    	FileInputStream fis= new FileInputStream(xls);
    	HSSFWorkbook workbook=new HSSFWorkbook(fis);
    	HSSFSheet sheet= workbook.getSheet(Tcname);
    	int row2=row;
    	int columns=sheet.getRow(0).getPhysicalNumberOfCells();
    	System.out.println("num of rows and columns:"+row+":"+columns);
    	
    	String[][] data=new String[row2-1][columns];
    	int rowNum=row;
    		HSSFRow row1=sheet.getRow(rowNum);
    		for(int colNum=0;colNum<columns;colNum++) {
    			HSSFCell cell=row1.getCell(colNum);
    			DataFormatter formatter=new DataFormatter();
    			String value=formatter.formatCellValue(cell);
    			data[rowNum-1][colNum]=value;
    			System.out.println("value is:"+data[rowNum-2][colNum]);
    		}
    	
		return data;
    	
    }
  	
    
    
    public Object[][] realExcel(String fileName , String sheetName,  int rowNumber,String columnname) throws IOException {
    	String fileName2 = fileName;
        POIFSFileSystem fileSystem = new POIFSFileSystem(new FileInputStream(fileName2));
        HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
    //  HSSFSheet sheet = workbook.getSheetAt(0); //Get first Excel Sheet
        HSSFSheet sheet = workbook.getSheet(sheetName); //Get data as per sheet name
        for (Row row : sheet) { // For each Row.
              Cell cell = row.getCell(0); // Get the Cell at the Index / Column you want.
              if(cell.getStringCellValue().equalsIgnoreCase("test")) {
                  System.out.println(cell.getRow().getLastCellNum());
                  for(int i=0;i<=cell.getRow().getLastCellNum()-1;i++) {
                      System.out.println(cell.getRow().getCell(i));
                  }
              }
           }
		return null;
    }
      
    
    public int randomnumgenrator() {
    	Random randomGenerator = new Random();  
		int randomInt = randomGenerator.nextInt(1000000);
		return randomInt; 
    }
    
    
    public static String readjsonfile(String filename) throws Exception {
    	String file = System.getProperty("user.dir")+"//src//test//resources//testdata//"+filename+".json";
        String json = readFileAsString(file);
        System.out.println(json);
        
		return json;		    	
    }
    
    public static String readFileAsString(String file)throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }
    
    
    public String replaceallvalue(String json,String val1,String[] split) {
    	
    	String replacedjson=json.replaceAll(val1, "\""+split[0]+"\""+",\""+split[1]+"\""+",\""+split[2]+"\"");
    	System.out.println(replacedjson);
		return replacedjson;
    }
}

package test.Regressiontest;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import test.Utility.BaseClass;

public class Aftersuite extends BaseClass{

	
	 @AfterTest(alwaysRun = true)
	 public void aftrtst() throws IOException, InterruptedException {
		 
		 
		 extent.flush();
		 try {
			 driver.quit();
		 }catch(Exception e) {
			 
		 }
//		 Runtime.getRuntime().exec("cmd /c start docker_stop.bat");
//		 Thread.sleep(15000);
//		 Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
		 
		 Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
	 }
	 
	 
	 
	 @BeforeTest(alwaysRun = true)
	 public void beforetest() throws IOException, InterruptedException {
		 System.out.println("************Before test is getting executed*********");
//		 Runtime.getRuntime().exec("cmd /c start docker_start.bat");
//		 Thread.sleep(45000);
	 }
}

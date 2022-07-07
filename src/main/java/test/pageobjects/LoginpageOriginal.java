package test.pageobjects;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.Utility.BaseClass;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Map;

public class LoginpageOriginal extends BaseClass{

	//Authenticationpage 
	By username=By.xpath("//input[@name='username']");
	By password= By.xpath("//input[@name='password']");
	By signinButton= By.xpath("//span[text()='Sign In']");
	By namedisplay=By.xpath("//div[@class='h5 brown-neutral margin-none']");
	By failedloginpage=By.xpath("//span[text()='Invalid user name or password']");
	
	

	//login scenario valid and invalid pass String method as LoginPositive or LoginNegative
	public void loginSignals(WebDriver driver,ExtentTest logger,String usernamesignals,String passwordsignals,WebDriverWait wait,String method) throws InterruptedException {
		driver.manage().window().maximize();
		wait.until(ExpectedConditions.presenceOfElementLocated(username)).sendKeys(usernamesignals);
		logger.log(LogStatus.PASS, "loginname "+usernamesignals+" entered successfully");
		wait.until(ExpectedConditions.presenceOfElementLocated(password)).sendKeys(passwordsignals);
		logger.log(LogStatus.PASS, "password for "+usernamesignals+" entered successfully");
		wait.until(ExpectedConditions.presenceOfElementLocated(signinButton)).click();
		logger.log(LogStatus.PASS, "Sign button clicked successfully successfully");
		
		
		if(method.equalsIgnoreCase("LoginPositive")) {
			try {

			wait.until(ExpectedConditions.presenceOfElementLocated(namedisplay));
			String screenshotpath=BaseClass.addScreenshot(driver);
			logger.log(LogStatus.INFO, logger.addScreenCapture(screenshotpath));
			}catch(Exception e) {
				
				wait.until(ExpectedConditions.presenceOfElementLocated(namedisplay));
				String screenshotpath=BaseClass.addScreenshot(driver);
				logger.log(LogStatus.INFO, logger.addScreenCapture(screenshotpath));
			}
			logger.log(LogStatus.PASS, "Loginpage is loaded successfully for user "+usernamesignals);

		}else if(method.equalsIgnoreCase("LoginNegative")) {
			Boolean isPresent = driver.findElements(failedloginpage).size()>0;
			if(isPresent) {
				logger.log(LogStatus.PASS, "Unauthorized user not logged in");
			}else {
				logger.log(LogStatus.FAIL, "Unauthorized user allowed to log in");
			}
			
		}
		
	}

	
	public void loginpagepopupdismiss(WebDriver driver,ExtentTest logger,WebDriverWait wait) {
		try {
			driver.findElement(By.xpath("//button[text()='Dismiss']")).click();
		}catch(Exception e) {
			
		}
	}
	
	

	

	

}

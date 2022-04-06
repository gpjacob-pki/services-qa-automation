package test.pageobjects;

import java.util.ArrayList;
import java.util.Collections;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import test.Utility.BaseClass;

public class ExperimentcreationPage extends Loginpage {
	
	
	static By actionsbutton=By.xpath("//button[@id='actions-dropdown']/i");
	static By chemicaldrawinsideexperiment=By.xpath("(//div[@class='binder__toc']//span[contains(text(),'ChemDraw')])[1]");
	static By textinsideexperiment=By.xpath("(//div[@class='binder__toc']//span[contains(text(),'Text')])[1]");
	static By predefinedvalues=By.xpath("//button[contains(@title,'Insert Predefined Value')]//span");
	static By insertTextTemplate=By.xpath("//button[contains(@title,'Insert Text Template')]//span");
	
	static By chemdrawActionsButton=By.xpath("//div[@class='binder__element binder__element--focused']//button[@id='operation-btnExternalActions']/i");
	static By dropdownmenuActionsbutton=By.xpath("//div[@class='dropdown-menu show dropdown-menu-right']/a");
	
	
	static By Actionsdropdown=By.xpath("//div[@aria-labelledby='actions-dropdown']");	
	
	
	Loginpage login=new Loginpage();
	static int experiment=0;
	static int chemicalDrawing=0;
	static int notebookcount=0;
	
	

	static int experimentcreationcount=0;
	
	
	
	Loginpage exppage=new Loginpage();
	
	
	static String actionslablepresentornot1= "//div[@aria-labelledby='actions-dropdown']/a[text()='ExternalActionslable.get(i)']";
	

	static int actionslablepresentornotfunction(WebDriver driver,String xpathValue, String substitutionValue ) {
		System.out.println("got in to it ***");

	        return driver.findElements(By.xpath(xpathValue.replace("ExternalActionslable.get(i)", substitutionValue))).size();
	}

	
	
	public void validateExternalActionsinEnduserterminal(WebDriver driver,WebDriverWait wait,ExtentTest logger,String Usertype,String template,String description,String notebook,String users,String project,String Department,String internalfield,String shareproject,ArrayList ExternalActionslable,ArrayList ExternalActionsstatus,ArrayList applyto) throws InterruptedException {
		int externalaction_applytosize=applyto.size();
		for(int i=0;i<externalaction_applytosize;i++) {
			
			
			if((applyto.get(i)).toString().equalsIgnoreCase("Experiment") || (applyto.get(i)).toString().equalsIgnoreCase("Chemical Drawing"))  {
				if(experimentcreationcount==0) {
				login.newExperimentcreation(driver, wait, logger, Usertype, template, description, notebook, users, project, Department, internalfield);
				
				logger.log(LogStatus.PASS, "new experiemnt is created successfully");
				String screenshotpath=BaseClass.addScreenshot(driver);
				logger.log(LogStatus.INFO, logger.addScreenCapture(screenshotpath));
				
				}
				experimentcreationcount=experimentcreationcount+1;
				
				if((applyto.get(i)).toString().equalsIgnoreCase("Experiment"))  {
				if(experiment>0) {
//					System.out.println("proceeding with continue experiment");
					continue;
				}
				
			
			clickActionsbutton(driver, wait, logger);
			validateActions(driver, wait, logger, ExternalActionslable, ExternalActionsstatus,applyto,"Experiment");
				experiment=experiment+1;
			}
			
			
				else if((applyto.get(i)).toString().equalsIgnoreCase("Chemical Drawing"))  {
				if(chemicalDrawing>0) {
//					System.out.println("proceeding with continue chemicalDrawing");
					continue;
				}
				
				
				ExperimentcreationPage experimentcreationPage = new ExperimentcreationPage();
				
				experimentcreationPage.createNewChemicalDrawInExperiment(driver, wait, logger, "Chemical Drawing");
				clickonChemicalDrawinside(driver, wait, logger);
				String yorno=validatetestActionForChemDraw(driver, wait, logger,i,ExternalActionsstatus);
				if(yorno.equalsIgnoreCase("yes")) {
				validateActions(driver, wait, logger, ExternalActionslable, ExternalActionsstatus,applyto,"Chemical Drawing");
				}
				chemicalDrawing=chemicalDrawing+1;
			}
			
			}else if((applyto.get(i)).toString().equalsIgnoreCase("Notebook")){
				ExperimentcreationPage experimentcreationPage = new ExperimentcreationPage();
				if((applyto.get(i)).toString().equalsIgnoreCase("Notebook"))  {
					if(notebookcount>0) {
						
						continue;
					}
					experimentcreationPage.endusernotebookCreation(driver, wait, logger);
					String screenshotpath1=BaseClass.addScreenshot(driver);
					logger.log(LogStatus.INFO, logger.addScreenCapture(screenshotpath1));
					
					clickActionsbutton(driver, wait, logger);
					validateActionsNotebook(driver, wait, logger, ExternalActionslable, ExternalActionsstatus, applyto, "Notebook");
					
					
			}
				notebookcount=notebookcount+1;	
		}
		}
		experiment=0;
		chemicalDrawing=0;
		experimentcreationcount=0;
		notebookcount=0;
		}	
	
	
	public static void clickActionsbutton(WebDriver driver,WebDriverWait wait,ExtentTest logger) throws InterruptedException {
		wait.until(ExpectedConditions.presenceOfElementLocated(actionsbutton));
		//Thread.sleep(5000);
		wait.until(ExpectedConditions.presenceOfElementLocated(actionsbutton)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(Actionsdropdown));
		String screenshotpath=BaseClass.addScreenshot(driver);
		logger.log(LogStatus.INFO, logger.addScreenCapture(screenshotpath));
		
	}
	
	
	
	
	public  void createNewChemicalDrawInExperiment(WebDriver driver,WebDriverWait wait,ExtentTest logger,String template) throws InterruptedException {
		
		exppage.clickonAddContent(driver, wait, logger, "test");
		exppage.selecttemplatefromAddContent(driver, wait, logger, template);
	}
	
	public static void clickonChemicalDrawinside(WebDriver driver,WebDriverWait wait,ExtentTest logger) throws InterruptedException {
		wait.until(ExpectedConditions.presenceOfElementLocated(chemicaldrawinsideexperiment));
		//Thread.sleep(5000);
		logger.log(LogStatus.PASS, "chemicaldraw is clicked successfully");
		wait.until(ExpectedConditions.presenceOfElementLocated(chemicaldrawinsideexperiment)).click();
	}
	
	
	public static void clickonTextcreated(WebDriver driver,WebDriverWait wait,ExtentTest logger) throws InterruptedException {
		//text template created from add content to experiment
		wait.until(ExpectedConditions.presenceOfElementLocated(textinsideexperiment));
		Thread.sleep(5000);
		logger.log(LogStatus.PASS, "textinsideexperiment is clicked");
		
		wait.until(ExpectedConditions.presenceOfElementLocated(textinsideexperiment)).click();
		String screenshotpath=BaseClass.addScreenshot(driver);
		logger.log(LogStatus.INFO, logger.addScreenCapture(screenshotpath));
	}
	
	
	public static void clickonPredefinedValues(WebDriver driver,WebDriverWait wait,ExtentTest logger) throws InterruptedException {
		wait.until(ExpectedConditions.presenceOfElementLocated(predefinedvalues));
		Thread.sleep(5000);
		logger.log(LogStatus.PASS, "predefinedvalues is clicked");
		
		wait.until(ExpectedConditions.presenceOfElementLocated(predefinedvalues)).click();
		String screenshotpath=BaseClass.addScreenshot(driver);
		logger.log(LogStatus.INFO, logger.addScreenCapture(screenshotpath));
	}
	
	public static void clickoninsertTextTemplate(WebDriver driver,WebDriverWait wait,ExtentTest logger) throws InterruptedException {
		wait.until(ExpectedConditions.presenceOfElementLocated(insertTextTemplate));
		Thread.sleep(5000);
		logger.log(LogStatus.PASS, "insertTextTemplate is clicked");
		
		wait.until(ExpectedConditions.presenceOfElementLocated(insertTextTemplate)).click();
		String screenshotpath=BaseClass.addScreenshot(driver);
		logger.log(LogStatus.INFO, logger.addScreenCapture(screenshotpath));
	}
	
	
	public static ArrayList getAutotextList_EnduserText(WebDriver driver,WebDriverWait wait,ExtentTest logger) throws InterruptedException {
		
		ArrayList PredefinedAutolist_Enduser= new ArrayList();
		int getAutotextList_EnduserText_size= wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@id='popover-basic']/div[2]/div[@class='select-menu-entry select-menu-value list-group-item' or @class='select-menu-entry select-menu-section list-group-item'] "))).size();
		for(int i=0;i<getAutotextList_EnduserText_size;i++) {
			PredefinedAutolist_Enduser.add(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@id='popover-basic']/div[2]/div[@class='select-menu-entry select-menu-value list-group-item' or @class='select-menu-entry select-menu-section list-group-item'])["+(i+1)+"]"))).getText());
			
			logger.log(LogStatus.PASS, PredefinedAutolist_Enduser+" end user terminal value");
		}
		return PredefinedAutolist_Enduser;
	}
	
	
public static ArrayList getpredefinedtexttemplate_EnduserText(WebDriver driver,WebDriverWait wait,ExtentTest logger) throws InterruptedException {
		
		ArrayList getpredefinedtexttemplate_EnduserText= new ArrayList();
		int getAutotextSnippet_EnduserText_size= wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='snippet']/h4"))).size();
		String screenshotpath=BaseClass.addScreenshot(driver);
		logger.log(LogStatus.INFO, logger.addScreenCapture(screenshotpath));
		for(int i=0;i<getAutotextSnippet_EnduserText_size;i++) {
			getpredefinedtexttemplate_EnduserText.add(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='snippet']/h4)["+(i+1)+"]"))).getText());
			logger.log(LogStatus.PASS,getpredefinedtexttemplate_EnduserText+" end user terminal value");
			
		}
		return getpredefinedtexttemplate_EnduserText;
	}
	
	
	
	public static Boolean ValidateEqualityofTwoArray(WebDriver driver,WebDriverWait wait,ExtentTest logger,ArrayList Autotextlist,ArrayList getAutotextList_EnduserText) throws InterruptedException {
		Collections.sort(Autotextlist);
    	Collections.sort( getAutotextList_EnduserText);
    	boolean isEqual = Autotextlist.equals(getAutotextList_EnduserText);
		return isEqual;
	}
	
	
	public static String validatetestActionForChemDraw(WebDriver driver,WebDriverWait wait,ExtentTest logger,int i,ArrayList ExternalActionsstatus) throws InterruptedException {
		if((boolean) ExternalActionsstatus.get(i)) {
			wait.until(ExpectedConditions.presenceOfElementLocated(chemdrawActionsButton));
			Thread.sleep(5000);
			System.out.println("chemicaldraw is clicked");
			wait.until(ExpectedConditions.presenceOfElementLocated(chemdrawActionsButton)).click();
			return "yes";
		}else {
			return "no";
		}
		
		
		
//		wait.until(ExpectedConditions.presenceOfElementLocated(dropdownmenuActionsbutton));
		
	}
	
	
	
	public static void validateActions(WebDriver driver,WebDriverWait wait,ExtentTest logger,ArrayList ExternalActionslable,ArrayList ExternalActionsstatus,ArrayList applyto,String validationcheck) {
		
		int ExternalActionsstatus_size=ExternalActionsstatus.size();
		//System.out.println("the expected size is*********"+ExternalActionsstatus_size);
		for(int i=0;i<ExternalActionsstatus_size;i++) {
			
			
			if(((boolean) ExternalActionsstatus.get(i)) && ((applyto.get(i)).toString().equalsIgnoreCase("Experiment")) && (validationcheck.equalsIgnoreCase("Experiment"))) {
				//System.out.println("inside boolean yes loop Experiment");
				
				int ele= actionslablepresentornotfunction(driver,actionslablepresentornot1,(ExternalActionslable.get(i)).toString());
				System.out.println("got in to it with size***"+ele);
				Boolean actionslablepresentornot=ele>0;
			//Boolean actionslablepresentornot= driver.findElements(By.xpath("//div[@aria-labelledby='actions-dropdown']/a[text()='"+ExternalActionslable.get(i)+"']")).size()>0;
			if(actionslablepresentornot) {
				logger.log(LogStatus.PASS, ExternalActionslable.get(i)+" is present in the actions of setting symbol");
				//System.out.println(ExternalActionslable.get(i)+" is present in the actions of setting symbol1 **Passed**");
			}else {
				logger.log(LogStatus.FAIL, ExternalActionslable.get(i)+" is not present in the actions of setting symbol");
				//System.out.println(ExternalActionslable.get(i)+" is not present in the actions of setting symbol1 ** failed**");
			}
			}else if((!(boolean) ExternalActionsstatus.get(i)) && ((applyto.get(i)).toString().equalsIgnoreCase("Experiment")) && (validationcheck.equalsIgnoreCase("Experiment"))){
				//System.out.println("inside boolean No loop Experiment");
				Boolean actionslablepresentornot= driver.findElements(By.xpath("//div[@aria-labelledby='actions-dropdown']/a[text()='"+ExternalActionslable.get(i)+"']")).size()>0;
				if(actionslablepresentornot) {
					logger.log(LogStatus.FAIL, ExternalActionslable.get(i)+" is not present in the actions of setting symbol");
				}else {
					logger.log(LogStatus.PASS, ExternalActionslable.get(i)+" is present in the actions of setting symbol");
					
				}
			}
			
			else if(((boolean) ExternalActionsstatus.get(i)) && ((applyto.get(i)).toString().equalsIgnoreCase("Chemical Drawing")) && (validationcheck.equalsIgnoreCase("Chemical Drawing"))) {
				//System.out.println("inside boolean yes loop chemicaldraw");
			Boolean actionslablepresentornot= driver.findElements(By.xpath("//div[@class='dropdown-menu show dropdown-menu-right']/a[text()='"+ExternalActionslable.get(i)+"']")).size()>0;
			
			if(actionslablepresentornot) {
				
				logger.log(LogStatus.PASS, ExternalActionslable.get(i)+" is present in the actions of setting symbol");
			}else {
				logger.log(LogStatus.FAIL, ExternalActionslable.get(i)+" is not present in the actions of setting symbol");
			}
			}else if((!(boolean) ExternalActionsstatus.get(i)) && ((applyto.get(i)).toString().equalsIgnoreCase("Chemical Drawing"))&& (validationcheck.equalsIgnoreCase("Chemical Drawing"))){
				System.out.println("inside boolean No loop chemicaldraw");
				Boolean actionslablepresentornot= driver.findElements(By.xpath("///div[@class='dropdown-menu show dropdown-menu-right']/a[text()='"+ExternalActionslable.get(i)+"']")).size()>0;
				if(actionslablepresentornot) {
					logger.log(LogStatus.FAIL, ExternalActionslable.get(i)+" is not present in the actions of setting symbol");
				}else {
					logger.log(LogStatus.PASS, ExternalActionslable.get(i)+" is present in the actions of setting symbol");
					
				}
			}
			
			
			
			else {
				logger.log(LogStatus.INFO, ExternalActionslable.get(i)+" is skipped not a failure");
			}
		}
	}
	
	
	public void endusernotebookCreation(WebDriver driver,WebDriverWait wait,ExtentTest logger) throws InterruptedException {
		
		
		Thread.sleep(3000);
		Boolean ispresent_Addnew= driver.findElements(Addnewicon).size()>0;
		if(ispresent_Addnew) {
			wait.until(ExpectedConditions.presenceOfElementLocated(Addnewicon)).click();
			logger.log(LogStatus.PASS, "Add new button is clicked successfully");
			//logger.log(LogStatus.INFO, logger.addScreenCapture(screenshotpath));
		}else {
			logger.log(LogStatus.FAIL, "Add new button is not clicked successfully");
		}
		
		
		Thread.sleep(3000);
		Boolean ispresent_newExperimentbutton= driver.findElements(newNotebookbutton).size()>0;
		if(ispresent_newExperimentbutton) {
			wait.until(ExpectedConditions.presenceOfElementLocated(newNotebookbutton)).click();
			//driver.findElement(newNotebookbutton).click();
			logger.log(LogStatus.PASS, "newNotebookbutton is clicked successfully");
			//logger.log(LogStatus.INFO, logger.addScreenCapture(screenshotpath));
		}else {
			logger.log(LogStatus.FAIL, "newNotebookbutton is not clicked successfully");
		}
	
		
		wait.until(ExpectedConditions.presenceOfElementLocated(cancelbutton));
		
		
		Thread.sleep(3000);
		Boolean ispresent_DeptInput=driver.findElements(deptInput).size()>0;
		if(ispresent_DeptInput) {
			
			
				//driver.findElement(departmentInput).click();
				driver.findElement(deptInput).sendKeys("Dept 1");
				Thread.sleep(6000);
				String screenshotpath=BaseClass.addScreenshot(driver);
				logger.log(LogStatus.INFO, logger.addScreenCapture(screenshotpath));
//				driver.findElement(departmentInput).sendKeys(Keys.TAB);
				wait.until(ExpectedConditions.presenceOfElementLocated(dropdownselection)).click();
				logger.log(LogStatus.PASS, "depart is clicked and value entered successfully");
			
			
		}else {
			
		}
		
		Boolean ispresent_departmentInput=driver.findElements(departmentInput).size()>0;
		if(ispresent_departmentInput) {
			
			
				//driver.findElement(departmentInput).click();
				driver.findElement(departmentInput).sendKeys("Department 1");
				Thread.sleep(6000);
				//logger.log(LogStatus.INFO, logger.addScreenCapture(screenshotpath));
//				driver.findElement(departmentInput).sendKeys(Keys.TAB);
				wait.until(ExpectedConditions.presenceOfElementLocated(dropdownselection)).click();
				logger.log(LogStatus.PASS, "departmentInput is clicked and value entered successfully");
			
			
		}else {
			
		}
		
		
		wait.until(ExpectedConditions.presenceOfElementLocated(createbutton)).click();
		Thread.sleep(5000);
		
	}
	
	
	
	
public static void validateActionsNotebook(WebDriver driver,WebDriverWait wait,ExtentTest logger,ArrayList ExternalActionslable,ArrayList ExternalActionsstatus,ArrayList applyto,String validationcheck) {
		
		int ExternalActionsstatus_size=ExternalActionsstatus.size();
		System.out.println("the expected size is*********"+ExternalActionsstatus_size);
		for(int i=0;i<ExternalActionsstatus_size;i++) {
			
			
			if(((boolean) ExternalActionsstatus.get(i)) && ((applyto.get(i)).toString().equalsIgnoreCase("Notebook")) && (validationcheck.equalsIgnoreCase("Notebook"))) {
				System.out.println("inside boolean yes loop notebook");
			Boolean actionslablepresentornot= driver.findElements(By.xpath("//div[@aria-labelledby='actions-dropdown']/a[text()='"+ExternalActionslable.get(i)+"']")).size()>0;
			if(actionslablepresentornot) {
				String screenshotpath=BaseClass.addScreenshot(driver);
				logger.log(LogStatus.INFO, logger.addScreenCapture(screenshotpath));
				logger.log(LogStatus.PASS, ExternalActionslable.get(i)+" is present in the actions of setting symbol1 ");
				//System.out.println(ExternalActionslable.get(i)+" is present in the actions of setting symbol1 **Passed**");
			}else {
				logger.log(LogStatus.FAIL, ExternalActionslable.get(i)+" is present in the actions of setting symbol1 ");
				//System.out.println(ExternalActionslable.get(i)+" is not present in the actions of setting symbol1 ** failed**");
				String screenshotpath=BaseClass.addScreenshot(driver);
				logger.log(LogStatus.INFO, logger.addScreenCapture(screenshotpath));
			}
			}else if((!(boolean) ExternalActionsstatus.get(i)) && ((applyto.get(i)).toString().equalsIgnoreCase("Notebook")) && (validationcheck.equalsIgnoreCase("Notebook"))){
				System.out.println("inside boolean No loop Notebook");
				Boolean actionslablepresentornot= driver.findElements(By.xpath("//div[@aria-labelledby='actions-dropdown']/a[text()='"+ExternalActionslable.get(i)+"']")).size()>0;
				if(actionslablepresentornot) {
					String screenshotpath=BaseClass.addScreenshot(driver);
					logger.log(LogStatus.INFO, logger.addScreenCapture(screenshotpath));
					logger.log(LogStatus.FAIL, ExternalActionslable.get(i)+" is present in the actions of setting symbol1 ");
					//System.out.println(ExternalActionslable.get(i)+" is present in the actions of setting symbol2 ** failed**");
				}else {
					String screenshotpath=BaseClass.addScreenshot(driver);
					logger.log(LogStatus.INFO, logger.addScreenCapture(screenshotpath));
					logger.log(LogStatus.PASS, ExternalActionslable.get(i)+" is present in the actions of setting symbol1 ");
				}
			}
		}
	}
	
}
	




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

public class DashboardPage extends BaseClass{
	SystemConfigpage sysconfig=new SystemConfigpage();
	//Authenticationpage 

	By namedisplay=By.xpath("//div[@class='h5 brown-neutral margin-none']");
	By signout =By.xpath("//a[@id='logout'][text()='Sign Out']");
	By systemconfigbutton=By.xpath("//a[text()='System Configuration']");
	By inventrybutton=By.xpath("//a[text()='Inventory']");
	By browseInventory_Search=By.xpath("//div[contains(text(),'Browse Inventory')]");

	
	//Dashborad icons
	By Notebooksicon=By.xpath("//a[@id='notebooks']/i");
	By Experimentsicon=By.xpath("//a[@id='experiments']/i");
	By Parallelexperimentsicon=By.xpath("//a[@id='parallelExperiments']/i");
	By Requestsicon=By.xpath("//a[@id='requests']/i");
	By Tasksicon=By.xpath("//a[@id='tasks']/i");
	By ChemicalReactionsicon=By.xpath("//a[@id='chemdraw']/i");
	By Chemicalsicon=By.xpath("//a[@id='chemicals']/i");
	By Favoritesicon=By.xpath("//a[@id='favorites']/i");
	By Customobjectsicon=By.xpath("//a[@id='ados']/i");
	By Materialsicon=By.xpath("//a[@id='materials']/i");
	By Materials_dropdown=By.xpath("//button[@id='secondary-nav-dropdown']");
	By Materials_Dropdown_size=By.xpath("//button[@id='secondary-nav-dropdown']/following-sibling::div/a");
	By Usergroupicon=By.xpath("//a[@id='groups']/i");
	By Dashboardicon=By.xpath("//a[@id='dashboard']/i");
	By Searchicon=By.xpath("//a[@id='search']/i");
	By Addnewicon=By.xpath("//span[text()='Add New']");
	By Addcontenticon=By.xpath("//span[text()='Add Content']");
	
	
	
	By AdmindefinedtemplateDialogbox=By.xpath("//input[@id='select-table-template']");
	By createButton=By.xpath("//button[text()='Create']");
	//button[text()='Create']
	
	
	//username dropdown lists in home page.
	By userSetting=By.xpath("//a[@class='dropdown-item'][text()='User Settings']");
	By templates=By.xpath("//a[@class='dropdown-item'][text()='Templates']");
	By trashedItems=By.xpath("//a[@class='dropdown-item'][text()='Trashed Items']");
	By quickStartGuide=By.xpath("//a[@class='dropdown-item'][text()='Quick Start Guide']");
	By userGuide=By.xpath("//a[@class='dropdown-item'][text()='User Guide']");
	By faQs=By.xpath("//a[@class='dropdown-item'][text()='FAQs']");
	By chemdrwawuserguide=By.xpath("//a[@class='dropdown-item'][text()='ChemDraw User Guide']");
	By trainingVideos=By.xpath("//a[@class='dropdown-item'][text()='Training Videos']");
	By termsandcondition=By.xpath("//a[@class='dropdown-item'][text()='Terms & Conditions']");
	By serviceleveladd=By.xpath("//a[@class='dropdown-item'][text()='Service Level Addendum']");
	By globalSecurityadd=By.xpath("//a[@class='dropdown-item'][text()='Global Security Addendum']");
	By acceptableusePolicy=By.xpath("//a[@class='dropdown-item'][text()='Acceptable Use Policy']");	
	By whatsnew=By.xpath("//a[@class='dropdown-item'][text()=\"What's New\"]");	
	By contactsupport=By.xpath("//a[@class='dropdown-item'][text()='Contact Support']");	
	By systemAlert=By.xpath("//a[@class='dropdown-item'][text()='System Alerts']");
	By materialbulkImport=By.xpath("//a[@class='dropdown-item'][text()='Material Bulk Import Results']");
	By inventory=By.xpath("//a[@class='dropdown-item'][text()='Inventory']");
	By systemconfig=By.xpath("//a[@class='dropdown-item'][text()='System Configuration']");
	By location_Addnew=By.xpath("//a[@id='new-location']");
	By locationtype_enduser=By.xpath("//label[contains(text(),'Location Type')]");
	By locationtype_inputbox_enduser=By.xpath("//div[@class='rbt']//input[@aria-haspopup='listbox']");
	By locationtype_dropdownselection_enduser=By.xpath("//div[@aria-label='menu-options']/a");
	
	static String locationtype_valuegetter_enduser= "//div[@aria-label='menu-options']/a[testvalue]";
	
	
	//new user deletion xpath
	By trashicon=By.xpath("//i[@class='icon icon-trash icon-lg']");
	By checkboxdelete=By.xpath("//input[@type='checkbox']");
	By deleteuserbutton=By.xpath("//button[@id='DeleteUserButton']/span[contains(text(),'Delete User & Don')]");
	By filterinputbox=By.xpath("//input[@placeholder='Filter'][@type='text']");
	By filteremaildisplayed=By.xpath("//div[@class='user-info-text']/div[2]");
	By statusofuser=By.xpath("//div[@class='user-admin-column ']/div");
	
	
	//create new experiment
	By addnewbutton=By.xpath("//span[text()='Add New']");
	By newExperimentbutton=By.xpath("//a[@id='new-experiment']");
	By newNotebookbutton=By.xpath("//a[@id='new-journal']/i");
	By newAdminDefinedbutton=By.xpath("//a[text()='Admin defined']");
	By templateClosebutton=By.xpath("//button[@class='close rbt-close']/span[1]");
	By templateinput=By.xpath("//label[text()='Template']/..//input");
	By descriptioninput=By.xpath("//input[@id='description-input']");
	By notebookinput=By.xpath("//input[@id='notebook-auto']");
	By usersinput=By.xpath("//label[contains(text(),'Users')]/..//input[1]");
	By projectinput=By.xpath("//label[contains(text(),'Project')]/..//input[1]");
	By departmentInput=By.xpath("//label[contains(text(),'Department')]/..//input[1]");
	By deptInput=By.xpath("//label[contains(text(),'Dept')]/..//input[1]");
	By internalfieldInput=By.xpath("//label[contains(text(),'InternalField')]/..//input[1]");
	By cancelbutton=By.xpath("//button[@id='cancel-btn']");
	By createbutton=By.xpath("//button[@id='create-btn']");
	By dropdownselection=By.xpath("//a[@class='dropdown-item']/span[1]");
	By createdexperimentnamefetch=By.xpath("//div[@class='inline-input toolbar__name blue']/span/span");
	By sharingbutton=By.xpath("//button[text()='Sharing']");
	By signingbutton=By.xpath("//button[text()='Signing']");
	
	
	By experimentcreatepageName=By.xpath("//label[text()='Name']");
	
	
	static String locationfieldsvalue="//div[@aria-label='menu-options']/a[text()='testvalue']";
	//Loginpage loginpage=new Loginpage();
	
	
	public void loginpagepopupdismiss(WebDriver driver,ExtentTest logger,WebDriverWait wait) {
		try {
			driver.findElement(By.xpath("//button[text()='Dismiss']")).click();
		}catch(Exception e) {
			
		}
	}
	
	
	public void waituuntilloginpageload(WebDriver driver,ExtentTest logger,WebDriverWait wait) {
		wait.until(ExpectedConditions.presenceOfElementLocated(namedisplay));;
	}
	
	public void ClickonMaterials_fromuserDashboard(WebDriver driver,ExtentTest logger,WebDriverWait wait) {
		wait.until(ExpectedConditions.presenceOfElementLocated(Materialsicon)).click();;
		logger.log(LogStatus.PASS, "Materials icon from end user dashboard is clicked");
		String screenshotpath=BaseClass.addScreenshot(driver);
		logger.log(LogStatus.INFO, logger.addScreenCapture(screenshotpath));
	}
	
	public void VerifynoMaterialsicon_fromuserDashboard(WebDriver driver,ExtentTest logger,WebDriverWait wait,String usertype) {
		Boolean materialvisiblity=driver.findElements(Materialsicon).size()<0;
		if(!materialvisiblity) {
			//System.out.println();
			logger.log(LogStatus.PASS, "Materials sections is not visible as expected for usertype"+usertype);
			
		}else {
			logger.log(LogStatus.FAIL, "Materials sections is  visible and is not as expected for usertype"+usertype);
		}
	}
	
	public void ClickonMaterial_dropdown(WebDriver driver,ExtentTest logger,WebDriverWait wait) {
		wait.until(ExpectedConditions.presenceOfElementLocated(Materials_dropdown)).click();;
		logger.log(LogStatus.PASS, "Materials dropdown for end user is clicked");
		String screenshotpath=BaseClass.addScreenshot(driver);
		logger.log(LogStatus.INFO, logger.addScreenCapture(screenshotpath));
	}
	
	public ArrayList getlistofMaterialsDropdown(WebDriver driver,ExtentTest logger,WebDriverWait wait) {
		ArrayList obj=new ArrayList();
		int size=wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(Materials_Dropdown_size)).size();
		for(int i=0;i<size;i++) {
			obj.add(driver.findElement(By.xpath("//button[@id='secondary-nav-dropdown']/following-sibling::div/a["+(i+1)+"]")).getText());
			//System.out.println(obj.get(i));
		}
		return obj;
	}
	
	
	public void verifyConfigpathanduserpage_Materials(WebDriver driver,ExtentTest logger,WebDriverWait wait,ArrayList getlistofMaterialsDropdown,ArrayList materialsActivationlable,ArrayList materialsActivatiostatus) {
		int materialsActivatiostatus_size=materialsActivatiostatus.size();
		int materialsActivationlable_size=materialsActivatiostatus.size();
		System.out.println("size of materialsActivatiostatus_size"+materialsActivatiostatus_size+" and materialsActivationlable_size "+materialsActivationlable_size);
		for(int i=0;i<materialsActivatiostatus_size;i++) {
			if((boolean) ( materialsActivatiostatus.get(i))) {
				if(getlistofMaterialsDropdown.contains((materialsActivationlable).get(i))){
					logger.log(LogStatus.PASS, (materialsActivationlable).get(i)+" is activated for enduser also");
					
				}else {
					
					logger.log(LogStatus.PASS, (materialsActivationlable).get(i)+" is not activated for enduser");
				}
			}else if(!(boolean) (materialsActivatiostatus.get(i))) {
				if(getlistofMaterialsDropdown.contains((materialsActivationlable).get(i))){
					logger.log(LogStatus.FAIL, (materialsActivationlable).get(i)+" is activated for enduser but not activated in config page");
					
				}else {
					
					logger.log(LogStatus.PASS, (materialsActivationlable).get(i)+" is not activate for enduser and not  activated in config page");
				}
			}
		}
	}
	
	
	public void VerifyifUserIsAbleToADDMaterial(WebDriver driver,ExtentTest logger,WebDriverWait wait,ArrayList getlistofMaterialsDropdown) {
		int getlistofMaterialsDropdown_size=getlistofMaterialsDropdown.size();
		for(int i=0;i<getlistofMaterialsDropdown_size;i++) {
			
			
			String elementclickablevalidation=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='secondary-nav-dropdown']/following-sibling::div/a[text()='"+getlistofMaterialsDropdown.get(i)+"']"))).getText();
			String materialpresetvalidation=wait.until(ExpectedConditions.presenceOfElementLocated(Materials_dropdown)).getText();
			
			if(!(materialpresetvalidation.equalsIgnoreCase(elementclickablevalidation))) {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='secondary-nav-dropdown']/following-sibling::div/a[text()='"+getlistofMaterialsDropdown.get(i)+"']"))).click();
			}else {
				
			}
			
			addnewbuttonEndUser(driver, logger, wait);
			boolean MaterialsAddfeature=wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='show dropdown']//div[contains(text(),'"+getlistofMaterialsDropdown.get(i)+"')]"))).size()>0;
			if(MaterialsAddfeature) {
				logger.log(LogStatus.PASS, getlistofMaterialsDropdown.get(i)+" Have options to create new materials");
				String screenshotpath=BaseClass.addScreenshot(driver);
				logger.log(LogStatus.INFO, logger.addScreenCapture(screenshotpath));
			}else {
				logger.log(LogStatus.FAIL, getlistofMaterialsDropdown.get(i)+" Have  no options to create new materials");
				String screenshotpath=BaseClass.addScreenshot(driver);
				logger.log(LogStatus.INFO, logger.addScreenCapture(screenshotpath));
			}
			ClickonMaterial_dropdown(driver, logger, wait);
		}
	}
	
	
	public void addnewbuttonEndUser(WebDriver driver,ExtentTest logger,WebDriverWait wait) {
		wait.until(ExpectedConditions.presenceOfElementLocated(Addnewicon));
		Boolean ispresent_Addnew=driver.findElements(Addnewicon).size()>0;
		if(ispresent_Addnew) {
			driver.findElement(Addnewicon).click();
			logger.log(LogStatus.PASS, "Add new button is clicked successfully");
			String screenshotpath=BaseClass.addScreenshot(driver);
			logger.log(LogStatus.INFO, logger.addScreenCapture(screenshotpath));
		}else {
			logger.log(LogStatus.FAIL, "Add new button is not clicked successfully");
			String screenshotpath=BaseClass.addScreenshot(driver);
			logger.log(LogStatus.INFO, logger.addScreenCapture(screenshotpath));
		}
	}
	
	//verify if system config drop down is present.
	public void verifySystemConfigDropdown(WebDriver driver,ExtentTest logger,String usertype,WebDriverWait wait) throws InterruptedException {
		try {
			driver.findElement(By.xpath("//button[text()='Dismiss']")).click();
		}catch(Exception e) {
			
		}
		
		wait.until(ExpectedConditions.presenceOfElementLocated(namedisplay)).click();;
		
		if(usertype.equalsIgnoreCase("System admin")) {
			Boolean isPresent = driver.findElements(systemconfigbutton).size()>0;
			if(isPresent) {
				logger.log(LogStatus.PASS, "System admin user is having System config button as expected:");
				
				Thread.sleep(3000);
			}else {
				logger.log(LogStatus.FAIL, "System admin user is not having System config button not as expected:");
			}
			
		}else if(usertype.equalsIgnoreCase("Standard user")) {
			Boolean isPresent = driver.findElements(systemconfigbutton).size()>0;
			if(isPresent) {
				logger.log(LogStatus.FAIL, "Standard  user is having System config buttonwhich is not as expected:");
			}else {
				logger.log(LogStatus.PASS, "Standard user is not having System config button as expected:");
			}
		}
	}
	
	public void clickonnamedropdown(WebDriver driver,ExtentTest logger,String usertype,WebDriverWait wait) {
		wait.until(ExpectedConditions.presenceOfElementLocated(namedisplay)).click();;
	}
	
	public void verifyifInventoryPresentInDropdown(WebDriver driver,ExtentTest logger,String usertype,WebDriverWait wait) {
		Boolean bool=driver.findElements(inventrybutton).size()>0;
		if(bool) {
			logger.log(LogStatus.PASS, "inventrybutton is presemt as expected");
			System.out.println("inventrybutton is presemt as expected");
		}else {
			logger.log(LogStatus.FAIL, "inventrybutton is not presemt as expected");
			System.out.println("inventrybutton is not present");
		}
	}
	
	
	public void clickOnInventoryDropdown(WebDriver driver,ExtentTest logger,String usertype,WebDriverWait wait) {
		Boolean bool=driver.findElements(inventrybutton).size()>0;
		if(bool) {
			wait.until(ExpectedConditions.presenceOfElementLocated(inventrybutton)).click();
			logger.log(LogStatus.PASS, "inventrybutton is clicked successfully");
			System.out.println("inventrybutton is clicked successfully as expected");
		}else {
			logger.log(LogStatus.FAIL, "inventrybutton is not presemt so not clicked");
			System.out.println("inventrybutton is not present so not clicked");
		}
	}
	
	
	public void VerifyAddLocation(WebDriver driver,ExtentTest logger,String usertype,WebDriverWait wait) throws InterruptedException {
		wait.until(ExpectedConditions.presenceOfElementLocated(browseInventory_Search));
		
		
		Boolean bool=driver.findElements(Addnewicon).size()>0;
		if(bool) {
			wait.until(ExpectedConditions.presenceOfElementLocated(Addnewicon)).click();
			logger.log(LogStatus.PASS, "Addnewicon button is clicked successfully");
			System.out.println("Addnewicon is clicked successfully as expected");
			
		}else {
			logger.log(LogStatus.FAIL, "Addnewicon is not presemt so not clicked");
			System.out.println("Addnewicon is not present so not clicked");
		}
		
		Boolean bool2=driver.findElements(location_Addnew).size()>0;
		if(bool2) {
			wait.until(ExpectedConditions.presenceOfElementLocated(location_Addnew)).click();
			logger.log(LogStatus.PASS, "location_Addnew button is clicked successfully");
			System.out.println("location_Addnew is clicked successfully as expected");
			
		}else {
			logger.log(LogStatus.FAIL, "location_Addnew is not presemt so not clicked");
			System.out.println("location_Addnew is not present so not clicked");
		}
		
		Thread.sleep(5000);
	}
	
	public void locationtype_Validation(WebDriver driver,ExtentTest logger,String usertype,WebDriverWait wait) {
		wait.until(ExpectedConditions.presenceOfElementLocated(locationtype_enduser));
		wait.until(ExpectedConditions.presenceOfElementLocated(locationtype_inputbox_enduser)).click();;
		wait.until(ExpectedConditions.presenceOfElementLocated(locationtype_dropdownselection_enduser));
		String screenshotpath=BaseClass.addScreenshot(driver);
		logger.log(LogStatus.INFO, logger.addScreenCapture(screenshotpath));
		int value=wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locationtype_dropdownselection_enduser)).size();
		//int value=driver.findElements(locationtype_dropdownselection_enduser).size();
		System.out.println("size of the end user location types are "+value);
		for(int i=1;i<=value;i++) {
			String val=Integer.toString(i);
			By locationtypexpaths=locationtypes(driver, locationtype_valuegetter_enduser, val);
					System.out.println(driver.findElement(locationtypexpaths).getText());
		}
		
		
			
	}
	
	
	static By locationfields(WebDriver driver,String xpathValue, String substitutionValue ) {
		

	        return By.xpath(xpathValue.replace("testvalue", substitutionValue));
	}
	
	
	public void LocationFieldValidation(WebDriver driver,ExtentTest logger,String usertype,WebDriverWait wait,ArrayList locationtypes,Map location_fields) {
		int size=locationtypes.size();
		
		for(int i=0;i<size;i++) {
			By locationfieldsxpath=locationfields(driver, locationfieldsvalue, locationtypes.get(i).toString());
			wait.until(ExpectedConditions.presenceOfElementLocated(locationfieldsxpath)).click();
			
			//validation code
			String screenshotpath=BaseClass.addScreenshot(driver);
			logger.log(LogStatus.INFO, logger.addScreenCapture(screenshotpath));
			
			ArrayList locationfieldtempvalidation=(ArrayList) location_fields.get(locationtypes.get(i));
			int sizeofFields=locationfieldtempvalidation.size();
			for(int j=0;j<sizeofFields;j++) {
				Boolean bool=wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//label[contains(text(),'"+locationfieldtempvalidation.get(j)+"')]"))).size()>0;
				
				String textval=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[contains(text(),'"+locationfieldtempvalidation.get(j)+"')]"))).getText();
				
//				String screenshotpath1=BaseClass.addScreenshot(driver);
//				logger.log(LogStatus.INFO, logger.addScreenCapture(screenshotpath1));
				
				if(bool) {
					System.out.println("Lable field of location "+locationtypes.get(i)+" is present Lable is: "+textval);
					logger.log(LogStatus.PASS, " location type "+locationtypes.get(i)+" is present with lable: "+textval+" as expected");
				}else {
					System.out.println("Lable field of location "+locationtypes.get(i)+" is present Lable is: "+textval);
					logger.log(LogStatus.FAIL, " location type "+locationtypes.get(i)+" is not present with lable: "+textval+" as expected");
				}
			}
			
			
			wait.until(ExpectedConditions.presenceOfElementLocated(locationtype_inputbox_enduser)).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(locationtype_inputbox_enduser)).sendKeys(Keys.CONTROL + "a");
			wait.until(ExpectedConditions.presenceOfElementLocated(locationtype_inputbox_enduser)).sendKeys(Keys.DELETE);
			
		}
		//div[@aria-label='menu-options']/a[text()='A']
	}
	
	public void mapValidation(WebDriver driver,ExtentTest logger,String usertype,WebDriverWait wait,Map location_fields,ArrayList locationtypes) {
		System.out.println("map valudation *******");
		int size=location_fields.size();
		System.out.println("size of map is "+size);
		System.out.println(location_fields.get(locationtypes.get(0))+"*************");
		System.out.println(location_fields.get(locationtypes.get(1))+"*************");
		System.out.println(location_fields.get(locationtypes.get(2))+"*************");
		System.out.println(location_fields.get(locationtypes.get(3))+"*************");
	}
	
	static By locationtypes(WebDriver driver,String xpathValue, String substitutionValue ) {
		System.out.println("got in to it ***");

	        return By.xpath(xpathValue.replace("testvalue", substitutionValue));
	}
	
	
	
	
	public void verifyifInventoryNotPresentInDropdown(WebDriver driver,ExtentTest logger,String usertype,WebDriverWait wait) {
		Boolean bool=driver.findElements(inventrybutton).size()>0;
		if(!bool) {
			logger.log(LogStatus.PASS, "inventrybutton is not presemt as expected");
			System.out.println("inventrybutton is not presemt as expected");
		}else {
			logger.log(LogStatus.FAIL, "inventrybutton is  presemt not as expected");
			System.out.println("inventrybutton is  present not as expected");
		}
	}
	
	
	//click on system config button which is availble for sysytem admin under the user name dropdown
	public void clickonSystemconfig(WebDriver driver,ExtentTest logger,String usertype,WebDriverWait wait) {
		String screenshotpath=BaseClass.addScreenshot(driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(systemconfigbutton)).click();
		logger.log(LogStatus.PASS, "user clicked on system config button");
		logger.log(LogStatus.INFO, logger.addScreenCapture(screenshotpath));
	}
	
	
	
	
	//get url from props file and jenkins parameter using this method
	
	public void geturl(WebDriver driver) {
		
		System.out.println("configfile reader value is :"+ConfigFileReader("runon"));
		
		if(properties.getProperty("runon").equalsIgnoreCase("local")) {
			
			String env=properties.getProperty("environment");
			System.out.println("prinitning config local "+env+"::"+properties.getProperty("environment"));
			if(env.equalsIgnoreCase("srv18")) {
				driver.get(properties.getProperty("srv18url"));
			}
			else {
				//we can write for other environments in else confitions
				System.out.println("No url to load" );
			}	
			
		}
		else if(ConfigFileReader("runon").equalsIgnoreCase("jenkin")) {
			String env=System.getenv("environment");
			System.out.println("prinitning config"+env+"::"+System.getenv("environment"));
			if(env.equalsIgnoreCase("srv18")) {
				System.out.println("Executing in jenking for the url"+ConfigFileReader("srv18url"));
				driver.get(ConfigFileReader("srv18url"));
	// 			logger.log(LogStatus.PASS, "URL is fetched based on Jenkin input");
		}
		else {
			//we can write for other environments in else conditions.
			System.out.println("No url to load" );
		}
	}
	}
	
	//Home page dashbord icons are validated with this function
	public void dashboardiconValidation(WebDriver driver,ExtentTest logger,String usertype,WebDriverWait wait,String Dashboardicons) throws InterruptedException {
		String[] icons=Dashboardicons.split(",");  
		int noOfIcons=icons.length;
		System.out.println("no of icons:"+noOfIcons);
		wait.until(ExpectedConditions.presenceOfElementLocated(Requestsicon));
		for(int i=0;i<noOfIcons;i++) {
			if(icons[i].equalsIgnoreCase("Notebooks")) {
				Boolean isPresent = driver.findElements(Notebooksicon).size()>0;
				if(isPresent) {
					logger.log(LogStatus.PASS, "Notebooks icon is present as expected");
				}else {
					logger.log(LogStatus.FAIL, "Notebooks icon is not present and not as expected");
				}
				
			}else if(icons[i].equalsIgnoreCase("Experiments")) {
				Boolean isPresent = driver.findElements(Experimentsicon).size()>0;
				if(isPresent) {
					logger.log(LogStatus.PASS, "Experiments icon is present as expected");
				}else {
					logger.log(LogStatus.FAIL, "Experiments icon is not present and not as expected");
				}
				
				
			}else if(icons[i].equalsIgnoreCase("Parallel Experiments")) {
				Boolean isPresent = driver.findElements(Parallelexperimentsicon).size()>0;
				if(isPresent) {
					logger.log(LogStatus.PASS, "Parallelexperiments icon is present as expected");
				}else {
					logger.log(LogStatus.FAIL, "Parallelexperiments icon is not present and not as expected");
				}
				
			}else if(icons[i].equalsIgnoreCase("Requests")) {
				
				Boolean isPresent = driver.findElements(Requestsicon).size()>0;
				if(isPresent) {
					logger.log(LogStatus.PASS, "Requests icon is present as expected");
				}else {
					logger.log(LogStatus.FAIL, "Requests icon is not present and not as expected");
				}
				
			}
			else if(icons[i].equalsIgnoreCase("Tasks")) {
				
				Boolean isPresent = driver.findElements(Tasksicon).size()>0;
				if(isPresent) {
					logger.log(LogStatus.PASS, "Tasksicon is present as expected");
				}else {
					logger.log(LogStatus.FAIL, "Tasksicon is not present and not as expected");
				}
				
			}else if(icons[i].equalsIgnoreCase("Chemical Reactions")) {
				
				Boolean isPresent = driver.findElements(ChemicalReactionsicon).size()>0;
				if(isPresent) {
					logger.log(LogStatus.PASS, "ChemicalReactions icon is present as expected");
				}else {
					logger.log(LogStatus.FAIL, "ChemicalReactions icon is not present and not as expected");
				}
				
			}else if(icons[i].equalsIgnoreCase("Chemicals")) {
				
				Boolean isPresent = driver.findElements(Chemicalsicon).size()>0;
				if(isPresent) {
					logger.log(LogStatus.PASS, "Chemicalsicon  is present as expected");
				}else {
					logger.log(LogStatus.FAIL, "Chemicalsicon  is not present and not as expected");
				}
				
			}else if(icons[i].equalsIgnoreCase("Favorites")) {
				
				Boolean isPresent = driver.findElements(Favoritesicon).size()>0;
				if(isPresent) {
					logger.log(LogStatus.PASS, "Favoritesicon  is present as expected");
				}else {
					logger.log(LogStatus.FAIL, "Favoritesicon  is not present and not as expected");
				}
				
			}else if(icons[i].equalsIgnoreCase("Custom Objects")) {
				
				Boolean isPresent = driver.findElements(Customobjectsicon).size()>0;
				if(isPresent) {
					logger.log(LogStatus.PASS, "Customobjectsicon  is present as expected");
				}else {
					logger.log(LogStatus.FAIL, "Customobjectsicon  is not present and not as expected");
				}
				
			}else if(icons[i].equalsIgnoreCase("Materials")) {
				
				Boolean isPresent = driver.findElements(Materialsicon).size()>0;
				if(isPresent) {
					logger.log(LogStatus.PASS, "Materialsicon  is present as expected");
				}else {
					logger.log(LogStatus.FAIL, "Materialsicon  is not present and not as expected");
				}
				
			}else if(icons[i].equalsIgnoreCase("User Groups")) {
				
				Boolean isPresent = driver.findElements(Usergroupicon).size()>0;
				if(isPresent) {
					logger.log(LogStatus.PASS, "Usergroupicon  is present as expected");
				}else {
					logger.log(LogStatus.FAIL, "Usergroupicon  is not present and not as expected");
				}
				
			}
			
			
		}
		Boolean isPresent = driver.findElements(Dashboardicon).size()>0;
		if(isPresent) {
			logger.log(LogStatus.PASS, "Dashboardicon  is present as expected");
		}else {
			logger.log(LogStatus.FAIL, "Dashboardicon  is not present and not as expected");
		}
		
		Boolean isPresent_Search = driver.findElements(Searchicon).size()>0;
		if(isPresent_Search) {
			logger.log(LogStatus.PASS, "Searchicon  is present as expected");
		}else {
			logger.log(LogStatus.FAIL, "Searchicon  is not present and not as expected");
		}
		
		Boolean isPresent_Addnew= driver.findElements(Addnewicon).size()>0;
		if(isPresent_Search) {
			logger.log(LogStatus.PASS, "Addnewicon  is present as expected");
		}else {
			logger.log(LogStatus.FAIL, "Addnewicon  is not present and not as expected");
		}
	}
	
	
	//dropdown icons under the username in home page is validated using this method.
	public void usernameDropDownValidation(WebDriver driver,ExtentTest logger,String usertype,WebDriverWait wait,String userDropdown) {
		String[] dropdownlist=userDropdown.split(",");  
		int noOfIcons=dropdownlist.length;
		System.out.println("no of icons:"+noOfIcons);
		
		String screenshotpath=BaseClass.addScreenshot(driver);
		logger.log(LogStatus.INFO, logger.addScreenCapture(screenshotpath));
		for(int i=0;i<noOfIcons;i++) {
			if(dropdownlist[i].equalsIgnoreCase("User Settings")) {
					Boolean isPresent = driver.findElements(userSetting).size()>0;
					if(isPresent) {
						logger.log(LogStatus.PASS, "userSetting icon is present in dropdown as expected");
					}else {
						logger.log(LogStatus.FAIL, "userSetting icon is not present in dropdown and not as expected");
				}
				
		
			}
			else if(dropdownlist[i].equalsIgnoreCase("Templates")) {
				
				Boolean isPresent = driver.findElements(templates).size()>0;
				if(isPresent) {
					logger.log(LogStatus.PASS, "Templates  is present in dropdown as expected");
				}else {
					logger.log(LogStatus.FAIL, "Templates  is not present in dropdown and not as expected");
				}	
	
			}
			else if(dropdownlist[i].equalsIgnoreCase("Trashed items")) {
				
				Boolean isPresent = driver.findElements(trashedItems).size()>0;
				if(isPresent) {
					logger.log(LogStatus.PASS, "trashedItems  is present in dropdown as expected");
				}else {
					logger.log(LogStatus.FAIL, "trashedItems  is not present in dropdown and not as expected");
				}	
	
			}
			else if(dropdownlist[i].equalsIgnoreCase("Material Bulk Import Results")) {
				
				Boolean isPresent = driver.findElements(materialbulkImport).size()>0;
				if(isPresent) {
					logger.log(LogStatus.PASS, "materialbulkImport  is present in dropdown as expected");
				}else {
					logger.log(LogStatus.FAIL, "materialbulkImport  is not present in dropdown and not as expected");
				}	
	
			}
			else if(dropdownlist[i].equalsIgnoreCase("Inventory")) {
				
				Boolean isPresent = driver.findElements(inventory).size()>0;
				if(isPresent) {
					logger.log(LogStatus.PASS, "Inventory  is present in dropdown as expected");
				}else {
					logger.log(LogStatus.FAIL, "Inventory  is not present in dropdown and not as expected");
				}	
	
			}
			else if(dropdownlist[i].equalsIgnoreCase("Quick Start Guide")) {
				
				Boolean isPresent = driver.findElements(quickStartGuide).size()>0;
				if(isPresent) {
					logger.log(LogStatus.PASS, "quickStartGuide  is present in dropdown as expected");
				}else {
					logger.log(LogStatus.FAIL, "quickStartGuide  is not present in dropdown and not as expected");
				}	
	
			}
			else if(dropdownlist[i].equalsIgnoreCase("User Guide")) {
				
				Boolean isPresent = driver.findElements(userGuide).size()>0;
				if(isPresent) {
					logger.log(LogStatus.PASS, "userGuide  is present in dropdown as expected");
				}else {
					logger.log(LogStatus.FAIL, "userGuide  is not present in dropdown and not as expected");
				}	
	
			}
			else if(dropdownlist[i].equalsIgnoreCase("FAQs")) {
				
				Boolean isPresent = driver.findElements(faQs).size()>0;
				if(isPresent) {
					logger.log(LogStatus.PASS, "FAQs  is present in dropdown as expected");
				}else {
					logger.log(LogStatus.FAIL, "FAQs  is not present in dropdown and not as expected");
				}	
	
			}
			else if(dropdownlist[i].equalsIgnoreCase("ChemDraw user Guide")) {
				
				Boolean isPresent = driver.findElements(chemdrwawuserguide).size()>0;
				if(isPresent) {
					logger.log(LogStatus.PASS, "chemdrwawuserguide  is present in dropdown as expected");
				}else {
					logger.log(LogStatus.FAIL, "chemdrwawuserguide  is not present in dropdown and not as expected");
				}	
	
			}
			else if(dropdownlist[i].equalsIgnoreCase("Training Videos")) {
				
				Boolean isPresent = driver.findElements(trainingVideos).size()>0;
				if(isPresent) {
					logger.log(LogStatus.PASS, "Training Videos  is present in dropdown as expected");
				}else {
					logger.log(LogStatus.FAIL, "Training Videos  is not present in dropdown and not as expected");
				}	
	
			}
			else if(dropdownlist[i].equalsIgnoreCase("Terms & Conditions")) {
				
				Boolean isPresent = driver.findElements(termsandcondition).size()>0;
				if(isPresent) {
					logger.log(LogStatus.PASS, " Terms & Conditions  is present in dropdown as expected");
				}else {
					logger.log(LogStatus.FAIL, "Terms & Conditions  is not present in dropdown and not as expected");
				}	
	
			}
			else if(dropdownlist[i].equalsIgnoreCase("Service Level Addendum")) {
				
				Boolean isPresent = driver.findElements(serviceleveladd).size()>0;
				if(isPresent) {
					logger.log(LogStatus.PASS, " Service Level Addendum  is present in dropdown as expected");
				}else {
					logger.log(LogStatus.FAIL, "Service Level Addendum  is not present in dropdown and not as expected");
				}	
	
			}
			else if(dropdownlist[i].equalsIgnoreCase("Global Security Addendum")) {
				
				Boolean isPresent = driver.findElements(globalSecurityadd).size()>0;
				if(isPresent) {
					logger.log(LogStatus.PASS, " Global Security Addendum  is present in dropdown as expected");
				}else {
					logger.log(LogStatus.FAIL, "Global Security Addendum  is not present in dropdown and not as expected");
				}	
	
			}
			else if(dropdownlist[i].equalsIgnoreCase("Acceptable use policy")) {
				
				Boolean isPresent = driver.findElements(acceptableusePolicy).size()>0;
				if(isPresent) {
					logger.log(LogStatus.PASS, " acceptableusePolicy  is present in dropdown as expected");
				}else {
					logger.log(LogStatus.FAIL, "acceptableusePolicy  is not present in dropdown and not as expected");
				}	
	
			}
			else if(dropdownlist[i].equalsIgnoreCase("What's New")) {
				
				Boolean isPresent = driver.findElements(whatsnew).size()>0;
				if(isPresent) {
					logger.log(LogStatus.PASS, " whatsnew  is present in dropdown as expected");
				}else {
					logger.log(LogStatus.FAIL, "whatsnew  is not present in dropdown and not as expected");
				}	
	
			}
			else if(dropdownlist[i].equalsIgnoreCase("Contact Support")) {
				
				Boolean isPresent = driver.findElements(contactsupport).size()>0;
				if(isPresent) {
					logger.log(LogStatus.PASS, " contactsupport  is present in dropdown as expected");
				}else {
					logger.log(LogStatus.FAIL, "contactsupport  is not present in dropdown and not as expected");
				}	
	
			}
			else if(dropdownlist[i].equalsIgnoreCase("System Alerts")) {
				
				Boolean isPresent = driver.findElements(systemAlert).size()>0;
				if(isPresent) {
					logger.log(LogStatus.PASS, " systemAlert  is present in dropdown as expected");
				}else {
					logger.log(LogStatus.FAIL, "systemAlert  is not present in dropdown and not as expected");
				}	
	
			}
			else if(dropdownlist[i].equalsIgnoreCase("Sign out")) {
				
				Boolean isPresent = driver.findElements(signout).size()>0;
				if(isPresent) {
					logger.log(LogStatus.PASS, " Sign out  is present in dropdown as expected");
				}else {
					logger.log(LogStatus.FAIL, "Sign out  is not present in dropdown and not as expected");
				}	
	
			}
	
	
		}
	}
	
	//delete a newly created user by system admin input of email address of newly created should be given.
public void deleteNewlyCreatedUser(WebDriver driver,ExtentTest logger,String email,WebDriverWait wait) throws InterruptedException {
		
		Boolean ispresent_trashicon=driver.findElements(trashicon).size()>0;
		if(ispresent_trashicon) {
			wait.until(ExpectedConditions.presenceOfElementLocated(trashicon)).click();
			Thread.sleep(2000);
			logger.log(LogStatus.PASS, "clicked on trash icon successfully:"+email);
		}else {
			logger.log(LogStatus.FAIL, "not clicked on trash icon successfully:"+email);
		}
		
		
		String winHandleBefore=sysconfig.winhandlebefore(driver);
		WebDriver switcheddriver=sysconfig.windowsswitch(driver);
		switcheddriver.findElement(checkboxdelete).click();
		logger.log(LogStatus.PASS, "Checkbox for full control is checked by user:"+email);
		String screenshotpath=BaseClass.addScreenshot(driver);
		logger.log(LogStatus.INFO, logger.addScreenCapture(screenshotpath));
		switcheddriver.findElement(deleteuserbutton).click();
		
		//sysconfig.switchbacktodefaultdriver(switcheddriver, winHandleBefore);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.presenceOfElementLocated(filterinputbox)).clear();
		wait.until(ExpectedConditions.presenceOfElementLocated(filterinputbox)).sendKeys(email);
		Thread.sleep(2000);
		
		Boolean ispresent_filteremaildisplayed=driver.findElements(filteremaildisplayed).size()>0;
		if(ispresent_filteremaildisplayed) {
			
			logger.log(LogStatus.FAIL, "user is not deleted fail:"+email);
		}else {
			logger.log(LogStatus.PASS, "user is deleted successfully:"+email);
		}
		
	}
	



//create new experiment
public void newExperiment(WebDriver driver,WebDriverWait wait,ExtentTest logger,String Usertype,String template,String description,String notebook,String users,String project,String Department,String internalfield,String shareproject) throws InterruptedException {
	
	Boolean ispresent_Addnew=driver.findElements(Addnewicon).size()>0;
	if(ispresent_Addnew) {
		driver.findElement(Addnewicon).click();
		logger.log(LogStatus.PASS, "Add new button is clicked successfully");
	}else {
		logger.log(LogStatus.FAIL, "Add new button is not clicked successfully");
	}
	
	
	
	Boolean ispresent_newExperimentbutton=driver.findElements(newExperimentbutton).size()>0;
	if(ispresent_newExperimentbutton) {
		driver.findElement(newExperimentbutton).click();
		logger.log(LogStatus.PASS, "newExperimentbutton is clicked successfully");
	}else {
		logger.log(LogStatus.FAIL, "newExperimentbutton is not clicked successfully");
	}
	
	
	wait.until(ExpectedConditions.presenceOfElementLocated(cancelbutton));

	Boolean ispresent_templateClosebutton=driver.findElements(templateClosebutton).size()>0;
	if(ispresent_templateClosebutton) {
		driver.findElement(templateClosebutton).click();
		logger.log(LogStatus.PASS, "templateClosebutton is clicked successfully");
	}else {
		//nothing to do if close button is not present.
	}
	
	
	Boolean ispresent_templateinput=driver.findElements(templateinput).size()>0;
	if(ispresent_templateinput) {
		driver.findElement(templateinput).click();
		
		logger.log(LogStatus.PASS, "templateinput is clickedsuccessfully");
	}else {
		//nothing to do if close button is not present.
	}
	
	if(template.equalsIgnoreCase("NA")) {
		
	}else {
		driver.findElement(templateinput).sendKeys(template);
		Thread.sleep(2000);
		driver.findElement(templateinput).sendKeys(Keys.ENTER);
	}
	
	
	wait.until(ExpectedConditions.presenceOfElementLocated(cancelbutton));
	Boolean ispresent_descriptioninput=driver.findElements(descriptioninput).size()>0;
	if(ispresent_descriptioninput) {
//		driver.findElement(descriptioninput).click();
		driver.findElement(descriptioninput).sendKeys(description);
		logger.log(LogStatus.PASS, "descriptioninput is clicked and value entered successfully");
	}else {
		logger.log(LogStatus.FAIL, "descriptioninput is not clicked and value not entered successfully");
	}
	
	
	Boolean ispresent_notebookinput=driver.findElements(notebookinput).size()>0;
	if(ispresent_notebookinput) {
		
		if(notebook.equalsIgnoreCase("NA")) {
			
		}else {
			//driver.findElement(notebookinput).click();
			driver.findElement(notebookinput).sendKeys(notebook);
			
//			driver.findElement(notebookinput).sendKeys(Keys.TAB);
			wait.until(ExpectedConditions.presenceOfElementLocated(dropdownselection)).click();
			logger.log(LogStatus.PASS, "notebookinput is clicked and value entered successfully");
			
		}
		
	}else {
		logger.log(LogStatus.FAIL, "notebookinput is not present");
	}
	
	Boolean ispresent_usersinput=driver.findElements(usersinput).size()>0;
	if(ispresent_usersinput) {
		
		if(users.equalsIgnoreCase("NA")) {
			
		}else {
			//driver.findElement(usersinput).click();
			driver.findElement(usersinput).sendKeys(users);
//			Thread.sleep(2000);
//			driver.findElement(usersinput).sendKeys(Keys.TAB);
			wait.until(ExpectedConditions.presenceOfElementLocated(dropdownselection)).click();
			logger.log(LogStatus.PASS, "usersinput is clicked and value entered successfully");
		}
		
	}else {
		if(users.equalsIgnoreCase("NA")) {
		logger.log(LogStatus.PASS, "usersinput is not present as expectted"+users);
		}else {
			logger.log(LogStatus.FAIL, "usersinput is not present "+users);	
		}
	}
	Thread.sleep(3000);
//	((JavascriptExecutor)driver).executeScript("scroll(0,500)");
//	JavascriptExecutor js = ((JavascriptExecutor) driver);
//	js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
//	Thread.sleep(3500);


	
	Boolean ispresent_projectinput=driver.findElements(projectinput).size()>0;
	if(ispresent_projectinput) {
		
		if(project.equalsIgnoreCase("NA")) {
			
		}else {
			//driver.findElement(projectinput).click();
			driver.findElement(projectinput).sendKeys(project);
			Thread.sleep(10000);
//			driver.findElement(projectinput).sendKeys(Keys.TAB);
			Actions actions = new Actions(driver);

			actions.moveToElement(driver.findElement(dropdownselection)).click().perform();
			//wait.until(ExpectedConditions.presenceOfElementLocated(dropdownselection)).click();
			logger.log(LogStatus.PASS, "projectinput is clicked and value entered successfully");
		}
		
	}else {
		if(project.equalsIgnoreCase("NA")) {
		logger.log(LogStatus.PASS, "projectinput is not present as expectted");
		}else {
			logger.log(LogStatus.FAIL, "projectinput is not present ");	
		}
	}
	
	
	Boolean ispresent_departmentInput=driver.findElements(departmentInput).size()>0;
	if(ispresent_departmentInput) {
		
		if(Department.equalsIgnoreCase("NA")) {
			
		}else {
			//driver.findElement(departmentInput).click();
			driver.findElement(departmentInput).sendKeys(Department);
//			Thread.sleep(2000);
//			driver.findElement(departmentInput).sendKeys(Keys.TAB);
			wait.until(ExpectedConditions.presenceOfElementLocated(dropdownselection)).click();
			logger.log(LogStatus.PASS, "departmentInput is clicked and value entered successfully");
		}
		
	}else {
		if(Department.equalsIgnoreCase("NA")) {
		logger.log(LogStatus.PASS, "departmentInput is not present as expectted");
		}else {
			logger.log(LogStatus.FAIL, "departmentInput is not present ");	
		}
	}
	
	
	
	Boolean ispresent_internalfieldInput=driver.findElements(internalfieldInput).size()>0;
	if(ispresent_internalfieldInput) {
		
		if(internalfield.equalsIgnoreCase("NA")) {
			
		}else {
			//driver.findElement(internalfieldInput).click();
			driver.findElement(internalfieldInput).sendKeys(internalfield);
//			Thread.sleep(2000);
//			driver.findElement(internalfieldInput).sendKeys(Keys.TAB);
			wait.until(ExpectedConditions.presenceOfElementLocated(dropdownselection)).click();
			logger.log(LogStatus.PASS, "internalfieldInput is clicked and value entered successfully");
		}
		
	}else {
		if(internalfield.equalsIgnoreCase("NA")) {
		logger.log(LogStatus.PASS, "internalfieldInput is not present as expectted");
		}else {
			logger.log(LogStatus.FAIL, "internalfieldInput is not present "+internalfield);	
		}
	}
	
	String screenshotpath=BaseClass.addScreenshot(driver);
	logger.log(LogStatus.INFO, logger.addScreenCapture(screenshotpath));
	
	wait.until(ExpectedConditions.presenceOfElementLocated(createbutton)).click();
	
	
	String experiment_name=wait.until(ExpectedConditions.presenceOfElementLocated(createdexperimentnamefetch)).getText();
	System.out.println("experiment_name:"+experiment_name);
	if(shareproject.equalsIgnoreCase("yes")) {
		Boolean ispresent_sharingbutton=driver.findElements(sharingbutton).size()>0;
		if(ispresent_sharingbutton) {
			logger.log(LogStatus.PASS, "Sharing button is enabled for Admin user as expected for experiment"+experiment_name);
		}else {
			logger.log(LogStatus.FAIL, "Sharing button is not enabled for Admin user as expected for experiment"+experiment_name);
		}
	}else if(shareproject.equalsIgnoreCase("no")|| shareproject.equalsIgnoreCase("NA")){
		Boolean ispresent_sharingbutton=driver.findElements(sharingbutton).size()<1;
		if(ispresent_sharingbutton) {
			logger.log(LogStatus.PASS, "Sharing button is not enabled  for standard user as expected for experiment"+experiment_name);
		}else {
			logger.log(LogStatus.FAIL, "Sharing button is enabled  for Standard user not as expected for experiment"+experiment_name);
		}
	}
	
	String screenshotpath_Signing=BaseClass.addScreenshot(driver);
	logger.log(LogStatus.INFO, logger.addScreenCapture(screenshotpath_Signing));
	
	Boolean ispresent_signingbutton=driver.findElements(signingbutton).size()>0;
	if(ispresent_signingbutton) {
		
		logger.log(LogStatus.PASS, "signingbutton is enabled for user as expected for experiment"+experiment_name);
	}else {
		logger.log(LogStatus.FAIL, "signingbutton is enabled for  user as expected for experiment"+experiment_name);
	}
	
}



public void newExperimentcreation(WebDriver driver,WebDriverWait wait,ExtentTest logger,String Usertype,String template,String description,String notebook,String users,String project,String Department,String internalfield) throws InterruptedException {
	
	Boolean ispresent_Addnew=driver.findElements(Addnewicon).size()>0;
	if(ispresent_Addnew) {
		driver.findElement(Addnewicon).click();
		logger.log(LogStatus.PASS, "Add new button is clicked successfully");
	}else {
		logger.log(LogStatus.FAIL, "Add new button is not clicked successfully");
	}
	
	
	
	Boolean ispresent_newExperimentbutton=driver.findElements(newExperimentbutton).size()>0;
	if(ispresent_newExperimentbutton) {
		driver.findElement(newExperimentbutton).click();
		logger.log(LogStatus.PASS, "newExperimentbutton is clicked successfully");
	}else {
		logger.log(LogStatus.FAIL, "newExperimentbutton is not clicked successfully");
	}
	
	
	wait.until(ExpectedConditions.presenceOfElementLocated(cancelbutton));

	Boolean ispresent_templateClosebutton=driver.findElements(templateClosebutton).size()>0;
	if(ispresent_templateClosebutton) {
		driver.findElement(templateClosebutton).click();
		logger.log(LogStatus.PASS, "templateClosebutton is clicked successfully");
	}else {
		//nothing to do if close button is not present.
	}
	
	
	Boolean ispresent_templateinput=driver.findElements(templateinput).size()>0;
	if(ispresent_templateinput) {
		driver.findElement(templateinput).click();
		
		logger.log(LogStatus.PASS, "templateinput is clickedsuccessfully");
	}else {
		//nothing to do if close button is not present.
	}
	
	if(template.equalsIgnoreCase("NA")) {
		
	}else {
		driver.findElement(templateinput).sendKeys(template);
		Thread.sleep(2000);
		driver.findElement(templateinput).sendKeys(Keys.ENTER);
	}
	
	
	wait.until(ExpectedConditions.presenceOfElementLocated(cancelbutton));
	Boolean ispresent_descriptioninput=driver.findElements(descriptioninput).size()>0;
	if(ispresent_descriptioninput) {
//		driver.findElement(descriptioninput).click();
		driver.findElement(descriptioninput).sendKeys(description);
		logger.log(LogStatus.PASS, "descriptioninput is clicked and value entered successfully");
	}else {
		logger.log(LogStatus.FAIL, "descriptioninput is not clicked and value not entered successfully");
	}
	
	
	Boolean ispresent_notebookinput=driver.findElements(notebookinput).size()>0;
	if(ispresent_notebookinput) {
		
		if(notebook.equalsIgnoreCase("NA")) {
			
		}else {
			//driver.findElement(notebookinput).click();
			driver.findElement(notebookinput).sendKeys(notebook);
			
//			driver.findElement(notebookinput).sendKeys(Keys.TAB);
			wait.until(ExpectedConditions.presenceOfElementLocated(dropdownselection)).click();
			logger.log(LogStatus.PASS, "notebookinput is clicked and value entered successfully");
			
		}
		
	}else {
		logger.log(LogStatus.FAIL, "notebookinput is not present");
	}
	
	Boolean ispresent_usersinput=driver.findElements(usersinput).size()>0;
	if(ispresent_usersinput) {
		
		if(users.equalsIgnoreCase("NA")) {
			
		}else {
			//driver.findElement(usersinput).click();
			driver.findElement(usersinput).sendKeys(users);
//			Thread.sleep(2000);
//			driver.findElement(usersinput).sendKeys(Keys.TAB);
			wait.until(ExpectedConditions.presenceOfElementLocated(dropdownselection)).click();
			logger.log(LogStatus.PASS, "usersinput is clicked and value entered successfully");
		}
		
	}else {
		if(users.equalsIgnoreCase("NA")) {
		logger.log(LogStatus.PASS, "usersinput is not present as expectted"+users);
		}else {
			logger.log(LogStatus.FAIL, "usersinput is not present "+users);	
		}
	}
	Thread.sleep(3000);
//	((JavascriptExecutor)driver).executeScript("scroll(0,500)");
//	JavascriptExecutor js = ((JavascriptExecutor) driver);
//	js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
//	Thread.sleep(3500);


	
	Boolean ispresent_projectinput=driver.findElements(projectinput).size()>0;
	if(ispresent_projectinput) {
		
		if(project.equalsIgnoreCase("NA")) {
			
		}else {
			//driver.findElement(projectinput).click();
			driver.findElement(projectinput).sendKeys(project);
			Thread.sleep(5000);
//			driver.findElement(projectinput).sendKeys(Keys.TAB);
			Actions actions = new Actions(driver);

			actions.moveToElement(driver.findElement(dropdownselection)).click().perform();
			//wait.until(ExpectedConditions.presenceOfElementLocated(dropdownselection)).click();
			logger.log(LogStatus.PASS, "projectinput is clicked and value entered successfully");
		}
		
	}else {
		if(project.equalsIgnoreCase("NA")) {
		logger.log(LogStatus.PASS, "projectinput is not present as expectted");
		}else {
			logger.log(LogStatus.FAIL, "projectinput is not present ");	
		}
	}
	
	
	Boolean ispresent_departmentInput=driver.findElements(departmentInput).size()>0;
	if(ispresent_departmentInput) {
		
		if(Department.equalsIgnoreCase("NA")) {
			
		}else {
			//driver.findElement(departmentInput).click();
			driver.findElement(departmentInput).sendKeys(Department);
//			Thread.sleep(2000);
//			driver.findElement(departmentInput).sendKeys(Keys.TAB);
			wait.until(ExpectedConditions.presenceOfElementLocated(dropdownselection)).click();
			logger.log(LogStatus.PASS, "departmentInput is clicked and value entered successfully");
		}
		
	}else {
		if(Department.equalsIgnoreCase("NA")) {
		logger.log(LogStatus.PASS, "departmentInput is not present as expectted");
		}else {
			logger.log(LogStatus.FAIL, "departmentInput is not present ");	
		}
	}
	
	
	
	Boolean ispresent_internalfieldInput=driver.findElements(internalfieldInput).size()>0;
	if(ispresent_internalfieldInput) {
		
		if(internalfield.equalsIgnoreCase("NA")) {
			
		}else {
			//driver.findElement(internalfieldInput).click();
			driver.findElement(internalfieldInput).sendKeys(internalfield);
//			Thread.sleep(2000);
//			driver.findElement(internalfieldInput).sendKeys(Keys.TAB);
			wait.until(ExpectedConditions.presenceOfElementLocated(dropdownselection)).click();
			logger.log(LogStatus.PASS, "internalfieldInput is clicked and value entered successfully");
		}
		
	}else {
		if(internalfield.equalsIgnoreCase("NA")) {
		logger.log(LogStatus.PASS, "internalfieldInput is not present as expectted");
		}else {
			logger.log(LogStatus.FAIL, "internalfieldInput is not present "+internalfield);	
		}
	}
	
	String screenshotpath=BaseClass.addScreenshot(driver);
	logger.log(LogStatus.INFO, logger.addScreenCapture(screenshotpath));
	
	wait.until(ExpectedConditions.presenceOfElementLocated(createbutton)).click();
}


public void verifyCreatedExperimentTemplate(WebDriver driver,WebDriverWait wait) throws InterruptedException {
	wait.until(ExpectedConditions.presenceOfElementLocated(Addnewicon)).click();
	Thread.sleep(7000);
}


//click on add new and clicks on create new experiment
public void clickonNEwexperiment(WebDriver driver,WebDriverWait wait,ExtentTest logger,String templatetype) throws InterruptedException {
	
		Boolean ispresent_Addnew=driver.findElements(Addnewicon).size()>0;
		if(ispresent_Addnew) {
			driver.findElement(Addnewicon).click();
			logger.log(LogStatus.PASS, "Add new button is clicked successfully");
		}else {
			logger.log(LogStatus.FAIL, "Add new button is not clicked successfully");
		}
		
		
		if(templatetype.equalsIgnoreCase("experiment")) {
		Boolean ispresent_newExperimentbutton=driver.findElements(newExperimentbutton).size()>0;
		if(ispresent_newExperimentbutton) {
			driver.findElement(newExperimentbutton).click();
			logger.log(LogStatus.PASS, "newExperimentbutton is clicked successfully");
		}else {
			logger.log(LogStatus.FAIL, "newExperimentbutton is not clicked successfully");
		}
		}else if(templatetype.equalsIgnoreCase("Admin defined")) {
			Boolean ispresent_newadmintemplatebutton=driver.findElements(newAdminDefinedbutton).size()>0;
			if(ispresent_newadmintemplatebutton) {
				driver.findElement(newAdminDefinedbutton).click();
				logger.log(LogStatus.PASS, "newadmintemplatebutton is clicked successfully");
			}else {
				logger.log(LogStatus.FAIL, "newadmintemplatebutton is not clicked successfully");
			}
		}
	
}

//Search for a template while creating a experiemnt and validate if search template is present.
public void SearchforNewExperimenttemplate(WebDriver driver,WebDriverWait wait,ExtentTest logger,String template) throws InterruptedException {
	
		wait.until(ExpectedConditions.presenceOfElementLocated(cancelbutton));
	
		Boolean ispresent_templateClosebutton=driver.findElements(templateClosebutton).size()>0;
		if(ispresent_templateClosebutton) {
			driver.findElement(templateClosebutton).click();
			logger.log(LogStatus.PASS, "templateClosebutton is clicked successfully");
		}else {
			//nothing to do if close button is not present.
		}
		
		
		Boolean ispresent_templateinput=driver.findElements(templateinput).size()>0;
		if(ispresent_templateinput) {
			driver.findElement(templateinput).click();
			
			logger.log(LogStatus.PASS, "templateinput is clickedsuccessfully");
		}else {
			//nothing to do if close button is not present.
		}
		
		driver.findElement(templateinput).sendKeys(template);
		Thread.sleep(3000);
		driver.findElement(templateinput).sendKeys(Keys.ENTER);
	
	
	
		wait.until(ExpectedConditions.presenceOfElementLocated(cancelbutton));
		
		Boolean ispresent_templateClosebutton2=driver.findElements(templateClosebutton).size()>0;
		if(ispresent_templateClosebutton2) {
			driver.findElement(templateClosebutton).click();
			logger.log(LogStatus.PASS, "template created by system admin is reflected for end user for experiment creation.");
		}else {
			//no
			logger.log(LogStatus.FAIL, "template created by system admin is not reflected for end user for experiment creation.");
		
		}
		

	}

public void mandatoryOrNotFieldValidation(WebDriver driver,WebDriverWait wait,ExtentTest logger,String field1,String field1YorN) {
	
	
	String[] fields=field1.split(",");
	String[] mandatoryYorN=field1YorN.split(",");
	
	int fieldlength=fields.length;
	
	for(int i=0;i<fieldlength;i++) {
		
			
			Boolean ispresent_experimentcreatepageName=driver.findElements(By.xpath("//label[text()='"+fields[i]+"']")).size()>0;
			if(ispresent_experimentcreatepageName) {
				logger.log(LogStatus.PASS, fields[i]+" field is displayed as expected");
				if(mandatoryYorN[i].equalsIgnoreCase("Yes")) {
					Boolean ispresent_mandatorysymbol=driver.findElements(By.xpath("//label[text()='"+fields[i]+"']//span[text()='*']")).size()>0;
					if(ispresent_mandatorysymbol) {
						logger.log(LogStatus.PASS, fields[i]+" field is displayed with mandatory symbol as expected");
					}else {
						logger.log(LogStatus.FAIL, fields[i]+" field is not displayed with mandatory symbol as expected");	
					}
				}
			}else {
				logger.log(LogStatus.FAIL, fields[i]+" field is  not displayed as expected");
			}
			String screenshotpath=BaseClass.addScreenshot(driver);
			logger.log(LogStatus.INFO, logger.addScreenCapture(screenshotpath));
		
	}	
}

public void experimenticonclick(WebDriver driver,WebDriverWait wait,ExtentTest logger,String Experimentname) throws InterruptedException {
	wait.until(ExpectedConditions.presenceOfElementLocated(Experimentsicon)).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//bdi[@title='"+Experimentname+"']")).click();
	logger.log(LogStatus.PASS,Experimentname+" experiment is clicked successfully ");
	String screenshotpath=BaseClass.addScreenshot(driver);
	logger.log(LogStatus.INFO, logger.addScreenCapture(screenshotpath));
}


public void clickonAddContent(WebDriver driver,WebDriverWait wait,ExtentTest logger,String Experimentname) throws InterruptedException {
	Thread.sleep(2000);
	wait.until(ExpectedConditions.presenceOfElementLocated(Addcontenticon)).click();
	Thread.sleep(2000);
	logger.log(LogStatus.PASS,"Add content icon is clicked successfully");
	String screenshotpath=BaseClass.addScreenshot(driver);
	logger.log(LogStatus.INFO, logger.addScreenCapture(screenshotpath));
}
	
public void selecttemplatefromAddContent(WebDriver driver,WebDriverWait wait,ExtentTest logger,String selecttemplate) {
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='dropdown-item']//div[text()='"+selecttemplate+"']/span/i"))).click();
	logger.log(LogStatus.PASS,"from the dropdown "+selecttemplate+"  is clicked successfully");
}

public void Uploadfilepath(String Filename) throws AWTException, InterruptedException {
		
		String path=System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\"+Filename;
		System.out.println(path);
		StringSelection ss = new StringSelection(path);
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
	    Thread.sleep(5000);
	    Robot robot = new Robot();
	    robot.keyPress(KeyEvent.VK_CONTROL);
	    robot.keyPress(KeyEvent.VK_V);
	    robot.keyRelease(KeyEvent.VK_V);
	    robot.keyRelease(KeyEvent.VK_CONTROL);
	    robot.keyPress(KeyEvent.VK_ENTER);
	    robot.keyRelease(KeyEvent.VK_ENTER);
	    
}

public void Fileuploadchecker(WebDriver driver,WebDriverWait wait,ExtentTest logger,String selecttemplate,String filename) throws InterruptedException {
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='binder__element-wrapper']//span[text()='"+filename+"']")));
	Boolean element_Presentornot=driver.findElements(By.xpath("//div[@class='binder__element-wrapper']//span[text()='"+filename+"']")).size()>0;
	Boolean encrytederrormessage=driver.findElements(By.xpath("//span[contains(text(),'"+filename+"\" is an encrypted')]")).size()>0;
	if(element_Presentornot && !encrytederrormessage) {
		logger.log(LogStatus.PASS, "encrypted file is uploaded successfully as checkbox is not checked in systemconfig ");
		System.out.println("encrypted file is uploaded successfully as checkbox is not checked in systemconfig 1");
	}else {
		logger.log(LogStatus.FAIL, "encrypted file is not uploaded successfully as checkbox even though not checked in systemconfig");
		System.out.println("encrypted file is not uploaded successfully as checkbox even though is not checked in systemconfig fail 1");
	}
	
	Thread.sleep(8000);
}



public void Fileencrytederrormessageuploadchecker(WebDriver driver,WebDriverWait wait,ExtentTest logger,String selecttemplate,String filename) throws InterruptedException {
	
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='binder__element-wrapper']//span[text()='"+filename+"']")));
	Boolean element_Presentornot=driver.findElements(By.xpath("//div[@class='binder__element-wrapper']//span[text()='"+filename+"']")).size()>0;
	Boolean encrytederrormessage=driver.findElements(By.xpath("//span[contains(text(),'"+filename+"\" is an encrypted')]")).size()>0;
	if(element_Presentornot && encrytederrormessage) {
		logger.log(LogStatus.PASS, "encrypted file is not uploaded successfully as checkbox is  checked in systemconfig");
		System.out.println("encrypted file is not uploaded successfully as checkbox is  checked in systemconfig 2");
//		Boolean encrytederrormessage_closeicon=driver.findElements(By.xpath("//span[contains(text(),'"+filename+"\" is an encrypted')]/following-sibling::span")).size()>0;
//		if(encrytederrormessage_closeicon) {
//			driver.findElement(By.xpath("//span[contains(text(),'"+filename+"\" is an encrypted')]/following-sibling::span")).click();
//			System.out.println("close button of error message is clicked.");
//		}
		
	}else {
		logger.log(LogStatus.FAIL, "encrypted file is not uploaded successfully as checkbox  checked in systemconfig ");
		System.out.println("encrypted file is not uploaded successfully as checkbox even though is not checked in systemconfig fail 2");
	}
	Thread.sleep(8000);
}



public String[] filesnotallowed(WebDriver driver,WebDriverWait wait,ExtentTest logger) {
	String uploadfilesexcept=ConfigFileReader("filesnotallowed");
	//String uploadfilesexcept=properties.getProperty("uploadfilesexcept");
	String[] uploadfilesname=uploadfilesexcept.split(",");
	return uploadfilesname;
}

public String[] filesnames(WebDriver driver,WebDriverWait wait,ExtentTest logger) {
	String uploadfilesexcept=ConfigFileReader("filenames");
	//String uploadfilesexcept=properties.getProperty("uploadfilesexcept");
	String[] uploadfilesname=uploadfilesexcept.split(",");
	return uploadfilesname;
}

public int getfilenames_size(WebDriver driver,WebDriverWait wait,ExtentTest logger,String[] Filenames) {
	int size=Filenames.length;
	return size;
	
}

public void Filetypenotalloweduploadchecker(WebDriver driver,WebDriverWait wait,ExtentTest logger,String selecttemplate,String filename) throws InterruptedException {
	
	String[] Arrayvalue=filename.split("\\.");
	//System.out.println("Arraylength is :"+Arrayvalue.length+ " and file value is:"+filename);
	//System.out.println("filetype is: "+Arrayvalue[1]);
	
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='binder__element-wrapper']//span[text()='"+filename+"']")));
	Boolean element_Presentornot=driver.findElements(By.xpath("//div[@class='binder__element-wrapper']//span[text()='"+filename+"']")).size()>0;
	Boolean filenotallowederrormessage=driver.findElements(By.xpath("//span[contains(text(),'Files of type ."+Arrayvalue[1]+" are not allowed')]")).size()>0;
	System.out.println("element_Presentornot:"+element_Presentornot+" filenotallowederrormessage:"+filenotallowederrormessage);
	if(element_Presentornot && filenotallowederrormessage) {
		logger.log(LogStatus.PASS, Arrayvalue[1]+"type  is not uploaded successfully as checkbox of all types ystemconfig");
		System.out.println(Arrayvalue[1]+"type  is not uploaded successfully as checkbox of all types ystemconfig");
//		Boolean filenotallowederrormessage_closeicon=driver.findElements(By.xpath("//span[contains(text(),'Files of type "+Arrayvalue[1]+" are not allowed')]/following-sibling::span")).size()>0;
//		if(filenotallowederrormessage_closeicon) {
//			driver.findElement(By.xpath("//span[contains(text(),'Files of type "+Arrayvalue[1]+" are not allowed')]/following-sibling::span")).click();
//			System.out.println("close button of error message is clicked.");
//		}
		
	}else {
		logger.log(LogStatus.FAIL, Arrayvalue[1]+"type  is  uploaded but should not have been uploaded because alltype checkbox is checked ");
		System.out.println(Arrayvalue[1]+"type  is  uploaded but should not have been uploaded because alltype checkbox is checked ");
	}
	Thread.sleep(8000);
}




public void whitelistfiletypechecker(WebDriver driver,WebDriverWait wait,ExtentTest logger,String selecttemplate,String filename,ArrayList array_filetype_merged) throws InterruptedException {
	String[] Arrayvalue=filename.split("\\.");
	//System.out.println("Arraylength is :"+Arrayvalue.length+ " and file value is:"+filename);
	//System.out.println("filetype is: "+Arrayvalue[1]);

	Boolean containsornot=array_filetype_merged.contains("."+Arrayvalue[1]) ;
	//System.out.println("containsornot:"+containsornot);
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='binder__element-wrapper']//span[text()='"+filename+"']")));
	boolean errormessagepresent_or_not=driver.findElements(By.xpath("//span[contains(text(),'Files of type ."+Arrayvalue[1]+" are not allowed')]")).size()>0;
	
	if(containsornot) {
		System.out.println("no error message is displayed for :"+Arrayvalue[1]);
		if(errormessagepresent_or_not) {
			System.out.println("Failed error message is present for acceptable whitelisted file type");
		}else {
			System.out.println("passed error message is not present for acceptable whitelisted file type");
		}
		
	}else {
		System.out.println("error message has to be displayed for :"+Arrayvalue[1]);
		if(errormessagepresent_or_not) {
			System.out.println("Pass error message is present for not acceptable whitelisted file type");
		}else {
			System.out.println("Fail error message is not present for not acceptable whitelisted file type");
		}
	}
	
	
	
}


public void selectTableTemplate(WebDriver driver,WebDriverWait wait,ExtentTest logger,String Templatename ){
	wait.until(ExpectedConditions.presenceOfElementLocated(AdmindefinedtemplateDialogbox)).sendKeys(Templatename);;
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='table-template-selection-item-undefined']/strong[text()='"+Templatename+"']"))).click();
	
	wait.until(ExpectedConditions.presenceOfElementLocated(createButton)).click();
	
	logger.log(LogStatus.PASS,"Create button is clicked successfully");
	
}

public void tableTemplateValidation(WebDriver driver,WebDriverWait wait,ExtentTest logger,String Templatename,String field1 ) throws InterruptedException{
	
	String[] fields=field1.split(",");
	int fieldlength=fields.length;
	
	String screenshotpath=BaseClass.addScreenshot(driver);
	logger.log(LogStatus.INFO, logger.addScreenCapture(screenshotpath));
	
	for(int i=0;i<fieldlength;i++) {
		try {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='binder__element-header']//span[text()='"+Templatename+"']/../../../..//div[@class='header']//div[text()='"+fields[i]+"']")));
		}catch(Exception e) {
			
		}
		
		Boolean ispresent_values=driver.findElements(By.xpath("//div[@class='binder__element-header']//span[text()='"+Templatename+"']/../../../..//div[@class='header']//div[text()='"+fields[i]+"']")).size()>0;
		if(ispresent_values) {
			logger.log(LogStatus.PASS, fields[i]+" value is present in header of table template as expected");
		}else {
			logger.log(LogStatus.FAIL, fields[i]+" value is not present in header of table template as expected");
		}
	}
}

public void ExternalactionValidation(WebDriver driver,WebDriverWait wait,ExtentTest logger) {
	
}

}

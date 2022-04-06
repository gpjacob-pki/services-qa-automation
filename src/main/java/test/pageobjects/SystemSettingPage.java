package test.pageobjects;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import test.Utility.BaseClass;

public class SystemSettingPage {

	By Autotext_list=By.xpath("//div[@class='materials-sidebar__subsection']//a[text()='Lists']");
	By Autotext_Snippets=By.xpath("//div[@class='materials-sidebar__subsection']//a[text()='Snippets']");
	
	By Autotext_List_size=By.xpath("(//div[@class='editable-list__content'])[1]//div[contains(@data-index,'list-element')]");
	By Autotext_sublist_size=By.xpath("(//div[@class='editable-list'])[2]//div[@class='editable-list__content']//div[contains(@data-index,'list-element')]");
	
	
	
	public void ClickonAutotextList(WebDriver driver,WebDriverWait wait,ExtentTest logger) {
		wait.until(ExpectedConditions.presenceOfElementLocated(Autotext_list)).click();
	}
	
	public void ClickonAutotextSnippet(WebDriver driver,WebDriverWait wait,ExtentTest logger) {
		wait.until(ExpectedConditions.presenceOfElementLocated(Autotext_Snippets)).click();
	}
	
	public ArrayList getsnippet_valueAutotext(WebDriver driver,WebDriverWait wait,ExtentTest logger) throws InterruptedException {
		ClickonAutotextSnippet(driver, wait, logger);
		ArrayList Autotextsnippets=new ArrayList();
		int AutotextList_size=wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(Autotext_List_size)).size();
		for(int i=0;i<AutotextList_size;i++) {
			Autotextsnippets.add(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='editable-list__dragable-row ']//div[@class='editable-list__row'])["+(i+1)+"]"))).getText());
			
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='editable-list__dragable-row ']//div[@class='editable-list__row'])["+(i+1)+"]"))).click();
			System.out.println(Autotextsnippets.get(i)+" and sixe is :"+AutotextList_size);
			Thread.sleep(2000);
			String screenshotpath=BaseClass.addScreenshot(driver);
			logger.log(LogStatus.INFO, logger.addScreenCapture(screenshotpath));
			//System.out.println(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body[@class='mce-content-body ']/p"))).getText());
		}
		return Autotextsnippets;
		}
	
	public ArrayList getAutotxt_listvalues(WebDriver driver,WebDriverWait wait,ExtentTest logger) {
		ArrayList Autotextlist=new ArrayList();
		ClickonAutotextList(driver, wait, logger);
		int AutotextList_size=wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(Autotext_List_size)).size();
		for(int i=0;i<AutotextList_size;i++) {
			Autotextlist.add(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='editable-list__content']//div[contains(@data-index,'list-element')]//div[@class='editable-list__row__text oneline'])["+(i+1)+"]"))).getText());
			//System.out.println(Autotextlist.get(i));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='editable-list__content']//div[contains(@data-index,'list-element')]//div[@class='editable-list__row__text oneline'])["+(i+1)+"]"))).click();
		
			int AutotextSubList_size=wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(Autotext_sublist_size)).size();
			for(int j=0;j<AutotextSubList_size;j++) {
				Autotextlist.add(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("((//div[@class='editable-list'])[2]//div[@class='editable-list__content']//div[contains(@data-index,'list-element')]//div[@class='editable-list__row__text oneline'])["+(j+1)+"]"))).getText());
			}
		
		}
		
		int list_size=Autotextlist.size();
		for(int i=0;i<list_size;i++) {
			System.out.println(Autotextlist.get(i));
		}
		return Autotextlist;
	
	}
	
	
}

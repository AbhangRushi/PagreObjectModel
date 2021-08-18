package main.java.automation.PageObject;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import automation.coreClasses.BasePreCondition;

public class LocationTaxRegionCreatePage extends CommonUtilityPage{

	public LocationTaxRegionCreatePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	private static final By verifyTaxRegionSubmitAndReturntoListBtn = By.xpath("//*[@id='btnSubmitList']");
	private static final By verifyTaxRegionSubmitBtn =By.xpath("//*[@id='btnSubmit']");
	
	private static final By verifyTaxRegionCancelAndReturnToListBtn = By.xpath("//*[@id='btnCancelList']");
	private static final By verifyTaxRegionCancelBtn = By.xpath("//*[@id='btnCancel']");
	
	//Locators for adding data in form
	
	private static final By TaxRegionStateSelect = By.xpath("//*[@id='select2-StateId-container']");
	private static final By TaxRegionTaxRegionField = By.xpath("//*[@id='TaxRegion']");
	private static final By taxRegionTaxRate = By.xpath("//*[@id='TaxRate']");
	private static final By TaxRegionSequenceId = By.xpath("//*[@id='SequenceId']");
	
	
	
	
	public boolean verifyTaxRegionSubmitAndReturnToListBtnForDisable() {
		
		if(!selenium.findElement(verifyTaxRegionSubmitAndReturntoListBtn).isEnabled()) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public boolean verifyTaxRegionsubmitBtnForDisable() {
		
		if(!selenium.findElement(verifyTaxRegionSubmitBtn).isEnabled()) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean verifyTaxRegionCancelAndReturnToLIstBtnForEnable() {
		
		if(!selenium.findElement(verifyTaxRegionCancelAndReturnToListBtn).isEnabled()) {
			return true;
		}
		    return false;
		
	}
	
	public boolean verifyTaxRegionCancelBtnForEnable() {
		
		if(!selenium.findElement(verifyTaxRegionCancelBtn).isEnabled()) {
			return true;
		}
			return false;
	}
	
	public void addInfoInNewTaxRegionForm() throws InterruptedException {
		
		click(TaxRegionStateSelect);
		//Actions action = new Actions(selenium);
		Thread.sleep(3000);
		implicitWait(10);
		try {
			
			Robot robot = new Robot();
			Thread.sleep(2000);	
			robot.keyPress(KeyEvent.VK_DOWN);
			Thread.sleep(2000);	
			robot.keyPress(KeyEvent.VK_DOWN);
			Thread.sleep(2000);	
			/*
			 * robot.keyPress(KeyEvent.VK_DOWN); Thread.sleep(2000);
			 */
			robot.keyPress(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		implicitWait(5);
		sendKeys(TaxRegionTaxRegionField, "abhangwadi");
		
		implicitWait(5);
		sendKeys(taxRegionTaxRate, "10");
		
		implicitWait(5);
		sendKeys(TaxRegionSequenceId, "12");
		
	}
	
	public void clickTaxRegionOnSubmitBtn() {
		implicitWait(5);
		click(verifyTaxRegionSubmitBtn);
	}
	
	public boolean verifyCountyForClearedForm(){
		
		WebElement element = selenium.findElement(TaxRegionTaxRegionField);
		String elementtxt = element.getAttribute("value");
		if (elementtxt.isEmpty()) {
			element.findElement(taxRegionTaxRate);
			elementtxt=element.getAttribute("value");
			if (elementtxt.isEmpty()) {
				return true;
			}
		}
		return false;
	}
	
	public void clickOnTaxRegionSubmitAndReturnToList() {
		
		implicitWait(5);
		click(verifyTaxRegionSubmitAndReturntoListBtn);
	}
	
	public void clickOnTaxRegionCancelBtn() {
		implicitWait(5);
		click(verifyTaxRegionCancelBtn);
	}
	public void verifyTaxRegionCancelAndReturnToListBtn() {
		implicitWait(5);
		click(verifyTaxRegionCancelAndReturnToListBtn);
	}
}

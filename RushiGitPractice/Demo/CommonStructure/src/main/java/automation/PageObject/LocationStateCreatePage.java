package main.java.automation.PageObject;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LocationStateCreatePage extends CommonUtilityPage{

	public LocationStateCreatePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	private static final By statePageSubmitAndReturnToListBtn = By.xpath("//*[@id='btnSubmitList']");
	private static final By statepageSubmitBtn = By.xpath("//*[@id='btnSubmit']");
	private static final By stateCancelAndReturnToListBtn = By.xpath("//a[@id='btnCancelList']");
	private static final By stateCancelBtn = By.xpath("//a[@id='btnCancel']"); 
	
	// ########################## Locators for Add data in new state ################################
	private static final By stateCreatepageCountryCode=By.xpath("//select[@id='CountryCode']");
	private static final By stateCreatepageStateName = By.xpath("//input[@id='StateName']");
	private static final By stateCreatepageStateCode = By.xpath("//input[@id='StateCode']");
	private static final By stateCreatepageTimeZone = By.xpath("//*[@id='StatesForm']/div/div/div[5]/div/button");
	private static final By stateCreatePageSequenceId = By.xpath("//input[@id='SequenceId']");
	
	
	
	public boolean verifyStateSubmitAndReturnToListBtn() {
		
		if (!selenium.findElement(statePageSubmitAndReturnToListBtn).isEnabled()) {
			return true;
		}
		
		return false;
		
	}
	
	public boolean verifyStateSubmitBtn() {
		
		if (!selenium.findElement(statepageSubmitBtn).isEnabled()) {
			return true;
		}
		return false;
		
	}
	
	public boolean verifyStateCancelAndReturnToListBtn() {
		
		if(!selenium.findElement(stateCancelAndReturnToListBtn).isEnabled()) {
			 return true;
		}
		 
		return false;
	 }
    public boolean verifyStateCancelBtn() {
	   
	   if (!selenium.findElement(stateCancelBtn).isEnabled()) {
		return true;
	   }
	   return false;
     }
   
     public void addInfoInNewState() {
	   
        try {
			
			implicitWait(5);
			selectValueFromDropdown(stateCreatepageCountryCode, "India - IN");
			Thread.sleep(3000);
			sendKeys(stateCreatepageStateName, "junnar");
			Thread.sleep(2000);
			sendKeys(stateCreatepageStateCode, "pune");
			implicitWait(5);
			
			//applaying Robot
			
			click(stateCreatepageTimeZone);
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
			
			Thread.sleep(5000);
			sendKeys(stateCreatePageSequenceId, "123456");
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception//*[@id='StatesForm']/div/div/div[5]/div/button
		}
    	
     }
     
     public void clickOnStatePageSubmitBtn() {
    	 
    	 implicitWait(5);
    	 click(statepageSubmitBtn);
     }
     
     public boolean stateClearedFormVerification() {
    	 
    	 implicitWait(5);
    	 
    	 WebElement element = selenium.findElement(stateCreatepageCountryCode);
    	 String elementTxt = element.getAttribute("value");
    	 if(elementTxt.isEmpty()) {
    		 
    		  element.findElement(stateCreatePageSequenceId);
    		  elementTxt = element.getAttribute("value");
    		  if (elementTxt.isEmpty()) {
				return true;
			}
    	 }
    	 return false;
     }
     
     public void clickOnStatepageSubmitAndReturnToListBtn() {
    	 
    	 implicitWait(5);
    	 click(statePageSubmitAndReturnToListBtn);
     }
     
     public void ClickOnStateCancelBtn() {
    	 implicitWait(5);
    	 click(stateCancelBtn);
    }
}

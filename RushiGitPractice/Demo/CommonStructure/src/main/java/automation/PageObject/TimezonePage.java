package main.java.automation.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TimezonePage extends CommonUtilityPage{
	
	public static String timezoneHeading;
	public static String timezoneTitle;

	public TimezonePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	private static final By timezoneHeadingLocator = By.xpath("//h1[contains(text(),'Timezone')]");
	private static final By timezonePageTimeZoneName =By.xpath("//thead/tr[2]/td[3]/input[1]");
	
	private static final By timezoneChkBox = By.xpath("//tr[1]/td[1]");
	private static final By timezonedeleteAllbtn = By.xpath("//button[@id='deleteAll']");
	private static final By cityPageConfirmDeleteAlert = By.xpath("//button[@id='confirmDeleteAllBtn']");
	
	private static final By addNewTimeZoneBtn = By.cssSelector("body > div.wrapper > div > section.content > form > div.row.row-fluid.gridactions > div.span3.col-md-3.text-left > a:nth-child(1)");
	
	
	
	
	
	
	 public void headingVerification() {
	  		
			timezoneHeading = getText(timezoneHeadingLocator);
		}

	 public void timezoneTitleVerification() {
		
		String title = selenium.getTitle();
    	timezoneTitle= title;
    	logger.info("County title is "+timezoneTitle);
	}
	
	 public boolean verifyTimezoneData() {
 		  
	   		try {
	   				  
	   			 implicitWait(5);
	   				sendKeysWithEnter(timezonePageTimeZoneName, "demotimezone");
	   				 Thread.sleep(3000);
	   				    
	   				  String timezoneName= selenium.findElement(By.xpath("//tr[1]/td[3]")).getText();
	   				  System.out.println("state code found in city page is :"+timezoneName);
	   				  
	   				  return true;
	   				} catch (Exception e) {
	   				
	   				//e.printStackTrace();
	   				return false;
	   			}
	      }
	  public void deleteTimezone() {
	   		try {
	   			
	   			implicitWait(5);
	   			click(timezoneChkBox);
	   			implicitWait(5);
	   			click(timezonedeleteAllbtn);
	   			
	   			WebDriverWait wait=new WebDriverWait(selenium, 20);
	   			
	   			//WebElement OkBtnConfirmDeleteAlert = wait.until(ExpectedConditions.visibilityOfElementLocated(null)
	   			//click(cityPageConfirmDeleteAlert);
	   			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(cityPageConfirmDeleteAlert));
	   			element.click();
	   			
	   		} catch (Exception e) {
	   			e.printStackTrace();
	   	}
	  }
	  
	  public void clickOnAddNewTimezoneBtn() {
		  
		  implicitWait(5);
		  click(addNewTimeZoneBtn);
	 }
	  
}

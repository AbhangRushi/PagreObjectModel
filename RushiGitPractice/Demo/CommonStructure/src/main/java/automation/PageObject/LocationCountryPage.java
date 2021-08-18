package main.java.automation.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocationCountryPage extends CommonUtilityPage {
	
	public static String countryHeading;
	public static String countryTitle;

	public LocationCountryPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
    
	private static final By locationCountryHeading = By.xpath("//h1[contains(text(),'Country')]");
	private static final By countyPageCountryName= By.xpath("//thead/tr[2]/td[3]/input[1]");
	private static final By countryPageChkBox = By.xpath("//tbody/tr/td[1]/div[1]/ins[1]");
	private static final By countryPagedeleteAllbtn= By.xpath("//button[@id='deleteAll']");
	private static final By countryPageConfirmDeleteAlert = By.xpath("//button[@id='confirmDeleteAllBtn']");
	private static final By addNewCountryTypeBtn = By.cssSelector("body > div.wrapper > div > section.content > form > div.row.row-fluid.gridactions > div.span3.col-md-3.text-left > a:nth-child(1)");
	
	public void countryHeadingVerification() {
		
		countryHeading = getText(locationCountryHeading);
	}
	
	public void countryTitleVerification() throws InterruptedException {
	    	Thread.sleep(3000);
	    	String title = selenium.getTitle();
	    	countryTitle= title;
	    	logger.info("County title is "+countryTitle);
	    	
	    }
	
	 public boolean verifyCountryData() {
		  
	   		try {
	   				  
	   			 implicitWait(5);
	   				sendKeysWithEnter(countyPageCountryName, "africa");
	   				 Thread.sleep(3000);
	   				    
	   				  String countryName= selenium.findElement(By.xpath("//tr[1]/td[3]")).getText();
	   				  System.out.println("state code found in city page is :"+countryName);
	   				  
	   				  return true;
	   				} catch (Exception e) {
	   				
	   				//e.printStackTrace();
	   				return false;
	   			}
	      }
	  public void deleteCountry() {
	   		try {
	   			
	   			implicitWait(5);
	   			click(countryPageChkBox);
	   			implicitWait(5);
	   			click(countryPagedeleteAllbtn);
	   			
	   			WebDriverWait wait=new WebDriverWait(selenium, 20);
	   			
	   			//WebElement OkBtnConfirmDeleteAlert = wait.until(ExpectedConditions.visibilityOfElementLocated(null)
	   			//click(cityPageConfirmDeleteAlert);
	   			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(countryPageConfirmDeleteAlert));
	   			element.click();
	   			
	   			
	   		} catch (Exception e) {
	   			e.printStackTrace();
	   		}
	   		
	   	 }
	public void clickOnAddNewCountryBtn() {
		
		implicitWait(5);
		click(addNewCountryTypeBtn);
		
	}
	   	  
}

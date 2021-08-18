package main.java.automation.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocationCountyPage extends CommonUtilityPage{
	
	public static String countyHeading;
	public static String countyTitle;
	public LocationCountyPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	private static final By locationCountyHeading = By.xpath("//h1[contains(text(),'County')]");
	private static final By countyPageCountyName= By.xpath("//thead/tr[2]/td[3]/input[1]");
	private static final By cityPageConfirmDeleteAlert = By.xpath("//button[@id='confirmDeleteAllBtn']");
	private static final By cityPageChkBox = By.xpath("//tr[1]/td[1]");
	private static final By cityPagedeleteAllbtn = By.xpath("//button[@id='deleteAll']");
	
	//Locator for click on add new county type button method
	private static final By addNewCountyTypeBtn = By.cssSelector("body > div.wrapper > div > section.content > form > div.row.row-fluid.gridactions > div.span3.col-md-3.text-left > a:nth-child(1)");
	
     public void headingVerification() {
  		
		countyHeading = getText(locationCountyHeading);
		
		
	}
	
    public void countyTitleVerification() {
    	
    	String title = selenium.getTitle();
    	countyTitle= title;
    	logger.info("County title is "+countyTitle);
    }
	
    
    public boolean verifyCountyData() {
 		  
   		try {
   				  
   			 implicitWait(5);
   				sendKeysWithEnter(countyPageCountyName, "pune");
   				 Thread.sleep(3000);
   				    
   				  String countyName= selenium.findElement(By.xpath("//tr[1]/td[3]")).getText();
   				  System.out.println("state code found in city page is :"+countyName);
   				  
   				  return true;
   				} catch (Exception e) {
   				
   				//e.printStackTrace();
   				return false;
   			}
      }
   	  public void deleteCounty() {
   		try {
   			
   			implicitWait(5);
   			click(cityPageChkBox);
   			implicitWait(5);
   			click(cityPagedeleteAllbtn);
   			
   			WebDriverWait wait=new WebDriverWait(selenium, 20);
   			
   			//WebElement OkBtnConfirmDeleteAlert = wait.until(ExpectedConditions.visibilityOfElementLocated(null)
   			//click(cityPageConfirmDeleteAlert);
   			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(cityPageConfirmDeleteAlert));
   			element.click();
   			
   			
   		} catch (Exception e) {
   			e.printStackTrace();
   		}
   		
   	 }
    	public void clickOnAddNewCountyType() {
			
			implicitWait(5);
			click(addNewCountyTypeBtn);
    		
		}
}

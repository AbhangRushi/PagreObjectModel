package main.java.automation.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocationZipCodePage extends CommonUtilityPage{
	
	public static String zipCodeHeading;
	public static String zipCodeTitle;

	public LocationZipCodePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
 
	private static final By locationZipCodeHeading = By.xpath("//h1[contains(text(),'Zipcode')]");
	private static final By addNewzipCodeTypeBtn = By.cssSelector("body > div.wrapper > div > section.content > form > div.row.row-fluid.gridactions > div.span3.col-md-3.text-left > a:nth-child(1)");
	
	private static final By zipCodeZipCodeValue = By.xpath("//thead/tr[2]/td[3]/input[1]");
	
	private static final By zipPagePageChkBox = By.xpath("//tbody//td[1]/div");
	private static final By cityPagedeleteAllbtn = By.xpath("//button[@id='deleteAll']");
	private static final By cityPageConfirmDeleteAlert = By.xpath("//button[@id='confirmDeleteAllBtn']");
	
	
    public void zipCodeheadingVerification() {
  		
    	zipCodeHeading = getText(locationZipCodeHeading);
		
		
	}
	
   public void ZipCodeTitleVerification() {
    	
    	String title = selenium.getTitle();
    	zipCodeTitle= title;
    	logger.info("County title is "+zipCodeTitle);
    }
	
   public void clickOnAddNewCountyType() {
		
		implicitWait(5);
		click(addNewzipCodeTypeBtn);
		
   }
   
   public boolean verifyZipCodeData() {
	   
		try {
				  
  			 implicitWait(5);
  				sendKeysWithEnter(zipCodeZipCodeValue, "41221");
  				 Thread.sleep(3000);
  				    
  				  String countyName= selenium.findElement(By.xpath("//tr[1]/td[3]")).getText();
  				  System.out.println("state code found in city page is :"+countyName);
  				  
  				  return true;
  				} catch (Exception e) {
  				
  				//e.printStackTrace();
  				return false;
  			}
   }
   
   public void deleteZipCodeData() {
  		try {
  			
  			implicitWait(5);
  			click(zipPagePageChkBox);
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
   
}

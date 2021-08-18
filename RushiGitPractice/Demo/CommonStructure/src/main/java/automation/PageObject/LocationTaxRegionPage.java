package main.java.automation.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocationTaxRegionPage  extends CommonUtilityPage{
	
	public static String taxRgionHeading;
	public static String TaxRegionTitle;

	public LocationTaxRegionPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	private static final By locationTaxRegionHeading = By.xpath("//h1[contains(text(),'Tax Region')]");
	
	//Locators tro delete preiviously added data
	private static final By taxRegionPageTaxRegionName= By.xpath("//thead/tr[2]/td[5]/input[1]");
	private static final By cityPageConfirmDeleteAlert = By.xpath("//button[@id='confirmDeleteAllBtn']");
	private static final By cityPageChkBox = By.xpath("//tr[1]/td[1]");
	private static final By cityPagedeleteAllbtn = By.xpath("//button[@id='deleteAll']");
	private static final By addNewTaxRegionBtn = By.cssSelector("body > div.wrapper > div > section.content > form > div.row.row-fluid.gridactions > div.span3.col-md-3.text-left > a:nth-child(1)");
	
	
	
	
	
	 public void headingVerification() {
	  		implicitWait(5);
			taxRgionHeading = getText(locationTaxRegionHeading);
			
			
		}
	
	
	 public void taxRegionTitleVerification() {
	    	
	    	String title = selenium.getTitle();
	    	TaxRegionTitle= title;
	    	logger.info("-------------------County title is "+TaxRegionTitle);
	    }

	 
	 public boolean verifyCountyData() {
		  
	   		try {
	   				  
	   			 implicitWait(5);
	   				sendKeysWithEnter(taxRegionPageTaxRegionName, "abhangwadi");
	   				 Thread.sleep(3000);
	   				    
	   				  String countyName= selenium.findElement(By.xpath("//tr[1]/td[5]")).getText();
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
	  public void clickOnAddNewTaxRegionBtn(){
		 
		  implicitWait(5);
		  click(addNewTaxRegionBtn);
	 }
}

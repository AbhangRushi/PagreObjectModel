package main.java.automation.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocationZipCodeCreatePage extends CommonUtilityPage{

	public LocationZipCodeCreatePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	private static final By verifyCountySubmitAndReturntoListBtn = By.xpath("//*[@id='btnSubmitList']");
	private static final By verifyCountySubmitBtn =By.xpath("//*[@id='btnSubmit']");
	
	private static final By verifyZipCodeCancelAndReturnToListBtn = By.xpath("//*[@id='btnCancelList']");
	private static final By verifyZipCodeCancelBtn = By.xpath("//*[@id='btnCancel']");
	
	// adding information in AddZipCode page Locators
	
	private static final By countyCreatepageCountryCode = By.xpath("//*[@id='CountryCode']");
	private static final By countyCreatePageState = By.xpath("//select[@id='StateCode']");
	private static final By zipCodeCity = By.xpath("//select[@id='CityId']");
	private static final By zipCodeZipCode = By.xpath("//input[@id='Zipcode']");
	
	private static final By zipCodeSubmitBtn = By.xpath("//input[@id='btnSubmit']");
	
   private static final By zipCodeZipCodeValue = By.xpath("//thead/tr[2]/td[3]/input[1]");
	
	private static final By zipPagePageChkBox = By.xpath("//tbody//td[1]/div");
	private static final By cityPagedeleteAllbtn = By.xpath("//button[@id='deleteAll']");
	private static final By cityPageConfirmDeleteAlert = By.xpath("//button[@id='confirmDeleteAllBtn']");
	

	
   public boolean verifyZipCodeSubmitAndReturnToListBtnForDisable() {
		
		if(!selenium.findElement(verifyCountySubmitAndReturntoListBtn).isEnabled()) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public boolean verifyZipCodesubmitBtnForDisable() {
		
		if(!selenium.findElement(verifyCountySubmitBtn).isEnabled()) {
			return true;
		}else {
			return false;
		}
		
	}
	
   public boolean verifyZipCodeCancelAndReturnToLIstBtnForEnable() {
		
		if(!selenium.findElement(verifyZipCodeCancelAndReturnToListBtn).isEnabled()) {
			return true;
		}
		    return false;
		
	}
	
	public boolean verifyZipCodeCancelBtnForEnable() {
		
		if(!selenium.findElement(verifyZipCodeCancelBtn).isEnabled()) {
			return true;
		}
			return false;
	}
	
	
	// adding imformation in Add ZipCode form
	
	public void addInfoInNewZipCode() {
		try {
			
			implicitWait(5);
			selectValueFromDropdown(countyCreatepageCountryCode, "India - IN");
			Thread.sleep(3000);
			selectValueFromDropdown(countyCreatePageState, "Maharashtra - MH");
			Thread.sleep(2000);
			selectValueFromDropdown(zipCodeCity, "Jalgaun");
			Thread.sleep(2000);
			sendKeys(zipCodeZipCode, "41221");
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void clickOnCountySubmitBtn() {
		implicitWait(5);
		click(zipCodeSubmitBtn);
	}
   public void clickONverifyCountySubmitAndReturntoListBtn() {
	   implicitWait(5);
	   click(verifyCountySubmitAndReturntoListBtn);
   }
   public void clickOnverifyZipCodeCancelAndReturnToListBtn() {
	   
	   implicitWait(5);
	   click(verifyZipCodeCancelAndReturnToListBtn);
   }
	
	public void clickONverifyZipCodeCancelBtn() {
		implicitWait(5);
		click(verifyZipCodeCancelBtn);
	}
	
  public boolean verifyCountyForClearedForm() {
		
		WebElement element = selenium.findElement(countyCreatepageCountryCode);
		String elementtxt = element.getAttribute("value");
		if (elementtxt.isEmpty()) {
			element.findElement(countyCreatePageState);
			elementtxt=element.getAttribute("value");
			if (elementtxt.isEmpty()) {
				return true;
			}
		}
		
		return false;
  }
  
/*  public boolean verifyZipCodeData() {
	   
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
		}*/
    
	

    
}

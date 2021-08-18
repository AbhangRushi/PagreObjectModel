package main.java.automation.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LocationCountryCreatePage extends CommonUtilityPage{

	public LocationCountryCreatePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	private static final By verifyCountrySubmitAndReturntoListBtn = By.xpath("//*[@id='btnSubmitList']");
	private static final By verifyCountrySubmitBtn =By.xpath("//*[@id='btnSubmit']");
	private static final By verifyCountryCancelAndReturnToListBtn = By.xpath("//*[@id='btnCancelList']");
	private static final By verifyCountryCancelBtn = By.xpath("//*[@id='btnCancel']");
	private static final By countryFormCountryName = By.xpath("//input[@id='CountryName']");
	private static final By countryPageCountryCode = By.xpath("//input[@id='CountryCode']");
	private static final By countrySubmitBtn = By.xpath("//input[@id='btnSubmit']");

	public boolean verifyCountrySubmitAndReturnToListBtnForDisable() {
		
		if(!selenium.findElement(verifyCountrySubmitAndReturntoListBtn).isEnabled()) {
			return true;
		}else {
			return false;
		}
		
	}
	public boolean verifyCountrysubmitBtnForDisable() {
	
	if(!selenium.findElement(verifyCountrySubmitBtn).isEnabled()) {
		return true;
	}else {
		return false;
	}
	
	
    }
	
	public boolean verifyCountryCancelAndReturnToLIstBtnForEnable() {
		
		if(!selenium.findElement(verifyCountryCancelAndReturnToListBtn).isEnabled()) {
			return true;
		}
		    return false;
	}
	
	public boolean verifyCountryCancelBtnForEnable() {
		
		if(!selenium.findElement(verifyCountryCancelBtn).isEnabled()) {
			return true;
		}
			return false;
	}
	public void addInfoInNewCountry() {
		try {
			implicitWait(5);
			sendKeys(countryFormCountryName, "africa");
			implicitWait(5);
			sendKeys(countryPageCountryCode, "AF");
			implicitWait(5);
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}	
	
	public void clickOnCountrySubmitBtn() {
		implicitWait(5);
		click(countrySubmitBtn);
	}
	
	public boolean verifyCountyForClearedForm() {
		
		WebElement element = selenium.findElement(countryFormCountryName);
		String elementtxt = element.getAttribute("value");
		if (elementtxt.isEmpty()) {
			element.findElement(countryPageCountryCode);
			elementtxt=element.getAttribute("value");
			if (elementtxt.isEmpty()) {
				return true;
			}
		}
		
		return false;
	}
	public void clickOnverifyCountrySubmitAndReturntoListBtn() {
		implicitWait(5);
		click(verifyCountrySubmitAndReturntoListBtn);
	}
	
	public void clickOnverifyCountryCancelBtn() {
		
		implicitWait(5);
		click(verifyCountryCancelBtn);
	}
	public void clickOnverifyCountryCancelAndReturnToListBtn() {
		
		implicitWait(5);
		click(verifyCountryCancelAndReturnToListBtn);
	}
}

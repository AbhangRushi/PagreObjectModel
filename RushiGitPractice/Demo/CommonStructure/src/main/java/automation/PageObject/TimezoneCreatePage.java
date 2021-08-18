package main.java.automation.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TimezoneCreatePage extends CommonUtilityPage {

	public TimezoneCreatePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private static final By verifyCountySubmitAndReturntoListBtn = By.xpath("//*[@id='btnSubmitList']");
	private static final By verifyCountySubmitBtn =By.xpath("//*[@id='btnSubmit']");
	
	private static final By verifyCountyCancelAndReturnToListBtn = By.xpath("//*[@id='btnCancelList']");
	private static final By verifyCountyCancelBtn = By.xpath("//*[@id='btnCancel']");
	
	//locators for filling imformation in add new timezone
	
	private static final By timezonePageTimezone = By.xpath("//*[@id=\'TimezoneForm\']/div/div/div[1]/input[1]");
	private static final By timezonepageTimezoneLable = By.xpath("//*[@id='TimezoneForm']/div/div/div[2]/input[1]");
	private static final By timezoneCountrycode = By.xpath("//*[@id='TimezoneForm']/div/div/div[3]/input[1]");
	private static final By timezoneCoordinates = By.xpath("//*[@id='TimezoneForm']/div/div/div[4]/input[1]");
	private static final By timezoneComments = By.xpath("//*[@id='TimezoneForm']/div/div/div[5]/input[1]");
	private static final By timezoneUTCOffset = By.xpath("//*[@id='TimezoneForm']/div/div/div[6]/input[1]");
	private static final By timezoneUTCDSTOffset = By.xpath("//*[@id='TimezoneForm']/div/div/div[7]/input[1]");
	private static final By timezoneNotes = By.xpath("//*[@id='TimezoneForm']/div/div/div[8]/input[1]");
	
	
	
	
	
	
	public boolean verifyTimezoneSubmitAndReturnToListBtnForDisable() {
		
		if(!selenium.findElement(verifyCountySubmitAndReturntoListBtn).isEnabled()) {
			return true;
		}else {
			return false;
		}
	}
	public boolean verifyTimezonesubmitBtnForDisable() {
		
		if(!selenium.findElement(verifyCountySubmitBtn).isEnabled()) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean verifyTimezoneCancelAndReturnToLIstBtnForEnable() {
		
		if(!selenium.findElement(verifyCountyCancelAndReturnToListBtn).isEnabled()) {
			return true;
		}
		    return false;
		
	}
	
	public boolean verifyTimezoneCancelBtnForEnable() {
		
		if(!selenium.findElement(verifyCountyCancelBtn).isEnabled()) {
			return true;
		}
			return false;
	}
	
	public void addInfoInNewTimezone() {
		
		try {
			implicitWait(5);
			sendKeys(timezonePageTimezone, "demotimezone");
			implicitWait(5);
			sendKeys(timezonepageTimezoneLable, "demotimezonelable");
			implicitWait(5);
			sendKeys(timezoneCountrycode, "DT");
			implicitWait(5);
			sendKeys(timezoneCoordinates, "12.12");
			implicitWait(5);
			sendKeys(timezoneComments, "ItsTimezoneComment");
			implicitWait(5);
			sendKeys(timezoneUTCOffset, "UTC OffsetDemo");
			implicitWait(5);
			sendKeys(timezoneUTCDSTOffset, "UTC DST Offset demo ");
			implicitWait(5);
			sendKeys(timezoneNotes, "Timezone Notes");
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void clickOTimezonesubmitBtn() {
		implicitWait(5);
		click(verifyCountySubmitBtn);
	}
	
	public boolean verifyTimezoneForClearedForm() {
		WebElement element = selenium.findElement(timezonePageTimezone);
		String elementtxt = element.getAttribute("value");
		if (elementtxt.isEmpty()) {
			element.findElement(timezonepageTimezoneLable);
			elementtxt=element.getAttribute("value");
			if (elementtxt.isEmpty()) {
				return true;
			}
		}
		return false;
	}
	
	 public void clickOnTimezoneSubmitAndReturnToListBtn() {
		implicitWait(5);
		click(verifyCountySubmitAndReturntoListBtn);
		
	 }
	 public void clickOnTimeZoneCancelBtn() {
		implicitWait(5);
		click(verifyCountyCancelBtn);
	 }
	 public void clickOnTimezoneCancelAndReturnToListBtn() {
		
		 implicitWait(5);
		 click(verifyCountyCancelAndReturnToListBtn);
	 }
}

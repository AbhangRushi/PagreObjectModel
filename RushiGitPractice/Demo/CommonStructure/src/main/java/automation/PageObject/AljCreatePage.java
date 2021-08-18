package main.java.automation.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AljCreatePage extends CommonUtilityPage {

	public AljCreatePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	private static final By verifyAljSubmitAndReturntoListBtn = By.xpath("//*[@id='btnSubmitList']");
	private static final By verifyAljSubmitBtn =By.xpath("//*[@id='btnSubmit']");
	
	private static final By verifyAljCancelAndReturnToListBtn = By.xpath("//*[@id='btnCancelList']");
	private static final By verifyAljCancelBtn = By.xpath("//*[@id='btnCancel']");

	// ocators for add info in Add New Alj form
	
	private static final By aljfirstName = By.xpath("//input[@id='FirstName']");
	private static final By aljLatsName  = By.xpath("//input[@id='LastName']");
	private static final By aljOffice = By.xpath("//input[@id='Office']");
	private static final By aljDataRange = By.xpath("//input[@id='DataRange']");
	private static final By aljTotalDisposition1 = By.xpath("//input[@id='TotalDispositions1']");
	private static final By aljTotalDisposition2 = By.xpath("//input[@id='TotalDispositions2']");
	private static final By aljDecisions = By.xpath("//input[@id='Decisions']");
	private static final By aljAwards = By.xpath("//input[@id='Awards']");
	private static final By aljAwaredPercentage = By.xpath("//input[@id='AwardPercentage']");
	private static final By aljDenials = By.xpath("//input[@id='Denials']");
	private static final By aljDenialsPercentage = By.xpath("//input[@id='DenialPercentage']");
	private static final By aljFullyFavorable = By.xpath("//input[@id='FullyFavorable']");
	private static final By aljpartiallyFavorable = By.xpath("//input[@id='PartiallyFavorable']");
	private static final By aljNotes = By.xpath("//input[@id='Notes']");
	
	
	
	
	public boolean verifyAljSubmitAndReturnToListBtnForDisable() {
		
		if(!selenium.findElement(verifyAljSubmitAndReturntoListBtn).isEnabled()) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public boolean verifyAljsubmitBtnForDisable() {
		
		if(!selenium.findElement(verifyAljSubmitBtn).isEnabled()) {
			return true;
		}else {
			return false;
		}
	}
	public boolean verifyAljCancelAndReturnToLIstBtnForEnable() {
		
		if(!selenium.findElement(verifyAljCancelAndReturnToListBtn).isEnabled()) {
			return true;
		}
		    return false;
		
	}
	
	public boolean verifyAljCancelBtnForEnable() {
		
		if(!selenium.findElement(verifyAljCancelBtn).isEnabled()) {
			return true;
		}
			return false;
	}
	public void addInfoInNewAlj() {
		
		implicitWait(5);
		sendKeys(aljfirstName, "TestFirstName");
		implicitWait(5);
		sendKeys(aljLatsName, "TestLastName");
		implicitWait(5);
		sendKeys(aljOffice, "TestOffice");
		implicitWait(5);
		sendKeys(aljDataRange, "TestDataRange");
		implicitWait(5);
		sendKeys(aljTotalDisposition1, "12345");
		implicitWait(5);
		sendKeys(aljTotalDisposition2, "3265");
		implicitWait(5);
		sendKeys(aljDecisions, "213");
		implicitWait(5);
		sendKeys(aljAwards, "23");
		implicitWait(5);
		sendKeys(aljAwaredPercentage, "56");
		implicitWait(5);
		sendKeys(aljDenials, "12");
		implicitWait(5);
		sendKeys(aljDenialsPercentage, "50");
		implicitWait(5);
		sendKeys(aljFullyFavorable, "15");
		implicitWait(5);
		sendKeys(aljpartiallyFavorable, "11");
		implicitWait(5);
		sendKeys(aljNotes, "TestNotes");
	}
	
	// ################################## SCENARIO 07 METHODS  ############################################
	
	public void clickOnAljSubmitBtn() {
			implicitWait(5);
			click(verifyAljSubmitBtn);
		}
		
	public boolean verifyAljForClearedForm() {
			
		WebElement element = selenium.findElement(aljfirstName);
			String elementtxt = element.getAttribute("value");
		if (elementtxt.isEmpty()) {
			element.findElement(aljLatsName);
			elementtxt=element.getAttribute("value");
			if (elementtxt.isEmpty()) {
				return true;
			}
		}
		return false;
	}
	 public void clickOnAljSubmitAndReturnToList() {
	    	implicitWait(5);
	    	click(verifyAljSubmitAndReturntoListBtn);
	    }
	 
	 public void clickOnAljCancelBtn() {
		 
		 implicitWait(5);
		 click(verifyAljCancelBtn);
	 }
	 
 public void clickOnAljCancelAndReturnToListBtn() {
		 
		 implicitWait(5);
		 click(verifyAljCancelAndReturnToListBtn);
	 }
}

package main.java.automation.PageObject;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.print.DocFlavor.BYTE_ARRAY;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class ODAROfficeListCreatePage extends CommonUtilityPage {
	
	public static String BtnResult;
	public static String BtnResult2;
	public static String BtnResult3;
	public static String BtnResult4;
	public  String Success;
	public ODAROfficeListCreatePage(WebDriver driver) {
		
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	private static final By submitandreturnlistBtn= By.xpath("//*[@id='btnSubmitList']");
	private static final By submitBtn = By.xpath("//*[@id='btnSubmit']");
	
	private static final By addNewOdrOfficeTypeBtn = By.cssSelector("body > div.wrapper > div > section.content > form > div.row.row-fluid.gridactions > div.span3.col-md-3.text-left > a:nth-child(1)");
	
	//Locators of enable buttons for TC004
	
	private static final By cancelandreturntolistBtn = By.id("btnCancelList") ;
	private static final By cancelBtn = By.id("btnCancel");
	
	// Locators for TC005
	
	private static final By sitecode = By.xpath("//*[@id='SiteCode']");
	private static final By DLname = By.xpath("//*[@id='DLName']");
	private static final By odarDescription = By.xpath("//*[@id='ODARDescription']");
	private static final By odarType = By.xpath("//*[@id='Type']");		
	// Locators for TC005 and TC006
	
	private static final By waitTime = By.xpath("//*[@id='WaitTime']");
	private static final By odarOfficeName = By.xpath("//*[@id='ODAROffice']");
	private static final By street1 = By.xpath("//*[@id=\'Street1\']");
	private static final By street2 = By.xpath("//*[@id='Street2']");
	private static final By region = By.xpath("//*[@id='Region']");
	private static final By stateSelect = By.xpath("//span[@id='select2-StateCode-container']");
	private static final By citySelect = By.xpath("//*[@id='select2-City-container']");
	private static final By zipCode = By.xpath("//*[@id='select2-Zipcode-container']");
	private static final By odarOfficezipCode = By.xpath("//*[@id='Zipcode2']");
	private static final By phoneNumber = By.xpath("//*[@id='PhoneNumber']");
	private static final By faxNumber = By.xpath("//*[@id=\'FaxNumber\']");
	private static final By ereFax2 = By.xpath("//*[@id='EREFax']");
	private static final By notes = By.xpath("//*[@id='Notes']");
	private static final By id2 = By.xpath("//*[@id='ID2']");
	private static final By sequenceId = By.xpath("//*[@id='SequenceId']");
	private static final By state = By.xpath("//*[@id='select2-StateCode-result-ijz1-16']");
	private static final By listPagesequenceId = By.xpath("//thead/tr[2]/td[3]/input[1]");
	private static final By createpagecancelbtn = By.xpath("//*[@id='btnCancel']");
	
	public void clickOnaddNewOdrOfficeTypeBtn() {
		try {
			
		click(addNewOdrOfficeTypeBtn);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void verifybuttonsForDisable()
	{
		 
		if(!(selenium.findElement(submitandreturnlistBtn).isEnabled()))
		{
			BtnResult = "disable";
		}
       if (!(selenium.findElement(submitBtn)).isEnabled()) 
        {
		    BtnResult2="disable";
	    }
       
   }
	
	public void verifyButtonsForEnable() 
	{
		if(selenium.findElement(cancelandreturntolistBtn).isEnabled()) {
			BtnResult3="enable";
		}
		if (selenium.findElement(cancelBtn).isEnabled()) {
			BtnResult4="enable";
		}       
	}
	
	public void addDataNewOdrOfficeList() throws InterruptedException {
		
		sendKeys(sitecode, "123");
		sendKeys(DLname, "Rushikesh Abhang");
		sendKeys(waitTime, "20 minutes");
		sendKeys(odarDescription, "Its ODAR Description");
		sendKeys(odarType, "Its Type");
		sendKeys(odarOfficeName, "Its ODAR Office Name");
		sendKeys(street1,"its Street1 value");
		sendKeys(street2, "Its street2 Value");
		sendKeys(region, "its region");
		//click(stateSelect);
		/*
		 * WebElement element = selenium.findElement(state); moveToElement(element);
		 * element.click();
		 */
		/*
		 * implicitWait(5); WebElement element = selenium.findElement(stateSelect);
		 * Actions action = new Actions(selenium);
		 * action.moveToElement(element).click();
		 * 
		 * implicitWait(10); WebElement stateelement = selenium.findElement(state);
		 * action.moveToElement(stateelement).click(); action.build(); action.perform();
		 */
		
		/*
		 * Select select = new Select(selenium.findElement(By.xpath(
		 * "//body/div[1]/div[1]/section[2]/div[1]/form[1]/div[1]/div[1]/div[11]/span[1]/span[1]/span[1]"
		 * ))); select.selectByValue("Arkansas");
		 */
		click(stateSelect);
		//Actions action = new Actions(selenium);
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
		Thread.sleep(3000);
	    implicitWait(5);
		click(citySelect);
		Thread.sleep(4000);
		implicitWait(10);
		try {
			
			Robot robot = new Robot();
			Thread.sleep(2000);	
			robot.keyPress(KeyEvent.VK_DOWN);
			Thread.sleep(2000);	
			robot.keyPress(KeyEvent.VK_DOWN);
			Thread.sleep(2000);	
			robot.keyPress(KeyEvent.VK_DOWN);
			Thread.sleep(2000);	
			robot.keyPress(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Thread.sleep(2000);
		
		click(zipCode);
		Thread.sleep(1000);
		implicitWait(10);
		try {
			
			Robot robot = new Robot();
			Thread.sleep(2000);	
			robot.keyPress(KeyEvent.VK_DOWN);
			Thread.sleep(2000);	
			robot.keyPress(KeyEvent.VK_DOWN);
			Thread.sleep(2000);	
			robot.keyPress(KeyEvent.VK_DOWN);
			Thread.sleep(2000);	
			robot.keyPress(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Thread.sleep(2000);
		
		sendKeys(odarOfficezipCode, "4124");
		sendKeys(phoneNumber, "1234567895");
		sendKeys(faxNumber, "525898565458545");
		sendKeys(ereFax2, "ereFAX2 156458 ");
		sendKeys(notes, "Its notes ");
		sendKeys(id2, "rushikesh@123456");
		sendKeys(sequenceId, "123456");
		
		}
	public void clickOnSubmit() {
		
		
		implicitWait(5);
		click(submitBtn);
	}
	public void clickOnSubmitANdReturn() {
		
		implicitWait(5);
		click(submitandreturnlistBtn);
	}
	
	
	 /* public void fieldsStatus() {
	  
	  String s=getText(sitecode);
	  String V= getAttribute(sitecode, "value");
	  
	  System.out.println("value of attribut is "+V);
	  if(s.equals(null)) {
		  System.out.println("sitecode value is cleared");
	  }
	  }
	  */
	
	  public boolean verifyData() {
		  try {
			  
			  sendKeysWithEnter(listPagesequenceId, "123");
			  
			  Thread.sleep(3000);
			  String sitecode= selenium.findElement(By.xpath("//*[@class=\"table table-striped table-bordered\"]/tbody/tr/td[4]")).getText();
			  System.out.println("sitecode is : "+sitecode);
			  
			  String OdarDescription = selenium.findElement(By.xpath("//*[@class=\"table table-striped table-bordered\"]/tbody/tr/td[5]")).getText();
			  System.out.println("ODAR Description is "+OdarDescription);
			  
			  
			  String city = selenium.findElement(By.xpath("//*[@class=\"table table-striped table-bordered\"]/tbody/tr/td[6]")).getText();
			  System.out.println("city is "+city);
			  
			  String state = selenium.findElement(By.xpath("//*[@class=\"table table-striped table-bordered\"]/tbody/tr/td[7]")).getText();
			  System.out.println("city is "+state);
			  
			  return true;
			 
			
		} catch (Exception e) {
			
			//e.printStackTrace();
			return false;
		}
		
		 
	  }
	  
	  public void clickOnCancelBtn() {
		  
		  implicitWait(5);
		  click(createpagecancelbtn);
		  
		  }
	  
	  public void clickOnCancelAndeturnToListBtn() {
		  implicitWait(5);
		  click(cancelandreturntolistBtn);
	  }
	 /* public boolean clearedDataVerification() {
		  try {
			
			  
			  
			  String sitecodetxt = getText(sitecode);
			 
			  String sitecodevrification="**"+sitecodetxt;
			  System.out.println("site code text is ---------:"+"**"+sitecodevrification);
			  
			  String dlNametxt = getText(DLname);
			 
			  String dlNameVrification = "**"+dlNametxt;
			  System.out.println("site code text is ---------:"+"**"+dlNameVrification);
			  
			  
			  String waitTimetxt = getText(waitTime);
			 
			  String waitTimeverification="**"+waitTimetxt;
			  System.out.println("site code text is ---------:"+"**"+waitTimeverification);
			  
			  String odarDescriptiontxt = getText(odarDescription);
			  
			  String odarDescriptionverification = "**"+odarDescriptiontxt;
			  System.out.println("site code text is ---------:"+"**"+odarDescriptionverification);
			  
			  if(sitecodevrification=="**" || dlNameVrification == "**" || waitTimeverification=="**"|| odarDescriptionverification=="**") {
				  return true;
			  }
			  
			  
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		  return false;
		  */
		  
	  public void clearedDataVerification() {
		  
		  WebElement inputBox = selenium.findElement(sitecode);
		  String textInsideInputBox = inputBox.getAttribute("value");

		  // Check whether input field is blank
		  if(textInsideInputBox.isEmpty())
		  {
		     System.out.println("Input field is empty");
		  }
		  else {
			System.out.println("value in input field is not empty");
		}
		  
		 
	  }
	  
	 
	  
	  
     
	 
	 
}

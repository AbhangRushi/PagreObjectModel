package main.java.automation.PageObject;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MenuCreatePage extends CommonUtilityPage {

	public MenuCreatePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	private static final By verifyMenuSubmitAndReturntoListBtn = By.xpath("//*//button[@id='savetemplate']");
	private static final By verifyMenuSubmitBtn =By.xpath("//input[@id='btnSaveMenu']");
	private static final By verifyManuCancelAndReturnToListBtn = By.xpath("//*[@id='btnCancelList']");
	private static final By verifyManuCancelBtn = By.xpath("//*[@id='btnCancel']");
	
	//Locators for ADD NEW MENU form
	
	private static final By menuManuNameLocator = By.xpath("//input[@name='name']");
	private static final By menuLableLocator = By.xpath("//input[@name='label']");
	private static final By menuPageTitleLocator = By.xpath("//input[@name='PageTitle']");
	
	private static final By menuPageSubTitle = By.xpath("//input[@name='PageSubTitle']");
	private static final By menuRouteLocator = By.xpath("//body/div[1]/div[1]/section[2]/div[1]/form[1]/div[1]/div[1]/div[5]/span[1]/span[1]/span[1]/span[2]");
	private static final By menuParentmenu = By.xpath("//span[@id='select2-parent_menu_id-container']");
	private static final By menuExrternalLink = By.xpath("//input[@name='uri']");
	private static final By menusequence = By.xpath("//input[@name='order_number']");
	
	
	

	
	public boolean verifyMenuSubmitAndReturnToListBtnForDisable() throws InterruptedException {
		Thread.sleep(2000);
		if(!selenium.findElement(verifyMenuSubmitAndReturntoListBtn).isEnabled()) {
			return true;
		}else {
			return false;
		}
	}
	public boolean verifyMenusubmitBtnForDisable() {
		
		if(!selenium.findElement(verifyMenuSubmitBtn).isEnabled()) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean verifyMenuCancelAndReturnToLIstBtnForEnable() {
		
		if(!selenium.findElement(verifyManuCancelAndReturnToListBtn).isEnabled()) {
			return true;
		}
		    return false;
	}
	
	public boolean verifyMenuCancelBtnForEnable() {
		
		if(!selenium.findElement(verifyManuCancelBtn).isEnabled()) {
			return true;
		}
			return false;
	}
	
	public void addDataInAddmenuForm() {
		
		implicitWait(5);
		
		sendKeys(menuManuNameLocator, "Demo Menuname");
		implicitWait(5);
		sendKeys(menuLableLocator, "DemoLable");
		implicitWait(5);
		sendKeys(menuPageTitleLocator, "DemoPageTitle");
		implicitWait(5);
		sendKeys(menuPageSubTitle, "Demo Mewnu SubTitle");
		
		implicitWait(5);
		click(menuRouteLocator);
		
		try {
			
			Robot robot = new Robot();
			Thread.sleep(2000);	
			robot.keyPress(KeyEvent.VK_DOWN);
			Thread.sleep(2000);	
			robot.keyPress(KeyEvent.VK_DOWN);
			Thread.sleep(2000);	
			robot.keyPress(KeyEvent.VK_DOWN);
			Thread.sleep(2000);	
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyPress(KeyEvent.VK_ENTER);
			
		} catch (Exception e) {
			
			System.out.println("[[[[[[[[[[[[[[[[ - Error selecting value via Robot - ]]]]]]]]]]]]]]]");
		}
		
		implicitWait(5);
		click(menuParentmenu);
		
		try {
			
			Robot robot = new Robot();
			Thread.sleep(2000);	
			robot.keyPress(KeyEvent.VK_DOWN);
			Thread.sleep(2000);	
			robot.keyPress(KeyEvent.VK_DOWN);
			Thread.sleep(2000);	
			robot.keyPress(KeyEvent.VK_DOWN);
			Thread.sleep(2000);	
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyPress(KeyEvent.VK_ENTER);
			
		} catch (Exception e) {
			
			System.out.println("[[[[[[[[[[[[[[[[ - Error selecting value via Robot - ]]]]]]]]]]]]]]]");
		}
		
		/*
		 * implicitWait(5); sendKeys(menuExrternalLink, "demoExternal Link");
		 */
		
		implicitWait(5);
		sendKeys(menusequence, "12416");
		
	}
	public void clickOnSubmitBtn() {
		
		implicitWait(5);
		click(verifyMenuSubmitBtn);
	}
	
	public boolean verifyMenuForClearedForm() {
		
		WebElement element = selenium.findElement(menuManuNameLocator);
		String elementtxt = element.getAttribute("value");
		if (elementtxt.isEmpty()) {
			element.findElement(menuLableLocator);
			elementtxt=element.getAttribute("value");
			if (elementtxt.isEmpty()) {
				return true;
			}
		}
		return false;
	}
	
	 public void clickOnMenuSubmitAndReturnToList() {
	    	implicitWait(5);
	    	click(verifyMenuSubmitAndReturntoListBtn);
	}
	 
}

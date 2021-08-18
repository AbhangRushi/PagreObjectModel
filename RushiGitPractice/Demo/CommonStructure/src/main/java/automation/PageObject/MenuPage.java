package main.java.automation.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MenuPage extends CommonUtilityPage{
	public static String menuHeading;
	public static String menuTitle;

	public MenuPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	private static final By menuHeadingLoctor = By.xpath("//h1[contains(text(),'Menu List')] ");
	private static final By addNewMenuBtn = By.cssSelector("body > div.wrapper > div > section.content > form > div.row.row-fluid.gridactions > div.span3.col-md-3.text-left > a:nth-child(1)");
	
	

	 public void headingVerification() {
	  		
		 menuHeading = getText(menuHeadingLoctor);
	 }
	 
	 public void menuTitleVerification() {
	    	
	    	String title = selenium.getTitle();
	    	menuTitle= title;
	    	logger.info("County title is "+menuTitle);
	 }
	 
	 public void clickOnAddNewCountyType() {
			
			implicitWait(5);
			click(addNewMenuBtn);
 		}
}

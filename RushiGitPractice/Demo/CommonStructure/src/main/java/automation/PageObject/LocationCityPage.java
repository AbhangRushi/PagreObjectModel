package main.java.automation.PageObject;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LocationCityPage extends CommonUtilityPage {
	
	public static String cityHeading;
	public static String cityPageTitle;
	
	
	public LocationCityPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	private static final By locationCityHeading = By.xpath("//h1[contains(text(),'City')]");
	private static final By addNewCityTypebtn=By.cssSelector("body > div.wrapper > div > section.content > form > div.row.row-fluid.gridactions > div.span3.col-md-3.text-left > a:nth-child(1)");
	private static final By cityPageSubmitBtn = By.xpath("//*[@id='btnSubmit']");
	
	// locators for delete city method
	
	
	
	
	
	
	public void cityTitleVerification() {
		
		 cityPageTitle = selenium.getTitle();
		 System.out.println("CITY PAGE TITLE IS :"+cityPageTitle);
		
	}
	
	
	
	public void headingVerification() {
		
		cityHeading = getText(locationCityHeading);
		
	}
	
	public void clickOnAddNewCityTypeBtn() {
		
		implicitWait(5);
		click(addNewCityTypebtn);
		
	}
	
	
	 
	 

}

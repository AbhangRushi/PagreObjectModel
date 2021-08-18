package main.java.automation.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AljPage extends CommonUtilityPage{
	
	public static String AljHeading;
	public static String aljTitle;

	public AljPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	private static final By AljCountyHeading = By.xpath("//h1[contains(text(),'Administrative law judges')]");
	private static final By addNewAljBtnLocator = By.cssSelector("body > div.wrapper > div > section.content > form > div.row.row-fluid.gridactions > div.span3.col-md-3.text-left > a:nth-child(1)");
	
	
	
	
	
	public void aljheadingVerification() {
	  		
			AljHeading = getText(AljCountyHeading);
		}
	public void aljTitleVerification() {
    	
    	String title = selenium.getTitle();
    	aljTitle= title;
    	logger.info("County title is "+aljTitle);
    }
	public void clicKOnAddNewAljBtn() {
		implicitWait(5);
		click(addNewAljBtnLocator);
	}
}

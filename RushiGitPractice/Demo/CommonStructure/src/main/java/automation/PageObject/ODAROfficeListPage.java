package main.java.automation.PageObject;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import automation.coreClasses.ConstantPage;

public class ODAROfficeListPage extends CommonUtilityPage 
{
	public static String Heading;
	public static String PageTitle;

	public ODAROfficeListPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	private static final By administrative = By.cssSelector("body > div > aside > section > ul > li:nth-child(4) > a > span");
	private static final By ssaDdsOdarOffices = By.cssSelector("body > div > aside > section > ul > li.treeview.active > ul > li:nth-child(3) > a > span");
	private static final By odarOfficeList = By.cssSelector("body > div > aside > section > ul > li.treeview.active > ul > li.treeview.active > ul > li:nth-child(5) > a > span");
	private static final By odarOfficeListHeading = By.xpath("//h1[contains(text(),'ODAROfficeList')]");
	private static final By dataTablechkbox= By.cssSelector("td:nth-child(1) > div > ins"); 
	private static final By delectselected = By.xpath("//*[@id=\'deleteAll\']");
	private static final By confirmDeleteallBtn = By.id("confirmDeleteAllBtn");
	
	// Locators for Locations 
	
	private static final By locationbtn= By.cssSelector("body > div > aside > section > ul > li.treeview.active > ul > li:nth-child(4) > a > span");
	private static final By citybtn=By.cssSelector("body > div > aside > section > ul > li.treeview.active > ul > li.treeview.active > ul > li:nth-child(1) > a > span");
	private static final By countyBtn = By.cssSelector("body > div > aside > section > ul > li.treeview.active > ul > li.treeview.active > ul > li:nth-child(2) > a > span");
	private static final By StateBtn = By.cssSelector("body > div > aside > section > ul > li.treeview.active > ul > li.treeview.active > ul > li:nth-child(3) > a > span"); 
	private static final By zipCode = By.cssSelector("body > div > aside > section > ul > li.treeview.active > ul > li.treeview.active > ul > li:nth-child(4) > a > span");
	private static final By countryBtn = By.cssSelector("body > div > aside > section > ul > li.treeview.active > ul > li.treeview.active > ul > li:nth-child(5) > a > span");
	private static final By TaxRegionBtn = By.cssSelector("body > div > aside > section > ul > li.treeview.active > ul > li.treeview.active > ul > li:nth-child(6) > a > span");
	private static final By timezoneBtn = By.cssSelector("body > div > aside > section > ul > li.treeview.active > ul > li:nth-child(5) > a > span");
	private static final By menuBtn = By.cssSelector("body > div > aside > section > ul > li.treeview.active > ul > li:nth-child(8) > a > span");
	private static final By AliBtnLocator = By.cssSelector("body > div > aside > section > ul > li.treeview.active > ul > li:nth-child(10) > a > span");
	
	
	public void clickOnadministrativeBtn() throws IOException {
		try {
			Thread.sleep(5000);
			click(administrative);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void clickOnSsaDdsOdarOfficesBtn() {
		
		waitForParticularElement(ssaDdsOdarOffices, 30);
		click(ssaDdsOdarOffices);
		
		waitForParticularElementExist(odarOfficeList, 30);
		click(odarOfficeList);
		
	}
	
	
	public void headingVerification() {
		
		 Heading = getText(odarOfficeListHeading);
		
		
	
	}
	public void titlevarification() {
		
		String title = selenium.getTitle();
		PageTitle = title;
		System.out.println("-------------------------------------title of listing page is-----: "+PageTitle);
	}
	
	public void deleteData() {
		try {
			    
			    implicitWait(5);
				click(dataTablechkbox);
				implicitWait(5);
				click(delectselected);
				Thread.sleep(2000);
			    implicitWait(5);
			    click(confirmDeleteallBtn);
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}
	
	// methods of list page  for Locations
	
	public void clickOnLocationBtn() {
		
		implicitWait(5);
		click(locationbtn);
		
	}
	public void clickOnCity() {
		implicitWait(5);
		click(citybtn);
	}
	
	public void clickOnCounty() {
		
		implicitWait(5);
		click(countyBtn);
	}
	
	//############################################## METHOD OF SCENARIO 1 OF LOCATIONS #####################
	
	public void clickOnState() {
		
		implicitWait(5);
		click(StateBtn);
		
		
	}
	//############################################# METHOD OD LOCATION- ZIPCODE ############################
	
	public void clickOnZipCode() {
		
		implicitWait(5);
		click(zipCode);
	}
	//###########################################METODS OF LOCATION - COUNTRY ###############################
	
	
   public void clickOnCountry() {
		
		implicitWait(5);
		click(countryBtn);
	}
   
   //########################################## METHODS OF LOCATION - TAXREGION #############################
   
    public void clickOnTaxRegionBtn() {
		
		implicitWait(5);
		click(TaxRegionBtn);
	}
    
    //######################################## METHODS OF TIMEZONE ##########################################
    
    public void clickOnTimezone() {
    	
    	implicitWait(5);
    	click(timezoneBtn);
    }
    
    //######################################## METHODS OF MEUN ################################################
    public void clickOnMenu() {
    	implicitWait(5);
    	click(menuBtn);
    }
    
    public void clickonAliBtn() {
    	implicitWait(5);
    	click(AliBtnLocator);
    	
    }
}

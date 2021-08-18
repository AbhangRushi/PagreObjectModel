package main.java.automation.PageObject;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocationCityCreatePage extends CommonUtilityPage  {

	public LocationCityCreatePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	private static final By cityCountry = By.xpath("//*[@id='CountryCode']");
	private static final By cityState = By.xpath("//*[@id='StateCode']");
	private static final By cityCounty = By.xpath("//*[@id='CountyId']");
	private static final By cityCityName = By.xpath("//*[@id='CityName']");
	
	
	private static final By cityPageCityName = By.xpath("//thead/tr[2]/td[3]/input[1]");
	private static final By cityPageChkBox = By.xpath("//tr[1]/td[1]");
	private static final By cityPagedeleteAllbtn = By.xpath("//button[@id='deleteAll']");
	private static final By cityPageConfirmDeleteAlert = By.xpath("//button[@id='confirmDeleteAllBtn']");
	private static final By cityPageSubmitAndReturnToListBtn = By.xpath("//*[@id='btnSubmitList']");
	
	
	//private static final By cityIsPrototypeState = By.xpath("//*[@id='select2-IsPrototype-oa-container']");
	private static final By cityPageSubmitBtn = By.xpath("//*[@id='btnSubmit']");
	private static final By verifyCityCancelAndReturnToListBtn = By.xpath("//*[@id='btnCancelList']");
	private static final By verifyCityCancelBtn = By.xpath("//*[@id='btnCancel']");
	
	
	
	
     public void addDatainNewCity() {
		 
	 try {
		 selectValueFromDropdown(cityCountry, "India - IN");
		 Thread.sleep(3000);
		 selectValueFromDropdown(cityState, "Maharashtra - MH");
		 Thread.sleep(2000);
		 implicitWait(5);
		 selectValueFromDropdown(cityCounty, "Vidarbh");
		 Thread.sleep(1000);
		 sendKeys(cityCityName, "pune");
		 Thread.sleep(2000);
		// selectValueFromDropdown(cityIsPrototypeState, "Prototype State ");
		 
		 
		 Thread.sleep(3000);
		  }
	            catch (Exception e)
	             {
		          e.printStackTrace();
	             }
	 }
     public boolean verifyCleareddata() {
    	 
    	 implicitWait(10);
    	 WebElement element = selenium.findElement(cityCountry);
    	 String txtElement=element.getAttribute("value");
    	 
    	 if (txtElement.isEmpty())
    	 {
			
    		 WebElement element2 = selenium.findElement(cityState);
    		 String txtElement2 = element2.getAttribute("value");
    		 
    		 if (txtElement2.isEmpty())
    		 {
				WebElement element3 = selenium.findElement(cityCounty);
				String txtElement3 = element3.getAttribute("value");
				if (txtElement3.isEmpty()) 
				{
					return true;
				}
			 }
		}
    	 return false;
   }
     public  void clickOnSubmitBtn() {
		  
		  implicitWait(5);
		  click(cityPageSubmitBtn);
		  
	  }
     
     public boolean verifySubmitAndReturnToListBtnforDisable() {
   	  
   	  if(!(selenium.findElement(cityPageSubmitAndReturnToListBtn).isEnabled())) {
   	  return true;
   	  
   	  }else
   	  { 
   		  return false; 
   	  
   	  }
   	  
   	 }
   	  
   	  public boolean verifySubmitBtnforDisable() {
   	  
   	  if (!(selenium.findElement(cityPageSubmitBtn).isEnabled())) { 
   		  return true;
   	  }else
   	  {
   		  return false;
   	  } 
   	  
   	 }
   	 
   	/*
   	 * public boolean verifyCancelBtn() {
   	 * 
   	 * return verifybtnCancel(isEnable(true, null));
   	 * 
   	 * }
   	 */
   	
   	  
   	  //method for  deleting city details added previously
   	  public boolean verifyCityData() {
   		  
   		try {
   				  
   			 implicitWait(5);
   				sendKeysWithEnter(cityPageCityName, "pune");
   				 Thread.sleep(3000);
   				    
   				  String stateCode= selenium.findElement(By.xpath("//tr[1]/td[4]")).getText();
   				  System.out.println("state code found in city page is :"+stateCode);
   				  
   				  return true;
   				} catch (Exception e) {
   				
   				//e.printStackTrace();
   				return false;
   			}
      }
   	  public void deleteCity() {
   		try {
   			
   			implicitWait(5);
   			click(cityPageChkBox);
   			implicitWait(5);
   			click(cityPagedeleteAllbtn);
   			implicitWait(5);
   			
   			WebDriverWait wait=new WebDriverWait(selenium, 20);
   			
   			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(cityPageConfirmDeleteAlert));
   			element.click();
   			
   			} catch (Exception e) {
   			e.printStackTrace();
   		}
   		
   	}
   	  
   	  public void clickOnverifyCityCancelAndReturnToListBtn() {
		
		implicitWait(5);
		click(verifyCityCancelAndReturnToListBtn);
	}
   	  public void clickOnSubmitAndReturnToLIst() {
   		  
   		  implicitWait(5);
   		  click(cityPageSubmitAndReturnToListBtn);
   	  }
   	  
   	 public void clickOnCityCancelButton() {
   		 implicitWait(5);
   		 click(verifyCityCancelBtn);
   	 }
		 
}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


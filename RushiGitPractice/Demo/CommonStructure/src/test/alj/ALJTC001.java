package test.alj;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;
import org.testng.annotations.Test;


import automation.coreClasses.Assert;
import automation.coreClasses.BasePreCondition;
import automation.coreClasses.ConstantPage;
import main.java.automation.PageObject.AljCreatePage;
import main.java.automation.PageObject.AljPage;
import main.java.automation.PageObject.ODAROfficeListPage;

public class ALJTC001 extends BasePreCondition{
	
	
	@Test(priority = 1)
	void scenario01() throws Exception{
		
		testCaseDevelopedBy("Rushikesh Abhang", "Checking Location - County ");
		testcaseID("CountyTC001");
		
		scenarioHeading(1, "Checkign URL of page is as expected or not");
		
		Thread.sleep(10000);
		logout();
		Thread.sleep(10000);
		doLogin();
		
		Reporter.log("clicking on administrative button");
		ODAROfficeListPage ListPage= new ODAROfficeListPage(selenium);
		Reporter.log("Clicking on Administrative button");
		ListPage.clickOnadministrativeBtn();
		
		Reporter.log("clicking on ALJ Button");
		
        Thread.sleep(2000);
        ListPage.clickonAliBtn();
        
        
        Reporter.log("Detecting result page ");
		
		resultpage= resultpage.detectPage();
		//System.out.println("result page is "+resultpage);
		Thread.sleep(500);
		
		System.out.println("detetcted page is "+resultpage);
		
		expectedResult("Alj page should be open");
		if (!(resultpage instanceof AljPage )) {
			 
			 fail("Not detect Listing Page");
				Assert.assertTrue(false);
				System.out.println("Inside the scenario fail if block");
			 }
		pass("Alj Page URL verification successful  ");
		
	}
	
	@Test(priority = 2)
	void scenario02() throws Exception{
		
		scenarioHeading(2, "Checking Heading of page is as expected as per module name ");
		Reporter.log("checking the heading of page is as expected per module name");
		
		AljPage aljPage = (AljPage)resultpage;
		aljPage.aljheadingVerification();
		
		expectedResult("Listing Page Heading should show"+ConstantPage.ALJ_HEADING);
		if (!(aljPage.AljHeading).equals(ConstantPage.ALJ_HEADING)) {
			fail("Alj page heading verification fail");
			Assert.assertTrue(false);
			}
	    
		pass("Alj page heading verification successful ");
		Thread.sleep(500);
	}
	
	@Test(priority = 3)
	void scenario03() throws Exception{
		
		scenarioHeading(3, "Checking title of page is as expected as per module name ");
		Reporter.log("checking the title of page is as expected per module name");
		AljPage aljPage = (AljPage)resultpage;
		
		Reporter.log("verifying title of page Title should be :County - PCMS ");
		aljPage.aljTitleVerification();
		
		expectedResult("Listing page Title should be match");
		if(!(aljPage.aljTitle).equals(ConstantPage.ALJ_TITLE)) {
			
			fail("County tital verifaction Unsuccessful");
			Assert.assertTrue(false);
		}
		pass("County Page title verification successful");
	}
	
	@Test(priority = 4)
	void scenario04() throws Exception{
		
		scenarioHeading(4, "Checking Submit, Submit & return to list buttons should be disable state bydefault in add/edit page");
		Reporter.log("checking Submit, Submit & return to list buttons should be disable state bydefault in add/edit page");
		AljPage aljPage = (AljPage)resultpage;
		aljPage.clicKOnAddNewAljBtn();
		
		resultpage = resultpage.detectPage();
		
		System.out.println("detetcted page after clicking on Add new alj type button"+resultpage);
	    
	    Reporter.log("Verifying Submit and Return to list buttons is in  disable state  bydefault");
	    
	    AljCreatePage aljCreatePage = new AljCreatePage(selenium);
	   
	    expectedResult("Submit and return to list button should be disable by default");
	    
	    if (aljCreatePage.verifyAljSubmitAndReturnToListBtnForDisable()==false)
	    	 {
	 	        fail("submit and return to list detect disable as default");
		        Assert.assertTrue(false);
	         }
	    pass("Submit and retun to list button dected as disable by default");
	   
	    Reporter.log("Verifying submit button for disable by default");
	   
	    expectedResult("submit button should be in disable state By default ");
	    if (aljCreatePage.verifyAljsubmitBtnForDisable()==false) 
	    	{
	    		fail("submit button detect as enable state by default");
	    		Assert.assertTrue(false);
	    	}
	    pass("Submit button detect as enable state by default");
	  }
	
	@Test(priority = 5)
	void scenario05() throws Exception{
		
		scenarioHeading(5, "checking Cancel, Cancel & return to list buttons should be enable state bydefault in add/edit page");
		
		Reporter.log("Checkling Cancel, Cancel & return to list buttons are in enable state bydefault in add/edit page");
		
		AljCreatePage aljCreatePage = new AljCreatePage(selenium);
		 
		expectedResult("cancel and return to list button should be enable by default");
		
		if(!(aljCreatePage.verifyAljCancelAndReturnToLIstBtnForEnable()==false)) 
			{
			   fail("Cancel and return to list button detetc as disable state");
			   Assert.assertTrue(false);
			}
		pass("cancel and return to list button detetced as enable ");
		
		expectedResult("Cancel button should be in enable state by default");
		
		if (!(aljCreatePage.verifyAljCancelBtnForEnable())==false)
			{
			   fail("Cancel button detetc as disable state");
			   Assert.assertTrue(false);
			
			}
		pass("Cancel button detetced as enable by default");
	}
	
	@Test(priority = 6)
	void scenario06() throws Exception{
		
		scenarioHeading(6, "checking Submit, Submit & return to list buttons should get enable in add/edit page if user did changes in any field");
		
		Reporter.log("checking Submit, Submit and return to list buttons are in enable state after user did any changes in add/edit page");
		AljCreatePage aljCreatePage = new AljCreatePage(selenium);
		
		Reporter.log("Filling information in Add new Alj ");
		aljCreatePage.addInfoInNewAlj();
		
		Reporter.log("Checking SUBMIT & RETURN TO LIST  button are in enable state after user did changes in add/edit page");
		expectedResult("SUBMIT & RETURN TO LIST button should be enable after user did any changes in add/edit page");
		if (aljCreatePage.verifyAljSubmitAndReturnToListBtnForDisable()==true) {
			fail("submit button are in disable state after user did any changes in add/edit page");
			Assert.assertTrue(false);
		}
		pass("SUBMIT & RETURN TO LIST button detect as enable after user did any changes in add/edit page");
		
		Reporter.log("Checking SUBMIT button is in enable state after user did any changes in add/edit page");
		
		expectedResult("SUBMIT button should be enable after user did any changes in add/edit page");
		if (aljCreatePage.verifyAljsubmitBtnForDisable()==true) {
			fail("submit button detetc as disable after user did any changes in add/edit form");
			Assert.assertTrue(false);
		}
		pass("SUBMIT button detected as enable after did any changes in add/edit page of county page");
	}
	
	@Test(priority = 7)
	void scenario07() throws Exception{
		
		scenarioHeading(7, "Checking system is saving data, clear form and remain on same page after user click on submit button ");
		
		Reporter.log("Checking system is saving data, cleare form and remain on same page afrter clecking on submit button ");
		
		Thread.sleep(3000);
		Reporter.log("clicking on submit button");
		AljCreatePage aljCreatePage = new AljCreatePage(selenium);
		aljCreatePage.clickOnAljSubmitBtn();
		
		resultpage = resultpage.detectPage();
		
		if (!(resultpage instanceof AljCreatePage )) {
			fail("After clicking on submit button user redirected to diffrent page");
			Assert.assertTrue(false);
		}
		pass("after clicking on submit button user redirect to same page");
		
		expectedResult("data should be cleared");
		if (aljCreatePage.verifyAljForClearedForm()==false) {
			fail("add/edit page not cleared after clicking on submit button");
			Assert.assertTrue(false);
		}
		pass("add/edit page is cleared after clicking on submit button");
	}
	
	@Test(priority = 8)
	void scenario08() throws Exception{
		
		scenarioHeading(8, "checking System saving data and user should redirect to listing page after click on Submit & return to list button");
		
		Reporter.log("checking system is saving data and user is redirect to listing page after clicking on SUBMIT & RETURN TO LIST page");
		AljCreatePage aljCreatePage = new AljCreatePage(selenium);
		Thread.sleep(3000);
		Reporter.log("Filling information in Add new Alj ");
		aljCreatePage.addInfoInNewAlj();
		
		Thread.sleep(2000);
		Reporter.log("clicking on submit and return to list button ");
		aljCreatePage.clickOnAljSubmitAndReturnToList();
		Thread.sleep(3000);
		resultpage=resultpage.detectPage();
		expectedResult("user should redirected to listing page after clicking on submit and return button");
		if (!(resultpage instanceof AljPage)) {
			fail("detected page is wrong- user is rediirect to diffrent page");
			Assert.assertTrue(false);
		}
		pass("user redirect to listing page after clicking on SUBMIT & RETURN PAGE ");
	}
	
	@Test(priority = 9)
	void scenario09() throws Exception{
		
		scenarioHeading(9, "checking System should clear data without saving data and user should remain on same page after click on Cancel button");
		
		Reporter.log("checking System cleard data without saving data and user  remain on same page after click on Cancel button");
		
		AljPage aljPage = (AljPage)resultpage;
		aljPage.clicKOnAddNewAljBtn();
		
		AljCreatePage aljCreatePage = new AljCreatePage(selenium);
		Reporter.log("Adding data in add new ALJ form");
		aljCreatePage.addInfoInNewAlj();
		Thread.sleep(2000);
		Reporter.log("Clicking on cancel Button");
		aljCreatePage.clickOnAljCancelBtn();
		
		expectedResult("data should be cleared");
		if (aljCreatePage.verifyAljForClearedForm()==false) {
			fail("add/edit page not cleared after clicking on submit button");
			Assert.assertTrue(false);
		}
		pass("add/edit page is cleared after clicking on submit button");
		
		expectedResult("After clicking on cancel btn user remain on same page");
		resultpage = resultpage.detectPage();
		
		if (!(resultpage instanceof AljCreatePage )) {
			fail("After clicking on submit button user redirected to diffrent page");
			Assert.assertTrue(false);
		}
		pass("after clicking on submit button user redirect to same page");
	}
	
	@Test(priority = 10)
	void scenario10() throws Exception {
		
		scenarioHeading(10,"checking System should clear data without saving data and user should redirect to listing page after click on Cancel & return list button");
		
		Reporter.log("checking system should clear data without saving and user should redirect to listing page after clicking on cancel and return to list Button");
		AljCreatePage aljCreatePage = new AljCreatePage(selenium);
		aljCreatePage.addInfoInNewAlj();
		Thread.sleep(2000);
		aljCreatePage.clickOnAljCancelAndReturnToListBtn();
		
		Thread.sleep(5000);
		resultpage = resultpage.detectPage();
		expectedResult("after clicking on submit And Return To List button user redirect to Listing page");
		if (!(resultpage instanceof AljPage )) {
			fail("After clicking on submit button user remain on same page");
			Assert.assertTrue(false);
		}
		pass("after clicking on submit And Return To List button user redirect to Listing page");
	}
}

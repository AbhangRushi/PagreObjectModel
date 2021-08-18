package main.java.automation.PageObject;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import automation.coreClasses.ConstantPage;

public class CommonUtilityPage extends AdminLeftPanelPage
{
	private static final By SignIn=By.id("submitbutton");
	protected static final By btnCancel=By.id("btnCancel");
	protected static final By btnCancelAndReturnToAEList=By.id("btnCancelList");
	protected static final By btnSubmit=By.id("btnSubmit");
	protected static final By btnSubmitAndReturnToAEList=By.id("btnSubmitList");
	
	public CommonUtilityPage(WebDriver driver) {
		super(driver);
	}
	
	public void refreshPage() throws IOException
	{
		selenium.navigate().refresh();
		timeIntervel(1);
		waitUntilElementDisplays(loader);
		timeIntervel(1);
	}
	
	public void clickOnSignInBtn() throws IOException 
	{
		click(SignIn);
		timeIntervel(2);
	}
	
	public boolean verifyHeaderOfPage(String pageHeader)
	{
		return isDisplayed(By.xpath(".//h1[text()='"+pageHeader+"']"));
	}
	
	public void ClickOnSubModuleOfDocumentMailFax(String option) throws IOException
	{
		if(option.equals("Faxes"))
		{
			click(By.xpath("(.//div[@id='navbar-collapse']//a[contains(.,'"+option+"')])[2]"));
		}
		else if(option.equals("Mass Faxes"))
		{
			click(By.xpath("(.//div[@id='navbar-collapse']//a[contains(.,'"+option+"')])[1]"));
		}
		else
		{
			click(By.xpath(".//div[@id='navbar-collapse']//a[contains(.,'"+option+"')]"));
		}
		timeIntervel(1);
		waitUntilElementDisplays(loader);
		timeIntervel(1);	
	}
	
	public boolean verifyErrorOccured()
	{
		return isDisplayed(By.xpath(".//section[@class='content']/h1[text()='An error occurred']"));
	}
	
	public boolean verifyNoticeError()
	{
		return isDisplayed(By.xpath(".//*[text()='Notice']"));
	}
	
	public boolean verifyWarningError()
	{
		return isDisplayed(By.xpath(".//*[text()='Warning']"));
	}
	
	public void clickOnSearch() throws IOException
	{
		if(isDisplayed(By.xpath(".//*[@id='btnSearch']")))
			click(By.xpath(".//*[@id='btnSearch']"));
		else if(isDisplayed(By.xpath(".//*[@id='search']")))
			click(By.xpath(".//*[@id='search']"));
		else if(isDisplayed(By.xpath(".//*[@value='Search']")))
			click(By.xpath(".//*[@value='Search']"));
		else
			click(By.xpath(".//*[text()='Search']"));
		
		timeIntervel(1);
		waitUntilElementDisplays(loader);
		timeIntervel(1);
	}	
	
	private void clickOnfilterDate()
	{
		if(isDisplayed(By.xpath(".//div[@id='daterangeFilter']//strong[@class='caret']")))
		{
			click(By.xpath(".//div[@id='daterangeFilter']//strong[@class='caret']"));
			timeIntervel();
			waitForParticularElement(By.xpath(".//div[@class='pull-left active']"),10);
		}
		else
		{
			click(By.xpath(".//div[@id='daterange']//strong[@class='caret']"));
			timeIntervel();
			waitForParticularElement(By.xpath(".//div[@class='pull-right active']"),10);
		}
		timeIntervel();
	}
	
	public void cickOnAllTimeOptionFromDateFilter() throws IOException
	{
		clickOnfilterDate();
		click(By.xpath(".//div[@class='ranges']//li[text()='All Time']"));
		if(isDisplayed(By.xpath(".//*[@id='btnSearch']")))
			clickOnSearch();
		else 
			clickOnSubmitButton();
	}
	
	public String getPCMSId()
	{
		PCMSID=null;
		int totalPageCount=Integer.parseInt(getText(By.xpath(".//div[@data-role='pager']//span[@class='k-input']")));
		for(int i=1;i<=totalPageCount;)
		{
			int totalSize=getNumberOfListOfElements(By.xpath(".//div[contains(@class,'k-grid-content')]//tr"));
			for(int j=1;j<=totalSize;j++)
			{
				if(getText(By.xpath(".//div[contains(@class,'k-grid-content')]//tr["+j+"]//td[10]")).equals(ConstantPage.DESTINATION1)
						|| getText(By.xpath(".//div[contains(@class,'k-grid-content')]//tr["+j+"]//td[10]")).equals(ConstantPage.DESTINATION2))
				{
					System.out.println(PCMSID);
					PCMSID=getText(By.xpath(".//div[contains(@class,'k-grid-content')]//tr["+j+"]//td[1]"));
					return PCMSID;
				}
			}
			if(PCMSID==null)
			{
				i++;
				click(By.xpath(".//div[@data-role='pager']//ul/li/a[@data-page='"+i+"']"));
				timeIntervel(5);
			}
			else
			{
				logger.info("No any Client is imported");
				break;
			}
		}
		return PCMSID;
	}
	
	public void enterPCMSID(String id) throws IOException
	{
		sendKeys(By.xpath(".//span[@data-field='CaseId']//input"),id);
	}
	
	public String getClientId()
	{
		PCMSID=getText(By.xpath(".//div[contains(@class,'k-grid-content')]//tr[1]//td[1]"));
		return PCMSID;
	}
	
	
	public void clickOnSubmitButton() throws IOException
	{
		if(isDisplayed(By.xpath(".//*[text()='Submit']")))
			click(By.xpath(".//*[text()='Submit']"));
		else if(isDisplayed(By.xpath(".//*[@id='subBtn']")))
			click(By.xpath(".//*[@id='subBtn']"));
		
		timeIntervel(1);
		waitUntilElementDisplays(loader);
		timeIntervel(1);
	}
	
	public void clickOnRefresh() throws IOException
	{
		timeIntervel(1);
		waitUntilElementDisplays(loader);
		timeIntervel(1);
		click(By.xpath(".//a[@title='Refresh']"));
		timeIntervel(1);
		waitUntilElementDisplays(loader);
		timeIntervel(1);
	}
	
	public void clickOnOpenClientMainPageAction(String clientId)
	{
		click(By.xpath(".//div[@class='dataTables_wrapper no-footer']//td[text()='"+clientId+"']//..//a[@title='Open Client Main Page']"));
	}
	
	public void clickOnAdvanceSearch()
	{
		click(By.xpath(".//*[@title='Advance Search']"));
		timeIntervel(4);
	}
	
	public void clickOnCancel() throws IOException {
		if(isDisplayed(btnCancel))
		{
			ScrollToElement(btnCancel);
			click(btnCancel);
			timeIntervel(1);
			waitUntilElementDisplays(loader);
			timeIntervel(1);
		}
		else
		{
			ScrollToElement(By.linkText("Cancel"));
			click(By.linkText("Cancel"));
			timeIntervel(1);
			waitUntilElementDisplays(loader);
			timeIntervel(1);
		}
	}

	public void clickOnCancelAndReturnToAEList() throws IOException {
		ScrollToElement(btnCancelAndReturnToAEList);
		click(btnCancelAndReturnToAEList);
		timeIntervel(1);
		waitUntilElementDisplays(loader);
		timeIntervel(1);
	}

	public void clickOnSubmit() throws IOException {
		if(isDisplayed(By.id("btnUploadDocument")))
		{
			ScrollToElement(By.id("btnUploadDocument"));
			click(By.id("btnUploadDocument"));
			timeIntervel(1);
			waitUntilElementDisplays(loader);
			timeIntervel(1);
		}
		else if(isDisplayed(btnSubmit))
		{
			ScrollToElement(btnSubmit);
			click(btnSubmit);
			timeIntervel(1);
			waitUntilElementDisplays(loader);
			timeIntervel(1);
		}
		else
		{
			ScrollToElement(By.id("btnSaveMenu"));
			click(By.id("btnSaveMenu"));
			timeIntervel(1);
			waitUntilElementDisplays(loader);
			timeIntervel(1);
		}
	}

	public void clickOnSubmitAndReturnToAEList() throws IOException {
		
		if(isDisplayed(By.id("savetemplate"))){
			ScrollToElement(By.id("savetemplate"));
			click(By.id("savetemplate"));
			timeIntervel(1);
			waitUntilElementDisplays(loader);
			timeIntervel(1);
		}
		else if(isDisplayed(By.id("saveactivitymanagement"))){
			ScrollToElement(By.id("saveactivitymanagement"));
			click(By.id("saveactivitymanagement"));
			timeIntervel(1);
			waitUntilElementDisplays(loader);
			timeIntervel(1);
		}
		else if(isDisplayed(By.id("savedashboard")))
		{
			ScrollToElement(By.id("savedashboard"));
			click(By.id("savedashboard"));
			timeIntervel(1);
			waitUntilElementDisplays(loader);
			timeIntervel(1);
		}
		else{
			ScrollToElement(btnSubmitAndReturnToAEList);
			click(btnSubmitAndReturnToAEList);
			timeIntervel(1);
			waitUntilElementDisplays(loader);
			timeIntervel(1);
		}
	}
	
	public boolean verifybtnCancel(boolean verifyenable)
	{
		return isDisplayed(btnCancel) &&
				isEnable(verifyenable,"btnCancel");
	}
	
	public boolean verifybtnCancelAndReturntoList(boolean verifyenable)
	{
		return isDisplayed(btnCancelAndReturnToAEList) &&
				isEnable(verifyenable,"btnCancelList");
	}
	
	public boolean verifybtnSubmit(boolean verifyenable)
	{
		if(isDisplayed(By.id("btnUploadDocument"))){
			return isEnable(verifyenable,"btnUploadDocument");
		}
		else if(isDisplayed(btnSubmit)){
			return isEnable(verifyenable,"btnSubmit");
		}
		else
		{
			return isEnable(verifyenable, "btnSaveMenu");
		}
	}
	
	public boolean verifybtnSubmitAndReturntoList(boolean verifyenable)
	{
		if(isDisplayed(By.id("savetemplate"))){
			return isEnable(verifyenable,"savetemplate");
		}
		else if(isDisplayed(btnSubmitAndReturnToAEList)){
			return isEnable(verifyenable,"btnSubmitList");
		}
		else if(isDisplayed(By.id("savedashboard"))){
			return isEnable(verifyenable,"savedashboard");
		}
		else if(isDisplayed(By.id("saveactivitymanagement"))){
			return isEnable(verifyenable,"saveactivitymanagement");
		}
		return false;
	}
}

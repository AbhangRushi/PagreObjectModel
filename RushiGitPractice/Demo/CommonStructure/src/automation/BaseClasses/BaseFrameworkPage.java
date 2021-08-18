package automation.BaseClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BaseFrameworkPage
{
	private Logger logger;
	protected WebDriver selenium;
	protected Actions build;
	protected JavascriptExecutor jse;
	public int Seconds = 30;
	private  Random random = new Random();
	public Select dropdown;
	
	public BaseFrameworkPage(WebDriver driver)
	{
		PropertyConfigurator.configure(System.getProperty("user.dir")+"/src/main/resources/deployment/log4j.properties");
		this.selenium = driver;
		logger = Logger.getLogger(this.getClass().getName());
		if(BaseTestScript.browser.equals("app"))
		{
			PageFactory.initElements(new AjaxElementLocatorFactory(selenium, Seconds), this);
		}
		
		build = new Actions(selenium);
		jse = (JavascriptExecutor) selenium;
	}
	
	public void getHighlightElement(final WebElement element)
	{
		try
		{
			Wait<WebDriver> wait = new WebDriverWait(selenium, Seconds);
			// Wait for search to complete
			wait.until(new ExpectedCondition<Boolean>()
			{
				public Boolean apply(WebDriver webDriver)
				{
					return element != null;
				}
			});
			((JavascriptExecutor) selenium).executeScript("arguments[0].style.border='2px solid red'", element);
		}
		catch (Exception e)
		{
			logger.info("Fail to highlight the Element");
		}
	}
	
	public void moveToElement(By by)
	{
		try
		{
			getHighlightElement(selenium.findElement(by));
			build.moveToElement(selenium.findElement(by)).build().perform();
		}
		catch (NoSuchElementException e)
		{
			logger.info(e.getMessage());
		}
		catch (Exception e)
		{
			logger.info(e.getMessage());
		}
	}
	
	public void moveToElement(WebElement element)
	{
		try
		{
			getHighlightElement(element);
			build.moveToElement(element).build().perform();
		}
		catch (NoSuchElementException e)
		{
			logger.info(e.getMessage());
		}
		catch (Exception e)
		{
			logger.info(e.getMessage());
		}
	}
	
	public void click(By by)
	{
		moveToElement(by);
		selenium.findElement(by).click();
	}
	
	public void sendKeys(By by, String value)
	{
		waitForParticularElement(by, Seconds);
		try {
		moveToElement(by);
		clear(by);
		selenium.findElement(by).sendKeys(value);
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}	
	
	
	
	public void sendKeysWithEnter(By by, String value) throws IOException
	{
		waitForParticularElementExist(by, Seconds);
		try
		{
			moveToElement(by);
			timeIntervel();
			clear(by);
			selenium.findElement(by).sendKeys(value);
			selenium.findElement(by).sendKeys(Keys.ENTER);
		}
		catch (NoSuchElementException e)
		{
			Assert.assertTrue(false, "Fail to send keys from text box : " + by + " on page : " + e.getMessage());
		}
		catch (Exception e)
		{
			logger.info(e.getMessage());
		}
	}
	
	public void sendKeysWithKeysAction(By by, Keys value) throws IOException
	{
		waitForParticularElementExist(by, Seconds);
		try
		{
			moveToElement(by);
			timeIntervel();
			selenium.findElement(by).sendKeys(value);
		}
		catch (NoSuchElementException e)
		{
			Assert.assertTrue(false, "Fail to send keys from text box : " + by + " on page : " + e.getMessage());
		}
		catch (Exception e)
		{
			logger.info(e.getMessage());
		}
	}
	
	public void clear(By by) throws IOException
	{
		try
		{
			getHighlightElement(selenium.findElement(by));
			selenium.findElement(by).clear();
		}
		catch (NoSuchElementException e)
		{
			Assert.assertTrue(false, "Fail to clear value from text box : " + by + " on page : " + e.getMessage());
		}
		catch (Exception e)
		{
			logger.info(e.getMessage());
		}
	}
	
	public boolean isDisplayed(By by)
	{
		try
		{
			moveToElement(by);
			getHighlightElement(selenium.findElement(by));
			return selenium.findElement(by).isDisplayed();
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	public boolean isDisplayedAllXPath(By... bys)
	{
		try
		{
			for (By by : bys)
			{
				moveToElement(by);
				getHighlightElement(selenium.findElement(by));
				if (!selenium.findElement(by).isDisplayed())
					return false;
			}
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	public void moveScroll(By by, int value) throws IOException
	{
		WebElement scrollArea = selenium.findElement(by);
		jse.executeScript("arguments[0].scrollTop = arguments[1];", scrollArea, value);
	}
	
	public void Scroll(int xPoint, int yPoint)
	{
		jse.executeScript("window.scrollBy(" + xPoint + "," + yPoint + ")");
		timeIntervel(2);
	}
	
	public void ScrollToElement(By by) throws IOException
	{
		jse.executeScript("arguments[0].scrollIntoView(true);", selenium.findElement(by));
		timeIntervel(2);
	}
	
	public void ScrollToElement(WebElement ele) throws IOException
	{
		jse.executeScript("arguments[0].scrollIntoView(true);", ele);
		timeIntervel();
	}
	
	public void scrollToBottom()
	{
		timeIntervel(2);
		((JavascriptExecutor) selenium).executeScript("window.scrollTo(0, Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight))");
		timeIntervel(2);
	}
	
	public void scrollToTop()
	{
		Boolean vertscrollStatus = (Boolean) jse.executeScript("return document.documentElement.scrollHeight>document.documentElement.clientHeight;");
		if (vertscrollStatus)
		{
			selenium.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.HOME);
			timeIntervel(2);
		}
		else
		{
			jse.executeScript("scrollBy(0, -1000)");
		}
	}
	
	public synchronized void timeIntervel(int time)
	{
		try
		{
			wait(1000*time);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void implicitWait(int time)
	{
		selenium.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}
	
	public synchronized void timeIntervel()
	{
		try
		{
			wait(500);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public int countofTabs()
	{
		timeIntervel(3);
		ArrayList<String> tabs2 = new ArrayList<>(selenium.getWindowHandles());
		return tabs2.size();
	}
	
	public void switchTab(boolean value)
	{
		timeIntervel(3);
		ArrayList<String> tabs2 = new ArrayList<>(selenium.getWindowHandles());
		if(value)
		{
			selenium.close();
			selenium.switchTo().window(tabs2.get(1));
		}
		else
		{
			selenium.switchTo().window(tabs2.get(1));	
		}
		timeIntervel(3);
	}
	
	public void backToTab()
	{
		ArrayList<String> tabs2 = new ArrayList<>(selenium.getWindowHandles());
		selenium.close();
		timeIntervel(3);
		selenium.switchTo().window(tabs2.get(0));
	}
	
	public void waitForParticularElement(final By element, int waitForSeconds)
	{

		try
		{
			Wait<WebDriver> wait = new WebDriverWait(selenium, waitForSeconds);
			// Wait for search to complete
			wait.until(ExpectedConditions.visibilityOfElementLocated(element));

		}
		catch (Exception e)
		{
			logger.error(e + " Fail to Wait for element for " + element.toString());
		}
	}
	
	public void waitUntilElementDisplays(final By element) throws IOException
	{
		int i = 1;
		do
		{
			try
			{
				selenium.findElement(element).isDisplayed();
				timeIntervel(1);
				i++;
			}
			catch (NoSuchElementException e)
			{
				timeIntervel();
				logger.info("waiting for element : " + element.toString() + " :  Waiting Time [ " + i + " ] out of " + Seconds);
				break;
			}
			catch (StaleElementReferenceException ser)
			{
				timeIntervel();
				selenium.navigate().refresh();
				timeIntervel(3);
			}
		}
		while (i <= 200);
	}
	
	public void waitForParticularElementExist(final By element, int waitForSeconds)
	{

		try
		{
			Wait<WebDriver> wait = new WebDriverWait(selenium, waitForSeconds);
			// Wait for search to complete
			wait.until(ExpectedConditions.presenceOfElementLocated((element)));

		}
		catch (Exception e)
		{
			logger.error(e + " Element not present in DOM " + element.toString());
		}
	}
	
	public String getCSSValue(By by, String attributeName)
	{
		waitForParticularElement(by, Seconds);
		try
		{
			getHighlightElement(selenium.findElement(by));
			return selenium.findElement(by).getCssValue(attributeName).trim();
		}
		catch (NoSuchElementException e)
		{
			logger.error("Fail to get CSS value from : " + by + " on page : " + e.getMessage());
		}
		catch (Exception e)
		{
			logger.info(e.getMessage());
		}
		return null;
	}
	
	public String getWindowPopUpMsgAndAccept(By xpath,By Value)
	{
		timeIntervel(2);
		String msg = "";
		try
		{
			String window=selenium.getWindowHandle();
			selenium.switchTo().window(window);
			msg=getText(xpath);
			click(Value);
			
			timeIntervel(1);
			return msg;
		}
		catch (Exception e)
		{
			logger.info(e.getMessage());
			return "";
		}
	}
	
	public String getText(By by)
	{
		waitForParticularElement(by, Seconds);
		try
		{
			getHighlightElement(selenium.findElement(by));
			return selenium.findElement(by).getText().trim();
		}
		catch (NoSuchElementException e)
		{
			Assert.assertTrue(false, "Fail to get text value from : " + by + " on page : " + e.getMessage());

		}
		catch (Exception e)
		{
			logger.info(e.getMessage());

		}
		return null;
	}
	
	public String getAttribute(By by,String attributeName)
	{
		waitForParticularElement(by, Seconds);
		try
		{
			getHighlightElement(selenium.findElement(by));
			return selenium.findElement(by).getAttribute(attributeName).trim();
		}
		catch (NoSuchElementException e)
		{
			Assert.assertTrue(false, "Fail to get text value from : " + by + " on page : " + e.getMessage());

		}
		catch (Exception e)
		{
			logger.info(e.getMessage());

		}
		return null;
	}
	
	public String getPopupMsg_fromSystemPopup_andAccept()
	{
		timeIntervel(2);
		String msg = "";
		try
		{
			timeIntervel(1);
			msg = selenium.switchTo().alert().getText();
			timeIntervel(2);
			selenium.switchTo().alert().accept();
			timeIntervel(1);
			return msg;
		}
		catch (Exception e)
		{
			logger.info(e.getMessage());
			return "";
		}
	}
	
	public void alertAcceptandDismiss(boolean value)
	{
		selenium.switchTo().alert();
		timeIntervel(2);
		if(value)
		{
			selenium.switchTo().alert().accept();	
		}
		else
		{
			selenium.switchTo().alert().dismiss();
		}
		
		timeIntervel(1);
	}
	
	public void getAlert(String message, int second)
	{
		try
		{
			((JavascriptExecutor) BaseTestScript.selenium).executeAsyncScript(
					"setTimeout(arguments[0]);setTimeout(function(){window.alert('" + message + "'); });");
			timeout(second);
			BaseTestScript.selenium.switchTo().alert().accept();
		}
		catch (Exception e)
		{
			logger.info("Fail To Generated or Handled Alert...!!! " + e);
		}
	}
	
	protected void timeout(long second)
	{
		try
		{
			Thread.sleep(second * 600);
		}
		catch (Exception e)
		{
			logger.error(e);
		}
	}
	
	public int getNumberOfListOfElements(By by)
	{
		try
		{
			return selenium.findElements(by).size();
		}
		catch(Exception e)
		{
			logger.info(e.getMessage());
			return 0;
		}
	}
	
	public boolean autoSuggestList(By textBox, String autoSuggestValue, By autoSuggestList, boolean verify) throws IOException
	{
		sendKeyWait(textBox, autoSuggestValue, true);
		timeIntervel(1);
		waitForParticularElement(autoSuggestList, Seconds);
		if (isDisplayed(autoSuggestList))
		{
			if (verify)
			{
				return true;
			}
			else
			{
				moveToElement(autoSuggestList);
			   
				click(autoSuggestList);
				return true;
			}
		}
		else
		{
			selenium.findElement(textBox).sendKeys("\t");
			return false;
		}
	}
	
	public void sendKeyWait(By by, String value, boolean withclear) throws IOException
	{
		waitForParticularElement(by,Seconds);
		try
		{
			moveToElement(by);
			timeIntervel();
			if (withclear)
			{
				clear(by);
			}
			for (int i = 0; i < value.length(); i++)
			{
				selenium.findElement(by).sendKeys(String.valueOf(value.charAt(i)));
			}

		}
		catch (NoSuchElementException e)
		{
			Assert.assertTrue(false, "Fail to send keys from text box : " + by + " on page : " + e.getMessage());
		}
	}
	
	public void exeFileRun(String exeFile) throws IOException
	{
		timeIntervel();
		Runtime.getRuntime().exec(exeFile);
		timeIntervel(2);
	}
	
	public void deleteExistFile(String path,String fileName)
	{
		File file= new File(path+fileName);
		try
		{
			if(file.exists() && !file.isDirectory())
			{
				file.delete();
				logger.info("Delete File Successfully");
			}
		}
		catch (Exception e) 
		{
			logger.info("File not Found");
		}
		
	}
	
	public void moveToFrame(By by)
	{
		getHighlightElement(selenium.findElement(by));
		try{
			selenium.switchTo().frame(selenium.findElement(by));
		}
		catch (Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	public void moveToDefaultContent()
	{
		try{
			selenium.switchTo().defaultContent();
		}
		catch (Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	public boolean isEnable(boolean verify,String by)
	{
		try
		{
			if(verify)
			{
				return selenium.findElement(By.id(by)).isEnabled();
			}
			else
			{
				String disableValue= ((JavascriptExecutor) selenium).executeScript("return document.getElementById('"+by+"').getAttribute('disabled')").toString();
				if(disableValue.equals("disabled"))
				{
					return true;
				}
				return false;
			}
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	public String getTitleOfPage()
	{
		try
		{
			return selenium.getTitle().trim();
		}
		catch (Exception e) 
		{
			logger.info(e.getMessage());
		}
		return null;
	}
	
	public void selectValueFromDropdown(By by,String selectValue)
	{
		try
		{
			moveToElement(by);
			timeIntervel();
			dropdown= new Select(selenium.findElement(by));
			dropdown.selectByVisibleText(selectValue);	
		}
		catch (Exception e) 
		{
			logger.info(e.getMessage());
		}
	}
	
	public String getSelectedValuefromDropdown(By by)
	{
		try
		{
			moveToElement(by);
			timeIntervel();
			dropdown= new Select(selenium.findElement(by));
			return dropdown.getFirstSelectedOption().getText();
		}
		catch (Exception e) 
		{
			logger.info(e.getMessage());
		}
		return null;
	}
	
	public List<String> getValuesFromDropdown(By by)
	{
		try
		{
			moveToElement(by);
			dropdown= new Select(selenium.findElement(by));
			List<WebElement> elements=dropdown.getOptions();
			List<String> values=new ArrayList<>();
			for (WebElement element : elements) 
			{
				values.add(element.getText());
			}
			return values;
		}
		catch(Exception e)
		{
			logger.info(e.getMessage());
		}
		return null;
	}
	
	public List<String> getvalues(By by)
	{
		List<WebElement> elements=selenium.findElements(by);
		
		List<String> values=new ArrayList<>();
		for (WebElement element : elements) 
		{
			values.add(element.getText());
		}
		return values;
	}
	
	public boolean isClickable(By by)
	{
		try
		{
			WebElement element=selenium.findElement(by);
			moveToElement(by);
			element.click();
			return true;
		}
		catch (Exception e)
		{
			logger.info(e.getMessage());
			return false;
		}
	}
	
	public boolean isSelected(By by) throws IOException
	{
		waitForParticularElement(by, Seconds);
		try
		{
			moveToElement(by);
			getHighlightElement(selenium.findElement(by));
			return selenium.findElement(by).isSelected();
		}
		catch (NoSuchElementException e)
		{
			logger.info(e.getMessage());
			return false;
		}
		catch (Exception e)
		{
			logger.info("Fail to check isSelected : " + by + " : " + e.getMessage());
			return false;
		}
	}
	
	public String getHiddenTextValue(By by, String id)
	{
		try
		{
			getHighlightElement(selenium.findElement(by));
			String textBoxValue=((JavascriptExecutor) selenium).executeScript("return document.getElementById('"+id+"').value;").toString();
			return textBoxValue;
		}
		catch (Exception e)
		{
			logger.info(e.getMessage());
			Assert.assertTrue(false, "Fail to get text value from : " + by + " on page : " + e.getMessage());
		}
		return null;
	}
	
	public void sendKeysWithClearTextBoxValue(By by, String value) throws IOException
	{
		waitForParticularElementExist(by, Seconds);
		try
		{
			moveToElement(by);
			timeIntervel();
			clearTextBoxValue(by);
			selenium.findElement(by).sendKeys(value);
		}
		catch (NoSuchElementException e)
		{
			Assert.assertTrue(false, "Fail to send keys from text box : " + by + " on page : " + e.getMessage());
		}
		catch (Exception e)
		{
			logger.info(e.getMessage());
		}
	}
	
	private void clearTextBoxValue(By by)
	{
		moveToElement(by);
		WebElement element= selenium.findElement(by);
		element.sendKeys(Keys.CONTROL,"a",Keys.DELETE);
	}
	
	/*************************** Java generic Methods ******************************/
	
	public int getRandomNumber()
	{
		random = new Random();
		return (10+random.nextInt(999));	
	}
	
	public String getRandomString()	{
		try
		{
			String alphabates="abcdefghijklmnopqrstuvwxyz";
			String randomString="";
			int lengh=4;
			
			char[] value= new char[lengh];
			
			for(int i=0;i<lengh;i++)
			{
				value[i]= alphabates.charAt(random.nextInt(alphabates.length()));
				randomString+=value[i];
			}		
			System.out.println(randomString);
			return randomString;
		}
		catch(Exception e)
		{
			logger.error(e);
		}
		return null;
	}
	
	
	protected void writeExcelSheet(String filepath,String filename,String sheetname,String[] dataToWrite) throws IOException
	{
		File file = new File(filepath+filename);
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet(sheetname);
        Row newRow = sheet.createRow(0);
        for(int j = 0; j < dataToWrite.length; j++)
        {
            Cell cell = newRow.createCell(j);
            cell.setCellValue(dataToWrite[j]);   
        }
        FileOutputStream outputStream = new FileOutputStream(file);
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
	}
	
	
	protected void readExcelSheet(String filePath,String fileName,String sheetName) throws IOException
	{
		File file = new File(filePath+fileName);
		FileInputStream inputStream= new FileInputStream(file);
		
		Workbook workbook= new XSSFWorkbook(inputStream);
		Iterator<Sheet> sheetIterator = workbook.sheetIterator();
		 while (sheetIterator.hasNext()) 
		 {
            Sheet sheet = sheetIterator.next();
            System.out.println("=> " + sheet.getSheetName());
	     }
		Sheet sheet= workbook.getSheetAt(0);
		
		 Iterator<Row> rowIterator = sheet.rowIterator();
	        while (rowIterator.hasNext()) 
	        {
	            Row row = rowIterator.next();

	            // Now let's iterate over the columns of the current row
	            Iterator<Cell> cellIterator = row.cellIterator();

	            while (cellIterator.hasNext())
	            {
	                Cell cell = cellIterator.next();
	                String cellValue = cell.getStringCellValue();
	                System.out.print(cellValue + "\t");
	            }
	        }
	        workbook.close();
	}
}

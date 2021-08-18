package main.java.automation.PageObject;

import java.io.IOException;
import java.sql.SQLException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import automation.BaseClasses.ApplicationProperties;
import automation.BaseClasses.BaseTestScript;
import automation.helper.SQLHelper;

public class TwoStepLogin extends CommonUtilityPage
{
	private static final By token=By.id("logSys_two_step_login-token");
	
	SQLHelper sqlhelper= new SQLHelper();
	
	public TwoStepLogin(WebDriver driver) {
		super(driver);
	}
	
	public void enterToken() throws SQLException, IOException
	{
		int id= clientId();
		String tokenvalue=getToken("SELECT * FROM  `user_tokens` WHERE uid='"+id+"' ORDER BY requested DESC LIMIT 0,1");
		System.out.println(tokenvalue);
		sendKeys(token, tokenvalue);
	}
	
	private String getToken(String query) throws SQLException
	{
		String tokenvalue=sqlhelper.getResult(query).toString();
		return tokenvalue;
	}
	
	private int clientId() throws SQLException, IOException
	{
		String username=BaseTestScript.p.getProperty("setUsername").trim();
		int clientID=Integer.parseInt(sqlhelper.getResult("SELECT id FROM `users` WHERE email='"+username+"'").toString()) ;
		return clientID;
	}
}
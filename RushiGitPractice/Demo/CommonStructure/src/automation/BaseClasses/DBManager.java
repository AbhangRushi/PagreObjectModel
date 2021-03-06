package automation.BaseClasses;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;

public class DBManager
{
	private static Logger logger = Logger.getLogger(DBManager.class.getName());
	
	private static Connection con=null;
	private static Statement stmt=null;
	private static String DB_URL;
	private static String DB_DOMAIN;
	private static String DB_PORT;
	private static String DB_USERNAME;
	private static String DB_PASSWORD;
	private static String DB_NAME;
	private static String DB_DRIVER;
	
	
	public static Connection getConnection() throws SQLException, IOException, ClassNotFoundException
	{	
		
//		DB_DOMAIN= ApplicationProperties.getInstance().getProperty("db.domain").trim();
//		DB_NAME=ApplicationProperties.getInstance().getProperty("db.name").trim();
//		DB_PORT=ApplicationProperties.getInstance().getProperty("db.port").trim();
//		DB_USERNAME=ApplicationProperties.getInstance().getProperty("db.username").trim();
//		DB_PASSWORD=ApplicationProperties.getInstance().getProperty("db.password").trim();		
		
		DB_DOMAIN= BaseTestScript.p.getProperty("db.domain");
		DB_NAME=BaseTestScript.p.getProperty("db.name").trim();
		DB_PORT=BaseTestScript.p.getProperty("db.port").trim();
		DB_USERNAME=BaseTestScript.p.getProperty("db.username").trim();
		DB_PASSWORD=BaseTestScript.p.getProperty("db.password").trim();
		
		DB_URL="jdbc:mysql://"+DB_DOMAIN+":"+DB_PORT+"/"+DB_NAME+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		
		//DB_DRIVER = "com.mysql.jdbc.Driver";
		DB_DRIVER = "com.mysql.cj.jdbc.Driver";
		
		// load the Driver Class
		Class.forName(DB_DRIVER);

		// create the connection now
		con = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
		return con;
	}
	
	public static void connectionClose() throws SQLException
	{
		 if (stmt != null)
		 {
			  try
			  {
				   stmt.close();
			  }
			  catch (SQLException se)
			  {
				  logger.error(se);
			  }
		 }
	}
}

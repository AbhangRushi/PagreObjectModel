package automation.helper;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import automation.BaseClasses.DBManager;

public class SQLHelper
{
	private static Logger logger = Logger.getLogger(SQLHelper.class.getName());
	private static Connection con=null;
	private static Statement stmt=null;
	private static ResultSet resultSet=null;
	private static List<Object> result=new ArrayList<>();
	
	
	public synchronized Object getResult(String query) throws SQLException
	{
		try
		{
			con=DBManager.getConnection();	
			stmt=con.createStatement();
			resultSet=stmt.executeQuery(query);
		
			while(resultSet.next())
			{
				return resultSet.getObject(1);
			}
		}
		catch(SQLException se)
		{
			logger.error("SQLException accured in getResult() method:", se);
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		finally
		{
			DBManager.connectionClose();
		}
		return null;
	}
	
	public List<Object> getResultSet(String query) throws SQLException, ClassNotFoundException, IOException
	{
		try
		{
			con=DBManager.getConnection();
			stmt=con.createStatement();
			resultSet=stmt.executeQuery(query);
			
			while(resultSet.next()) 
			{
				int i=1;
				result.add(resultSet.getObject(i));
				logger.info(result);
				i++;
			}
			return result;
		}
		catch(SQLException se)
		{
			logger.error("SQLException accured in getResult() method:", se);
		}
		catch (Exception e) 
		{
			logger.error(e);
		}
		finally
		{
			DBManager.connectionClose();
		}
		return null;
	}
		
	
}

package automation.helper;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map.Entry;
import org.apache.tools.ant.Task;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import com.socketLabs.injectionApi.SendResponse;
import com.socketLabs.injectionApi.SocketLabsClient;
import com.socketLabs.injectionApi.message.BulkMessage;
import com.socketLabs.injectionApi.message.BulkRecipient;
import com.socketLabs.injectionApi.message.EmailAddress;
import automation.BaseClasses.ApplicationProperties;
import automation.BaseClasses.BaseTestScript;
import automation.BaseClasses.Reporter;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

public class ScreenshotHelper extends Task
{
	private String folderPath = "D:/Shakuntala/CommonStructure/target/";
	private static final String[] statusColor = {"#009900","#ff0000","#ff7f00"};
	private static final String ROW = "</TH></H3></TR>";
	private static final String data = "</TD>";
	private static final String dateTimeFormate = "dd-MMM-YYYY:hh:mm:ss";
	private static final String success = "Success";
	private static final String bold = "<TD align=center><b>";
	private static final String alignment = "<TD align=center>";
	private static final String propertyMessage = "Problem To Load values From ApplicationProperties in HelperClass...!!!";
	private static String environment;
	private String to;
	private String from;
	//private final String pass = "devitqc@123";
	private String host;
	private int port;
	private String subjectPrefix;
	protected Logger logger = Logger.getLogger(this.getClass());
	private static String result;
	public static int serverId = 18892;
    public static String apiKey = "Dk46Xcd7PJr9y5R2NqFm";

	@Override
	public void execute()
	{
		if (folderPath != null)
		{
			saveScreenShotPath(folderPath);
		}
		log(folderPath);
	}
	
	private void saveScreenShotPath(String folderpath)
	{
		try
		{
			File file = new File("/screenshot.txt");
			PrintWriter writer = new PrintWriter(file.getCanonicalPath(), "UTF-8");
			writer.println(folderpath);
			writer.close();
		}
		catch (IOException e)
		{
			System.err.println(e);
		}
	}

	public void setfolderPath(String folderPath)
	{
		this.folderPath = folderPath;
	}

	protected static String captureErrorScreen(String timeStemp) throws IOException
	{
		String path = "";
		if (!"No".equalsIgnoreCase(ApplicationProperties.getInstance().getProperty("report.with.screenshot")))
		{
			try
			{
				WebDriver augmentedDriver = new Augmenter().augment(BaseTestScript.selenium);
				path = BaseTestScript.screenshotPath + timeStemp + ".jpeg";
				File error = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(error, new File(path));
			}
			catch (Exception exception)
			{
				exception.getMessage();
			}
		}
		return path;
	}
	
	public void mailConfiguration() throws Exception
	{
		if (!("No").equalsIgnoreCase(BaseTestScript.p.getProperty("report.with.mail")))
		{
			
			sendMailViaGmail();
//			// Changes here
//			if ("smtp.gmail.com".equalsIgnoreCase(ApplicationProperties.getInstance().getProperty("mail.server")))
//			{
//				sendMailViaGmail();
//			}
//			else
//			{
//				logger.error("Please check mail configuration");
//			}
		}
	}

	public void sendMailViaGmail() throws Exception
	{

		commonSendMail();
		
		BulkMessage message = new BulkMessage();
		
		logger.info("Start to Send the mail.....");
//		Properties properties = System.getProperties();
//		properties.put("mail.smtp.starttls.enable", "true");
//		properties.put("mail.smtp.ssl.trust", getHost());
//		properties.put("mail.smtp.host", getHost());
//		properties.put("mail.smtp.user", getFrom());
//		properties.put("mail.smtp.password", pass);
//		properties.put("mail.smtp.port", getPort());
//		properties.put("mail.smtp.auth", "true");
//
//		Session session = Session.getDefaultInstance(properties);
//		MimeMessage message = new MimeMessage(session);
//		message.setFrom(new InternetAddress(from));
//		InternetAddress[] iAdressArray = InternetAddress.parse(to);
//		message.setRecipients(Message.RecipientType.TO, iAdressArray);
		
		if(BaseTestScript.APPLICATION_URL.equals("https://"+"devpcms.chotomojo.com"))
		{
			environment="Dev Environment ";
		}
		else if(BaseTestScript.APPLICATION_URL.equals("https://"+"stagingpcms.chotomojo.com"))
		{
			environment="Staging Environment ";
		}
		else
		{
			environment="Live Environment ";
		}

		if (CustomisedListener.getBuildStatus().equalsIgnoreCase(success))
		{
			message.setSubject(getSubjectPrefix() + "| PASS (All Cases) |"+environment+" at " + BaseTestScript.dateAndSystemTime(dateTimeFormate));
		}
		else
		{
			message.setSubject(getSubjectPrefix() + "| FAIL ("+CustomisedListener.getTotalFail()+" out of "+CustomisedListener.getTotalSecnarioCount()+" cases) |"+environment+" at " + BaseTestScript.dateAndSystemTime(dateTimeFormate));
		}
		//message.setContent(result, "text/html");
		
		message.setHtmlBody(result);
		message.setFrom(new EmailAddress(getFrom()));
		
		String[] abc=to.trim().split(",");
		for (String s : abc) {
			message.getTo().add(new BulkRecipient(s));
		}
      
		SocketLabsClient client = new SocketLabsClient(serverId,apiKey); //Your SocketLabs ServerId and Injection API key

		SendResponse response =  client.send(message);
		
//		ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
//
//        System.out.println("Response body : ");
//        System.out.println(mapper.writeValueAsString(response));
		

//		Transport transport = session.getTransport("smtp");
//		transport.connect(host, from, pass);
//		transport.sendMessage(message, message.getAllRecipients());
//		transport.close();
		logger.info("Sent message successfully....");

	}

	private void commonSendMail() throws IOException
	{
		String testcaseStatusColor;
		int i = 0;
		validateAttributes();

		try
		{
			StringWriter out = new StringWriter();
			PrintWriter writer = new PrintWriter(out);
			writer.print("<TABLE BORDER= 5 Align=center WIDTH=50%   CELLPADDING=4  CELLSPACING=3><TR><TH COLSPAN=8 bgcolor=#d9d9d9><BR><font color=#008ae6><H2>Report Summary</H2></font>");
			writer.print("<TR><TH><h3><font color=#008ae6 > Testcase Name </font></TH>");
			writer.print("<TH><h3><font color=#008ae6 >Status </font></TH>");
			writer.print("<TH><h3><font color=#008ae6 >Time(In Minutes) </font></TH>");
			writer.print("<TH><h3><font color=#008ae6 >Total Scenarios </font></TH>");
			writer.print("<TH><h3><font color=#008ae6 >Pass scenarios </font></TH>");
			writer.print("<TH><h3><font color=#008ae6 >Fail scenarios </font></TH>");
			writer.print("<TH><h3><font color=#008ae6 >Skip scenarios </font></TH>");
			writer.print("<TH><h3><font color=#008ae6 >Pass Rate </font></TH></TR>");

			for (Entry<String, String> singleTC : CustomisedListener.getTestcaseFinalStatus().entrySet())
			{
				if (CustomisedListener.pass.equalsIgnoreCase(singleTC.getValue()))
				{
					testcaseStatusColor = statusColor[0];
				}
				else if(CustomisedListener.fail.equalsIgnoreCase(singleTC.getValue()))
				{
					testcaseStatusColor = statusColor[1];	
				}
				else
				{
					testcaseStatusColor = statusColor[2];
				}

				writer.println("<TR><TD>" + singleTC.getKey() + data);
				writer.println("<TD align=center><font color=" + testcaseStatusColor + "> " + singleTC.getValue() + "</font></TD>");
				writer.println(alignment + CustomisedListener.timeList.get(i) + data);
				writer.println(alignment + CustomisedListener.totalScenarioCount.get(i) + data);
				writer.println(alignment + CustomisedListener.passList.get(i) + data);
				writer.println(alignment + CustomisedListener.failList.get(i) + data);
				writer.println(alignment + CustomisedListener.skipList.get(i) + data);
				writer.println(alignment + CustomisedListener.passRate.get(i) + "%" + "</TD></TR>");
				i++;
			}

			writer.print("<TR><TH><h3><font color=#008ae6 > Final Statistics </font></TH>");
			writer.println("<TD align=right>" + " " + "</TD>");
			writer.println(bold + CustomisedListener.getFinalTime() + data);
			writer.println(bold + CustomisedListener.getTotalSecnarioCount() + data);
			writer.println(bold + CustomisedListener.getTotalPass() + data);
			writer.println(bold + CustomisedListener.getTotalFail() + data);
			writer.println(bold + CustomisedListener.getTotalSkip() + data);
			writer.println(bold + CustomisedListener.getTotalPassRate() + "%" + "</TD></TR>");
			writer.println("<TR> <TH><font color=#008ae6 > TOTAL TESTCASES </font></TH> <TH COLSPAN=7><H3>"+ CustomisedListener.getTotalTestCases() + ScreenshotHelper.ROW);
			writer.println("<TR> <TH><font color=#008ae6 > TOTAL SCENARIOS </font></TH> <TH COLSPAN=7><H3>"+ CustomisedListener.getTotalSecnarioCount() + ScreenshotHelper.ROW);
			writer.println("<TR> <TH><font color=#008ae6 > TOTAL PASS </font></TH> <TH COLSPAN=7><H3>"+ CustomisedListener.getTotalPass() + ScreenshotHelper.ROW);
			writer.println("<TR> <TH><font color=#008ae6 > TOTAL FAIL </font></TH> <TH COLSPAN=7><H3>"+ CustomisedListener.getTotalFail() + ScreenshotHelper.ROW);
			writer.println("<TR> <TH><font color=#008ae6 > TOTAL SKIP </font></TH> <TH COLSPAN=7><H3>"+ CustomisedListener.getTotalSkip() + ScreenshotHelper.ROW);
			writer.println("</TABLE>");
			writer.flush();
			result = out.toString();
		}
		catch (Exception e)
		{
			logger.info(e);
		}
	}
	
	private void validateAttributes()
	{
//		try
//		{
//			host = ApplicationProperties.getInstance().getProperty("mail.server");
//			setHost(host);
//			if ((getHost() == null))
//			{
//				Reporter.log("Please specify the mail.server name [HostName] into propertie file.");
//			}
//		}
//		catch (IOException e)
//		{
//			logger.info(e);
//			Reporter.log(ScreenshotHelper.propertyMessage);
//		}
//		
//		try
//		{
//			port = Integer.parseInt(ApplicationProperties.getInstance().getProperty("mail.port"));
//			setPort(port);
//			if ((getPort() == null))
//			{
//				Reporter.log("Please specify the mail.port [Port] into propertie file.");
//			}
//		}
//		catch (IOException e)
//		{
//			logger.info(e);
//			Reporter.log(ScreenshotHelper.propertyMessage);
//		}

		from = BaseTestScript.p.getProperty("mail.from.email");
		setFrom(from);
		if ((getFrom() == null))
		{
			Reporter.log("Please specify the mail.server [From] into propertie file.");
		}

		try
		{
			subjectPrefix = ApplicationProperties.getInstance().getProperty("mail.subject");
			setSubjectPrefix(subjectPrefix);
			if ((getSubjectPrefix() == null))
			{
				throw new IOException("Please specify the mail.subject [Subject] into propertie file.");
			}
		}
		catch (IOException e)
		{
			logger.info(e);
			Reporter.log(ScreenshotHelper.propertyMessage);
		}

		try
		{
			to = ApplicationProperties.getInstance().getProperty("mail.to.email");
			setTo(to);
			if ((getTo() == null))
			{
				throw new IOException("Please specify the mail.to.email [To] into propertie file.");
			}
		}
		catch (IOException e)
		{
			logger.info(e);
			Reporter.log(ScreenshotHelper.propertyMessage);
		}
	}
	
	public void setHost(String host)
	{
		this.host=host;
	}
	
	public void setPort(int port)
	{
		this.port = port;
	}
	
	public Integer getPort()
	{
		return this.port;
	}
	
	public String getHost()
	{
		return this.host;
	}

	public void setTo(String to)
	{
		this.to = to;
	}

	public String getTo()
	{
		return this.to;
	}

	public void setFrom(String from)
	{
		this.from = from;
	}

	public String getFrom()
	{
		return this.from;
	}

	public void setSubjectPrefix(String subjectPrefix)
	{
		this.subjectPrefix = subjectPrefix;
	}

	public String getSubjectPrefix()
	{
		return this.subjectPrefix;
	}
}
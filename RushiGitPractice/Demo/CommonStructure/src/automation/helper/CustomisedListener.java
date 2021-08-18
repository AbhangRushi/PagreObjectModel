package automation.helper;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.testng.IExecutionListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import automation.BaseClasses.ApplicationProperties;
import automation.BaseClasses.BaseTestScript;

public class CustomisedListener extends BaseTestScript implements ITestListener,IExecutionListener
{
	private String moduleName;
	private String line = "====================================================================================";
	static List<String> timeList = new ArrayList<>();
	private long testStartTime;
	private long finalStartTime;
	private static String finalTime;
	private static Map<String, String> testcaseFinalStatus = new LinkedHashMap<>();
	private static Map<String, String> scenerioListFinalStatus = new LinkedHashMap<>();
	private String testcaseName;
	private static String buildStatus;
	private static int totalTestCases = 0;
	private static int countOfPassScenarios = 0;
	private static int countOfSkipScenarios = 0;
	private static int countOfFailScenarios = 0;
	protected static List<Integer> totalScenarioCount = new ArrayList<>();
	protected static List<Integer> passList = new ArrayList<>();
	protected static List<Integer> skipList = new ArrayList<>();
	protected static List<Integer> failList = new ArrayList<>();
	protected static List<Integer> passRate = new ArrayList<>();
	protected static int totalPassRate;
	protected static int RateOfTestPass;
	private static int countOfTestScenarios = 0;
	protected static int sumOfAllScenarioCount = 0;
	protected static int sumOfAllPassScenarioCount = 0;
	protected static int sumOfAllSkipScenarioCount = 0;
	protected static int sumOfAllFailScenarioCount = 0;
	public static final String pass="PASS";
	public static final  String fail="FAIL";
	public static final String skip="SKIP";
	
	
	public void onTestStart(ITestResult result) {
 		String packageName = result.getInstanceName();
		String[] splitValue=packageName.split("tcRepository.");
		moduleName=splitValue[1];
	}

	
	public void onTestSuccess(ITestResult result) {
		setCountOfPassScenarios(getCountOfPassScenarios() + 1);
		scenerioListFinalStatus.put(result.getName(),pass);
	}

	
	public void onTestFailure(ITestResult result) {
		logger.info("***** Error " + result.getName() + " test has failed *****");
		String testMethodName=result.getName().trim();
		
		setCountOfFailScenarios(getCountOfFailScenarios() + 1);
		scenerioListFinalStatus.put(result.getName(),fail);
		
		String timestemp=systemTime();
		if(!result.isSuccess()){
			try{
				if(!("no").equals(BaseTestScript.p.getProperty("report.with.screenshot"))){
					String path = ScreenshotHelper.captureErrorScreen(moduleName+"_"+testMethodName + "_" + timestemp);
					timeout(1);
					System.setProperty("org.uncommons.reportng.escape-output","false");
					Reporter.log("<a title= \" Error ScreenShot... \" href=\"" + path
							+ "\"><img width=\"100\" height=\"100\" alt=\"" + result.getThrowable().getMessage()
							+ "\" title=\" Error ScreenShot... \" src=\"" + path + "\">" + "</a>");
				}
			}
			catch(Exception e){
				logger.info(e);
			}
		}
	}

	
	public void onTestSkipped(ITestResult result) {
		setCountOfSkipScenarios(getCountOfSkipScenarios() + 1);
		scenerioListFinalStatus.put(result.getName(),skip);
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	
	public void onStart(ITestContext context) {
		testStartTime = System.currentTimeMillis();
		scenerioListFinalStatus.clear();
		setTotalTestCases(getTotalTestCases()+1);
		setCountOfPassScenarios(0);
		setCountOfFailScenarios(0);
		setCountOfSkipScenarios(0);
		setCountOfTestScenarios(0);
		testcaseName=context.getName();
		logger.info("***** Testcase is " + testcaseName + "  *****");
		
	}

	
	public void onFinish(ITestContext context) {
		final long testEndTime = System.currentTimeMillis();
		double min = ((testEndTime - testStartTime) / 1000.0) / 60;
		String executionTime = new DecimalFormat("0.00").format(min);
		timeList.add(executionTime);
		
		if (scenerioListFinalStatus.containsValue(fail)){
			getTestcaseFinalStatus().put(testcaseName, fail);
		}
		else if(scenerioListFinalStatus.containsValue(skip)){
			getTestcaseFinalStatus().put(testcaseName,skip);
		}
		else{
			getTestcaseFinalStatus().put(testcaseName,pass);
		}
		
		countOfTestScenarios=scenerioListFinalStatus.size();
		setCountOfTestScenarios(countOfTestScenarios);
		
		RateOfTestPass=(getCountOfPassScenarios()*100)/getCountOfTestScenarios();
		passRate.add(RateOfTestPass);
	
		totalScenarioCount.add(countOfTestScenarios);
		passList.add(countOfPassScenarios);
		failList.add(countOfFailScenarios);
		skipList.add(countOfSkipScenarios);
	}

	
	public void onExecutionStart() {
		finalStartTime = System.currentTimeMillis();
	}
	
	
	public void onExecutionFinish() {
		final long finalEndTime = System.currentTimeMillis();
		double min = ((finalEndTime - finalStartTime) / 1000.0) / 60;
		String executionTime = new DecimalFormat("0.00").format(min);
		
		setFinalTime(executionTime);
		
		if (getTestcaseFinalStatus().containsValue(fail)){
			setBuildStatus(fail);
		}
		else{
			setBuildStatus("Success");
		}
		
		setTotalSecnarioCount(totalScenarioCount.stream().reduce(0,(a,b) -> a+b));
		setTotalPass(passList.stream().reduce(0,(a,b) -> a+b));
		setTotalFail(failList.stream().reduce(0,(a,b) -> a+b));
		setTotalSkip(skipList.stream().reduce(0,(a,b) -> a+b));
		setTotalPassRate((getTotalPass()*100)/getTotalSecnarioCount());
		ScreenshotHelper emailhelper = new ScreenshotHelper();
		try{
			emailhelper.mailConfiguration();
		}
		catch (Exception e){
			logger.info(e);
			logger.info("You have to Add listener For send Mail " + e);
		}
		
		logger.info(line);
		logger.info("CONSOLIDATED TEST SUMMARY REPORT");
		logger.info(line);
		logger.info("Total testcases Executed:- " +getTotalTestCases());
		logger.info("Total scenario Passed:- " + getTotalPass());
		logger.info("Total scenario Failed:- " + getTotalFail());
		logger.info("Total scenario Skipped:- " + getTotalSkip());
		logger.info("Total Scenarios :" + getTotalSecnarioCount());
		logger.info("Total Pass Rate: "+getTotalPassRate());
		logger.info("Final Time: "+finalTime);
		Reporter.log(finalTime);
		logger.info(line);
	}
	
	public static int setCountOfPassScenarios(int passScenarioCount){
		return CustomisedListener.countOfPassScenarios=passScenarioCount;
	}
	
	public static int getCountOfPassScenarios()
	{
		return countOfPassScenarios;
	}
	
	public static int setCountOfFailScenarios(int failScenarioCount)
	{
		return CustomisedListener.countOfFailScenarios=failScenarioCount;
	}
	
	public static int getCountOfFailScenarios()
	{
		return countOfFailScenarios;
	}
	
	private static void setCountOfSkipScenarios(int skipScenrarioCount)
	{
		CustomisedListener.countOfSkipScenarios = skipScenrarioCount;
	}
	
	public static int getCountOfSkipScenarios()
	{
		return countOfSkipScenarios;
	}
	
	private static void setCountOfTestScenarios(int testScenariosCount)
	{
		CustomisedListener.countOfTestScenarios = testScenariosCount;
	}
	
	public static int getCountOfTestScenarios()
	{
		return countOfTestScenarios;
	}
	
	public static Map<String, String> getTestcaseFinalStatus()
	{
		return testcaseFinalStatus;
	}
	
	public static int getTotalSecnarioCount()
	{
		return sumOfAllScenarioCount;
	}
	
	private static void setTotalSecnarioCount(int totalFail)
	{
		CustomisedListener.sumOfAllScenarioCount = totalFail;
	}
	
	public static int getTotalFail()
	{
		return sumOfAllFailScenarioCount;
	}
	
	private static void setTotalFail(int totalFail)
	{
		CustomisedListener.sumOfAllFailScenarioCount = totalFail;
	}
	
	private static void setTotalPass(int totalPass)
	{
		CustomisedListener.sumOfAllPassScenarioCount = totalPass;
	}
	
	public static int getTotalPass()
	{
		return sumOfAllPassScenarioCount;
	}
	
	private static void setTotalSkip(int totalFail)
	{
		CustomisedListener.sumOfAllSkipScenarioCount = totalFail;
	}
	
	private static void setTotalPassRate(int totalPassRation) 
	{
		CustomisedListener.totalPassRate=totalPassRation;
	}
	
	public static int getTotalPassRate() 
	{
		 return totalPassRate;
	}
	
	public static int getTotalSkip()
	{
		return sumOfAllSkipScenarioCount;
	}
	
	public static int getTotalTestCases()
	{
		return totalTestCases;
	}
	
	private static void setTotalTestCases(int totalTestCases)
	{
		CustomisedListener.totalTestCases = totalTestCases;
	}
	
	public static String getBuildStatus()
	{
		return buildStatus;
	}
	
	public static void setBuildStatus(String buildStatus)
	{
		CustomisedListener.buildStatus = buildStatus;
	}
	
	private static void setFinalTime(String finalTime)
	{
		CustomisedListener.finalTime = finalTime;
	}
	
	public static String getFinalTime()
	{
		return finalTime;
	}
}
	

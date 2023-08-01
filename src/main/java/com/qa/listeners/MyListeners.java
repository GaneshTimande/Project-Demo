package com.qa.listeners;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import com.qa.utilities.Utilities;
import com.util.ExtentReporter;
import com.util.JiraPolicy;
import com.util.JiraServiceProvider;

public class MyListeners implements ITestListener {

	ExtentReports extentReport;
	ExtentTest extentTest;

	@Override
	public void onStart(ITestContext context) {
		
		extentReport = ExtentReporter.generateExtentReport();
	
	}

	@Override
	public void onTestStart(ITestResult result) {
		
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.INFO,result.getName()+" started executing");
		extentTest.assignDevice("Mobile");
		 extentTest.assignAuthor("Ganesh");
		 extentTest.assignCategory("Smoke");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		extentTest.log(Status.PASS,result.getName()+" got successfully executed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		WebDriver driver = null;
		
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		
		String destinationScreenshotPath = Utilities.captureScreenshot(driver,result.getName());
		
		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.FAIL,result.getName()+" got failed");

		
		JiraPolicy jiraPolicy = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(JiraPolicy.class);
			boolean isTicketReady = jiraPolicy.logTicketReady();
			if (isTicketReady) {
				// raise jira ticket:
				System.out.println("is ticket ready for JIRA: " + isTicketReady);
			
				JiraServiceProvider jiraSp = new JiraServiceProvider("https://jira.vahanacloud.com",
					"ganesh.timande@decimal.co.in", "Deep@27#1987", "RTB");
				String issueSummary = result.getMethod().getConstructorOrMethod().getMethod().getName()+" "+ "Failed";
						
				String issueDescription = result.getThrowable().getMessage() + "\n";
				issueDescription.concat(ExceptionUtils.getFullStackTrace(result.getThrowable()));
				
				String Methodname =result.getMethod().getConstructorOrMethod().getMethod().getName();
				
			
					try {
						jiraSp.createJiraTicket("Feature", issueSummary, issueDescription,Methodname);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();  
					}
			}
					
			
		}
	

	@Override
	public void onTestSkipped(ITestResult result) {
		
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.SKIP, result.getName()+" got skipped");
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
		extentReport.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
	
}

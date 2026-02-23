package com.comcast.listeners;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.comcast.crm.*;
import com.comcast.crm.basecls.BaseClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ListenersPractice implements ITestListener,ISuiteListener 
{
	ExtentReports report;
	ExtentTest test;
	@Override
	public void onStart(ISuite suite) 
	{
		ExtentSparkReporter spark = new ExtentSparkReporter("./LowLevelReport/VtigerReport.html");
		spark.config().setDocumentTitle("AdvanceReports");
		spark.config().setReportName("VTigerReport");
		spark.config().setTheme(Theme.STANDARD);
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("Laptop", "Lenovo");
		report.setSystemInfo("Os", "Window");
		report.setSystemInfo("Browser", "Chrome");	
	}

	

	@Override
	public void onTestStart(ITestResult result) {
		String testCase = result.getMethod().getMethodName();
		//Reporter.log(testCase+"Excecution Started");
		test =report.createTest(testCase);
		test.log(Status.INFO,testCase+"Excecution Started"); 
	}
	
	@Override
	public void onFinish(ISuite suite)
	{
		report.flush();
	}
	@Override
	public void onTestSuccess(ITestResult result) {
		String testCase = result.getMethod().getMethodName();
		//Reporter.log(testCase+"Excecution Success  ");
		test.log(Status.PASS, testCase+"Excecution passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testCase = result.getMethod().getMethodName();
		//Reporter.log(testCase+"Excecution Failed");
		test.log(Status.FAIL,testCase+"Excecution Failed");
		WebDriver driver=BaseClass.swDriver;
		TakesScreenshot ts = (TakesScreenshot)driver;
		//File src = ts.getScreenshotAs(OutputType.FILE);
		String time = new Date().toString().replace(" ","_").replace(":", "_");
		//File dest = new File("./screenshot/"+testCase+"+"+time+".png");
		String src = ts.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(src);
//		try {
//			FileHandler.copy(src, dest);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testCase = result.getMethod().getMethodName();
		//Reporter.log(testCase+"Excecution skipped");
		test.log(Status.SKIP,testCase+"Excecution Skipped");
	}
}

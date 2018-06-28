package org.sayem.api.json.placeholder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;


import com.beust.jcommander.Parameter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.testng.ITestContext;

public abstract class BaseTest {

	public static ExtentReports report;
	public static ExtentTest logger;
	public static ExtentTest parent;
	public static String CampName = "";
	public static String testcasefile;
	public static String className;
	public static String id;
	private String testcase;
	//****************************Extent report
	
	public BaseTest(String string) {
		// TODO Auto-generated constructor stub
		this.testcase = string;
	}



	@BeforeSuite(alwaysRun = true)
	public void reportSetup() throws IOException {

		report = new ExtentReports("target/surefire-reports/TestReport.html");
		report.loadConfig(new File("Extent-Config.xml"));
	}
	
	
	
//
//	@BeforeTest
//	public void generateNode(final ITestContext testContext) {
//		  	
//			parent = report.startTest("Testcases for :" );	
//			
//
//	}
	
	
//	@AfterTest(alwaysRun = true)
//	public void closenode() {
//		
//		report.flush();
//		
//	}

	@AfterMethod(alwaysRun = true)
	public void generateReport(ITestResult result) throws IOException {
		System.out.println("@After Method");
		try {
			if (result.getStatus() == ITestResult.FAILURE) {
				String TestCaseName = this.getClass().getSimpleName() + " Failed";
				String methodname = result.getMethod().getMethodName();
				logger.log(LogStatus.FAIL, methodname);
				logger.log(LogStatus.FAIL, TestCaseName );
				logger.log(LogStatus.FAIL, result.getThrowable());
			} else if (result.getStatus() == ITestResult.SUCCESS) {
				logger.log(LogStatus.PASS, this.getClass().getSimpleName() + " Test Case Success and Verified");
			} else if (result.getStatus() == ITestResult.SKIP) {
				logger.log(LogStatus.SKIP, this.getClass().getSimpleName() + " Test Case Skipped");
			}
			report.endTest(logger);
			report.flush();
		} catch (Throwable t) {
			logger.log(LogStatus.ERROR, t.fillInStackTrace());
		}

	}


	//***************** intialising  browser
		
	@BeforeMethod
	public void setup(final ITestContext testContext, ITestResult result) throws Exception {
		
		logger = report.startTest(testcase).assignCategory(testContext.getName());
		logger.log(LogStatus.INFO, "Log for Each Step in Test Case");
		
	}
	
//	@AfterClass
//	public void teardown()
//	{
//		report.endTest(logger);
//	}


	
	
	
	
	


}

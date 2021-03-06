package com.extentListeners;


import java.util.Arrays;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ExtentListeners implements ITestListener {

	static Date d = new Date();
	static String fileName = "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
//	static String fileName = "Extent.html";
	private static ExtentReports extent = ExtentManager.createInstance(System.getProperty("user.dir")+"\\extentReports\\"+fileName);
	public static ExtentTest test;
	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();
	

	public void onTestStart(ITestResult result) {

		System.out.println("Test start Listener: " +result.getMethod().getMethodName());
		test = extent.createTest(result.getTestClass().getName()+"     @TestCase : "+result.getMethod().getMethodName());
        testReport.set(test);
        

	}

	public void onTestSuccess(ITestResult result) {

	//	System.out.println("Test success Listener: "+result.getMethod().getMethodName());
		String methodName=result.getMethod().getMethodName();
		String logText="<b>"+"TEST CASE:- "+ methodName+ " PASSED"+"</b>";		
		Markup m=MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		testReport.get().pass(m);
		

	}

	public void onTestFailure(ITestResult result) {

	//	System.out.println("Test failure Listener: "+result.getMethod().getMethodName());
		
		String methodName = result.getMethod().getMethodName();
		
		String excepionMessage = result.getThrowable().getMessage();
		
		
		testReport.get().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
				+ "</font>" + "</b >" + "</summary>" +excepionMessage.replaceAll(",", "<br>")+"</details>"+" \n");
		
		excepionMessage=Arrays.toString(result.getThrowable().getStackTrace()).replaceAll(",", "\n");
		System.out.println("===========");
		System.out.println("Exception from Listener : "+excepionMessage);
		System.out.println("===========");
//		testReport.get().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
//				+ "</font>" + "</b >" + "</summary>" +excepionMessage.replaceAll(",", "<br>")+"</details>"+" \n");
//		
		try {

			ExtentManager.captureScreenshot();
			testReport.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
					MediaEntityBuilder.createScreenCaptureFromPath(ExtentManager.screenshotName)
							.build());
		} catch (Exception e) {

		}
		String logText="<b>"+"TEST CASE:- "+ methodName+ " FAILED"+"</b>";		
		
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
	//	testReport.get().log(Status.FAIL, m);
		testReport.get().fail(m);

	}

	public void onTestSkipped(ITestResult result) {
	//	System.out.println("Test skip Listener: "+result.getMethod().getMethodName());
		
		String methodName=result.getMethod().getMethodName();
		String logText="<b>"+"TEST CASE:- "+ methodName+ " SKIPPED"+"</b>";		
		Markup m=MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		testReport.get().skip(m);

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {

		

	}

	public void onFinish(ITestContext context) {

		if (extent != null) {
			
			System.out.println("Extent report: "+fileName); 
			extent.flush();
		}

	}

}

package assignment.listeners;

import assignment.base.TestBase;
import assignment.utilities.ExtentManager;
import assignment.utilities.TestUtil;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

public class CustomListeners extends TestBase implements ITestListener,ISuiteListener {

	static Date d = new Date();
	static String fileName = "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
	static String messageBody;
	private static final ExtentReports extent = ExtentManager.createInstance(System.getProperty("user.dir")+"\\reports\\"+fileName);
	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<>();
	

	public void onTestStart(ITestResult result) {

	
		ExtentTest test = extent.createTest(result.getTestClass().getName()+"     @TestCase : "+result.getMethod().getMethodName());
        testReport.set(test);
        

	}

	public void onTestSuccess(ITestResult result) {

		
		String methodName=result.getMethod().getMethodName();
		String logText="<b>"+"TEST CASE:- "+ methodName.toUpperCase()+ " PASSED"+"</b>";		
		Markup m=MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		//testReport.get().pass(m);
		try {
			TestUtil.captureScreenshot();
			testReport.get().pass("<b>" + "<font color=" + "green>" + "Screenshot of success" + "</font>" + "</b>",
					MediaEntityBuilder.createScreenCaptureFromPath(TestUtil.screenshotName)
							.build());
		} catch (IOException ignored) {

		}
	}

	public void onTestFailure(ITestResult result) {
		String exceptionMessage=Arrays.toString(result.getThrowable().getStackTrace());
		testReport.get().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
				+ "</font>" + "</b >" + "</summary>" +exceptionMessage.replaceAll(",", "<br>")+"</details>"+" \n");
		
		try {

			TestUtil.captureScreenshot();
			testReport.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
					MediaEntityBuilder.createScreenCaptureFromPath(TestUtil.screenshotName)
							.build());
		} catch (IOException ignored) {

		}
		
		String failureLogg="TEST CASE FAILED";
		Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
		testReport.get().log(Status.FAIL, m);

	}

	public void onTestSkipped(ITestResult result) {
		String methodName=result.getMethod().getMethodName();
		String logText="<b>"+"Test Case:- "+ methodName+ " Skipped"+"</b>";		
		Markup m=MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		testReport.get().skip(m);

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {

		

	}

	public void onFinish(ISuite arg0) {
		

		
	}

	public void onStart(ISuite arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {


		if (extent != null) {

			extent.flush();
		}
		
	}


}

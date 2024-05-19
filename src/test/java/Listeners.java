import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import extentmanagers.ExtentManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.DriverFactory;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {
        ExtentReports extentReports;
        ExtentTest extentTest;
        ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<ExtentTest>();
        BaseTest baseTest;

        public WebDriver getDriver() {
            return DriverFactory.getDriver();
        }

        @Override
        public void onTestStart(ITestResult result) {
            extentReports = ExtentManager.getReporterObject();
            extentTest = extentReports.createTest(result.getMethod().getMethodName());
            extentTestThreadLocal.set(extentTest);
        }

        @Override
        public void onTestSuccess(ITestResult result) {

            extentTest.log(Status.PASS, "Test case " + result.getMethod().getMethodName() + " is passed");
        }

        @Override
        public void onTestFailure(ITestResult result) {
            extentTest.fail(result.getThrowable());
            WebDriver driver = getDriver();
            try {
                driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
            String filePath = null;
            try {
                filePath = getScreenShot(result.getMethod().getMethodName(), driver);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            extentTest.addScreenCaptureFromBase64String(filePath, result.getMethod().getMethodName());
        }

        @Override
        public void onFinish(ITestContext context) {
            if (extentReports != null) {
                extentReports.flush();
            }
        }
    }

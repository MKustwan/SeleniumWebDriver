package extentmanagers;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.util.Date;

public class ExtentManager {
    public static Date date = new Date();
    public static String reportsPath = date.toString().replace(" ", "_").replace(":", "_");

    public static ExtentReports getReporterObject() {
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("./reports/" + reportsPath);
        extentSparkReporter.config().setReportName("Web Automation Results");
        extentSparkReporter.config().setDocumentTitle("Test Results");
        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        extentReports.setSystemInfo("Tester", "Magdalena Kustwan");
        return extentReports;
    }
}

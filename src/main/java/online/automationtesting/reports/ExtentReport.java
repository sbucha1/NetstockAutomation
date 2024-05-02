package online.automationtesting.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
    private static ExtentReports extent;
    private static ExtentTest test;

    public static void createReportInstance() {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test-output/TestResults.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    public static void createTest(String testName, String testDescription) {
        // Create a test with the given name and description
        test = extent.createTest(testName, testDescription);
    }

    public static void logTestStatus(Status status, String message) {
        // Log the status and message for the test
        test.log(status, message);
    }

    public static void flushReport() {
        // Flush the ExtentReports instance
        extent.flush();
    }
}

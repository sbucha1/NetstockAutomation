package online.automationtesting.utils;

import com.aventstack.extentreports.Status;
import online.automationtesting.reports.ExtentReport;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.testng.Assert.assertEquals;

public class Utilities extends BaseTest {
    public void validateText(String actual, String expected) {
        assertEquals(actual, expected);
    }

    public void assertTextEquals(String actual, String expected) {
        if (actual.contains(expected)) {
            ExtentReport.logTestStatus(Status.PASS, "Actual value is '" + actual + "', Expected value is '" + expected + "'");
        } else {
            ExtentReport.logTestStatus(Status.FAIL, "Actual value is '" + actual + "', Expected value is '" + expected + "'");
        }
    }

    public static void takeScreenshot(WebDriver driver, String filename) {
        // Check if the driver supports taking screenshots
        if (driver instanceof TakesScreenshot) {
            // Convert WebDriver instance to TakesScreenshot
            TakesScreenshot screenshotDriver = (TakesScreenshot) driver;

            // Take screenshot as File
            File screenshotFile = screenshotDriver.getScreenshotAs(OutputType.FILE);

            try {

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
                String timestamp = dateFormat.format(new Date());

                // Define the screenshot filename with timestamp
                filename = "screenshot_" + timestamp + ".png";

                // Define the path where the screenshot will be saved
                Path destinationPath = Paths.get("test-output/screenshots/", filename);

                // Create directories if they don't exist
                Files.createDirectories(destinationPath.getParent());

                // Move the screenshot file to the destination path
                Files.move(screenshotFile.toPath(), destinationPath);

                System.out.println("Screenshot saved successfully: " + destinationPath);
            } catch (IOException e) {
                System.out.println("Failed to save screenshot: " + e.getMessage());
            }
        } else {
            System.out.println("WebDriver does not support taking screenshots");
        }
    }


}

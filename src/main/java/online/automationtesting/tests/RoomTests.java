package online.automationtesting.tests;

import com.aventstack.extentreports.Status;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import io.github.bonigarcia.wdm.WebDriverManager;
import online.automationtesting.pages.HomePage;
import online.automationtesting.reports.ExtentReport;
import online.automationtesting.utils.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;

import java.io.FileReader;
import java.io.IOException;

public class RoomTests extends Utilities {

    public static HomePage homePage;
    public WebDriver driver;

    @BeforeSuite
    public void set() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterSuite
    public void tear() {
        driver.quit();
    }

    @BeforeClass
    public void setUp() {
        ExtentReport.createReportInstance();

        homePage = new HomePage(driver);
        driver.get("https://automationintesting.online/");
    }

    @Test(description = "Make a booking with blank email")
    public void testEmailIsMissing() throws IOException, CsvValidationException, InterruptedException {
        ExtentReport.createTest("Make booking a room with blank email", "Make booking with missing data");

        ExtentReport.logTestStatus(Status.INFO, "Click Book This Room button");
        homePage.clickBookThisRoomButton();

        String csvFile = "src/main/resources/testData.csv";
        String[] cell;
        CSVReader reader = new CSVReader(new FileReader(csvFile));

        reader.readNext();
        while ((cell = reader.readNext()) != null) {

            String firstname = cell[0];
            String lastname = cell[1];
            String email = cell[2];
            String phone = cell[3];

            homePage.enterFirstName(firstname);
            ExtentReport.logTestStatus(Status.INFO, "Enter firstname");
            homePage.enterLastName(lastname);
            ExtentReport.logTestStatus(Status.INFO, "Click lastname");
            homePage.enterEmail(email);
            ExtentReport.logTestStatus(Status.INFO, "Click email");
            homePage.enterPhone(phone);
            ExtentReport.logTestStatus(Status.INFO, "Click phone");

        }

        selectCalendarBookingDates();
        ExtentReport.logTestStatus(Status.INFO, "Select booking dates on calendar");

        homePage.clickBookButton();
        ExtentReport.logTestStatus(Status.INFO, "Click book button");
        Thread.sleep(5000);
        takeScreenshot(driver,"testEmailIsMissing");

        String actual = homePage.getEmailErrorText();
        String expected = "must not be empty";
        assertTextEquals(actual, expected);

    }

    @Test(description = "Make a successfully booking")
    public void testSuccessfulBooking() {
        ExtentReport.createTest("Make a successful booking", "Make a booking with valid data");

        homePage.clickBookThisRoomButton();
        ExtentReport.logTestStatus(Status.INFO, "Click Book This Room Button");
        homePage.enterFirstName("Sheldon");
        ExtentReport.logTestStatus(Status.INFO, "Enter firstname");
        homePage.enterLastName("Coops");
        ExtentReport.logTestStatus(Status.INFO, "Enter last");
        homePage.enterEmail("Sheldon@mailme.com");
        ExtentReport.logTestStatus(Status.INFO, "Enter email");
        homePage.enterPhone("01189652177");
        ExtentReport.logTestStatus(Status.INFO, "Enter phone");

        selectCalendarBookingDates();
        ExtentReport.logTestStatus(Status.INFO, "Select booking dates on calendar");

        homePage.clickBookButton();
        ExtentReport.logTestStatus(Status.INFO, "Click Book button");

        String actual = homePage.getSuccessText();
        String expected = "Booking Successful!";

        assertTextEquals(actual, expected);


        homePage.clickSuccessfulBookingCloseButton();
        ExtentReport.logTestStatus(Status.INFO, "Click Close button");
    }


    @Test(description = "Make a with blank firstname booking")
    public void testFirstnameBlankBooking() {
        ExtentReport.createTest("Valid Data Test", "Make a with blank firstname booking");

        homePage.clickBookThisRoomButton();
        ExtentReport.logTestStatus(Status.INFO, "Click Book This Room Button");
        homePage.enterFirstName("");
        ExtentReport.logTestStatus(Status.INFO, "Leave firstname blank");
        homePage.enterLastName("Coops");
        ExtentReport.logTestStatus(Status.INFO, "Enter last");
        homePage.enterEmail("Sheldon@mailme.com");
        ExtentReport.logTestStatus(Status.INFO, "Enter email");
        homePage.enterPhone("01189652177");
        ExtentReport.logTestStatus(Status.INFO, "Enter phone");

        selectCalendarBookingDates();
        ExtentReport.logTestStatus(Status.INFO, "Select booking dates on calendar");

        homePage.clickBookButton();
        ExtentReport.logTestStatus(Status.INFO, "Click Book button");

        String actual = homePage.getAlertDangerText();
        String expected = "Firstname should not be blank";

        assertTextEquals(actual, expected);

    }


    @Test(description = "Make a with blank lastname booking")
    public void testLastnameBlankBooking() {
        ExtentReport.createTest("Make a with blank lastname booking", "Blank lastname");

        homePage.clickBookThisRoomButton();
        ExtentReport.logTestStatus(Status.INFO, "Click Book This Room Button");

        homePage.enterFirstName("Sheldon");
        ExtentReport.logTestStatus(Status.INFO, "Enter firstname");
        homePage.enterLastName("");
        ExtentReport.logTestStatus(Status.INFO, "Leave lastname blank");
        homePage.enterEmail("Sheldon@mailme.com");
        ExtentReport.logTestStatus(Status.INFO, "Enter email");
        homePage.enterPhone("01189652177");
        ExtentReport.logTestStatus(Status.INFO, "Enter phone");

        selectCalendarBookingDates();
        ExtentReport.logTestStatus(Status.INFO, "Select booking dates on calendar");

        homePage.clickBookButton();
        ExtentReport.logTestStatus(Status.INFO, "Click Book button");

        String actual = homePage.getAlertDangerText();
        String expected = "Lastname should not be blank";

        assertTextEquals(actual, expected);

    }


    @Test
    public void deleteBooking() {
        //no functionality
    }

    @AfterClass
    public void tearDown() {
        ExtentReport.flushReport();
    }

    public void selectCalendarBookingDates() {

        WebElement startDateElement = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[4]/div/div[2]/div[2]/div/div[2]/div[4]/div[1]/div[4]"));
        WebElement endDateElement = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[4]/div/div[2]/div[2]/div/div[2]/div[4]/div[1]/div[6]"));

        Actions actions = new Actions(driver);
        actions.dragAndDropBy(startDateElement, 0, 100)
                .build()
                .perform();

    }

}

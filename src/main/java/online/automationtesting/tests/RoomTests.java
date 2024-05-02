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
    public void testEmailIsMissing() throws IOException, CsvValidationException {
        ExtentReport.createTest("Invalid Data Test", "Test booking a room with invalid data");

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
            homePage.enterLastName(lastname);
            homePage.enterEmail(email);
            homePage.enterPhone(phone);

        }

        selectCalendarBookingDates();
        homePage.clickBookButton();

        validateText(homePage.getEmailErrorText(), "must not be empty");
        ExtentReport.logTestStatus(Status.PASS, "Room booked successfully");

    }

    @Test(description = "Make a successfully booking")
    public void testSuccessfulBooking() {
        ExtentReport.createTest("Valid Data Test", "Make a successfully booking");

        homePage.clickBookThisRoomButton();
        homePage.enterFirstName("Sheldon");
        homePage.enterLastName("Coops");
        homePage.enterEmail("Sheldon@mailme.com");
        homePage.enterPhone("01189652177");

        selectCalendarBookingDates();

        homePage.clickBookButton();

        validateText(homePage.getSuccessText(), "Thanks for getting in touch Sheldon!");

    }

    @Test
    public void deleteBooking() {

    }

    @AfterClass
    public void tearDown() {
        ExtentReport.flushReport();
    }

    public void selectCalendarBookingDates() {
        WebElement startDateElement = driver.findElement(By.xpath("//button[contains(text(),'15')]"));
        WebElement endDateElement = driver.findElement(By.xpath("//button[contains(text(),'23')]"));

        // Perform left-click and drag action
        Actions actions = new Actions(driver);
        actions
                .clickAndHold(startDateElement)
                .moveToElement(startDateElement)
                .moveByOffset(20,80)
                .dragAndDrop(startDateElement,endDateElement)
                .release()
                .build()
                .perform() ;



    }

}

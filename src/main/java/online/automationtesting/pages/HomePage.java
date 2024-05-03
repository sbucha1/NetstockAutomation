package online.automationtesting.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    protected WebElement getFirstNameField() {
        return driver.findElement(By.name("firstname"));
    }

    protected WebElement getLastNameField() {
        return driver.findElement(By.name("lastname"));
    }

    protected WebElement getEmailField() {
        return driver.findElement(By.name("email"));
    }

    protected WebElement getPhoneField() {
        return driver.findElement(By.name("phone"));
    }

    protected WebElement getSubjectField() {
        return driver.findElement(By.id("subject"));
    }

    protected WebElement getMessageField() {
        return driver.findElement(By.id("description"));
    }

    protected WebElement getSubmitButton() {
        return driver.findElement(By.id("submitContact"));
    }

    protected WebElement getBookButton() {
        return driver.findElement(By.xpath("//button[contains(text(),'Book')]"));
    }

    protected WebElement getCancelButton() {
        return driver.findElement(By.xpath("//button[contains(text(),'Cancel')]"));
    }

    protected WebElement getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div[2]/div/div[5]/div[2]/form/div[6]/p[2]")));
    }

    protected WebElement getEmailErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div[2]/div/div[4]/div/div[2]/div[3]/div[5]/p")));
    }

    protected WebElement getAlertDangerMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-danger']")));
    }

    protected WebElement getSuccessfulMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]//h3")));
    }

    public WebElement getCalendarDay() {
        return driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[4]/div/div[2]/div[2]/div/div[2]/div[5]/div[2]/div/div[2]"));

    }

    protected WebElement getBookThisRoomButton() {
        return driver.findElement(By.xpath("//button[contains(text(),'Book this room')]"));
    }

    protected WebElement getSuccessfulBookingCloseButton() {
        return driver.findElement(By.xpath("//button[text()='Close']]"));
    }

    public void clickBookThisRoomButton() {
        getBookThisRoomButton().click();
    }

    public void clickSuccessfulBookingCloseButton() {
        getSuccessfulBookingCloseButton().click();
    }
    public void enterFirstName(String name) {
        getFirstNameField().sendKeys(name);
    }

    public void enterLastName(String name) {
        getLastNameField().sendKeys(name);
    }

    public void enterEmail(String email) {
        getEmailField().sendKeys(email);
    }

    public void enterPhone(String phone) {
        getPhoneField().sendKeys(phone);
    }

    public void enterSubject(String subject) {
        getSubjectField().sendKeys(subject);
    }

    public void enterMessage(String message) {
        getMessageField().sendKeys(message);
    }

    public String getErrorText() {
        return getErrorMessage().getText();

    }

    public String getEmailErrorText() {
        return getEmailErrorMessage().getText();

    }

    public String getAlertDangerText() {
        return getAlertDangerMessage().getText();

    }

    public String getSuccessText() {
        return getSuccessfulMessage().getText();

    }

    public void clickSubmitButton() {
        getSubmitButton().click();
    }


    public void clickBookButton() {
        getBookButton().click();
    }

    public void clickCalendarDay() {
        getCalendarDay().click();
    }

}

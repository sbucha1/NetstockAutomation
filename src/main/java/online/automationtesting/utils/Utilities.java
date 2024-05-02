package online.automationtesting.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.testng.Assert.assertEquals;

public class Utilities extends BaseTest {
    public void validateText(String actual, String expected) {
        assertEquals(actual, expected);
    }


}

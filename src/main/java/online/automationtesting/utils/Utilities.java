package online.automationtesting.utils;

import static org.testng.Assert.assertEquals;

public class Utilities {
    public void validateText(String actual, String expected) {
        assertEquals(actual, expected);
    }
}

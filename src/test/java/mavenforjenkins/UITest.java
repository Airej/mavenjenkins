package mavenforjenkins;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class UITest {

    private WebDriver driver; // Declaring driver at the class level

    @Parameters("Browser")
    @BeforeMethod
    public void setUp(String browserName) {
        System.out.println("Parameter value is " + browserName);

        if (browserName.contains("Chrome")) {
            ChromeOptions opt = new ChromeOptions();
            opt.addArguments("--headless");
            opt.addArguments("--no-sandbox");
            opt.addArguments("--disable-dev-shm-usage");
            driver = new ChromeDriver(opt);
        } else if (browserName.contains("Edge")) {
            driver = new EdgeDriver();
        }

        if (driver != null) {
            driver.manage().window().maximize();
        } else {
            throw new IllegalStateException("Driver object is null. Failed to initialize WebDriver.");
        }
    }

    @Test
    public void verifyTitle() {
        if (driver != null) {
            driver.get("https://opensource-demo.orangehrmlive.com/");
            Assert.assertTrue(driver.getTitle().contains("Orange"), "Title does not match");
        } else {
            throw new IllegalStateException("Driver object is null. Cannot execute test.");
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

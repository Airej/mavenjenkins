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
        driver.manage().window().maximize();
    }

    @Test
    public void verifyTitle() {
        driver.get("https://opensource-demo.orangehrmlive.com/");
        Assert.assertTrue(driver.getTitle().contains("Orange"), "Title does not match");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

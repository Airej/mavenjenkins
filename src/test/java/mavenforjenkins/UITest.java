package mavenforjenkins;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class UITest {

    @Parameters("Browser")
    @Test
    public void startBrowser(String browserName) {
        System.out.println("Parameter value is " + browserName);
        WebDriver driver = initializeDriver(browserName);

        if (driver != null) {
            driver.get("https://opensource-demo.orangehrmlive.com/");
            Assert.assertTrue(driver.getTitle().contains("Orange"), "Title does not match");
            driver.quit();
        } else {
            Assert.fail("Driver initialization failed. Check browser parameter.");
        }
    }

    private WebDriver initializeDriver(String browserName) {
        WebDriver driver = null;

        if (browserName.contains("chrome")) {
            ChromeOptions opt = new ChromeOptions();
            opt.addArguments("--headless");
            opt.addArguments("--no-sandbox");
            opt.addArguments("--disable-dev-shm-usage");
            driver = new ChromeDriver(opt);
            driver.manage().window().maximize(); // Maximize the window after initialization
        } else if (browserName.contains("Edge")) {
            driver = new EdgeDriver();
            driver.manage().window().maximize(); // Maximize the window after initialization
        }

        return driver;
    }
}

package hooks;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.time.Duration;

public class Hooks {

    public static WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().browserVersion("147").setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @After
    public void tearDown(Scenario scenario) {
        if (driver != null) {
            if (scenario.isFailed()) {
                takeScreenshot(scenario.getName());
            }
            driver.quit();
            driver = null;
        }
    }

    private void takeScreenshot(String scenarioName) {
        try {
            File screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);
            String sanitized = scenarioName.replaceAll("[^a-zA-Z0-9]", "_");
            Path dest = Paths.get("screenshots/" + sanitized + "_"
                    + System.currentTimeMillis() + ".png");
            Files.createDirectories(dest.getParent());
            Files.copy(screenshot.toPath(), dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
package hooks;

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
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hooks {

    public static WebDriver driver;

    @Before
    public void setUp() {
        suppressCdpWarnings();
        System.setProperty(
            "webdriver.chrome.driver",
            "C:\\Users\\amine\\.cache\\selenium\\chromedriver\\win64\\147.0.7727.117\\chromedriver.exe"
        );
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    private void suppressCdpWarnings() {
        Logger cdpFinder = Logger.getLogger(
            "org.openqa.selenium.devtools.CdpVersionFinder");
        cdpFinder.setLevel(Level.OFF);
        cdpFinder.setUseParentHandlers(false);

        Logger devtools = Logger.getLogger("org.openqa.selenium.devtools");
        devtools.setLevel(Level.OFF);
        devtools.setUseParentHandlers(false);

        Logger chromium = Logger.getLogger(
            "org.openqa.selenium.chromium.ChromiumDriver");
        chromium.setLevel(Level.OFF);
        chromium.setUseParentHandlers(false);
    }

    @After
    public void tearDown(Scenario scenario) {
        if (driver != null) {
            if (scenario.isFailed()) {
                takeScreenshot(scenario);
            }
            driver.quit();
            driver = null;
        }
    }

    private void takeScreenshot(Scenario scenario) {
        try {
            File screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);
            String sanitized = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_");
            Path dest = Paths.get("screenshots/" + sanitized + "_"
                    + System.currentTimeMillis() + ".png");
            Files.createDirectories(dest.getParent());
            Files.copy(screenshot.toPath(), dest);
            scenario.attach(Files.readAllBytes(dest), "image/png", sanitized);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
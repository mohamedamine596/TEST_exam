import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SimpleTest {
    public static void main(String[] args) {
        System.out.println("Setting up ChromeDriver...");
        WebDriverManager.chromedriver().browserVersion("147").setup();
        System.out.println("ChromeDriver setup done");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--remote-allow-origins=*");

        System.out.println("Opening Chrome...");
        WebDriver driver = new ChromeDriver(options);
        System.out.println("Chrome opened!");

        driver.get("https://google.com");
        System.out.println("Title: " + driver.getTitle());
        driver.quit();
        System.out.println("Done!");
    }
}
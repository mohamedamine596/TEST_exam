package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class DropdownPage extends BasePage {

        private static final String URL =
            "https://www.selenium.dev/selenium/web/selectPage.html";
        private By simpleSelect = By.id("selectWithoutMultiple");
        private By visibilitySelect = By.id("visibility");

    public DropdownPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(URL);
        waitForElement(simpleSelect);
    }

    public void selectSimpleOption(String option) {
        Select select = new Select(waitForElement(simpleSelect));
        select.selectByVisibleText(option);
    }

    public String getSelectedSimpleOption() {
        Select select = new Select(waitForElement(simpleSelect));
        return select.getFirstSelectedOption().getText();
    }

    public void trySelectVisibilityOption(String option) {
        try {
            Select select = new Select(waitForElement(visibilitySelect));
            select.selectByVisibleText(option);
        } catch (Exception e) {
            // disabled options may not be selectable
        }
    }

    public String getSelectedVisibilityOption() {
        Select select = new Select(waitForElement(visibilitySelect));
        return select.getFirstSelectedOption().getText();
    }
}
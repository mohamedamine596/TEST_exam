package pages;

import org.openqa.selenium.*;

public class FormPage extends BasePage {

    // Using a public test form: https://www.selenium.dev/selenium/web/web-form.html
    private static final String URL =
            "https://www.selenium.dev/selenium/web/web-form.html";
    private By textInput = By.id("my-text-id");
    private By passwordInput = By.name("my-password");
    private By textareaInput = By.name("my-textarea");
    private By disabledInput = By.name("my-disabled");
    private By readonlyInput = By.name("my-readonly");
    private By submitButton = By.cssSelector("button[type='submit']");
    private By successMessage = By.id("message");

    public FormPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(URL);
        waitForElement(textInput);
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public void enterText(String text) {
        if (text != null) {
            type(textInput, text);
        }
    }

    public void enterPassword(String password) {
        if (password != null) {
            type(passwordInput, password);
        }
    }

    public void enterTextarea(String textarea) {
        if (textarea != null) {
            type(textareaInput, textarea);
        }
    }

    public void clearAllFields() {
        waitForElement(textInput).clear();
        waitForElement(passwordInput).clear();
        waitForElement(textareaInput).clear();
    }

    public void attemptTypeDisabled(String text) {
        try {
            waitForElement(disabledInput).clear();
            waitForElement(disabledInput).sendKeys(text);
        } catch (Exception e) {
            // disabled fields are not editable
        }
    }

    public void attemptTypeReadonly(String text) {
        try {
            waitForElement(readonlyInput).clear();
            waitForElement(readonlyInput).sendKeys(text);
        } catch (Exception e) {
            // readonly fields should not be editable
        }
    }

    public String getDisabledValue() {
        return waitForElement(disabledInput).getAttribute("value");
    }

    public String getReadonlyValue() {
        return waitForElement(readonlyInput).getAttribute("value");
    }

    public void fillForm(String text, String password, String textarea) {
        if (text != null) type(textInput, text);
        if (password != null) type(passwordInput, password);
        if (textarea != null) type(textareaInput, textarea);
    }

    public void submitForm() {
        click(submitButton);
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }

    public boolean isSuccessDisplayed() {
        return isDisplayed(successMessage);
    }
}
package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.AbstractComponents;
import utils.DriverFactory;

public class EnterNewPasswordPageObject extends AbstractComponents {
    private @FindBy(xpath = "//input[@type='email']")
    WebElement emailInput;
    private @FindBy(id = "userPassword")
    WebElement passwordInput;
    private @FindBy(id="confirmPassword")
    WebElement confirmPasswordInput;
    private @FindBy(xpath = "//button[@type='submit']")
    WebElement buttonSubmit;

    public EnterNewPasswordPageObject() {
        PageFactory.initElements(getDriver(), this);
    }

    public WebDriver getDriver() {
        return DriverFactory.getDriver();
    }
    public LoginPageObject newPassword(String email, String password){
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        confirmPasswordInput.sendKeys(password);
        buttonSubmit.click();
        return new LoginPageObject();
    }
}

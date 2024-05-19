package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.AbstractComponents;
import utils.DriverFactory;

public class LoginPageObject extends AbstractComponents {
    private @FindBy(id = "userEmail")
    WebElement userEmail;
    private @FindBy(id = "userPassword")
    WebElement userPassword;
    private @FindBy(id = "login")
    WebElement loginButton;
    private @FindBy(xpath = "//div[text()='*Enter Valid Email']")
    WebElement errorValidationText;
    private @FindBy(xpath = "//a[@class='forgot-password-link']")
    WebElement forgotPasswordLink;
    private @FindBy(xpath = "//h1[text()='Log in']")
    WebElement logInText;

    public LoginPageObject() {
        PageFactory.initElements(getDriver(), this);
    }

    public WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    public ProductsPageObject logIn(String userEmailText, String passwordText) {
        userEmail.sendKeys(userEmailText);
        userPassword.sendKeys(passwordText);
        loginButton.click();
        return new ProductsPageObject();
    }

    public String invalidLogIn(String userEmailText, String passwordText) {
        userEmail.sendKeys(userEmailText);
        userPassword.sendKeys(passwordText);
        loginButton.click();
        return errorValidationText.getText();
    }

    public EnterNewPasswordPageObject setNewPassword() {
        forgotPasswordLink.click();
        return new EnterNewPasswordPageObject();
    }
    public String getLogInText() {
        return logInText.getText();
    }
}

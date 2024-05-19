package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import utils.AbstractComponents;
import utils.DriverFactory;

import java.util.List;

public class BasketPageObject extends AbstractComponents {
    private @FindBys(@FindBy(xpath = "//div[@class='cart']"))
    List<WebElement> listOfProductsInBasket;
    private @FindBy(xpath = "//button[text()='Checkout']")
    WebElement checkoutButton;
    private @FindBy(xpath = "//i[@class='fa fa-trash-o']")
    WebElement trashIcon;
    private @FindBy (xpath = "//h1[text()='No Products in Your Cart !']")
    WebElement noProductsText;

    public BasketPageObject() {
        PageFactory.initElements(getDriver(), this);
    }

    public WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    public VerificationPageObject verificationOfProducts() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", checkoutButton);
        waitForElementToBeClicable(checkoutButton);
        js.executeScript("arguments[0].click()", checkoutButton);
        return new VerificationPageObject();
    }
    public BasketPageObject removeProductFromBasket() {
        trashIcon.click();
        return this;
    }
    public String noProductInBasketText(){
        return noProductsText.getText();
    }
}

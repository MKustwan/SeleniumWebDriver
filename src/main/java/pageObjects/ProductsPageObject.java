package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import utils.AbstractComponents;
import utils.DriverFactory;

import java.time.Duration;
import java.util.List;

public class ProductsPageObject extends AbstractComponents {
    private @FindBys(@FindBy(xpath = "//div[@class='card-body']"))
    List<WebElement> cardBody;
    private @FindBy(id = "toast-container")
    WebElement msgOfSuccessfulAddingProduct;

    private @FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
    WebElement basketIcon;
    private @FindBy(css = ".ng-tns-c31-1.ng-star-inserted")
    WebElement ngAnimating;
    private @FindBy(xpath = "//div[@class='py-2 border-bottom ml-3']/input[@formcontrolname='productName']")
    WebElement searchField;
    private @FindBy(xpath = "//div[@class='card-body']//b")
    WebElement chosenProduct;
    public WebDriver driver;

    public ProductsPageObject() {
        PageFactory.initElements(getDriver(), this);
    }

    public WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    public BasketPageObject addProductToBasket(String productNameText) throws InterruptedException {
        WebElement chosenProduct = cardBody.stream()
                .filter
                        (product -> product.findElement(By.cssSelector("b"))
                                .getText()
                                .equalsIgnoreCase(productNameText))
                .findFirst()
                .orElse(null);
        chosenProduct.findElement(By.xpath("//button[@class='btn w-10 rounded']")).click();
        waitForVisibilityOfElement(msgOfSuccessfulAddingProduct);
        waitForInVisibilityOfElement(ngAnimating);
        basketIcon.click();
        return new BasketPageObject();
    }

    public ProductsPageObject searchOfProduct(String productName) throws InterruptedException {
        searchField.sendKeys(productName, Keys.ENTER);
        waitForVisibilityOfElement(chosenProduct, Duration.ofSeconds(10));
        return this;
    }

    public String getChosenProductName() {
        return chosenProduct.getText();
    }
}

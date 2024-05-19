package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import utils.AbstractComponents;
import utils.DriverFactory;

import java.util.List;

public class OrdersPageObject extends AbstractComponents {
    @FindBys(@FindBy(xpath = "//tr/td[2]"))
    List<WebElement> products;

    public OrdersPageObject() {
        PageFactory.initElements(getDriver(), this);
    }

    public WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    public Boolean verifyOrderToDisplay(String productName) {
        Boolean match = products.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
        return match;
    }
}

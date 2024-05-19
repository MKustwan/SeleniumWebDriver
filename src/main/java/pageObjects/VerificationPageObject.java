package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.AbstractComponents;
import utils.DriverFactory;

import java.util.List;
import java.util.stream.Collectors;

public class VerificationPageObject extends AbstractComponents {
    private @FindBy(xpath = "//input[@placeholder='Select Country']")
    WebElement selectCountry;
    private @FindBy(css = ".ta-results.list-group.ng-star-inserted")
    WebElement listOfCountries;
    private @FindBy(xpath = "//a[@class='btnn action__submit ng-star-inserted']")
    WebElement placeOrderButton;

    public VerificationPageObject() {
        PageFactory.initElements(getDriver(), this);
    }

    public WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    public ResultsPageObject fillUpData(String countryNameText) throws InterruptedException {
        selectCountry.sendKeys(countryNameText);
        waitForVisibilityOfElement(listOfCountries);
        List<WebElement> chosenCountry = listOfCountries.findElements(By.xpath("//button"))
                .stream().filter(country -> country.getText().equalsIgnoreCase(countryNameText))
                .collect(Collectors.toList());
        for (WebElement country : chosenCountry) {
            if (country.getText().equalsIgnoreCase(countryNameText)) {
                country.click();
            }
        };
        placeOrderButton.click();
        return new ResultsPageObject();
    }
}

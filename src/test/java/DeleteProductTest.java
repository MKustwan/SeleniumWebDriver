import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.LoginPageObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class DeleteProductTest extends BaseTest {
    @Test(dataProvider = "getData")
    public void deleteProductTest(HashMap<String, String> input) throws InterruptedException {
        String noProductInfo = new LoginPageObject().logIn(input.get("email"), input.get("password"))
                .addProductToBasket(input.get("productName"))
                .removeProductFromBasket()
                .noProductInBasketText();
        Assert.assertEquals(noProductInfo,"No Products in Your Cart !");
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//data//PurchaseOrder.json");
        return new Object[][]{{data.get(0)}};
    }
}


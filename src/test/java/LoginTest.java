import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.LoginPageObject;
import pageObjects.OrdersPageObject;
import pageObjects.ProductsPageObject;
import pageObjects.ResultsPageObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class LoginTest extends BaseTest {

    @Test(dataProvider = "getData")
    public void logInTest(HashMap<String, String> input) throws InterruptedException {
        ResultsPageObject resultsPo;
        LoginPageObject loginPo = new LoginPageObject();
        resultsPo = loginPo
                .logIn(input.get("email"), input.get("password"))
                .addProductToBasket(input.get("productName"))
                .verificationOfProducts()
                .fillUpData(input.get("country"));
        Assert.assertEquals(resultsPo.getMsgText(), "THANKYOU FOR THE ORDER.");
    }

    @Test(dataProvider = "getData")
    public void orderHistoryTest(HashMap<String, String> input) {
        OrdersPageObject ordersPo;
        LoginPageObject loginPo = new LoginPageObject();
        ordersPo = loginPo.logIn(input.get("email"), input.get("password"))
                .goToOrdersPage();
        Boolean product = ordersPo.verifyOrderToDisplay(input.get("productName"));
        Assert.assertTrue(product);
    }

    @Test(dataProvider = "getData")
    public void searchProductTest(HashMap<String, String> input) throws InterruptedException {
        LoginPageObject loginPo = new LoginPageObject();
        ProductsPageObject productsPo;
        productsPo = loginPo.logIn(input.get("email"), input.get("password"))
                .searchOfProduct(input.get("productName"));
        Assert.assertEquals(productsPo.getChosenProductName(), input.get("productName"));
    }
    @Test
    public void invalidLogInTest(){
        LoginPageObject loginPageObject = new LoginPageObject();
        Assert.assertEquals(loginPageObject.invalidLogIn("xyz","xyz"),"*Enter Valid Email");

    }
    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//data//PurchaseOrder.json");
        return new Object[][]{{data.get(0)}};
    }
}

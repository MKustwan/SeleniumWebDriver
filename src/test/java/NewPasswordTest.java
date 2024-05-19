import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.LoginPageObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class NewPasswordTest extends BaseTest {

    @Test(dataProvider = "getData")
    public void enterNewPassword(HashMap<String, String> input) {
        LoginPageObject loginPageObject = new LoginPageObject();
        loginPageObject
                .setNewPassword()
                .newPassword(input.get("email"), input.get("password"));
        Assert.assertEquals(loginPageObject.getLogInText(),"Log in");
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//data//PurchaseOrder.json");
        return new Object[][]{{data.get(0)}};
    }
}

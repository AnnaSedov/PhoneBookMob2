package tests;

import config.AppiumConfig;
import config.TestData;
import helpers.TestHelpers;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.SplashScreen;

public class LoginDataProviderTest extends AppiumConfig  {
    @Test(dataProvider = "loginData",dataProviderClass = TestData.class)
    public void loginTestNegative(String email,String password){
        AuthenticationScreen authenticationScreen=new SplashScreen(driver).swithchToAuthScreen().fillEmailField(email).fillPasswordField(password).clickByLoginButton();
        Assert.assertTrue(authenticationScreen.isItAuthScreen());
    }
}

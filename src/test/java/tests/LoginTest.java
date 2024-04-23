package tests;

import config.AppiumConfig;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;
import screens.SplashScreen;

public class LoginTest extends AppiumConfig {
    @Test
    public void loginPositive(){
        ContactListScreen contactListScreen=new SplashScreen(driver).swithchToAuthScreen().fillEmailField("homeann7@gmail.com").fillPasswordField("21212zZ!").clickByLoginButton();
        AuthenticationScreen authenticationScreen=contactListScreen.logOut();
     //  authenticationScreen.isItAuthScreen();
        Assert.assertTrue(authenticationScreen.isItAuthScreen());
    }
    //TODO negative login
}

package tests;

import config.AppiumConfig;
import helpers.EmailGenerator;
import helpers.PropertiesReaderXML;
import helpers.TestHelpers;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;
import screens.SplashScreen;

public class LoginTest extends AppiumConfig implements TestHelpers {
    @Test
    public void loginPositive(){

        String mailExist=PropertiesReaderXML.getProperty(EMAIL);
        String passExist=PropertiesReaderXML.getProperty(PASSWORD);
        ContactListScreen contactListScreen=new SplashScreen(driver).swithchToAuthScreen().fillEmailField(mailExist).fillPasswordField(passExist).clickByLoginButton();
        AuthenticationScreen authenticationScreen=contactListScreen.logOut();
        Assert.assertTrue(authenticationScreen.isItAuthScreen());
    }

    //hw 240424 negative test with uncorrected password
   @Test
    public void loginNegative(){
        AuthenticationScreen authenticationScreen=new SplashScreen(driver).swithchToAuthScreen().fillEmailField(EmailGenerator.generateEmail(2,3,2)).fillPasswordField("111").clickByLoginButton();
        Assert.assertTrue(authenticationScreen.isItAuthScreen());
    }
}

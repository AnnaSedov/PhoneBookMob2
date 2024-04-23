package tests;

import config.AppiumConfig;
import helpers.EmailGenerator;
import helpers.PasswordStringGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.ContactListScreen;
import screens.SplashScreen;

public class RegistrationTest extends AppiumConfig {
    @Test
    public void registrationNewUserTest(){
        ContactListScreen contactListScreen=new SplashScreen(driver).swithchToAuthScreen().fillEmailField(EmailGenerator.generateEmail(2,2,2))
                .fillPasswordField(PasswordStringGenerator.generateString()).clickByRegistrationButton();
     Assert.assertTrue( contactListScreen.isContactListPresent());

    }
    //TODO reg neg
}

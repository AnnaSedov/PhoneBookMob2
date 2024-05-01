package tests;

import config.AppiumConfig;
import helpers.EmailGenerator;
import helpers.PropertiesReaderXML;
import helpers.TestHelpers;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import screens.ContactListScreen;
import screens.EditContactScreen;
import screens.SplashScreen;

public class EditTest extends AppiumConfig implements TestHelpers {

    @Test
    public void editEmailPosTest(){

                new SplashScreen(driver).swithchToAuthScreen().fillEmailField(PropertiesReaderXML.getProperty(EMAIL)).fillPasswordField(PropertiesReaderXML.getProperty(PASSWORD)).clickByLoginButton();
       String newEmail= EmailGenerator.generateEmail(3,3,2);
      ContactListScreen contactListScreen=new ContactListScreen(driver).editFirstContact().editFormEmail(newEmail).updateContact();
        Assert.assertTrue(contactListScreen.isContactEdit());
    }

}

package tests;

import config.AppiumConfig;
import helpers.EmailGenerator;
import helpers.PasswordStringGenerator;
import helpers.PropertiesWriterXML;
import helpers.TestHelpers;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;
import screens.SplashScreen;

public class RegistrationTest extends AppiumConfig implements TestHelpers {
    @Test
    public void registrationNewUserTest(){

        String newMail=EmailGenerator.generateEmail(2,2,2);
        String newPass=PasswordStringGenerator.generateString();
        System.out.println("TEST "+newMail+" "+newPass);
        PropertiesWriterXML propertiesWriterXML=new PropertiesWriterXML(FILE_PATH);
        propertiesWriterXML.setProperties(EMAIL,newMail,false);
        propertiesWriterXML.setProperties(PASSWORD,newPass,false);
        ContactListScreen contactListScreen=new SplashScreen(driver).swithchToAuthScreen().fillEmailField(newMail)
                .fillPasswordField(newPass).clickByRegistrationButton();

     Assert.assertTrue( contactListScreen.isContactListPresent());

    }

    //240424 hw negative registration
    @Test
    public void registrationTestNegativeWrongEmail(){
        AuthenticationScreen authenticationScreen =new SplashScreen(driver).swithchToAuthScreen().fillEmailField("1111").fillPasswordField(PasswordStringGenerator.generateString()).clickByRegistrationButton();
        Assert.assertTrue(authenticationScreen.isItAuthScreen());
    }

}

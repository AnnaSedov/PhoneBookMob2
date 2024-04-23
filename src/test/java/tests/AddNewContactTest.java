package tests;

import config.AppiumConfig;
import helpers.EmailGenerator;
import helpers.NameAndLastNameGenerator;
import helpers.PhoneNumberGenerator;
import models.Contact;
import org.testng.annotations.Test;
import screens.ContactListScreen;
import screens.SplashScreen;

public class AddNewContactTest extends AppiumConfig {
    @Test
    public void addNewContact(){
        new SplashScreen(driver).swithchToAuthScreen().fillEmailField("").fillPasswordField("").clickByLoginButton();
        Contact contact=new Contact(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(), EmailGenerator.generateEmail(2,3,2),
                "jjj",
                PhoneNumberGenerator.generatePhoneNumber(),"");
        new ContactListScreen(driver).openNewContactForm().fillTheForm(contact).createContact().isContactAdded();
    }
    //TODO like  in old project
}

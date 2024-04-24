package tests;

import config.AppiumConfig;
import helpers.*;
import models.Contact;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.ContactListScreen;
import screens.SplashScreen;

public class AddNewContactTest extends AppiumConfig implements TestHelpers {
    @Test
    public void addNewContact(){
        new SplashScreen(driver).swithchToAuthScreen().fillEmailField(PropertiesReaderXML.getProperty(EMAIL)).fillPasswordField(PropertiesReaderXML.getProperty(PASSWORD)).clickByLoginButton();
        Contact contact=new Contact(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(), EmailGenerator.generateEmail(2,3,2),
                AddressGenerator.generateAddress(),
                PhoneNumberGenerator.generatePhoneNumber(),"");
        ContactListScreen contactListScreen=  new ContactListScreen(driver).openNewContactForm().fillTheForm(contact).createContact().isContactAdded();
        Assert.assertTrue(contactListScreen.isElementPresent());
    }
    //TODO like  in old project
}

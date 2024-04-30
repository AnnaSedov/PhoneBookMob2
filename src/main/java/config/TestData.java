package config;

import helpers.NameAndLastNameGenerator;
import helpers.PasswordStringGenerator;
import org.testng.annotations.DataProvider;

public class TestData {
    @DataProvider(name = "loginData")
    public Object[][] loginData(){
        return new Object[][]{
                {"fakeUser1@mail.com","fakePassword"},
                {NameAndLastNameGenerator.generateName(), null}

        };
    }
}

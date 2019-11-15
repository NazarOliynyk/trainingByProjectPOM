package pageObjects.businessObjects;

import org.testng.Assert;
import pageObjects.LoginationPage;

public class LoginationBO {

    private LoginationPage loginationPage;

    public LoginationBO() {
        loginationPage= new LoginationPage();
    }




    public HomeBO login(String mail, String password) {
        loginationPage.enterEmail(mail)
                .enterPassword(password)
                .clickSignInButton();
        return new HomeBO();
    }

    public void verifyFailedLoginErrorMessageDisplayed(){
        Assert.assertTrue(LoginationPage.isLoginFailedErrorMessageDisplayed(),
                "'Login failed' error message is not displayed");
    }
}

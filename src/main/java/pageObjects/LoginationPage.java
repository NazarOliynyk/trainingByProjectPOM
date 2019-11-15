package pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class LoginationPage extends AbstractPage {

//    private static final Logger LOG = Logger.getLogger(LoginationPage.class);

    private By mailInput = By.id("signInEmail");

    private By passwordInput = By.id("signInPassword");

    private By signInButton = By.className("popup-reg-sign-in-form__sign-in");

    private static By loginFailedErrorMessage = By.xpath("//div[text()='Login failed. Please try again.']");

    public LoginationPage enterEmail(String email){
        getElement(mailInput).sendKeys(email);
//        LOG.info("Mail was entered.");
        return this;
    }

    public LoginationPage enterPassword(String password){
        getElement(passwordInput).sendKeys(password);
//        LOG.info("Password was entered.");
        return this;
    }



    public  HomePage clickSignInButton(){
        getElement(signInButton).click();
        // LOG.info("Sign in button clicked.");
        return new HomePage();
    }

    public static boolean isLoginFailedErrorMessageDisplayed() {
        //        LOG.info(String.format("Is 'Login Failed' error message displayed': '%s'",isDisplayed));
        return isDisplayed(loginFailedErrorMessage);
    }
}

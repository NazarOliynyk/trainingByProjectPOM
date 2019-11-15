import org.testng.annotations.Test;
import pageObjects.businessObjects.HomeBO;
import pageObjects.businessObjects.LoginationBO;

public class LogInTest extends BaseTest{

    @Test(description = "Verify error message appears when user logging in with inappropriate credentials.")
    public void verifyErrorMessageAppearsForIncorrectUser() {

        new HomeBO()
                .proceedToHomePage()
                .clickSignInButton()
                .login("incorrectmail@gmail.com","incorrectPassword");
        new LoginationBO()
                .verifyFailedLoginErrorMessageDisplayed();

    }

    @Test(description = "Verify user is successfully logged in with appropriate credentials.")
    public void verifyUserIsSuccessfullyLoggedIn() {

        HomeBO homeBO = new HomeBO();
        homeBO
                .proceedToHomePage()
                .clickSignInButton()
                .login("ivanhorintest@gmail.com","ivanhorintestPassword")
                .verifyUserIsLoggedIn();
        homeBO.
                verifyRightUserNameIsDisplayed("ivanhorintest");
    }

}

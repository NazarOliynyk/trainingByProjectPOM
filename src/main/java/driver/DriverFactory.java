package driver;

import enums.MainEnums;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class DriverFactory {

    private static WebDriver driver;

   protected void buildDriver(final String browserName){

        if(browserName.equalsIgnoreCase(MainEnums.CHROME_DRIVER.getValue())){
            System.setProperty(MainEnums.CHROME_DRIVER.getValue(),
                    MainEnums.CHROME_DRIVER_LOCATION.getValue());
            driver=new ChromeDriver();
        }else if(browserName.equalsIgnoreCase(MainEnums.FIREFOX_DRIVER.getValue())){
            System.setProperty(MainEnums.FIREFOX_DRIVER.getValue(),
                    MainEnums.FIREFOX_DRIVER_LOCATION.getValue());
            driver=new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public static WebDriver getDriver() {
        return driver;
    }

    protected void quitDriver(){
        if(driver!= null){
            driver.quit();
            driver= null;
        }
    }
}

import driver.DriverFactory;
import enums.MainEnums;
import org.apache.log4j.Logger;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public abstract class BaseTest extends DriverFactory {

    private Logger LOG = Logger.getLogger(BaseTest.class);


  //  @Parameters({"browserName"})
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod( Method method) {

        buildDriver(MainEnums.CHROME_DRIVER.getValue());
        LOG = Logger.getLogger(method.getDeclaringClass());
        Test test = method.getAnnotation(Test.class);
        LOG.info(String.format("Test '%s' started.",method.getName()));
        LOG.info(String.format("Description: %s.", test.description()));
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(final Method method){
        LOG.info(String.format("Test '%s' completed.",method.getName()));
        quitDriver();
    }
}
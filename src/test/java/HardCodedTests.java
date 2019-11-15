import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HardCodedTests {

    @Test(description = "Verify 'Trainings' search works properly with searching in 'Skills'")
    public void verifyTrainingsSearchWorksProperlyForSkills() {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver, 20);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://training.by/#/Home");

        WebElement signInButton = driver.findElement(By.xpath("//p[@class='header-auth__signin']//span"));
        signInButton.click();

        WebElement mailInput = driver.findElement(By.id("signInEmail"));
        mailInput.sendKeys("ivanhorintest@gmail.com");

        WebElement passwordInput = driver.findElement(By.id("signInPassword"));
        passwordInput.sendKeys("ivanhorintestPassword");

        WebElement signIn = driver.findElement(By.className("popup-reg-sign-in-form__sign-in"));
        signIn.click();

        WebElement expandSkillsArrow = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.className("filter-toggle__arrow-icon")));

        expandSkillsArrow.click();

        WebElement byLocationsButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(),'By locations')]")));
        byLocationsButton.click();

        WebElement locationsSearchInput = driver
                .findElement(By.xpath("//input[@name='training-filter-input']"));
        locationsSearchInput.sendKeys("Minsk");

        WebElement bySkillsButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(),'By skills')]")));
        bySkillsButton.click();

        WebElement skillsSearchInput = driver
                .findElement(By.xpath("//input[@name='training-filter-input']"));
        skillsSearchInput.sendKeys("Java");


        WebElement javaCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[contains(.,'Java')]//span")));
        javaCheckbox.click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)");

        WebElement collapseSkillsArrow = driver.findElement(By.className("filter-toggle__arrow-icon"));
        collapseSkillsArrow.click();

        List<WebElement> skillsSearchResultsList = driver.
                findElements(By.xpath("//div[@class='training-list__container training-list__desktop']//a"));

        skillsSearchResultsList.forEach(element -> Assert.assertTrue(element.getText().contains("JAVA"),
                String.format("Element %s does not contain 'Java' word.", element)));

        driver.quit();

    }

    @Test(description = "Verify logination with incorrect credentials")
    public void verifyIncorrectCredentials() {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver, 20);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://training.by/#/Home");

        WebElement signInButton = driver.findElement(By.xpath("//p[@class='header-auth__signin']//span"));
        signInButton.click();

        WebElement mailInput = driver.findElement(By.id("signInEmail"));
        mailInput.sendKeys("ivanhorintest@gmail.com");

        WebElement passwordInput = driver.findElement(By.id("signInPassword"));
        passwordInput.sendKeys("ivanhorintestWRONGPassword");

        WebElement signIn = driver.findElement(By.className("popup-reg-sign-in-form__sign-in"));
        signIn.click();

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(" div.popup__error-message.ng-binding")));
        System.out.println("errorMessage.getText(): " + errorMessage.getText());
        Assert.assertEquals("Ошибка авторизации. Пожалуйста, попробуйте еще раз.", errorMessage.getText());


        driver.quit();

    }

    @Test(description = "Verify 'Trainings' search works properly with searching in 'News'")
    public void verifyNewsPageMaterialSection() {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver, 20);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://training.by");

        WebElement signInButton = driver.findElement(By.xpath("//p[@class='header-auth__signin']//span"));
        signInButton.click();

        WebElement mailInput = driver.findElement(By.id("signInEmail"));
        mailInput.sendKeys("ivanhorintest@gmail.com");

        WebElement passwordInput = driver.findElement(By.id("signInPassword"));
        passwordInput.sendKeys("ivanhorintestPassword");

        WebElement signIn = driver.findElement(By.className("popup-reg-sign-in-form__sign-in"));
        signIn.click();

        WebElement newsButton = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("a[href='/#!/News']")));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", newsButton);


        // start from success stories because News is open by default

        //div[@class="tab-nav"]/div/div[2]/span
        WebElement successStoriesButton = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//span[contains(text(),'Success Stories')]")));


        try {
            Thread.sleep(1500);
            successStoriesButton.click();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //div[@class="tab-nav"]/div/div[3]/span
        WebElement materialsButton = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//span[contains(text(),'Materials')]")));

        try {
            Thread.sleep(1500);
            materialsButton.click();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //div[@class="tab-nav"]/div/div[4]/span
        WebElement videosButton = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//span[contains(text(),'Videos')]")));

        try {
            Thread.sleep(1500);
            videosButton.click();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // div.tab-nav > div > div:nth-child(1) > span
        WebElement newsBigButton = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//span[contains(text(),'News')]")));

        try {
            Thread.sleep(1500);
            newsBigButton.click();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        materialsButton.click();

        List<WebElement> skillsSearchResultsList = driver.
                findElements(By.cssSelector("div.news-page-item> div.news-page-item__title > a"));

        skillsSearchResultsList.forEach(element ->
                System.out.println(element.getText())
        );

        skillsSearchResultsList.forEach(element ->
                Assert.assertTrue(element.getText().contains("materials") || element.getText().contains("Useful")
                        || element.getText().contains("Materials"))
        );

        driver.quit();

    }



    @Test(description = "Verify 'Trainings' search works properly with searching in 'Locations'")
    public void verifyLocationsSection() {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        WebDriver driver =new ChromeDriver();

//        WebDriverWait wait=new WebDriverWait(driver, 20);
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://training.by");

        WebElement signInButton = driver.findElement(By.xpath("//p[@class='header-auth__signin']//span"));
        signInButton.click();

        WebElement mailInput = driver.findElement(By.id("signInEmail"));
        mailInput.sendKeys("ivanhorintest@gmail.com");

        WebElement passwordInput = driver.findElement(By.id("signInPassword"));
        passwordInput.sendKeys("ivanhorintestPassword");

        WebElement signIn = driver.findElement(By.className("popup-reg-sign-in-form__sign-in"));
        signIn.click();

        JavascriptExecutor executor = (JavascriptExecutor)driver;


        WebElement trainingListButton = wait.until(ExpectedConditions
                        .visibilityOfElementLocated(By.cssSelector("a[href='/#!/TrainingList']")));
        executor.executeScript("arguments[0].click();", trainingListButton);



        executor.executeScript("window.scrollBy(0,1650)");

        //*[@id="training"]/div[2]/div[1]/form/input
        WebElement searchTrainingArrow = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//input[@placeholder='Search for trainings']")));
        executor.executeScript("arguments[0].click();", searchTrainingArrow);


        // div.drop-down-choose__frame > div:nth-child(1) > div > div.location__countries > ul > li:nth-child(5)
        WebElement countrySelect = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//li[descendant::div[ contains(text(),'Ukraine')]]")));

        countrySelect.click();


        WebElement citySelect = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//li[descendant::text()[contains(., \"Lviv\")]]/label")));

        citySelect.click();

        //*[@id="training"]/div[4]/div/div/div[3]
        List<WebElement> lvivPositions = driver.
                findElements(By.xpath("//*[@id=\"training\"]/div[4]/div/div/div[3]"));

        lvivPositions.forEach(element->
                System.out.println(element.getText())
        );

        lvivPositions.forEach(element->
                Assert.assertTrue(element.getText().contains("Lviv"))
        );


        driver.quit();

    }

}


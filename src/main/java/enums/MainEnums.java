package enums;

public enum MainEnums {

     CHROME_DRIVER("webdriver.chrome.driver"),
     FIREFOX_DRIVER(""),

     CHROME_DRIVER_LOCATION("src/main/resources/chromedriver.exe"),
     FIREFOX_DRIVER_LOCATION("src/main/resources/chromedriver.exe"),

     MAIN_URL("https://training.by"),
     HOME_PAGE_URL("https://training.by/#/Home");

    public String getValue() {
        return value;
    }

    String value;
     MainEnums(String value){
      this.value = value;
    }
}

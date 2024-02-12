package pages;

import common.AbsCommon;
import org.openqa.selenium.WebDriver;

public abstract class AbsBasePage extends AbsCommon {
    private String BASE_URL = System.getProperty("base.url");
    public  AbsBasePage(WebDriver driver){
       super(driver);
    }
    public void open(String path) {
        driver.get(BASE_URL + path);
    }
}

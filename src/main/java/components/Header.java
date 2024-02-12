package components;

import common.AbsCommon;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Header extends AbsCommon {
    private String iconUserSelector = "img[src*='blue-owl']";
    public Header(WebDriver driver){
        super(driver);
    }
    public void clickLoginButton() {
        String loginButton = "//button[text()='Войти']";
        waitTools.waitForCondition(ExpectedConditions.presenceOfElementLocated(By.xpath(loginButton)));
        waitTools.waitForCondition(ExpectedConditions.elementToBeClickable(By.xpath(loginButton)));

        WebElement enterButton = driver.findElement(By.xpath(loginButton));
        enterButton.click();
    }
    public void checkLogoUser() {
        waitTools.waitForCondition(ExpectedConditions
                .presenceOfElementLocated(By.cssSelector(iconUserSelector)));
    }
    public void clickToUserIcon() {
        driver.findElement(By.cssSelector(iconUserSelector)).click();
    }
    public void clickPersonalArea() {
        driver.findElement(By
                        .xpath("//div[@class='sc-r03h0s-5 sc-1youhxc-2 bYKNcH imWQF sc-1og4wiw-0-Component fgPsmr']"))
                .click();
        driver.findElement(By.xpath("//div/a[@href='https://otus.ru/lk/biography/personal']")).click();
    }
}

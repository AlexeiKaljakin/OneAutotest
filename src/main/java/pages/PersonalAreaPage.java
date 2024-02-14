package pages;

import common.AbsCommon;
import data.countrycityes.ICityData;
import data.fielddata.InputFieldData;
import data.fielddata.genderdata.GenderData;
import data.language.EnglishLevelData;
import data.workformat.WorkFormatData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PersonalAreaPage extends AbsCommon {
    private Logger logger =  LogManager.getLogger(PersonalAreaPage.class);
    public PersonalAreaPage(WebDriver driver){
        super(driver);
    }
    public void clearFieldsData(InputFieldData... inputFieldData) {
        for (InputFieldData fieldData : inputFieldData) {
            driver.findElement(By.cssSelector(String.format("input[name='%s']", fieldData.getName()))).clear();
        }
        logger.info("Fields cleared");
    }
    public void clearFieldsCountryAndEnglish() {
        driver.findElement(By.xpath("//div[@data-num='0']/div/div/button[@type='button']")).click();
        driver.findElement(By.xpath("//div[@data-num='1']/div/div/button[@type='button']")).click();
        driver.findElement(By.cssSelector(".js-lk-cv-dependent-master.js-lk-cv-custom-select")).click();
        driver.findElement(By.xpath("//button[@data-value='' and @data-empty='Не указано' and @title='Не выбрано']")).click();
        driver.findElement(By.xpath("//input[@name='english_level']/ancestor:: div[contains(@class, 'js-lk-cv-custom-select')]")).click();
        driver.findElement(By.xpath("//div[@class='lk-cv-block__select-options js-custom-select-options-container']" +
                "/div/button[@title='Не выбрано']")).click();
    }
    public void addDataFields(InputFieldData inputFieldData, String data) {
        driver.findElement(By.cssSelector(String.format("input[name='%s']", inputFieldData.getName())))
                .sendKeys(data);
    }
    public void addCountry(ICityData cityData) {
        WebElement russiaSelectElement = driver.findElement(By.cssSelector("[data-slave-selector='.js-lk-cv-dependent-slave-city']"));
        russiaSelectElement.click();

        WebElement countryListContainer = russiaSelectElement
                .findElement(By.xpath(".//*[contains(@class, 'js-custom-select-options-container')]"));
        waitTools.waitForCondition(ExpectedConditions.not(ExpectedConditions
                .attributeContains(countryListContainer, "class", "hide")));

        driver.findElement(By.cssSelector(String.format("[title='%s']",
                cityData.getCountryData().getNameCountry()))).click();

        waitTools.waitForCondition(ExpectedConditions
                .attributeContains(countryListContainer, "class", "hide"));
    }
    public void addCity(ICityData cityData) {
        waitTools.waitForCondition(ExpectedConditions.elementToBeClickable(By.xpath("//input[@data-title='Город']")));
        WebElement city = driver.findElement(By.xpath("//*[contains(@class, 'js-lk-cv-dependent-slave-city')]"));
        city.click();
        driver.findElement(By.cssSelector(String.format("[title='%s']", cityData.getName()))).click();
    }
    public void addEnglishlevel(EnglishLevelData englishLevelData) {
        WebElement englishlevel = driver.findElement(By.xpath("//input[@name='english_level']/ancestor:: div[contains(@class, 'js-lk-cv-custom-select')]"));
        englishlevel.click();
        driver.findElement(By.cssSelector(String.format("[title*='%s']", englishLevelData.getEnglishLevel()))).click();
    }

    public void addWillingToRelocate(boolean isSelected) {
        String relocate = isSelected ? "Да" : "Нет";
        driver.findElement(By.xpath(String.format("//span[@class=\"radio__label\" and text()=\"%s\"]", relocate))).click();
    }

    public void addWorkFormat(boolean isSelected, WorkFormatData... workFormats) {
        for(WorkFormatData workFormatData : workFormats) {
            waitTools.waitForCondition(ExpectedConditions.elementToBeClickable(By.xpath("//input[@title='Удаленно']")));
            WebElement inputSelect = driver.findElement(By.cssSelector(String.format("input[title='%s']", workFormatData.getName())));
            if (inputSelect.isSelected() != isSelected) {
                inputSelect.click();
            }
        }
    }
public void addContactsOne(String contactType, String contactValue) {
    driver.findElement(By.xpath("//button[@type='button' and text()='Добавить']")).click();
    waitTools.waitForCondition(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"placeholder\" and" +
            " text()=\"Способ связи\"]")));
    driver.findElement(By.xpath("//span[@class=\"placeholder\" and text()=\"Способ связи\"]")).click();
    driver.findElement(By.xpath(String.format("//div[@class='lk-cv-block__select-options lk-cv-block__select-options_left" +
            " js-custom-select-options-container']/div/button[@data-value='%s']", contactType))).click();


    driver.findElement(By.xpath("//*[@id=\"id_contact-2-value\"]")).sendKeys(contactValue);
    logger.info("Добавлен 1 контакт");
    driver.findElement(By.cssSelector("button.lk-cv-block__action.lk-cv-block__action_md-no-spacing.js-formset-add" +
            ".js-lk-cv-custom-select-add")).click();

}
    public void addContactsTwo(String contactType, String contactValue) {
        driver.findElement(By.xpath("//span[@class='placeholder']")).click();
        driver.findElement(By.xpath(String.format("//div[@class='lk-cv-block__select-options lk-cv-block__select-options_left" +
                " js-custom-select-options-container']/div/button[@data-value='%s']", contactType))).click();

        driver.findElement(By.xpath("//*[@id=\"id_contact-3-value\"]")).sendKeys(contactValue);
        logger.info("Добавлен 2 контакт");

    }
    public void addGender(GenderData genderData) {
        driver.findElement(By.id("id_gender")).click();
        driver.findElement(By.cssSelector(String.format("option[value='%s']", genderData.getName()))).click();
    }
    public void clickSavePersonalArea() {
        driver.findElement(By.cssSelector("button[name='continue']")).click();
        WebElement saveText = driver.findElement(By.xpath("//div[@class='container container-padding-top-" +
                "half container-padding-bottom-half']/span/span"));
        String actualText = saveText.getText().trim();
        Assertions.assertEquals("Данные успешно сохранены", actualText);

    }
    public void checkPersonalArea(InputFieldData inputFieldData) {
        Assertions
                .assertTrue(!driver.findElement
                                (By.cssSelector(String.format("input[name='%s']", inputFieldData.getName())))
                        .getAttribute("value").isEmpty());
    }
    public void chechPersonalAreaData(){
        Assertions.assertTrue(!driver.findElement(By.cssSelector(".input-icon > input:nth-child(1)")).getAttribute("value").isEmpty());
        Assertions.assertTrue(!driver.findElement(By.cssSelector(".js-lk-cv-dependent-master > label:nth-child(1) > div:nth-child(2)")).getText().isEmpty());
        Assertions.assertTrue(!driver.findElement(By.cssSelector(".js-lk-cv-dependent-slave-city > label:nth-child(1) > div:nth-child(2)")).getText().isEmpty());
        Assertions.assertTrue(!driver.findElement(By.xpath("//input[@name='english_level']/ancestor:: div[contains(@class, 'js-lk-cv-custom-select')]")).getText().isEmpty());
        Assertions.assertTrue(driver.findElement(By.xpath("//input[@id='id_ready_to_relocate_1']")).isSelected());
        Assertions.assertTrue(driver.findElement(By.cssSelector("input[title='Удаленно']")).isSelected());
        Assertions.assertTrue(!driver.findElement(By.id("id_contact-0-value")).getAttribute("value").isEmpty());
        Assertions.assertTrue(!driver.findElement(By.id("id_contact-1-value")).getAttribute("value").isEmpty());
        Assertions.assertTrue(!driver.findElement(By.id("id_gender")).getAttribute("value").isEmpty());
    }
}

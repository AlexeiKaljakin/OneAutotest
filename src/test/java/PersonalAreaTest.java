import com.github.javafaker.Faker;
import components.Header;
import components.popups.AuthorizationPopup;
import data.countryCityes.CountryData;
import data.countryCityes.ICityData;
import data.countryCityes.RussiaCityData;
import data.fieldData.InputFieldData;
import data.genderData.GenderData;
import data.language.EnglishLevelData;
import data.workformat.WorkFormatData;
import driverfactory.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.MainPage;
import pages.PersonalAreaPage;
import tools.WaitTools;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class PersonalAreaTest {
   private Faker faker = new Faker();
   private WebDriver driver;
   private final Logger logger = LogManager.getLogger(PersonalAreaTest.class);

    @BeforeEach
    public void init () {
        driver = new WebDriverFactory("--start-maximized").create();
        logger.info("Start driver");

    }
    @AfterEach
    public void stopDriver() {
        if(driver != null) {
            driver.quit();
            logger.info("Quit driver");
        }
    }

    @Test
    public void addPersonalData(){
        new MainPage(driver).open("/");
        Header header = new Header(driver);
        header.clickLoginButton();
        AuthorizationPopup authorizationPopup = new AuthorizationPopup(driver);
        authorizationPopup.popupShouldBeVisible();
        authorizationPopup.enterEmail();
        authorizationPopup.enterPassword();
        authorizationPopup.enterLoginButton();
        header.checkLogoUser();
        header.clickPersonalArea();
        PersonalAreaPage personalAreaPage = new PersonalAreaPage(driver);
        personalAreaPage.clearFieldsData(InputFieldData.FNAME);
        personalAreaPage.clearFieldsData(InputFieldData.FNAMELATIN);
        personalAreaPage.clearFieldsData(InputFieldData.LNAME);
        personalAreaPage.clearFieldsData(InputFieldData.LNAMELATIN);
        personalAreaPage.clearFieldsData(InputFieldData.BLOGNAME);
        personalAreaPage.clearFieldsData(InputFieldData.DATEOFBRTH);
        personalAreaPage.clearFieldsCountryAndEnglish();
        personalAreaPage.addDataFields(InputFieldData.FNAME, faker.name().firstName());
        personalAreaPage.addDataFields(InputFieldData.FNAMELATIN, faker.name().lastName());
        personalAreaPage.addDataFields(InputFieldData.LNAME, faker.name().firstName());
        personalAreaPage.addDataFields(InputFieldData.LNAMELATIN, faker.name().name());
        personalAreaPage.addDataFields(InputFieldData.BLOGNAME, faker.name().name());
        personalAreaPage.addDataFields(InputFieldData.DATEOFBRTH,
                faker.date().birthday().toInstant().atZone(ZoneId.
                        systemDefault()).toLocalDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
       ICityData[] cityData = RussiaCityData.values();
       ICityData city = faker.options().nextElement(cityData);

        personalAreaPage.addCountry(city);

        personalAreaPage.addCity(city);

        personalAreaPage.addEnglishlevel(EnglishLevelData.FIRSTLEVEL);
        personalAreaPage.addWillingToRelocate(true);
        personalAreaPage.addWorkFormat(true, WorkFormatData.REMOTELY);
        personalAreaPage.addContactsOne("skype", "atata");
        personalAreaPage.addContactsTwo("habr", "ydydy");
        personalAreaPage.addGender(GenderData.MALE);
        personalAreaPage.addDataFields(InputFieldData.COMPANY, faker.company().name());
        personalAreaPage.addDataFields(InputFieldData.POSITION, faker.job().position());
        personalAreaPage.clickSavePersonalArea();
    }
    @Test
    public void checkData() {
        new MainPage(driver).open("/");
        Header header = new Header(driver);
        header.clickLoginButton();
        AuthorizationPopup authorizationPopup = new AuthorizationPopup(driver);
        authorizationPopup.popupShouldBeVisible();
        authorizationPopup.enterEmail();
        authorizationPopup.enterPassword();
        authorizationPopup.enterLoginButton();
        header.checkLogoUser();
        header.clickPersonalArea();
        new PersonalAreaPage(driver).checkPersonalArea();
    }

}

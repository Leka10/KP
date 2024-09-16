package tests.tasks;

import com.deque.axe.AXE;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePageClass;
import pages.HomePage;
import tests.BaseTestClass;
import utils.DateTimeUtils;

import java.net.URL;

public class KupujemProdajem extends BaseTestClass {

    private WebDriver driver;

        @BeforeMethod
        public void setUpTest() {
            log.debug("[SETUP TEST] ");
            driver = setUpDriver();
        }

    @Test
    public synchronized void testKupujemProdajemJedan() throws InterruptedException {

            HomePage homePage = new HomePage(driver).open();


            homePage.clickCloseLogInPopUp();
            homePage.openSearchFilters();
            homePage.openKategorija();
            homePage.selectZenskaOdeca();
            homePage.selectBluze();
            homePage.enterCenaOd();
            homePage.selectDinCurrency();
            homePage.selectSamoSaCenom();
            homePage.openStanjeFilter();
            homePage.selectStanjeNovo();
            homePage.selectStanjeKaoNovo();
            homePage.clickButtonPrimeniFiltere();
            DateTimeUtils.wait(1);
            Assert.assertTrue(homePage.resultsMoreThan(1000));
    }
    @Test
    public synchronized void testKupujemProdajemDva() throws InterruptedException {

        HomePage homePage = new HomePage(driver).open();


        homePage.clickCloseLogInPopUp();
        homePage.openSearchFilters();
        homePage.openKategorija();
        homePage.selectZenskaOdeca();
        homePage.selectBluze();
        homePage.enterCenaOd();
        homePage.selectDinCurrency();
        homePage.selectSamoSaCenom();
        homePage.openStanjeFilter();
        homePage.selectStanjeNovo();
        homePage.selectStanjeKaoNovo();
        homePage.clickButtonPrimeniFiltere();
        homePage.clickOnPrviOglas();
        homePage.clickDodajUAdresar();
        BasePageClass.waitUntilElementIsVisible(driver, HomePage.loginForma,10);
        Assert.assertTrue(homePage.isLoginVisible());


    }

    @AfterMethod(alwaysRun = true)
    public void tearDownTest(ITestResult testResult) {
        log.debug("[END TEST] ");
        tearDown(driver, testResult);
    }

}

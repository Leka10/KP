package tests.tasks;

import com.deque.axe.AXE;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import tests.BaseTestClass;

import java.net.URL;

public class KupujemProdajem extends BaseTestClass {
    private static final URL scriptUrl = KupujemProdajem.class.getClassLoader().getResource("axe.min.js");

    private WebDriver driver;

        @BeforeMethod
        public void setUpTest() {
            log.debug("[SETUP TEST] ");
            driver = setUpDriver();
        }

//    @Test
//    public synchronized void testKupujemProdajemJedan() throws InterruptedException {
//
//            HomePage homePage = new HomePage(driver).open();
//
//
//            homePage.clickCloseLogInPopUp();
//            homePage.openSearchFilters();
//            homePage.openKategorija();
//            homePage.selectZenskaOdeca();
//            homePage.selectBluze();
//            homePage.enterCenaOd();
//            homePage.selectDinCurrency();
//            homePage.selectSamoSaCenom();
//            homePage.openStanjeFilter();
//            homePage.selectStanjeNovo();
//            homePage.selectStanjeKaoNovo();
//            homePage.clickButtonPrimeniFiltere();
//            Thread.sleep(2000);
//            Assert.assertTrue(homePage.resultsMoreThan(2000));
//    }
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
        Thread.sleep(2000);
        homePage.clickOnPrviOglas();


    }

    @AfterMethod(alwaysRun = true)
    public void tearDownTest(ITestResult testResult) {
        log.debug("[END TEST] ");
//        tearDown(driver, testResult);
    }

}

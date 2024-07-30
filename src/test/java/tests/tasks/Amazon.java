package tests.tasks;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import tests.BaseTestClass;
import utils.DateTimeUtils;

public class Amazon extends BaseTestClass {

    private String sTestName = this.getClass().getName();
    private WebDriver driver;

//    private static final Logger logger = LoggerFactory.getLogger(testAmazon.class);
//
//    @Test
//    public void testLogging() {
//        logger.debug("This is a debug message");
//        logger.info("This is an info message");
//        logger.warn("This is a warn message");
//        logger.error("This is an error message");
//    }
        @BeforeMethod
        public void setUpTest() {
            log.debug("[SETUP TEST] ");
            driver = setUpDriver();
        }

    @Test
    public synchronized void testAmazon() throws InterruptedException {

            HomePage homePage = new HomePage(driver).open();
            homePage.clickOnTryDifferentImage();
            homePage.searchForElement("hats for men");
            homePage.clickOnFirstHat();
            homePage.clickOnSizeDropdown();
            homePage.selectAvailableSize();
            homePage.clickOnQuantityDropdown();
            homePage.selectQuantityTo2();
            homePage.clickOnAddToCartButton();
            homePage.clickOnGoToCartButton();
            homePage.totalCart();

            homePage.searchForElement("hats for women");
            homePage.clickOnFirstHat();
            homePage.clickOnSizeDropdown();
            homePage.clickOnQuantityDropdown();
            homePage.selectQuantityTo1();
            homePage.clickOnAddToCartButton();
            homePage.clickOnGoToCartButton();
            homePage.totalCart();
            homePage.clickOnDropdownBoxOnCartPage();
            homePage.changeQuantityTo1();
            DateTimeUtils.wait(2); //here need to w8 sometime just to be sure after we changed quantity new values are loaded, because test is so fast that sometime he gets old values after changing quantity to 1 and then we have incorrect total values
            homePage.totalCart();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownTest(ITestResult testResult) {
        log.debug("[END TEST] ");
        tearDown(driver, testResult);
    }

}

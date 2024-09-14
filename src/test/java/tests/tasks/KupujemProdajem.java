package tests.tasks;

import com.deque.axe.AXE;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
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

    @Test
    public synchronized void testAmazon() throws InterruptedException {

            HomePage homePage = new HomePage(driver).open();
        // Run Axe test
        JSONObject responseJSON = new AXE.Builder(driver, scriptUrl).analyze();

        // Show results
        JSONArray violations = responseJSON.getJSONArray("violations");
        if (violations.length() == 0) {
            System.out.println("Problems with accessibility are not found.");
        } else {
//            AXE.writeResults("testAmazon", responseJSON);
//            Assert.assertTrue(false, AXE.report(violations));
            System.out.println("Problems with accessibility are found:");
            for (int i = 0; i < violations.length(); i++) {
                JSONObject violation = violations.getJSONObject(i);
                System.out.println(violation.getString("description"));
                System.out.println("Impact level: " + violation.getString("impact"));
                JSONArray nodes = violation.getJSONArray("nodes");
                for (int j = 0; j < nodes.length(); j++) {
                    System.out.println("  - Element: " + nodes.getJSONObject(j).getJSONArray("target"));
                }
            }
        }

    }

    @AfterMethod(alwaysRun = true)
    public void tearDownTest(ITestResult testResult) {
        log.debug("[END TEST] ");
        tearDown(driver, testResult);
    }

}

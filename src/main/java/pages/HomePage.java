package pages;

import data.Time;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import utils.PropertiesUtils;


public class HomePage extends CommonLoggedOutPage {

    private final String LOGIN_PAGE_URL = PropertiesUtils.getBaseUrl();

    private static final By closeLogInPopUp = By.xpath("//button[@class='Button_base__G3HTK Button_big__vkHxv Modal_closeIcon__CJuTW']");
    private static final By kategorija = By.id("react-select-categoryId-input");
    private static final By zenskaOdeca = By.id("react-select-categoryId-option-46");
    private static final By bluze = By.id("react-select-groupId-option-0");
    private static final By searchFilters = By.xpath("//button[@aria-label='Pretražite detaljno ']");
    private static final String cenaOd = "priceFrom";
    private static final By dinCurrency = By.xpath("//input[@aria-label='rsd']");
    private static final By samoSaCenom = By.xpath("//label[@for='hasPriceyes']");
    private static final By stanjeFilter = By.id("react-select-condition-input");
    private static final By stanjeNovo = By.xpath("//label[@for='checkbox-new']");
    private static final By stanjeKaoNovo = By.xpath("//label[@for='checkbox-as-new']");
    private static final By buttonPrimeniFiltere = By.xpath("//button[@aria-label='Primeni filtere']");
    private static final By grid = By.xpath("//*[@id=\"__next\"]/div/div[3]/div/div/div[2]/div[1]/div/div/span/div/span");
    private static final By prviOglas = By.xpath("//div[@class='AdItem_name__Knlo6']");
    private static final By dodajUAdresar = By.xpath("//button[@class='Button_base__G3HTK Button_small__32ZFH ButtonTertiary2_tertiary2__AllBF ButtonTertiary2_small__2cEaR']");
    public static final By loginForma = By.xpath("//div[@class='LoginContent_register__IeA6K']");


    public HomePage(WebDriver driver) {
        super(driver);
        log.trace("new LoginPage()");
    }

    public HomePage open(boolean bVerify) {
        openUrl(LOGIN_PAGE_URL);
        log.debug("Open LoginPage(" + LOGIN_PAGE_URL + ")");
        if(bVerify) {
            verifyLoginPage();
        }
        return this;
    }

    public HomePage open() {
        return open(true);
    }

    public void verifyLoginPage() {
        log.debug("verifyLoginPage()");
        waitForExactUrl(LOGIN_PAGE_URL, Time.TIME_SHORT);
        waitUntilPageIsReady(Time.TIME_MEDIUM);
    }

    public void clickCloseLogInPopUp() {
        WebElement element = getWebElement(closeLogInPopUp);
        element.click();
    }
    public void openSearchFilters() {
        WebElement element = getWebElement(searchFilters);
        element.click();
    }
    public void openKategorija() {
        WebElement element = getWebElement(kategorija);
        element.click();
    }
    public void selectZenskaOdeca() {
        WebElement element = getWebElement(zenskaOdeca);
        element.click();
    }
    public void selectBluze() {
        WebElement element = getWebElement(bluze);
        element.click();
    }

    public void enterCenaOd ()
    {
        WebElement element = driver.findElement(By.id(cenaOd));
        typeTextToWebElement(element, "100");
    }
    public void selectDinCurrency ()
    {
        WebElement element = getWebElement(dinCurrency);
        element.click();
    }
    public void selectSamoSaCenom ()
    {
        WebElement element = getWebElement(samoSaCenom);
        element.click();
    }
    public void openStanjeFilter ()
    {
        WebElement element = getWebElement(stanjeFilter);
        element.click();
    }
    public void selectStanjeNovo ()
    {
        WebElement element = getWebElement(stanjeNovo);
        element.click();
    }
    public void selectStanjeKaoNovo ()
    {
        WebElement element = getWebElement(stanjeKaoNovo);
        element.click();
    }
    public void clickButtonPrimeniFiltere ()
    {
        WebElement element = getWebElement(buttonPrimeniFiltere);
        element.click();
    }
    public int getNumberOfResults ()
    {
        WebElement element = getWebElement(grid);
        System.out.println(element.getText());
        String text = element.getText();
        String[] texts = text.split(" ");
        String temp = texts[0];
        String sBrojRezultata;
        if (temp.contains(".")) {
            String string = temp.replace(".", "");
            sBrojRezultata = string;
        } else {
            sBrojRezultata = temp;
        }
        int i = Integer.parseInt(sBrojRezultata);
        return i;
    }
    public boolean resultsMoreThan (int i)
    {
        int iBrojRezultata = getNumberOfResults();
        if (iBrojRezultata > i)
        {
            System.out.println("Prikazano je vise rezultata od " + i );
            return true;
        }
        else
        {
            System.out.println("Prikazano je manje rezultata od " + i );
            return false;
        }
    }

    public void clickOnPrviOglas ()
    {
        WebElement element = getWebElement(prviOglas);
        element.click();
    }
    public void clickDodajUAdresar ()
    {
        WebElement element = getWebElement(dodajUAdresar);
        element.click();
    }
    public boolean isLoginVisible ()
    {
        WebElement element = getWebElement(loginForma);
        if (element.isDisplayed()) {
            System.out.println("Login forma je vidljiva.");
            return true;
        } else {
            System.out.println("Login forma nije vidljiva.");
            return false;
        }
    }


    public void refreshPage () {
        driver.navigate().refresh();
    }

}

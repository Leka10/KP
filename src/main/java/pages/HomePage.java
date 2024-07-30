package pages;

import data.Time;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import utils.PropertiesUtils;

import java.util.List;


public class HomePage extends CommonLoggedOutPage {

    private final String LOGIN_PAGE_URL = PropertiesUtils.getBaseUrl();

    private final By tryDifferentImageLocator = By.xpath("//div[@class='a-column a-span6 a-span-last a-text-right']");
    private final By searchFieldLocator = By.id("twotabsearchtextbox");
    private final By searchButtonLocator = By.id("nav-search-submit-button");
    private final By firstHatLocator = By.xpath("//div[@class='a-section aok-relative s-image-square-aspect'][1]");
    private final By sizeDropdownLocator = By.xpath("//span[@data-csa-c-content-id='dropdown_selected_size_name']");
    private final By availableSizeLocator = By.xpath("//li[@class='a-dropdown-item dropdownAvailable']");
    private final By quantityDropdownLocator = By.xpath("//div[@id='selectQuantity']");
    private final By quantity2Locator = By.xpath("//a[@data-value='{\"stringVal\":\"2\"}']");
    private final By quantity1Locator = By.xpath("//a[@data-value='{\"stringVal\":\"1\"}']");
    private final By addToCartButtonLocator = By.id("add-to-cart-button");
    private final By goToCartButtonLocator = By.id("sw-gtc");
    private final By numberOfItemsLocator = By.xpath("//div[@data-csa-c-type='item']");
    private final By quantityOfItemLocator = By.xpath("//span[@class='a-dropdown-prompt']");
    private final By priceOfItemLocator = By.xpath("//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap sc-product-price a-text-bold']");
    private final By totalItemsQuantityLocator = By.id("sc-subtotal-label-activecart");
    private final By totalItemsPriceLocator = By.id("sc-subtotal-amount-activecart");
    private final By quantityBoxOnCartLocator = By.id("a-autoid-3");

    private float fQuantityOfItem;
    float[] quantityPerItem = new float[10];


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

    public void clickOnTryDifferentImage() {
        try {
            WebElement clickOnTryDifferentImage = getWebElement(tryDifferentImageLocator);
            System.out.println("You are on robot verification page");
            clickOnTryDifferentImage.click();
        } catch (NoSuchElementException e) {
            System.out.println("You are on home page");
        }
    }
    public void typeToSearchFile(String string) {
        WebElement searchField = getWebElement(searchFieldLocator);
        clearAndTypeTextToWebElement(searchField, string);
    }
    public void clickOnSearchButton() {
        WebElement clickOnSearchButton = getWebElement(searchButtonLocator);
        waitUntilClickable(clickOnSearchButton);
        clickOnSearchButton.click();
    }
    public void searchForElement(String string) {
        log.debug("searchForElement(" + string + ")");
        typeToSearchFile(string);
        clickOnSearchButton();
    }
    public void clickOnFirstHat() {
        WebElement clickOnFirstHat = getWebElement(firstHatLocator);
//        waitUntilClickable(clickOnFirstHat);
        clickOnFirstHat.click();
    }
    public void clickOnSizeDropdown() {
        try {
            WebElement clickOnSizeDropdown = getWebElement(sizeDropdownLocator);
            System.out.println("Size dropdown is preset");
            clickOnSizeDropdown.click();
        } catch (NoSuchElementException e) {
            System.out.println("Its one sized item");
        }
    }

//    public void clickOnSizeDropdown2() {
//        boolean sizeDropdownPresent = driver.findElements(By.xpath("//select[@id='native_dropdown_selected_size_name']")).size() > 0;
//        if(sizeDropdownPresent) {
//            WebElement clickOnSizeDropdown = getWebElement(sizeDropdownLocator);
//            System.out.println("Size dropdown is present");
//            clickOnSizeDropdown.click();
//
//            selectAvailableSize();
//        }
//        else return;
//    }

    public void selectAvailableSize() {
        try {
            WebElement selectAvailableSize = getWebElement(availableSizeLocator);

            System.out.println("There is available size");
            selectAvailableSize.click();
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Size dropdown is not open or element is not clickable");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void clickOnQuantityDropdown() {
        WebElement clickOnQuantityDropdown = getWebElement(quantityDropdownLocator);
        clickOnQuantityDropdown.click();
    }
    public void selectQuantityTo1() {
        WebElement selectQuantityTo1 = getWebElement(quantity1Locator);
        selectQuantityTo1.click();
    }
    public void selectQuantityTo2() {
        WebElement selectQuantityTo2 = getWebElement(quantity2Locator);
        selectQuantityTo2.click();
    }
    public void clickOnAddToCartButton () {
        WebElement clickOnAddToCartButton = getWebElement(addToCartButtonLocator);
        clickOnAddToCartButton.click();
        System.out.println("Item is added to Cart!");
    }
    public void clickOnGoToCartButton() {
        WebElement clickOnGoToCartButton = getWebElement(goToCartButtonLocator);
        clickOnGoToCartButton.click();
        System.out.println("You are on Cart page!");
    }
    public int numberOfItems() {
        int numberOfItems = driver.findElements(numberOfItemsLocator).size();
        return numberOfItems;
    }

    public float quantityOfItem() {
        String quantityOfItem = "";
        float sum = 0;

        for (int i=1; i<=numberOfItems(); i++) {

//            WebElement element = getWebElement(By.xpath("(//div[@data-csa-c-type='item'][" + i + "]"));
//            quantityOfItem = getWebElement((By) quantityOfItemLocator).getText();
//            System.out.println("quantityOfItem: " + quantityOfItem);

            WebElement element = getWebElement(By.xpath("(//span[@class='a-dropdown-prompt'])[" + i + "]"));
            quantityOfItem = element.getText();
            fQuantityOfItem = Float.parseFloat(quantityOfItem);
            quantityPerItem[i - 1] = fQuantityOfItem;

            sum += Float.parseFloat(quantityOfItem);
        }
        System.out.println("Sum of items: " + sum);
        return sum;
        //return Float.parseFloat(quantityOfItem);
    }

    public float priceOfItem() {
        String priceOfItem = "";
        float sum = 0;

        for (int i=1; i<=numberOfItems(); i++) {
            WebElement element = getWebElement(By.xpath("(//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap sc-product-price a-text-bold'])" + "[" + i + "]"));
            priceOfItem = element.getText().substring(1);
            System.out.println("priceOfItem: " + priceOfItem);

            sum += Float.parseFloat(priceOfItem) * quantityPerItem[i-1];

        }
        System.out.println("Sum of price is: " + sum);
        return sum;
    }

    public boolean totalCart() {

        //compare number of elements
        WebElement totalQuant = getWebElement(totalItemsQuantityLocator);
        String sTotalQuantity = String.valueOf(totalQuant.getText().charAt(10));
        Float fTotalQuantity = Float.parseFloat(sTotalQuantity);
//        if(fTotalQuantity.equals(quantityOfItem())){
//            System.out.println("Quantity is correct");
//        }else {
//            System.out.println("Quantity is incorrect");
//        }

        WebElement totalPrice = getWebElement(totalItemsPriceLocator);
        String sTotalPrice = String.valueOf(totalPrice.getText().substring(2));
        Float fTotalPrice = Float.parseFloat(sTotalPrice);
//        if(fTotalPrice.equals(priceOfItem())){
//            System.out.println("Price is correct");
//        }else {
//            System.out.println("Price is incorrect");
//        }


        if(fTotalQuantity.equals(quantityOfItem()) && fTotalPrice.equals(priceOfItem())){
            System.out.println("Total quantity and total price are correct!");
            return true;
        }else {
            System.out.println("Total quantity and total price are incorrect!");
            return false;
        }

    }
    public void clickOnDropdownBoxOnCartPage () {

        WebElement clickOnDropdownBox = getWebElement(quantityBoxOnCartLocator);
        clickOnDropdownBox.click();

    }
    public void changeQuantityTo1 () {

        WebElement changeQuantityTo1 = getWebElement(quantity1Locator);
        changeQuantityTo1.click();

    }

    public void refreshPage () {
        driver.navigate().refresh();
    }

}

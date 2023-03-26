import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class FirstTest_before_refactoring {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone 14");
        capabilities.setCapability("platformVersion", "16.2");
        capabilities.setCapability("app", "/Users/vladimirevdokimov/Desktop/JavaAppiumAutomation/JavaAppiumAutomation/apks/Wikipedia.app");

        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testSeachAndClear()
    {
        waitElementAndClick(
                By.xpath("//XCUIElementTypeStaticText[@name=\"Skip\"]"),
                "not find button Skip",
                3
        );
        waitElementAndClick(
                By.id("Search Wikipedia"),
                "not find input to search",
                3
        );

        waitElementAndSendKeys(
                By.id("Search Wikipedia"),
                "Java",
                "not find input to send keys",
                3
        );

        waitForElementAndClear(
                By.id("Search Wikipedia"),
                "not find request for clear",
                3
        );
        waitElementAndClick(
                By.xpath("//XCUIElementTypeStaticText[@name=\"Cancel\"]"),
                "not find button Cancel on page",
                3
        );
        waitElementNotPresent(
                By.xpath("//XCUIElementTypeStaticText[@name=\"Cancel\"]"),
                "found button Cancel on page",
                3
        );
    }

@Test
public void testCompareArticleTitle()
{
    waitElementAndClick(
            By.xpath("//XCUIElementTypeStaticText[@name=\"Skip\"]"),
            "not find button Skip",
            3
    );
    waitElementAndClick(
            By.id("Search Wikipedia"),
            "not find input to search",
            3
    );

    waitElementAndSendKeys(
            By.id("Search Wikipedia"),
            "Appium",
            "not find input to send keys",
            3
    );
    waitElementAndClick(
            By.id("Automation for Apps"),
            "not find input to search",
            3

    );
    WebElement titleElement = waitElementPresent(
            By.id("The program was"),
            "not find text in page",
            15
    );

    String article_text = titleElement.getAttribute("value");
    Assert.assertEquals(
            "unexpected text",
            "The program was",
            article_text
    );
    }

    @Test
    public void swipeArticle()
    {
        waitElementAndClick(
                By.xpath("//XCUIElementTypeStaticText[@name=\"Skip\"]"),
                "not find button Skip",
                3
        );

        waitElementAndClick(
                By.id("Search Wikipedia"),
                "not find input to search",
                3
        );

        waitElementAndSendKeys(
                By.id("Search Wikipedia"),
                "Appium",
                "not find input to send keys",
                3
        );
        waitElementAndClick(
                By.id("Automation for Apps"),
                "not find input to search",
                3
        );
        swipeAndFindElement(By.xpath("//XCUIElementTypeStaticText[@name=\"View article in browser\"]"),
                "can`t find end of article",
                5
        );

    }

    @Test
    public void addArticleInListAndDelete()
    {
        waitElementAndClick(
                By.xpath("//XCUIElementTypeStaticText[@name=\"Skip\"]"),
                "not find button Skip",
                3
        );

        waitElementAndClick(
                By.id("Search Wikipedia"),
                "not find input to search",
                3
        );

        waitElementAndSendKeys(
                By.id("Search Wikipedia"),
                "Appium",
                "not find input to send keys",
                3
        );
        waitElementAndClick(
                By.id("Automation for Apps"),
                "not find Article by request",
                3
        );
        waitElementAndClick(
                By.id("Save for later"),
                "not find button Save for later",
                3
        );
        waitElementAndClick(
                By.xpath("//XCUIElementTypeApplication[@name=\"Wikipedia\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeImage"),
                "not find button Add at list",
                3
        );
        waitElementAndClick(
                By.xpath("//XCUIElementTypeStaticText[@name=\"Create a new list\"]"),
                "not find button Create new List",
                3
        );
        waitElementAndSendKeys(
                By.xpath("//XCUIElementTypeApplication[@name=\"Wikipedia\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[3]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeTextField"),
                "For read later",
                "not find input to send Name of list",
                3
        );
        waitElementAndClick(
                By.xpath("//XCUIElementTypeStaticText[@name=\"Create reading list\"]"),
                "not find button Create reading List",
                3
        );
        waitElementAndClick(
                By.id("Back"),
                "not find button Back",
                3
        );
        waitElementAndClick(
                By.xpath("//XCUIElementTypeStaticText[@name=\"Cancel\"]"),
                "not find button Cancel",
                3
        );
        waitElementAndClick(
                By.id("Saved"),
                "not find button Saved",
                3
        );
        waitElementAndClick(
                By.id("Close"),
                "not find button Close in popup",
                3
        );
        waitElementPresent(
                By.id("For read later"),
                "Not find List in Saved",
                3
        );
        swipeElementToLeft(
                By.id("For read later"),
                "not find article to swipe for delete"
        );
        waitElementAndClick(
                By.id("swipe action delete"),
                "not find button Delete",
                3
        );
        waitElementAndClick(
                By.id("Unsave"),
                "not find button Unsave",
                3
        );
        waitElementPresent(
                By.id("No saved pages yet"),
                "List is not empty",
                3
        );


    }
    @Test
    public void testAmountOfNotEmptySearch()
    {
        waitElementAndClick(
                By.xpath("//XCUIElementTypeStaticText[@name=\"Skip\"]"),
                "not find button Skip",
                3
        );

        waitElementAndClick(
                By.id("Search Wikipedia"),
                "not find input to search",
                3
        );

        String searchRequest = "Apple";
        String search_locator = "//XCUIElementTypeCollectionView/XCUIElementTypeOther";

        waitElementAndSendKeys(
                By.id("Search Wikipedia"),
                searchRequest,
                "not find input to send keys",
                3
        );
        waitElementPresent(
                By.xpath(search_locator),
                "uncorrected search_locator" + search_locator,
                10
        );
        int amount_of_search_resault = getAmountOfArticle(
                By.xpath(search_locator)
        );
        Assert.assertTrue(
                "we found too few results",
                amount_of_search_resault>0);


    }
    @Test
    public void amountOfEmptySearch()
    {
        waitElementAndClick(
                By.xpath("//XCUIElementTypeStaticText[@name=\"Skip\"]"),
                "not find button Skip",
                3
        );

        waitElementAndClick(
                By.id("Search Wikipedia"),
                "not find input to search",
                3
        );
        String search_locator = "//XCUIElementTypeCollectionView/XCUIElementTypeOther";
        String searchRequest = "xbgfdghra";


        waitElementAndSendKeys(
                By.id("Search Wikipedia"),
                searchRequest,
                "not find input to send keys",
                3
        );
        String empty_result_label = "No results found";

        waitElementPresent(
                By.id(empty_result_label),
                "not find empty result by the request " + searchRequest,
                10
        );
        assertElementNotPresent(
                By.id(search_locator),
                "we found any article by request" + searchRequest
        );

    }
    @Test
    public void testForRotation()
    {
        waitElementAndClick(
                By.xpath("//XCUIElementTypeStaticText[@name=\"Skip\"]"),
                "not find button Skip",
                3
        );

        waitElementAndClick(
                By.id("Search Wikipedia"),
                "not find input to search",
                3
        );

        String searchRequest = "appium";

        waitElementAndSendKeys(
                By.id("Search Wikipedia"),
                searchRequest,
                "not find input to send keys",
                3
        );
        waitElementAndClick(
                By.id("Automation for Apps"),
                "not find Article by request" + searchRequest,
                3
        );
        String title_before_rotation = waitForElementAndGetAttribute(
                By.xpath("(//XCUIElementTypeStaticText[@name=\"Appium\"])[1]"),
                "name",
                "Can`t find title of Article",
                5
        );
        driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = waitForElementAndGetAttribute(
                By.xpath("(//XCUIElementTypeStaticText[@name=\"Appium\"])[1]"),
                "name",
                "Can`t find title of Article",
                5
        );
        Assert.assertEquals(
                "title not equals after rotation",
                title_before_rotation,
                title_after_rotation
        );
        driver.rotate(ScreenOrientation.PORTRAIT);

        String title_after_second_rotation = waitForElementAndGetAttribute(
                By.xpath("(//XCUIElementTypeStaticText[@name=\"Appium\"])[1]"),
                "name",
                "Can`t find title of Article",
                5
        );
        Assert.assertEquals(
                "title not equals after second rotation",
                title_after_rotation,
                title_after_second_rotation
        );

    }
    @Test
    public void testExitOnBackground()
    {
        waitElementAndClick(
                By.xpath("//XCUIElementTypeStaticText[@name=\"Skip\"]"),
                "not find button Skip",
                3
        );

        waitElementAndClick(
                By.id("Search Wikipedia"),
                "not find input to search",
                3
        );

        waitElementAndSendKeys(
                By.id("Search Wikipedia"),
                "Appium",
                "not find input to send keys",
                3
        );
        waitElementPresent(
                By.id("Automation for Apps"),
                "not find input to search",
                3
        );
        driver.runAppInBackground(3);

        waitElementPresent(
                By.id("Automation for Apps"),
                "not find article after returning from background",
                3
        );
    }





    // Метод. Поиск элемента на странице с ожиданием
    private WebElement waitElementPresent(By by, String error_message, long timeoutInSecond)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    // Метод. Поиск элемента на странице без ожидания
    private WebElement waitElementPresent(By by, String error_message)
    {
        return waitElementPresent(by,error_message,5);
    }

    // Метод. Поиск элемента на странице и клик по нему
    private WebElement waitElementAndClick(By by, String error_message, long timeoutInSecond)
    {
    WebElement element = waitElementPresent(by, error_message, timeoutInSecond);
    element.click();
    return element;
    }

    // Метод. Поиск элемента на странице и ввод значения
    private WebElement waitElementAndSendKeys(By by, String value, String error_message, long timeoutInSecond)
    {
        WebElement element = waitElementPresent(by, error_message, timeoutInSecond);
        element.sendKeys(value);
        return element;
    }

    // Метод. Проверка отсутствия элемента на странице
    private boolean waitElementNotPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    // Метод. Поиск элемента и его очистка
    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSecond)
    {
        WebElement element = waitElementPresent(by, error_message, timeoutInSecond);
        element.clear();
        return element;
    }

    // Метод. Свайп вверх
    protected void swipeUp(int timeOfSwipe)
    {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width/2;
        int start_y = (int)(size.height*0.8);
        int end_y = (int)(size.height*0.2);

        action.
                press(x,start_y).
                waitAction(timeOfSwipe).
                moveTo(x,end_y).
                release().
                perform();
    }
    //Метод. Быстрый свайп
    protected void swipeUpQuick ()
    {
        swipeUp(200);
    }

    // Метод. Свайп и поиск элемента на странице
    protected void swipeAndFindElement(By by,String error_message, int maxSwipes)
    {
        int already_swipes = 0;
        while (driver.findElements(by).size() == 0){
            if (already_swipes > maxSwipes){
                waitElementPresent(by,"can`t find element by swipe, \n" + error_message,0);
            return;
            }
        swipeUpQuick();
        ++already_swipes;
        }

    }
    //Метод. Свайп вправо
    protected void swipeElementToLeft(By by, String error_message)
    {
       WebElement element =  waitElementPresent(
               by,
               error_message,
               5);
       int left_x = element.getLocation().getX();
       int right_x = left_x + element.getSize().getWidth();
       int upper_y = element.getLocation().getY();
       int lower_y = upper_y + element.getSize().getHeight();
       int middle_y = (upper_y + lower_y) / 2;

       TouchAction action = new TouchAction(driver);
       action.
               press(right_x,middle_y).
               waitAction(150).
               moveTo(left_x,middle_y).
               release().
               perform();


    }
    // Метод. Подсчет количества найденных статей
    private int getAmountOfArticle(By by)
    {
        List elements = driver.findElements(by);
        return elements.size();


    }
    // Метод. Нет результатов поиска
    private void assertElementNotPresent(By by, String error_message)
    {
        int amount_of_elements = getAmountOfArticle(by);
        if (amount_of_elements > 0) {
           String default_message = "An elements" + by.toString() + "suppoused to be not present";
           throw new AssertionError(default_message + "" + error_message);
        }
    }
    // Метод.Получение аттрибута элемента
    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds){

        WebElement element = waitElementPresent(by,error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

}



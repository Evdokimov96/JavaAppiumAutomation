package Libs.UI;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject_Methods
{
    protected AppiumDriver driver;
    public MainPageObject_Methods(AppiumDriver driver)
    {
        this.driver = driver;
    }

    // Метод. Поиск элемента на странице с ожиданием
    public WebElement waitElementPresent(String locator, String error_message, long timeoutInSecond)
    {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    // Метод. Поиск элемента на странице без ожидания
    public WebElement waitElementPresent(String locator, String error_message)
    {
        return waitElementPresent(locator,error_message,5);
    }

    // Метод. Поиск элемента на странице и клик по нему
    public WebElement waitElementAndClick(String locator, String error_message, long timeoutInSecond)
    {
        WebElement element = waitElementPresent(locator, error_message, timeoutInSecond);
        element.click();
        return element;
    }

    // Метод. Поиск элемента на странице и ввод значения
    public WebElement waitElementAndSendKeys(String locator, String value, String error_message, long timeoutInSecond)
    {
        WebElement element = waitElementPresent(locator, error_message, timeoutInSecond);
        element.sendKeys(value);
        return element;
    }

    // Метод. Проверка отсутствия элемента на странице
    public boolean waitElementNotPresent(String locator, String error_message, long timeoutInSeconds)
    {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    // Метод. Поиск элемента и его очистка
    public WebElement waitForElementAndClear(String locator, String error_message, long timeoutInSecond)
    {
        WebElement element = waitElementPresent(locator, error_message, timeoutInSecond);
        element.clear();
        return element;
    }

    // Метод. Свайп вверх
    public void swipeUp(int timeOfSwipe)
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
    public void swipeUpQuick ()
    {
        swipeUp(200);
    }

    // Метод. Свайп и поиск элемента на странице
    public void swipeAndFindElement(String locator,String error_message, int maxSwipes)
    {
        By by = this.getLocatorByString(locator);
        int already_swipes = 0;
        while (driver.findElements(by).size() == 0){
            if (already_swipes > maxSwipes){
                waitElementPresent(String.valueOf(by),"can`t find element by swipe, \n" + error_message,0);
                return;
            }
            swipeUpQuick();
            ++already_swipes;
        }

    }
    //Метод. Свайп вправо
    public void swipeElementToLeft(String locator, String error_message)
    {
        WebElement element =  waitElementPresent(
                locator,
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
    public int getAmountOfArticle(String locator)
    {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();


    }
    // Метод. Нет результатов поиска
    public void assertElementNotPresent(String locator, String error_message)
    {
        int amount_of_elements = getAmountOfArticle(locator);
        if (amount_of_elements > 0) {
            String default_message = "An elements" + locator + "supposed to be not present";
            throw new AssertionError(default_message + "" + error_message);
        }
    }
    // Метод.Получение аттрибута элемента
    public String waitForElementAndGetAttribute(String locator, String attribute, String error_message, long timeoutInSeconds){

        WebElement element = waitElementPresent(locator,error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    // Метод. Определение типа локатора
    private By getLocatorByString(String locator_with_type)
    {
         String[] exploded_locator = locator_with_type.split(Pattern.quote(":"), 2);
         String by_type = exploded_locator[0];
         String locator = exploded_locator[1];

         if (by_type.equals("xpath")){
             return By.xpath(locator);
         } else if (by_type.equals("id")){
             return By.id(locator);
         } else{
             throw new IllegalArgumentException("Can`t get type of locator. Locator: " + locator_with_type);
         }
     }
}

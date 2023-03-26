package Libs.UI;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject_Methods
{
    private static final String
        TITLE = "The program was",
        ELEMENT_ON_FOOTER = "//XCUIElementTypeStaticText[@name=\"View article in browser\"]",
        SAVE_FOR_LATER = "Save for later",
        ADD_AT_LIST = "//XCUIElementTypeApplication[@name=\"Wikipedia\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeImage",
        CREATE_LIST = "//XCUIElementTypeStaticText[@name=\"Create a new list\"]",
        INPUT_FOR_NAME_LIST = "//XCUIElementTypeApplication[@name=\"Wikipedia\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[3]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeTextField",
        BUTTON_CREATE = "//XCUIElementTypeStaticText[@name=\"Create reading list\"]";





    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitElementPresent(By.id(TITLE),"not find text in page",3);
    }
    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter()
    {
        this.swipeAndFindElement(By.xpath(ELEMENT_ON_FOOTER),"can`t find end of article",5);
    }
    public void addArticleAtMyList(String nameOfList)
    {
        this.waitElementAndClick(
                By.id(SAVE_FOR_LATER),
                "not find button Save for later",
                3
        );
        this.waitElementAndClick(
                By.xpath(ADD_AT_LIST),
                "not find button Add at list",
                3
        );
        this.waitElementAndClick(
                By.xpath(CREATE_LIST),
                "not find button Create new List",
                3
        );
        this.waitElementAndSendKeys(
                By.xpath(INPUT_FOR_NAME_LIST),
                "For read later",
                "not find input to send Name of list",
                3
        );
        this.waitElementAndClick(
                By.xpath(BUTTON_CREATE),
                "not find button Create reading List",
                3
        );
    }
}

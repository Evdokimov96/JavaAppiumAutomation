package Libs.UI;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject_Methods
{
    private static final String
        TITLE = "id:The program was",
        ELEMENT_ON_FOOTER = "xpath://XCUIElementTypeStaticText[@name=\"View article in browser\"]",
        SAVE_FOR_LATER = "id:Save for later",
        ADD_AT_LIST = "xpath://XCUIElementTypeApplication[@name=\"Wikipedia\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeImage",
        CREATE_LIST = "xpath://XCUIElementTypeStaticText[@name=\"Create a new list\"]",
        INPUT_FOR_NAME_LIST = "xpath://XCUIElementTypeApplication[@name=\"Wikipedia\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[3]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeTextField",
        BUTTON_CREATE = "xpath://XCUIElementTypeStaticText[@name=\"Create reading list\"]";





    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitElementPresent(TITLE,"not find text in page",3);
    }
    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter()
    {
        this.swipeAndFindElement(
                ELEMENT_ON_FOOTER,"can`t find end of article",5);
    }
    public void addArticleAtMyList(String nameOfList)
    {
        this.waitElementAndClick(
                SAVE_FOR_LATER,
                "not find button Save for later",
                3
        );
        this.waitElementAndClick(
                ADD_AT_LIST,
                "not find button Add at list",
                3
        );
        this.waitElementAndClick(
                CREATE_LIST,
                "not find button Create new List",
                3
        );
        this.waitElementAndSendKeys(
                INPUT_FOR_NAME_LIST,
                "For read later",
                "not find input to send Name of list",
                3
        );
        this.waitElementAndClick(
                BUTTON_CREATE,
                "not find button Create reading List",
                3
        );
    }
}

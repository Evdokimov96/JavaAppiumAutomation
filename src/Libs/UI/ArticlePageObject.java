package Libs.UI;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject_Methods
{
    private static final String
        TITLE = "The program was",
        ELEMENT_ON_FOOTER = "//XCUIElementTypeStaticText[@name=\"View article in browser\"]";

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

}

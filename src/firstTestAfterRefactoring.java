import Libs.CoreTestCase;
import Libs.UI.*;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;


public class firstTestAfterRefactoring extends CoreTestCase {

    private MainPageObject_Methods MainPageObject_Methods;
    protected void setUp() throws Exception
    {
        super.setUp();
        MainPageObject_Methods = new MainPageObject_Methods(driver);
    }

    @Test
    public void testSearchArticle()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.skipTutorial();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.waitForSearchResult("Automation for Apps");
    }

    @Test
    public void testSearchAndClear()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.skipTutorial();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelButton();
        SearchPageObject.waitForCancelButtonToDisappear();

    }

@Test
public void testCompareArticleTitle()
{
    SearchPageObject SearchPageObject = new SearchPageObject(driver);
    SearchPageObject.skipTutorial();
    SearchPageObject.initSearchInput();
    SearchPageObject.typeSearchLine("Appium");
    SearchPageObject.clickByArticlWithSubstring("Automation for Apps");

    ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
    String article_text = ArticlePageObject.getArticleTitle();

    Assert.assertEquals(
            "unexpected text",
            "The program was",
            article_text
    );
    }

    @Test
    public void testSwipeArticle()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.skipTutorial();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticlWithSubstring("Automation for Apps");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();

    }

    @Test
    public void testAddArticleInListAndDelete()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.skipTutorial();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticlWithSubstring("Automation for Apps");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();

        String nameOfList = "For read later";
        ArticlePageObject.addArticleAtMyList(nameOfList);

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.exitInMainMenu();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openListsForReadLater(nameOfList);
        MyListsPageObject.swipeListForDelete(nameOfList);


    }
    @Test
    public void testAmountOfNotEmptySearch()
    {
        MainPageObject_Methods.waitElementAndClick(
                By.xpath("//XCUIElementTypeStaticText[@name=\"Skip\"]"),
                "not find button Skip",
                3
        );

        MainPageObject_Methods.waitElementAndClick(
                By.id("Search Wikipedia"),
                "not find input to search",
                3
        );

        String searchRequest = "Apple";
        String search_locator = "//XCUIElementTypeCollectionView/XCUIElementTypeOther";

        MainPageObject_Methods.waitElementAndSendKeys(
                By.id("Search Wikipedia"),
                searchRequest,
                "not find input to send keys",
                3
        );
        MainPageObject_Methods.waitElementPresent(
                By.xpath(search_locator),
                "uncorrected search_locator" + search_locator,
                10
        );
        int amount_of_search_resault = MainPageObject_Methods.getAmountOfArticle(
                By.xpath(search_locator)
        );
        Assert.assertTrue(
                "we found too few results",
                amount_of_search_resault>0);


    }
    @Test
    public void testAmountOfEmptySearch()
    {
        MainPageObject_Methods.waitElementAndClick(
                By.xpath("//XCUIElementTypeStaticText[@name=\"Skip\"]"),
                "not find button Skip",
                3
        );

        MainPageObject_Methods.waitElementAndClick(
                By.id("Search Wikipedia"),
                "not find input to search",
                3
        );
        String search_locator = "//XCUIElementTypeCollectionView/XCUIElementTypeOther";
        String searchRequest = "xbgfdghra";


        MainPageObject_Methods.waitElementAndSendKeys(
                By.id("Search Wikipedia"),
                searchRequest,
                "not find input to send keys",
                3
        );
        String empty_result_label = "No results found";

        MainPageObject_Methods.waitElementPresent(
                By.id(empty_result_label),
                "not find empty result by the request " + searchRequest,
                10
        );
        MainPageObject_Methods.assertElementNotPresent(
                By.id(search_locator),
                "we found any article by request" + searchRequest
        );

    }
    @Test
    public void testForRotation()
    {
        MainPageObject_Methods.waitElementAndClick(
                By.xpath("//XCUIElementTypeStaticText[@name=\"Skip\"]"),
                "not find button Skip",
                3
        );

        MainPageObject_Methods.waitElementAndClick(
                By.id("Search Wikipedia"),
                "not find input to search",
                3
        );

        String searchRequest = "appium";

        MainPageObject_Methods.waitElementAndSendKeys(
                By.id("Search Wikipedia"),
                searchRequest,
                "not find input to send keys",
                3
        );
        MainPageObject_Methods.waitElementAndClick(
                By.id("Automation for Apps"),
                "not find Article by request" + searchRequest,
                3
        );
        String title_before_rotation = MainPageObject_Methods.waitForElementAndGetAttribute(
                By.xpath("(//XCUIElementTypeStaticText[@name=\"Appium\"])[1]"),
                "text",
                "Can`t find title of Article",
                5
        );
        driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = MainPageObject_Methods.waitForElementAndGetAttribute(
                By.xpath("(//XCUIElementTypeStaticText[@name=\"Appium\"])[1]"),
                "text",
                "Can`t find title of Article",
                5
        );
        Assert.assertEquals(
                "title not equals after rotation",
                title_before_rotation,
                title_after_rotation
        );
        driver.rotate(ScreenOrientation.PORTRAIT);

        String title_after_second_rotation = MainPageObject_Methods.waitForElementAndGetAttribute(
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
        MainPageObject_Methods.waitElementAndClick(
                By.xpath("//XCUIElementTypeStaticText[@name=\"Skip\"]"),
                "not find button Skip",
                3
        );

        MainPageObject_Methods.waitElementAndClick(
                By.id("Search Wikipedia"),
                "not find input to search",
                3
        );

        MainPageObject_Methods.waitElementAndSendKeys(
                By.id("Search Wikipedia"),
                "Appium",
                "not find input to send keys",
                3
        );
        MainPageObject_Methods.waitElementPresent(
                By.id("Automation for Apps"),
                "not find input to search",
                3
        );
        driver.runAppInBackground(3);

        MainPageObject_Methods.waitElementPresent(
                By.id("Automation for Apps"),
                "not find article after returning from background",
                3
        );
    }
}



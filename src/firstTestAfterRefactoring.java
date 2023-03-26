import Libs.CoreTestCase;
import Libs.UI.*;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;


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
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.skipTutorial();
        SearchPageObject.initSearchInput();
        String searchRequest = "Appium";
        SearchPageObject.typeSearchLine(searchRequest);
        int amount_of_search_result = SearchPageObject.getAmountOfFoundArticles();
        Assert.assertTrue(
                "was found few results",
                amount_of_search_result>0);
    }
    @Test
    public void testAmountOfEmptySearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.skipTutorial();
        SearchPageObject.initSearchInput();
        String searchRequest = "xbgfdghra";

        SearchPageObject.typeSearchLine(searchRequest);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.waitForNotEmptyResultsLabel();

    }
    @Test
    public void testForRotation()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.skipTutorial();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticlWithSubstring("Automation for Apps");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        String title_before_rotation = ArticlePageObject.getArticleTitle();
        this.rotateScreenPortrait();
        driver.rotate(ScreenOrientation.LANDSCAPE);
        String title_after_rotation = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "title not equals after rotation",
                title_before_rotation,
                title_after_rotation
        );

        this.rotateScreenLandscape();
        driver.rotate(ScreenOrientation.PORTRAIT);
        String title_after_second_rotation = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "title not equals after second rotation",
                title_after_rotation,
                title_after_second_rotation
        );

    }
    @Test
    public void testExitOnBackground()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.skipTutorial();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.waitForSearchResult("Automation for Apps");

        this.exitInBackground(2);

        SearchPageObject.waitForSearchResult("Automation for Apps");
    }
}



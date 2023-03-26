package tests;

import Libs.CoreTestCase;
import Libs.UI.ArticlePageObject;
import Libs.UI.SearchPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase
{
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

        assertEquals(
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


}

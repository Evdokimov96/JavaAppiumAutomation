package tests;

import Libs.CoreTestCase;
import Libs.UI.ArticlePageObject;
import Libs.UI.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.ScreenOrientation;

public class ChangeAppConditionTest extends CoreTestCase
{
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

        assertEquals(
                "title not equals after rotation",
                title_before_rotation,
                title_after_rotation
        );

        this.rotateScreenLandscape();
        driver.rotate(ScreenOrientation.PORTRAIT);
        String title_after_second_rotation = ArticlePageObject.getArticleTitle();

        assertEquals(
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

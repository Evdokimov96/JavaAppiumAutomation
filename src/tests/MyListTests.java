package tests;

import Libs.CoreTestCase;
import Libs.UI.ArticlePageObject;
import Libs.UI.MyListsPageObject;
import Libs.UI.NavigationUI;
import Libs.UI.SearchPageObject;
import org.junit.Test;

public class MyListTests extends CoreTestCase
{
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
}

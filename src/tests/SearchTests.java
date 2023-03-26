package tests;

import Libs.CoreTestCase;
import Libs.UI.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase
{
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
    public void testAmountOfNotEmptySearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.skipTutorial();
        SearchPageObject.initSearchInput();
        String searchRequest = "Appium";
        SearchPageObject.typeSearchLine(searchRequest);
        int amount_of_search_result = SearchPageObject.getAmountOfFoundArticles();
        assertTrue(
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
}

package Libs.UI;

import io.appium.java_client.AppiumDriver;

public class SearchPageObject extends MainPageObject_Methods
{
    private static final String
        SKIP_TUTORIAL = "xpath://XCUIElementTypeStaticText[@name=\"Skip\"]",
        SEARCH_INPUT = "id:Search Wikipedia",
        SEARCH_RESULT_BY_SUBSTRING_TPL = "id:SUBSTRING",
        SEARCH_CANCEL_BUTTON = "xpath://XCUIElementTypeStaticText[@name=\"Cancel\"]",
        search_locator = "//XCUIElementTypeCollectionView/XCUIElementTypeOther",
        empty_result_label = "id:No results found";

    public SearchPageObject(AppiumDriver driver)
   {
       super(driver);
   }



   /*Templates methods*/
   private static String getResultSearchElement(String substring)
   {
       return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("SUBSTRING",substring);
   }
    /*Templates methods*/


   public void skipTutorial()
   {
       this.waitElementAndClick(SKIP_TUTORIAL,"not find button Skip",3);
       this.assertElementNotPresent(SKIP_TUTORIAL,"button Skip is find");
   }

   public void initSearchInput()
   {
       this.waitElementAndClick(SEARCH_INPUT, "can`t find input to click",5 );
       this.waitElementPresent(SEARCH_INPUT,"can`t find input click on input");
   }
   public void typeSearchLine(String search_line)
   {
       this.waitElementAndSendKeys(SEARCH_INPUT,search_line,"not find input to send keys",3);
   }
   public void waitForSearchResult(String substring)
   {
       String search_result_id = getResultSearchElement(substring);
       this.waitElementPresent(search_result_id,"not find search result with substring" + substring,3);
   }

   public void  waitForCancelButtonToAppear()
   {
    this.waitElementPresent(SEARCH_CANCEL_BUTTON,"not find button Cancel on page",3);
   }

    public void  waitForCancelButtonToDisappear()
    {
        this.waitElementNotPresent(SEARCH_CANCEL_BUTTON,"button Cancel on page",3);
    }

    public void clickCancelButton()
    {
        this.waitElementAndClick(SEARCH_CANCEL_BUTTON,"not find button Cancel to click",3);
    }

    public void clickByArticlWithSubstring(String substring)
    {
        String search_result_id = getResultSearchElement(substring);
        this.waitElementAndClick(search_result_id,"not find and click search result with substring" + substring,3);
    }
    public int getAmountOfFoundArticles()
    {
        this.waitElementPresent(
                search_locator,
                "uncorrected search_locator" + search_locator,
                10
        );
        return this.getAmountOfArticle(search_locator);

    }
    public void waitForEmptyResultsLabel()
    {
        this.waitElementPresent(
                empty_result_label,
                "not find empty result by the request ",
                10
        );
    }
    public void waitForNotEmptyResultsLabel()
    {
        this.assertElementNotPresent(
                search_locator,
                "we found any article by request"
        );
    }
}

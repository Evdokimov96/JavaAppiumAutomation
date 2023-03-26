package Libs.UI;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject_Methods
{
    private static final String
        SKIP_TUTORIAL = "//XCUIElementTypeStaticText[@name=\"Skip\"]",
        SEARCH_INPUT = "Search Wikipedia",
        SEARCH_RESULT_BY_SUBSTRING_TPL = "SUBSTRING",
        SEARCH_CANCEL_BUTTON = "//XCUIElementTypeStaticText[@name=\"Cancel\"]";

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
       this.waitElementAndClick(By.xpath(SKIP_TUTORIAL),"not find button Skip",3);
       this.assertElementNotPresent(By.xpath(SKIP_TUTORIAL),"button Skip is find");
   }

   public void initSearchInput()
   {
       this.waitElementAndClick(By.id(SEARCH_INPUT), "can`t find input to click",5 );
       this.waitElementPresent(By.id(SEARCH_INPUT),"can`t find input click on input");
   }
   public void typeSearchLine(String search_line)
   {
       this.waitElementAndSendKeys(By.id(SEARCH_INPUT),search_line,"not find input to send keys",3);
   }
   public void waitForSearchResult(String substring)
   {
       String search_result_id = getResultSearchElement(substring);
       this.waitElementPresent(By.id(search_result_id),"not find search result with substring" + substring,3);
   }

   public void  waitForCancelButtonToAppear()
   {
    this.waitElementPresent(By.xpath(SEARCH_CANCEL_BUTTON),"not find button Cancel on page",3);
   }

    public void  waitForCancelButtonToDisappear()
    {
        this.waitElementNotPresent(By.xpath(SEARCH_CANCEL_BUTTON),"button Cancel on page",3);
    }

    public void clickCancelButton()
    {
        this.waitElementAndClick(By.xpath(SEARCH_CANCEL_BUTTON),"not find button Cancel to click",3);
    }

}

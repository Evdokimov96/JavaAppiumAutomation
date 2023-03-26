package Libs.UI;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject_Methods
{
    private static final String
            NAME_OF_LIST = "Text",
            BUTTON_SAVED = "Saved",
            BUTTON_CLOSE = "Close",
            BUTTON_DELETE = "swipe action delete",
            BUTTON_UNSAVE = "Unsave",
            TEXT_OF_ZAGLUSHKA = "No saved pages yet";


    private static String getNameOfListByID(String nameOfList)
    {
        return NAME_OF_LIST.replace("Text",nameOfList);
    }

    public MyListsPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void openListsForReadLater(String nameOfList)
    {
        this.waitElementAndClick(
                By.id(BUTTON_SAVED),
                "not find button Saved",
                3
        );
        this.waitElementAndClick(
                By.id(BUTTON_CLOSE),
                "not find button Close in popup",
                3
        );
        this.waitElementPresent(
                By.id(nameOfList),
                "Not find List in Saved",
                3
        );
        String list_name_id = getNameOfListByID(nameOfList);

    }

    public void swipeListForDelete(String nameOfList)
    {
        String list_name_id = getNameOfListByID(nameOfList);

        this.swipeElementToLeft(
                By.id(list_name_id),
                "not find article to swipe for delete"
        );
        this.waitElementAndClick(
                By.id(BUTTON_DELETE),
                "not find button Delete",
                3
        );
        this.waitElementAndClick(
                By.id(BUTTON_UNSAVE),
                "not find button Unsave",
                3
        );
        this.waitElementPresent(
                By.id(TEXT_OF_ZAGLUSHKA),
                "List is not empty",
                3
        );
    }


}


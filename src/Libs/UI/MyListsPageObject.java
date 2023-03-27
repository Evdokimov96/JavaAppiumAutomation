package Libs.UI;

import io.appium.java_client.AppiumDriver;

public class MyListsPageObject extends MainPageObject_Methods
{
    private static final String
            NAME_OF_LIST = "id:Text",
            BUTTON_SAVED = "id:Saved",
            BUTTON_CLOSE = "id:Close",
            BUTTON_DELETE = "id:swipe action delete",
            BUTTON_UNSAVE = "id:Unsave",
            TEXT_OF_ZAGLUSHKA = "id:No saved pages yet";


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
                BUTTON_SAVED,
                "not find button Saved",
                3
        );
        this.waitElementAndClick(
                BUTTON_CLOSE,
                "not find button Close in popup",
                3
        );
        this.waitElementPresent(
                nameOfList,
                "Not find List in Saved",
                3
        );
        String list_name_id = getNameOfListByID(nameOfList);

    }

    public void swipeListForDelete(String nameOfList)
    {
        String list_name_id = getNameOfListByID(nameOfList);

        this.swipeElementToLeft(
                list_name_id,
                "not find article to swipe for delete"
        );
        this.waitElementAndClick(
                BUTTON_DELETE,
                "not find button Delete",
                3
        );
        this.waitElementAndClick(
                BUTTON_UNSAVE,
                "not find button Unsave",
                3
        );
        this.waitElementPresent(
                TEXT_OF_ZAGLUSHKA,
                "List is not empty",
                3
        );
    }


}


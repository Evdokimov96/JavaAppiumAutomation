package Libs.UI;

import io.appium.java_client.AppiumDriver;

public class NavigationUI extends MainPageObject_Methods{

    private static final String
            BACK = "id:Back",
            CANCEL = "xpath://XCUIElementTypeStaticText[@name=\"Cancel\"]";


    public NavigationUI(AppiumDriver driver)
    {
        super(driver);
    }
    public void exitInMainMenu()
    {
        this.waitElementAndClick(
                BACK,
                "not find button Back",
                3
        );
        this.waitElementAndClick(
                CANCEL,
                "not find button Cancel",
                3
        );
    }

}

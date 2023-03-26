package Libs.UI;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject_Methods{

    private static final String
            BACK = "Back",
            CANCEL = "//XCUIElementTypeStaticText[@name=\"Cancel\"]";


    public NavigationUI(AppiumDriver driver)
    {
        super(driver);
    }
    public void exitInMainMenu()
    {
        this.waitElementAndClick(
                By.id(BACK),
                "not find button Back",
                3
        );
        this.waitElementAndClick(
                By.xpath(CANCEL),
                "not find button Cancel",
                3
        );
    }

}

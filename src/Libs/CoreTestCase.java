package Libs;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;

public class CoreTestCase extends TestCase {

    private static final String
    PLATFORM_IOS = "ios",
    PLATFORM_ANDROID = "android";

    protected AppiumDriver driver;
    private static String AppiumURL = "http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp() throws Exception
    {

        super.setUp();

        DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEny();

        driver = new IOSDriver(new URL(AppiumURL), capabilities);
        this.rotateScreenPortrait();
    }
    @Override
    protected void tearDown() throws Exception
    {
        driver.quit();
        super.tearDown();
    }

    protected void rotateScreenPortrait()
    {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }
    protected void rotateScreenLandscape()
    {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void exitInBackground(int seconds)
    {
        driver.runAppInBackground(seconds);
    }

    private DesiredCapabilities getCapabilitiesByPlatformEny() throws Exception {
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        if (platform.equals(PLATFORM_IOS)) {
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", "iPhone 14");
            capabilities.setCapability("platformVersion", "16.2");
            capabilities.setCapability("app", "/Users/vladimirevdokimov/Desktop/JavaAppiumAutomation/JavaAppiumAutomation/apks/Wikipedia.app");
        } else if (platform.equals(PLATFORM_ANDROID)) {
            capabilities.setCapability("platformName", "android");
            capabilities.setCapability("deviceName", "androidTestDevice");
            capabilities.setCapability("automationName", "appium");
            capabilities.setCapability("appPackage", "org.Wikipedia");
            capabilities.setCapability("appActivity", ".main.MainActivity");
            capabilities.setCapability("platformVersion", "15.0");
            capabilities.setCapability("app", "/Users/vladimirevdokimov/Desktop/JavaAppiumAutomation/JavaAppiumAutomation/apks/org.Wikipedia.apk");
        } else {
            throw new Exception("can`t get run from env variable. Platform value " + platform);
        }
        return capabilities;
    }
}
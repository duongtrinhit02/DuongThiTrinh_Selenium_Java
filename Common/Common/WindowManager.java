package Common;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

import Constant.Constant;

/**
 * WindowManager
 * Handle browser windows and tabs
 */
public class WindowManager {

    // Store window handles by name
    private static Map<String, String> windowMap = new HashMap<>();


    //SAVE / SWITCH BY NAME

    public static void saveWindow(String name, WebDriver webDriver) 
    {
        windowMap.put(name, webDriver.getWindowHandle());
    }

    public static void switchToTab(String name) 
    {
        String handle = windowMap.get(name);
        if (handle == null) {
        	throw new RuntimeException("No window found with name: " + name);
        }
        Constant.WEBDRIVER.switchTo().window(handle);
    }


    public static void switchToWindowByTitle(String expectedTitle)
    {
        for (String handle : Constant.WEBDRIVER.getWindowHandles()) {
            Constant.WEBDRIVER.switchTo().window(handle);
            if (Constant.WEBDRIVER.getTitle().equals(expectedTitle)) {
                return;
            }
        }
        throw new RuntimeException("Window with title not found: " + expectedTitle);
    }


    public static WebDriver newTab(String tabName)
    {
        WebDriver webDriver = Constant.WEBDRIVER.switchTo().newWindow(WindowType.TAB);
        saveWindow(tabName, webDriver);
        return webDriver;
    }
    
}

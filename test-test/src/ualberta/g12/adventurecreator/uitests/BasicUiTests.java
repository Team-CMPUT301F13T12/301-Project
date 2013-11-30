
package ualberta.g12.adventurecreator.uitests;

import android.util.Log;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class BasicUiTests extends UiAutomatorTestCase {

    public void testCheck() {
        UiObject anything = new UiObject(
                new UiSelector().packageName("ualberta.g12.adventurecreator"));
        if (!anything.exists()) {
            new L("Please open the AdventureCreator Application");
            fail("AdventureCreator Application is not open");
        }
    }

    /**
     * Silly Little logging class
     */
    private static class L {
        public L(String msg) {
            Log.d("BasicUiTests", "msg");
            System.out.println(msg);
        }
    }
}

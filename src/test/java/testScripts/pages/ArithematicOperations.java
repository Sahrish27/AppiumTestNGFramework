package testScripts.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import testScripts.utils.utility;

import static org.springframework.util.NumberUtils.parseNumber;

public class ArithematicOperations {
    public AndroidDriver<AndroidElement> androiddriver;

    public ArithematicOperations(AndroidDriver<AndroidElement> androidDriver) {
        this.androiddriver = androidDriver;
        PageFactory.initElements(new AppiumFieldDecorator(androiddriver), this);
        // PageFactory.initElements(new AppiumFieldDecorator(androidDriver), this);
    }

    @FindBy(id = "com.simplemobiletools.calculator:id/btn_decimal")
    AndroidElement btnDecLoc;
    @FindBy(id = "com.simplemobiletools.calculator:id/btn_clear")
    AndroidElement btnClrLoc;
    @FindBy(id = "com.simplemobiletools.calculator:id/btn_equals")
    AndroidElement btnEqLoc;
    @FindBy(id = "com.simplemobiletools.calculator:id/btn_plus")
    AndroidElement btnPlusLoc;
    @FindBy(id = "com.simplemobiletools.calculator:id/btn_minus")
    AndroidElement btnMinusLoc;
    @FindBy(id = "com.simplemobiletools.calculator:id/btn_multiply")
    AndroidElement btnMultiplyLoc;
    @FindBy(id = "com.simplemobiletools.calculator:id/btn_divide")
    AndroidElement btnDivideLoc;
    @FindBy(id = "com.simplemobiletools.calculator:id/btn_root")
    AndroidElement btnSqrootLoc;
    @FindBy(id = "com.simplemobiletools.calculator:id/btn_power")
    AndroidElement btnPowerLoc;
    @FindBy(id = "com.simplemobiletools.calculator:id/btn_percent")
    AndroidElement btnPercLoc;
    @FindBy(id = "com.simplemobiletools.calculator:id/result")
    AndroidElement btnResultLoc;
    @FindBy(id = "com.simplemobiletools.calculator:id/history")
    AndroidElement btnHistoryLoc;
    @FindBy(id = "com.simplemobiletools.calculator:id/settings")
    AndroidElement btnSettingLoc;
    @FindBy(id = "com.simplemobiletools.calculator:id/about")
    AndroidElement btnAboutLoc;
    @FindBy(xpath = "//android.widget.ImageView[@content-desc=\"More options\"]")
    AndroidElement btnMoreLoc;
    public Number calculate(Number inp1, Number inp2, String operator) throws InterruptedException {
        utility.longPress(btnClrLoc);
        utility.inputKeyboardMapping(String.valueOf(inp1));
        utility.performOperatorAction(operator);
        utility.inputKeyboardMapping(String.valueOf(inp2));
        btnEqLoc.click();
        String resultText = btnResultLoc.getText();
        System.out.println(resultText);
        Number result = utility.parseNumber(resultText);
        utility.wait2Seconds();
        if (operator.equals("/")) {
            result = utility.decimalRound(result);
        }
        return result;
    }
}


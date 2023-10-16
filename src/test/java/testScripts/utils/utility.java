package testScripts.utils;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import testScripts.appSetup;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.time.Duration;

public class utility extends appSetup {
    public utility() throws MalformedURLException {
    }

    public static void wait2Seconds() throws InterruptedException {
        Thread.sleep(2000);
    }

    public static void wait3Seconds() throws InterruptedException {
        Thread.sleep(3000);
    }


    public static void longPress(AndroidElement btnClear) {
        TouchAction touchAction = new TouchAction(androidDriver);
        LongPressOptions longPressOptions = new LongPressOptions()
                .withElement(ElementOption.element(btnClear))
                .withDuration(Duration.ofMillis(1000));
        touchAction.longPress(longPressOptions).release().perform();
    }
    public static void inputKeyboardMapping(String input) {
        for (int i = 0; i < input.length(); i++) {
            char digit = input.charAt(i);
            if (digit >= '0' && digit <= '9') {
                String buttonId = "com.simplemobiletools.calculator:id/btn_" + digit;
                androidDriver.findElementById(buttonId).click();
            }
        }
    }


    public static void performOperatorAction(String operator) {
        if (operator == "+") {
            (androidDriver.findElementById("com.simplemobiletools.calculator:id/btn_plus")).click();
        }
        if (operator == "-") {
            (androidDriver.findElementById("com.simplemobiletools.calculator:id/btn_minus")).click();
        }
        if (operator == "*") {
            (androidDriver.findElementById("com.simplemobiletools.calculator:id/btn_multiply")).click();
        }
        if (operator == "/") {
            (androidDriver.findElementById("com.simplemobiletools.calculator:id/btn_divide")).click();
        }
        if (operator == "") {
            (androidDriver.findElementById("com.simplemobiletools.calculator:id/btn_root")).click();
        }
        if (operator == "^") {
            (androidDriver.findElementById("com.simplemobiletools.calculator:id/btn_power")).click();
        }
        if (operator == "%") {
            (androidDriver.findElementById("com.simplemobiletools.calculator:id/btn_percent")).click();
        }

    }

    public static Number parseNumber(String numberString) {
        Number result = null;
        String stringWithoutComma = numberString.replace(",", "");

        System.out.println(stringWithoutComma); // Output: 1608
        try {
            // Try parsing as an Integer

            result = Integer.parseInt(stringWithoutComma);
        } catch (NumberFormatException e1) {
            try {
                // If parsing as an Integer fails, try parsing as a Double
                result = Double.parseDouble(stringWithoutComma);
            } catch (NumberFormatException e2) {
                // Handle the exception if the string cannot be parsed as a number
                System.err.println("Error parsing number: " + numberString);
                e2.printStackTrace();
            }
        }

        return result;
    }

    public static void resultTabClear(AndroidElement btnClrLoc, String text) {
        for (int i=0; i<text.length(); i++){
            btnClrLoc.click();
        }

    }

    public static Number decimalRound(Number result){
        DecimalFormat decimalFormat = new DecimalFormat("#.################");
        Number roundedNumber = Double.parseDouble(decimalFormat.format(result));
        System.out.println(roundedNumber); // Output: 3.14
        return roundedNumber;
    }

    public static String captureScreenshot(String methodName) {
        String screenshotDir="./Test-Reports/Screenshots";
        String screenshotPath = null;
        try{
            TakesScreenshot ts = (TakesScreenshot) androidDriver;
            File Source = ts.getScreenshotAs(OutputType.FILE);
            screenshotPath =screenshotDir + File.separator+methodName+".png";
            FileUtils.copyFile(Source,new File((screenshotPath)));

        }catch (Exception e){
            e.printStackTrace();
        }
        return screenshotPath;
    }
}

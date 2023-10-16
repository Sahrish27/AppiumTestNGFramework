package testScripts;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import testScripts.utils.PropertyReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class appSetup {
    private static final String remoteHub = new PropertyReader().readProperty("remoteHub");
    public static AndroidDriver<AndroidElement> androidDriver;
    protected static DesiredCapabilities capabilities=new DesiredCapabilities();


    public appSetup() throws MalformedURLException {
        if (androidDriver == null){
        initializeMobile();}
    }

    public void initializeMobile() throws MalformedURLException {

            createNewLocalMobileDriverInstance();

    }
    private void createNewLocalMobileDriverInstance() throws MalformedURLException {


        capabilities.setCapability("deviceName", "Galaxy A12");
        String version = new PropertyReader().readProperty("android");
        String appPackage= new PropertyReader().readProperty("AppPackage");
        String appActivity= new PropertyReader().readProperty("AppActivity");

        capabilities.setCapability("platformVersion", version);
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("appActivity", appActivity);
        capabilities.setCapability("unicodeKeyboard", false);
        capabilities.setCapability("resetKeyboard", true);
        capabilities.setCapability("noReset", true);
        try {
            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            androidDriver = new AndroidDriver<AndroidElement>(url,capabilities);
            androidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        } catch (Exception e) {
            System.out.println("URL not found");
        }
    }


    public String getReportConfigPath(){
        String report = new PropertyReader().readProperty("reportConfigPath");
        if(report != null) return report;
        else throw new RuntimeException("Extent Report Path is not specified in the Config.properties file");
    }



    public static void quitDriver() {
//		driver.quit();
//		driver = null;
    }
}

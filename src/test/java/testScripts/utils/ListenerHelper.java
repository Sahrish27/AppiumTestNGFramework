package testScripts.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import lombok.SneakyThrows;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static testScripts.utils.ExtentReportHelper.getReportObject;
public class ListenerHelper implements ITestListener {
    private final ExtentReports extentReports = getReportObject();
    ExtentTest logger;
    //ThreadLocal to generate the report for parallel execution
    ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();


    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
    String screenshotPath;

    public void onTestStart(ITestResult result) {
        logger = extentReports.createTest(result.getMethod().getMethodName(),
                "<b> Description for test:</b> " + result.getMethod().getDescription());
        extentTestThreadLocal.set(logger);
        logger.assignCategory(result.getMethod().getGroups());
        extentTestThreadLocal.get().log(Status.INFO, "Test Start Time: " + dtf.format(LocalDateTime.now()));
    }

    public void onTestSuccess(ITestResult result) {
        extentTestThreadLocal.get().log(Status.PASS, MarkupHelper.createLabel(result.getMethod().getMethodName() + "is successful!!", ExtentColor.GREEN));
    }

    @SneakyThrows
    public void onTestFailure(ITestResult result) {

        screenshotPath = utility.captureScreenshot(result.getMethod().getMethodName());
        extentTestThreadLocal.get().log(Status.FAIL, MarkupHelper.createLabel(result.getMethod().getMethodName() + " is failed!!", ExtentColor.RED));
        extentTestThreadLocal.get().log(Status.FAIL, result.getThrowable());
        extentTestThreadLocal.get().fail("Screenshot of the failure:", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
    }

    public void onFinish(ITestContext context) {
        extentTestThreadLocal.get().log(Status.INFO, "Driver quit time:  " + dtf.format(LocalDateTime.now()));
        extentReports.flush();
    }
}
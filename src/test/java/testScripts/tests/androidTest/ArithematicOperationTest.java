package testScripts.tests.androidTest;

import org.springframework.context.annotation.Description;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import testScripts.appSetup;
import testScripts.pages.ArithematicOperations;

import java.net.MalformedURLException;

public class ArithematicOperationTest extends appSetup {

    ArithematicOperations operations;
    double delta = 1e-8;

    public ArithematicOperationTest() throws MalformedURLException {
        operations = new ArithematicOperations(androidDriver);
    }

    @Test(priority = 1, description = "Simple 2 numbers addition")
    @Parameters({"input1", "input2"})
    public void Addition(int inp1, int inp2) throws InterruptedException {
        Assert.assertEquals(operations.calculate(inp1, inp2, "+"), (inp1 + inp2),"Addition operation failed");
    }

    @Test(priority = 2, description = "Simple 2 numbers subtraction")
    @Parameters({"input1", "input2"})
    public void Subtraction(int inp1, int inp2) throws InterruptedException {
        Assert.assertEquals( operations.calculate(inp1, inp2, "-"), (inp1 - inp2), "Subtraction operation failed");
    }

    @Test(priority = 3, description = "Simple 2 numbers Multiplication")
    @Parameters({"input1", "input2"})
    public void Multiply(int inp1, int inp2) throws InterruptedException {
        Assert.assertEquals(operations.calculate(inp1, inp2, "*"), (inp1 * inp2), "Multiply operation failed");
    }

    @Test(priority = 4, description = "Simple 2 numbers Division")
    @Parameters({"input1", "input2"})
    public void Divide(double inp1, double inp2) throws InterruptedException {
        Assert.assertEquals((Double) operations.calculate(inp1, inp2, "/"), (inp1 / inp2), delta, "Division operation failed");
    }
}

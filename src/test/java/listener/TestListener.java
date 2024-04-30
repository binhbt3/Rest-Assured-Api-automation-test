package listener;

import globals.ConfigsGlobal;
import helpers.PropertiesHelper;
import lombok.extern.log4j.Log4j2;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

@Log4j2
public class TestListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
    log.info("Start run test case: " + result.getName());
    ConfigsGlobal.TOTAL_TEST_CASE +=1;
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    log.info("Test case " + result.getName() + "is passed.");
    ConfigsGlobal.TEST_CASE_PASSED +=1;
    }

    @Override
    public void onTestFailure(ITestResult result) {
    log.info("Test case " + result.getName() + "is failed.");
    ConfigsGlobal.TEST_CASE_FAILED +=1;
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    log.warn("Test case " + result.getName() + "is skipped");
    ConfigsGlobal.TEST_CASE_SKIPED +=1;
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
        log.info("----------------------------------------------------------");
        log.info("Start run test suite");
        PropertiesHelper.loadAllFiles();
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("----------------------------------------------------------");
        log.info("Finish run test suite");
        log.info("Total test case: " + ConfigsGlobal.TOTAL_TEST_CASE);
        log.info("Total test case failed: " + ConfigsGlobal.TEST_CASE_FAILED);
        log.info("Total test case passed: " + ConfigsGlobal.TEST_CASE_PASSED);
        log.info("Total test case skipped: " + ConfigsGlobal.TEST_CASE_SKIPED);

    }
}

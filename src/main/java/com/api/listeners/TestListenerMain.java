package com.api.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.api.reporting.ExtentReportManagerFromCopiolet;
import com.api.reporting.ExtentReportManagerMain;

public class TestListenerMain implements ITestListener {
    private static final Logger logger = LogManager.getLogger(TestListenerMain.class);

    @Override
    public void onStart(ITestContext context) {
        logger.info("Test Suite Started!!!");
        // Initialize ExtentReports instance
        ExtentReportManagerMain.createInstance();
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Test Suite Completed!!!");
        // Flush and clean up
        ExtentReportManagerMain.endTest();
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Started!! " + result.getMethod().getMethodName());
        logger.info("Description!! " + result.getMethod().getDescription());

        // Start Extent test for this method
        ExtentReportManagerMain.startTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Passed!! " + result.getMethod().getMethodName());
        ExtentReportManagerMain.getTest().pass("Test passed successfully!");
        ExtentReportManagerMain.endTest();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Failure!! " + result.getMethod().getMethodName(), result.getThrowable());
        ExtentReportManagerMain.getTest().fail("Test failed: " + result.getThrowable());
        ExtentReportManagerMain.endTest();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.info("Skipped!! " + result.getMethod().getMethodName());
        ExtentReportManagerMain.getTest().skip("Test skipped!");
        ExtentReportManagerMain.endTest();
    }
}
package com.swapi.listeners;

import lombok.extern.log4j.Log4j2;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

@Log4j2
public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {
        log.info("--------------------------------------------------------------");
        log.info("Starting to run " + iTestResult.getMethod().getMethodName() + " test method");
        log.info("--------------------------------------------------------------");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        log.info("--------------------------------------------------------------");
        log.info("PASSED " + iTestResult.getMethod().getMethodName() + " test method");
        log.info("--------------------------------------------------------------");

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        log.info("--------------------------------------------------------------");
        log.info("FAILED " + iTestResult.getMethod().getMethodName() + " test method");
        log.info("--------------------------------------------------------------");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log.info("--------------------------------------------------------------");
        log.info("SKIPPED " + iTestResult.getMethod().getMethodName() + " test method");
        log.info("--------------------------------------------------------------");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }
}

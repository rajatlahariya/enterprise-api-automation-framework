package com.rajat.framework.reporting;

import org.testng.ISuite;
import org.testng.ISuiteListener;

public class AllureSuiteListener implements ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        AllureEnvironmentWriter.write();
    }
}
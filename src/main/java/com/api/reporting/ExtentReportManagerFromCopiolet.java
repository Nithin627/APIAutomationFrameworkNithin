package com.api.reporting;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;

public class ExtentReportManagerFromCopiolet {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    // Create or reuse ExtentReports instance
    public static ExtentReports createInstance() {
        if (extent == null) {
            String fileName = generateReportFileName();
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/report/" + fileName);

            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setDocumentTitle("API Test Report");
            sparkReporter.config().setReportName("REST API Test Results");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Environment", "Test");
            extent.setSystemInfo("User", System.getProperty("user.name"));
        }
        return extent;
    }

    private static String generateReportFileName() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        return "APITestReport_" + dtf.format(LocalDateTime.now()) + ".html";
    }

    // Start a new test and bind it to the current thread
    public static void startTest(String testName) {
        ExtentTest extentTest = createInstance().createTest(testName);
        test.set(extentTest);
    }

    public static void logRequest(FilterableRequestSpecification requestSpec) {
        ExtentTest currentTest = test.get();
        if (currentTest == null) {
            System.out.println("ExtentTest not initialized for this thread!");
            return;
        }

        StringBuilder requestDetails = new StringBuilder();
        requestDetails.append("<pre>");
        requestDetails.append("Request Method: ").append(requestSpec.getMethod()).append("\n");
        requestDetails.append("Request URI: ").append(requestSpec.getURI()).append("\n");
        requestDetails.append("Request Headers:\n");

        for (Header header : requestSpec.getHeaders()) {
            requestDetails.append(" ").append(header.getName()).append(": ").append(header.getValue()).append("\n");
        }

        if (requestSpec.getBody() != null) {
            requestDetails.append("Request Body:\n").append(requestSpec.getBody().toString());
        }

        requestDetails.append("</pre>");
        currentTest.log(Status.INFO, "Request Details: " + requestDetails.toString());
    }

    public static void logResponse(Response response) {
        ExtentTest currentTest = test.get();
        if (currentTest == null) {
            System.out.println("ExtentTest not initialized for this thread!");
            return;
        }

        StringBuilder responseDetails = new StringBuilder();
        responseDetails.append("<pre>");
        responseDetails.append("Response Status: ").append(response.getStatusCode()).append("\n");
        responseDetails.append("Response Headers:\n");

        for (Header header : response.getHeaders()) {
            responseDetails.append(" ").append(header.getName()).append(": ").append(header.getValue()).append("\n");
        }

        responseDetails.append("Response Body:\n").append(response.getBody().prettyPrint());
        responseDetails.append("</pre>");

        currentTest.log(Status.INFO, "Response Details: " + responseDetails.toString());

        if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) {
            currentTest.pass("Request completed with status code: " + response.getStatusCode());
        } else {
            currentTest.fail("Request failed with status code: " + response.getStatusCode());
        }
    }

    // Flush and clean up after each test
    public static void endTest() {
        if (extent != null) {
            extent.flush();
        }
        test.remove(); // important for parallel runs
    }

    public static ExtentTest getTest() {
        return test.get();
    }
}
package ru.javawebinar.topjava;

import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class TestTime extends Stopwatch {
    private static final Logger log = LoggerFactory.getLogger(TestTime.class);
    private static final StringBuilder sb = new StringBuilder("\nAll tests info: \n");

    private static void logInfo(Description description, String status, long nanos) {
        String testInfo =
                          "  Test name: " + description.getMethodName()
                        + ". Status: " + status
                        + ". Spent: " + TimeUnit.NANOSECONDS.toMicros(nanos) + " us.\n";

        log.info(testInfo);
        sb.append(testInfo);
    }

    @Override
    protected void finished(long nanos, Description description) {
        logInfo(description, "successful finished", nanos);
    }

    public static void getAllTestInfo() {
        log.info(sb.toString());
    }
}

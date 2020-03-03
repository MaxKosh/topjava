package ru.javawebinar.topjava;

import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class TestTimeLogger extends Stopwatch {
    private static final Logger log = LoggerFactory.getLogger(TestTimeLogger.class);
    private static final StringBuilder sb = new StringBuilder("\n\u001B[35m---------- ALL TEST INFO: ----------\n" +
                                                                            "TEST NAME\t\t\t\t\tTEST TIME\u001B[35m\n");
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String RESET_COLOR = "\033[0m";

    private static void logInfo(Description description, long nanos) {
        String name = String.format("%-30s", description.getMethodName());
        String time = TimeUnit.NANOSECONDS.toMicros(nanos) + " us.\n";

        sb.append(ANSI_YELLOW).append(name + time).append(RESET_COLOR);

        log.info("\n\u001B[33m TEST NAME: {} \t TEST TIME: {} \033[0m", name.trim(), time);
    }

    @Override
    protected void finished(long nanos, Description description) {
        logInfo(description, nanos);
    }

    public static void getAllTestInfo() {
        log.info(sb.toString());
    }
}

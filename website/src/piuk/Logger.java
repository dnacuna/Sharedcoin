package piuk;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Logger {
    public static final int SeverityWARN = 0;
    public static final int SeverityError = 1;
    public static final int SeveritySeriousError = 2;
    public static final int SeveritySecurityError = 3;
    public static final int SeverityINFO = 4;
    public static boolean log = true;
    public static boolean logInfo = false;
    public static final List<String> logStack = new ArrayList<>();

    public static void printLogStack() {
        for (String line : logStack) {
            System.out.println(line);
        }
    }

    public static void log(int severity, final Object args) {

        if (args instanceof Exception) {
            if (log || severity == SeveritySeriousError) {
                ((Exception)args).printStackTrace();
            }
        } else {
            synchronized (logStack) {
                logStack.add("------------ " + new Date() + " ------------\n" + args.toString());

                if (logStack.size() >= 100) {
                    logStack.remove(0);
                }
            }

            if (severity == SeverityINFO) {
                if (logInfo) {
                    System.out.println(new Date() + " INFO: " + args);
                }
            } else if (log || severity == SeveritySeriousError) {
                System.out.println(args);
            }
        }
    }
}

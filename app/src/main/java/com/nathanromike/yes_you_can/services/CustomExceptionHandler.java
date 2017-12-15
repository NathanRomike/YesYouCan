package com.nathanromike.yes_you_can.services;

import android.util.Log;

/**
 * Created by nromike on 12/15/17.
 */

public class CustomExceptionHandler implements Thread.UncaughtExceptionHandler {

    private static final String TAG = CustomExceptionHandler.class.getSimpleName();

    private static final String MAIN_THREAD_NAME = "main";
    public static final String EXCEPTION_ON_LAST_APP_LOAD = "exceptionOnLastAppLoad";

    private Thread.UncaughtExceptionHandler wrappedExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
    private Thread.UncaughtExceptionHandler defaultExceptionHandler;

    public void uncaughtException(Thread thread, Throwable throwable) {

        Boolean mainThread = thread.getName().equals(MAIN_THREAD_NAME);
        throwable = unwrap(throwable);

        if (mainThread) {
            handleMainThreadException(thread, throwable);
        } else {
            try {
                handleThrowable(throwable);
            } catch (Exception e) {
                logHandledExceptionSafe("backgroundThreadException", e);
            }
        }
    }

    private void logHandledExceptionSafe(String errorMessage, Throwable throwable) {
        Log.e(TAG + "1", errorMessage);
    }

    private Throwable unwrap(Throwable throwable) {
        Throwable cause = throwable.getCause();
        if (throwable.getClass().equals(RuntimeException.class) && (cause != null)) {
            return cause;
        }
        return throwable;
    }

    protected void handleMainThreadException(Thread thread, Throwable throwable) {
        try {
            try {
                wrappedExceptionHandler.uncaughtException(thread, throwable);
            } catch (Exception e) {
                wrappedExceptionHandler.uncaughtException(thread, e);
            }
            wrappedExceptionHandler.uncaughtException(thread, throwable);
        } catch (Exception e) {
            Log.e(TAG + "2", "Error when trying to handle UI exception: " + e.getMessage());
            defaultExceptionHandler.uncaughtException(thread, throwable);
        }
    }

    public void logHandledException(Throwable throwable) {
        logHandledException(null, throwable);
    }

    public void logHandledException(String errorMessage) {
    }

    public void logHandledException(String errorMessage, Throwable throwable) {
        Log.e(TAG, errorMessage);
    }

    public void setDefaultExceptionHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        defaultExceptionHandler = uncaughtExceptionHandler;
    }

    public void handleThrowable(Throwable throwable) {
        Log.e(TAG, throwable.getMessage());
        logHandledException(throwable);
    }

}

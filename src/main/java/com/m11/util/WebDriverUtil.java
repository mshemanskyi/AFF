package com.m11.util;

public class WebDriverUtil {
    public static String getWebDriverByOS(){
        if(OSCheck.isWindows()) return "src/main/resources/WebDrivers/geckodriver.exe";
        if(OSCheck.isMac()) return "src/main/resources/WebDrivers/geckodriver";
        if(OSCheck.isUnix()) return "src/main/resources/WebDrivers/geckodriver-linux64";

        return "";
    }
}

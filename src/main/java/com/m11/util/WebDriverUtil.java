package com.m11.util;

public class WebDriverUtil {
    public static String getWebDriverByOS(){
        if(OSCheck.isWindows()) return "src/main/resources/WebDrivers/chromedriver.exe";
        if(OSCheck.isMac()) return "src/main/resources/WebDrivers/chromedriver-mac64";
        if(OSCheck.isUnix()) return "src/main/resources/WebDrivers/chromedriver-linux64";

        return "";
    }
}

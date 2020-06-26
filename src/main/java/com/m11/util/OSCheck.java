package com.m11.util;

public class OSCheck {
    static String OS = System.getProperty("os.name").toLowerCase();
    public static boolean isWindows() {
        return (OS.contains("win"));
    }

    public static boolean isMac() {
        return (OS.contains("mac") || OS.contains("mac os x"));
    }

    public static boolean isUnix() {
        return (OS.contains("nix")
                || OS.contains("nux")
                || OS.contains("aix")
                || OS.contains("linux")
        );
    }
}

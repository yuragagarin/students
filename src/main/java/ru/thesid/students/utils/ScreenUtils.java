package ru.thesid.students.utils;

import java.io.UnsupportedEncodingException;

public class ScreenUtils {
    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }
}

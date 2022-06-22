package ru.thesid.students.validators;

import java.util.regex.Pattern;

public class StudentValidator {

    private static final int FIO_MAX_LENGTH = 152;
    private static final int GROUP_MAX_LENGTH = 4;

    public static boolean validateFio(String value) {
        if (value.trim().length() > FIO_MAX_LENGTH)
            return false;
        var regex = "^[A-Z]{1}[a-z]+\\s[A-Z]{1}[a-z]+\\s[A-Z]{1}[a-z]+$";
        var pattern = Pattern.compile(regex);
        var matcher = pattern.matcher(value);
        return matcher.find();
    }

    public static boolean validateGroup(String value) {
        if (value.trim().length() > GROUP_MAX_LENGTH) return false;
        return true;
    }

    public static boolean validateBirthday(String value) {
        var regex = "^(?=\\d)(?:(?:31(?!.(?:0?[2469]|11))|(?:30|29)(?!.0?2)|29(?=.0?2.(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00)))(?:\\x20|$))|(?:2[0-8]|1\\d|0?[1-9]))([-./])(?:1[012]|0?[1-9])\\1(?:1[6-9]|[2-9]\\d)?\\d\\d)?(\\x20?((0?[1-9]|1[012])(:[0-5]\\d){0,2}(\\x20[AP]M))|([01]\\d|2[0-3])(:[0-5]\\d){1,2})?$";
        var pattern = Pattern.compile(regex);
        var matcher = pattern.matcher(value);
        return matcher.find();
    }
}

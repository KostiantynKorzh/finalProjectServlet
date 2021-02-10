package me.project.controller.command.util;

import java.util.regex.Pattern;

public class Validation {

    public static final String NAME_PATTERN = "^[a-zA-Zа-яА-ЯёъЪЁґїєі'ҐЇЄІ]{3,}$";
    public static final String EMAIL_PATTERN = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";
    public static final String PASSWORD_PATTERN = "^.{8,}$";

    public static boolean isValidInput(String input) {
        return IsNotNull(input) && IsNotEmpty(input);
    }

    public static boolean isValidSignupForm(String firstName, String lastName,
                                            String email, String password) {
        return isValidForm(firstName, lastName, email, password) &&
                Pattern.matches(NAME_PATTERN, firstName) &&
                Pattern.matches(NAME_PATTERN, lastName) &&
                Pattern.matches(EMAIL_PATTERN, email) &&
                Pattern.matches(PASSWORD_PATTERN, password);
    }


    public static boolean isValidForm(String... inputs) {
        for (String input : inputs) {
            if (!isValidInput(input)) {
                return false;
            }
        }
        return true;
    }

    public static boolean IsNotNull(String input) {
        return input != null;
    }

    public static boolean IsNotEmpty(String input) {
        return !input.trim().isEmpty();
    }

}

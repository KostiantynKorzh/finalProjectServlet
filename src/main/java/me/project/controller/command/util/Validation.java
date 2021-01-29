package me.project.controller.command.util;

public class Validation {

    public static boolean isValidInput(String input) {
        return input != null && !input.trim().isEmpty();
    }

    public static boolean isValidForm(String... inputs) {
        for (String input : inputs) {
            System.out.println(input + " : " + isValidInput(input));
            if (!isValidInput(input)) {
                return false;
            }
        }
        return true;
    }


}

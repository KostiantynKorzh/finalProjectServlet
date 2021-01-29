package me.project.controller.servlet;

import java.util.ListResourceBundle;

public class Mes_en extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return contents;
    }

    static final Object[][] contents = {
            {"login.login", "login"},
            {"login.password", "password"},
            {"login.submit", "submit"},
            {"admin.page", "ADMIN PAGE"},
            {"user.page", "USER PAGE"}
    };

}

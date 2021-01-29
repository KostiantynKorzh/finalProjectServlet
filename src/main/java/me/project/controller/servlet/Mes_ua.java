package me.project.controller.servlet;

import java.util.ListResourceBundle;

public class Mes_ua extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return contents;
    }

    static final Object[][] contents = {
            {"login.login", "логін"},
            {"login.password", "пароль"},
            {" login.submit", "ввійти"},
            {"admin.page", "АДМІН"},
            {"user.page", "КОРИСТУВАЧ"}
    };

}

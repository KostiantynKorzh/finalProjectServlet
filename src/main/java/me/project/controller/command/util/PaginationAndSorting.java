package me.project.controller.command.util;

import javax.servlet.http.HttpServletRequest;

public class PaginationAndSorting {

    public static final Integer PER_PAGE = 2;

    private static int page = 1;
    private static String parameter = "id";

    public static void configurePageAndParameter(HttpServletRequest request, int numberOfRows) {

        int pages = 1;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        if (request.getParameter("sorted") != null) {
            parameter = request.getParameter("sorted");
        }
        if (page <= 1) {
            page = 1;
        }
        pages = (int) Math.ceil(numberOfRows * 1.0 / PER_PAGE);
        request.setAttribute("pages", pages);
        if (page > pages - 1) {
            page = pages;
        }
        request.setAttribute("page", page);
        request.setAttribute("parameter", parameter);
    }

    public static int getPage() {
        return page;
    }

    public static String getParameter() {
        return parameter;
    }

}

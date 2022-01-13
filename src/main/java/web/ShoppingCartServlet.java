package web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ShoppingCartServlet")
public class ShoppingCartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // get session Object (if it didn't exist, java creates a new one)
        HttpSession session = request.getSession();

        //get items list if exist
        List<String> items = (List<String>) session.getAttribute("items");
        //else...
        if (items == null) {
            items = new ArrayList<>();
            session.setAttribute("items", items);
        }

        // save the new item
        String newItem = request.getParameter("item");
        // add new item to the shopping cart
        if (newItem != null && !newItem.trim().equals("")) {
            items.add(newItem);
        }


        out.print("<h1>items list</h1>");
        out.print("<br>");
        for (String item : items) {
            out.print("<li>" + item + "</li>");
        }
        out.print("<br>");
        out.print("<a href='/SessionAndCookiesHandlingJava-1.0-SNAPSHOT'>return to index page</a>");
    }
}

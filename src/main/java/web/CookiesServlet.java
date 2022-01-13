package web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/CookiesServlet")
public class CookiesServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean userIsNew = true;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("recurrentVisitor") && c.getValue().equals("yes")) {// not a new user
                    userIsNew = false;
                    break;
                }
            }
        }
        String message = null;
        if (userIsNew) {
            Cookie newUserCookie = new Cookie("recurrentVisitor", "yes");
            response.addCookie(newUserCookie);
            message = "thanks for visiting our web site by FIRTS TIME";
        } else {
            message = "thanks for visiting our web site AGAIN";
        }
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("Message: " + message);
        out.close();
    }
}
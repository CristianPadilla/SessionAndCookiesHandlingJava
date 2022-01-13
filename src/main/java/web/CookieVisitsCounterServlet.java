package web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/CookieVisitsCounterServlet")
public class CookieVisitsCounterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int count = 0;

        // get count value if it's not the first visit
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("visitsCount")) {
                    count = Integer.parseInt(c.getValue());
                    break;
                }
            }
        }
        count++;
        // return visits count cookie to the client
        Cookie cookie = new Cookie("visitsCount", String.valueOf(count));
        // cookie only available along 1 hour (3600)
        cookie.setMaxAge(3600);
        response.addCookie(cookie);

        //show count to the client
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("Visits Number: " + count);
        out.close();


    }
}

package web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/SessionVisitsCounterServlet")
public class SessionVisitsCounterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        Integer visitsCount = (Integer) session.getAttribute("VisitsCounter");
        if (visitsCount == null) {
            visitsCount = 1;
        } else {
            visitsCount++;
        }

        session.setAttribute("VisitsCounter", visitsCount);
        PrintWriter out = response.getWriter();
        out.print("VisitsCounter" + visitsCount);

    }

}

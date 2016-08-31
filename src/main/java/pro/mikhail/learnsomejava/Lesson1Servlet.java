package pro.mikhail.learnsomejava;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Mikhail_Prosuntsov on 8/31/2016.
 */
public class Lesson1Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String paramName = "name";
        String paramValue = request.getParameter(paramName);

        PrintWriter out = response.getWriter();

        if (paramValue==null)
            out.write("try <a href = \"http://localhost:8080/hello?name=YourName\">http://localhost:8080/hello?name=YourName </a>");
        else
            out.write(paramValue);

        out.close();

    }
}
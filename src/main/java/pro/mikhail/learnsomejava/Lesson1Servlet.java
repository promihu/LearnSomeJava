package pro.mikhail.learnsomejava;

/**
 * Created by Mikhail_Prosuntsov on 03-Aug-16.
 */

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Lesson1Servlet extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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

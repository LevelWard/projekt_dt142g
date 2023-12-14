package com.antonsskafferi.projekt_dt142g;

import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
@Named
public class LoginTest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        //request.getRequestDispatcher("link.html").include(request, response);

        String name=request.getParameter("username");
        String password=request.getParameter("password");

        if(password.equals("hej123") && name.equals("olle")){
            /*out.print("Welcome, "+name);
            HttpSession session=request.getSession();
            session.setAttribute("name",name);*/

            response.sendRedirect("main.xhtml");
        }
        else{
            out.print("Sorry, username or password error!");
            request.getRequestDispatcher("AdminInlogg.xhtml").include(request, response);
        }
        out.close();
    }
}

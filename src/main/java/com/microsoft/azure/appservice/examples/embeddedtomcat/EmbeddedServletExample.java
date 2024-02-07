//------------------------------------------------------------------------------
// <copyright file="EmbeddedServletExample.java" company="Microsoft">
// Copyright (c) Microsoft Corporation. All rights reserved.
// </copyright>
//------------------------------------------------------------------------------

package com.microsoft.azure.appservice.examples.embeddedtomcat;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="EmbeddedServletExample", urlPatterns = {"/servlet"})
public class EmbeddedServletExample extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        ServletOutputStream out = resp.getOutputStream();
        out.println("<html><head><title>Embedded Tomcat example (Servlet)</title><link rel=\"stylesheet\" href=\"/css/reset.css\"></head>");
        out.println("<body><h1>Embedded Tomcat example: Servlet</h1>");
        out.println("<p>Current time:" + new java.util.Date() + "</p>");
        out.println("<hr><a href='/'>Home</a>");
        out.println("</body></html>");
    }

}

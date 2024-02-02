//------------------------------------------------------------------------------
// <copyright file="Main.java" company="Microsoft">
// Copyright (c) Microsoft Corporation. All rights reserved.
// </copyright>
//------------------------------------------------------------------------------

package com.microsoft.azure.appservice.examples.embeddedtomcat;

import java.io.File;
import java.io.FileWriter;
import java.net.URI;
import java.net.URL;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.JarResourceSet;
import org.apache.catalina.webresources.StandardRoot;

public class Main {

    /*
     * Returns the path of the fat JAR where application's classes and resources are found
     */
    private static String getJarPath() throws Exception {
        URL mainClassUrl = Main.class.getResource(Main.class.getSimpleName() + ".class").toURI().toURL();
        return URI.create(mainClassUrl.getPath().substring(0, mainClassUrl.getPath().lastIndexOf("!/"))).getPath();
    }

    /*
     * Creates a temporary directory required
     */
    private static File setTempWorkDir() throws Exception {
        File workdir = File.createTempFile("temp", "temp");
        workdir.delete();
        workdir.mkdirs();
        File context = new File(workdir, "context.xml");
        FileWriter contexWriter = new FileWriter(context);
        contexWriter.write("<Context></Context>");
        contexWriter.close();
        context.deleteOnExit();
        workdir.deleteOnExit();
        System.out.println("Created temp directory " + workdir.getAbsolutePath());
        return workdir;
    }

    public static void main(String[] args) throws Exception {

        // Setup
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(80); // Listen on port 80, which is used by the built-in Java images in App Service
        tomcat.getHost().setAppBase(".");
        tomcat.getConnector();

        // Create a StandardRoot to serve the app
        File workdir = setTempWorkDir();
        StandardContext ctx = (StandardContext) tomcat.addWebapp("", workdir.toString());
        WebResourceRoot resources = new StandardRoot(ctx);

        // Add resources from the fat JAR's own internal resources
        String jarPath = getJarPath();
        resources.addPreResources(new JarResourceSet(resources, "/", jarPath, "/"));
        resources.addPreResources(new JarResourceSet(resources, "/WEB-INF/classes", jarPath, "/"));
        ctx.setResources(resources);

        // Launch Tomcat
        tomcat.start();
        tomcat.getServer().await();
    }
}

<html>
<head>
    <title>Embedded Tomcat example (JSP)</title>
</head>
<body>
    <h1>Embedded Tomcat example: JSP</h1>

    <p>
        This image is bundled inside the JAR file too:<br/>
        <img src="img/MS-Azure_logo_stacked_c-gray_rgb.png" width="340" height="180">
    </p>

    <p>Current time: <%= new java.util.Date() %></p>
    <hr>
    <a href="/servlet">Hello Servlet</a>
</body>

</html>
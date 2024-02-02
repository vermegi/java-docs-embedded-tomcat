# Embedded Tomcat servlet example

This sample includes a Tomcat application run by an embedded Tomcat server. It's configured to be deployable as an executable uber JAR file to Azure App Service.

## Running locally

1. Build with `mvn clean package`.
1. Run locally with `java -jar .\target\embeddedTomcatExample.jar`.

## Deploy to App Service

To deploy to App Service, edit the section "Configure your deployment to Azure App Service" on the file pom.xml and run `mvn package azure-webapp:deploy`.

1. Add the plugin [azure-webapp-maven-plugin](https://github.com/microsoft/azure-maven-plugins/wiki/Azure-Web-App) by running the following command:

    ```bash
    mvn com.microsoft.azure:azure-webapp-maven-plugin:2.13.0:config
    ```

1. Deploy the app by running the following command:

    ```bash
    mvn package azure-webapp:deploy
    ```

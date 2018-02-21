Intro
------
spring-mvc-showcase was chosen to "showcase" how Contrast Assess could be integrated with Spring MVC environment built with maven.
The original spring-mvc-showcase README is found below for reference.

Contrast Assess is a revolutionary application security testing solution that infuses software with vulnerability assessment capabilities so that security flaws are automatically identified.
It follows the data flow from source to sink.  It marks potentially dangerous sources and raises a vulnerability trace if it makes it to sink without being sanitized/escaped.  
Contrast also offers a RASP called Protect that, amongst other things, can block such vulnerabilities from being exploited.

spring-mvc-showcase
        uses the Spring MVC framework
        is built and tested with maven
        already contained some Controller Test cases that can exercise the application  

It is important to note that Controller Testing is not the same end-to-end testing.  
Contrast Assess is typically deployed in QA environments where the full data flow through an application occurs. 

The goal here is that some level of (maven failsafe) automated testing could be achieved to catch vulnerabilities.
It is not a replacenment for QA testing.
At initiation of this project there are no failsafe tests written.


Contrast Steps
--------------
The steps to get this working with contrast are;
1. git clone git://github.com/SpringSource/spring-mvc-showcase.git
2. Download the Java contrast agent from TeamServer
3. Add the Contrast Agent to MAVEN_OPTS e.g.
    export MAVEN_OPTS="Xms4G -javaagent:<PathToAgent>/contrast.jar -Dcontrast.appname=spring-mvc-showcase -Dcontrast.server=serverName"
    serverName is the name of the server where the agent will run (as seen by TeamServer, usually the hostname)
4. Update the pom.xml file to reflect your serverName and the connection details for your TeamServer instance;
        username
        apiKey
        serviceKey
        apiUrl
        orgUuid
5. Run the application;
    $ cd spring-mvc-showcase
    $ mvn jetty:run
    Access the deployed web application at: http://localhost:8080/spring-mvc-showcase/
6. From TeamsServer, apply a license to spring-mvc-showcase.
7. Test with "verify"




________________________
ORIGINAL below this line
________________________

Spring MVC Showcase
-------------------
Demonstrates the capabilities of the Spring MVC web framework through small, simple examples.
After reviewing this showcase, you should have a good understanding of what Spring MVC can do and get a feel for how easy it is to use.
Includes project code along with a supporting slideshow and screen cast.

In this showcase you'll see the following in action:

* The simplest possible @Controller
* Mapping Requests
* Obtaining Request Data
* Generating Responses
* Message Converters
* Rendering Views
* Type Conversion
* Validation
* Forms
* File Upload
* Exception Handling

To get the code:
-------------------
Clone the repository:

    $ git clone git://github.com/SpringSource/spring-mvc-showcase.git

If this is your first time using Github, review http://help.github.com to learn the basics.

To run the application:
-------------------	
From the command line with Maven:

    $ cd spring-mvc-showcase
    $ mvn jetty:run 

Access the deployed web application at: http://localhost:8080/spring-mvc-showcase/

Note:
-------------------

This showcase originated from a [blog post](http://blog.springsource.com/2010/07/22/spring-mvc-3-showcase/) and was adapted into a SpringOne presentation called [Mastering MVC 3](http://www.infoq.com/presentations/Mastering-Spring-MVC-3).

A screen cast showing the showcase in action is [available in QuickTime format](http://s3.springsource.org/MVC/mvc-showcase-screencast.mov).

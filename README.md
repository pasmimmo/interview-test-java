Eco-Mind Prospective Hire Test - Java Edition
=================================================


Project Overview
----------------

This is a web application for simple department management. Currently, the application simply reads a list of tasks from a
relational data store and presents the list in a simple table.


Your Tasks
----------

Add a REST web service for querying, adding, updating and removing departments. Each department has zero or more employees. The service should respond to the
following URLs and HTTP verbs:

* list all departments, accepting filter for employee salary; optionally accept query parameters for pagination and
  other filters
* create a new department
* retrieve details of the department identified by *id*
* update the existing department identified by *id*
* delete the existing department identified by *id*

The service MUST accept and return resources in JSON format. Design the JSON schema you feel is appropriate given the
existing data structure, you can use the OpenAPI file at src/main/resources/openapi/integrationApi.yaml

Add any necessary unit and/or integration tests.

You may use any IDE or text editor you are comfortable with. You are encouraged to add any open source third-party
library (must be available on Maven Central) that you feel makes your task easier. You may make any structural changes
to the project that you see fit.


Evaluation Criteria
-------------------

1. Correctness of solution

   Naturally, the project you submit must be functional. You will also be evaluated on *how* your solution addresses the
   assigned tasks.

2. Platform knowledge

   Does your code demonstrate your knowledge of the capabilities of the Java platform and its resources?

3. Coding style

   Is your coding style neat? Does it fit in with the prevailing style of the project? Is it idiomatic such that it will
   be easily understood by other Java developers? Is it adequately (but not excessively)
   commented?

4. Working with git

   Good git usage is important for collaborating with other developers. Make sure each change is logically distinct (
   i.e. don't combine unrelated changes into a single "mega" commit). Make sure the commit message for each change is
   meaningful.

Getting Started
---------------

### Clone the project to your local machine

You are probably reading this on GitHub already. If not, you can find the
project [on GitHub](https://www.github.com/EcoMind/interview-test-java).

To begin work on the project, start by cloning the repository to your local machine. Do your work locally, committing
your changes to your local git repository as you go.

### Building the project

The project includes a pom.xml file allowing it to be built by
[Maven](http://maven.apache.org). Simply running `mvn package` will download all dependencies and build a standard WAR
file.

Additional Maven plugins have been configured for managing a database and running the project in a servlet container.

### Managing the database

The project uses an [H2 Database](http://www.h2database.com) which is a simple in-process file-backed data store. This
saves the trouble of configuring a database server.

The project tables are automatically created and populated using the script file at src/main/resources/data.sql

### Running the application

You can run the project inside a Tomcat container using this command:

    mvn spring-boot:run

This starts a Tomcat server on your local machine listening on port 8080. To see the application's home page, point your
browser to `http://localhost:8080/`. This page shows a welcome page. Pointing to `http://localhost:8080/departments` you
will see the list of department in the database.


Submitting Your Code For Evaluation
-----------------------------------

When you are finished and ready to submit your work, use the following command to generate a series of patch files with
all of your changes:

    git format-patch origin/main

This will create one or more numbered patch files.

Send an email to techevaluation@eco-mind.eu with the patch files attached to the person who sent you this test. If you have git set up to send email,
you may also use the `git send-email` command to do this.


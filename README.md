# Spring-TestFlow-Automated-Testing-and-CI-CD-with-Jenkins
In this project, I developed a Spring Boot application with a comprehensive testing strategy that includes both unit tests and integration tests, and automated the testing process using Jenkins for continuous integration and delivery.

The application is a simple RESTf built using Spring Boot. The key focus of the project was to ensure robust test coverage and integrate a seamless CI/CD pipeline to automate testing, build, and deployment processes.
Key Features:

    Unit Testing:
        Utilized JUnit 5 and Mockito to isolate and test individual components of the application such as services and controllers.
        Mocks were created for dependencies (e.g., repositories) to ensure true isolation of unit tests.
        Each unit test covered multiple scenarios, including edge cases and error handling, ensuring high code quality.

    Integration Testing:
        Used Spring Boot’s @SpringBootTest to create integration tests that load the full application context.
        Configured an in-memory H2 database to run tests in isolation without affecting the production database.
        Verified the correct interaction between components (e.g., services and repositories) and tested HTTP endpoints with MockMvc for complete end-to-end testing.

    Continuous Integration with Jenkins:
        Set up a Jenkins pipeline to automate the entire testing process, including:
            Code compilation
            Execution of unit and integration tests
            Generating test reports
        Configured Jenkins to automatically trigger builds on every code push, ensuring immediate feedback for code changes.
        Integrated with GitHub for version control and SonarQube for code quality checks, ensuring adherence to best coding practices

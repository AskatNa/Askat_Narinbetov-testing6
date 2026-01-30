Project Setup and Running Tests via testng.xml

This project uses TestNG for running automated tests on different browsers (Chrome, Edge) in parallel on Sauce Labs. Below is a guide on how to set up and run tests via the testng.xml configuration.

Prerequisites
- Java: Ensure you have Java 11 or higher installed.
- You can check your Java version by running: java -version
- Maven: This project uses Maven for dependency management.
- You can check if Maven is installed by running: mvn -v
- Sauce Labs Account: You need a valid Sauce Labs account with a proper API key for running tests.
- IntelliJ IDEA or any other preferred IDE for running TestNG tests.

# Setup
1. Clone the repository:
  - git clone https://your-repository-url.git
  - cd your-project-directory
2. Install dependencies:
  - mvn install
3. Configure Sauce Labs Credentials:
  - Inside utils/BrowserStackDriverFactory.java, replace the following placeholders with your Sauce Labs credentials:
-->   private static final String USERNAME = "your-sauce-labs-username";
-->   private static final String ACCESS_KEY = "your-sauce-labs-access-key";
4. Configure TestNG XML:
<?xml version="1.0" encoding="UTF-8"?>
<suite name="BrowserStack Suite" parallel="tests" thread-count="2">
    <test name="Chrome Tests">
        <parameter name="browser" value="Chrome" />
        <parameter name="platform" value="Windows 11" />
        <parameter name="version" value="latest" />
        <parameter name="testName" value="Chrome Login Test" />
        <classes>
            <class name="tests.AdminTests" />
            <class name="tests.LoginTests" />
        </classes>
    </test>
    <test name="Edge Tests">
        <parameter name="browser" value="Edge" />
        <parameter name="platform" value="Windows 11" />
        <parameter name="version" value="latest" />
        <parameter name="testName" value="Edge Login Test" />
        <classes>
            <class name="tests.AdminTests" />
            <class name="tests.LoginTests" />
        </classes>
    </test>
</suite>
This configuration runs tests in parallel across Chrome and Edge browsers. The parallel="tests" attribute ensures that both tests will be run simultaneously in separate threads.

# Running the Tests
Running via IntelliJ IDEA:
  Step 1: Open the project in IntelliJ IDEA.
  Step 2: Right-click on testng.xml and select Run 'testng.xml'.
  - This will run all tests in the testng.xml configuration in parallel, based on the parameters provided.

# Conclusion:
- View the Results on Sauce Labs

### Instructions to install and run project tests

## Prerequisites:
1. **Java Development Kit (JDK):**
   Ensure you have Java JDK installed on your system. You can download and install it from the official Oracle website or use a package manager like Homebrew (for macOS) or apt (for Linux).

2. **Maven:**
   Install Apache Maven, a build automation tool for Java projects. You can download it from the Apache Maven website or use a package manager.

3. **Integrated Development Environment (IDE):**
   Use an IDE such as IntelliJ IDEA or Eclipse. Make sure your IDE has TestNG and Maven integration for seamless project setup and execution.

## Steps to setup project:

1. **Clone the Project:**
   Clone the project repository from your version control system (e.g., GitHub) to your local machine.

2. **Open the Project in IDE:**
   Open the project in your preferred IDE (IntelliJ IDEA or Eclipse) by importing it as a Maven project.

3. **Resolve Dependencies:**
   Maven should automatically resolve project dependencies (Selenium, TestNG, Extent Reports) defined in the `pom.xml` file. If not, you may need to manually update Maven dependencies.

## How to run tests
1. **There are several ways to run the tests**
   Run the tests by right-clicking on the TestNG XML file located under src/test/java/online/automationtesting/testng.xml and selecting "Run As" > "TestNG Suite" (in IntelliJ IDEA) or "Run" > "Run As TestNG Suite" (in Eclipse).
   Or Navigate to the test class located in this path src/main/java/online/automationtesting/tests/RoomTests.java and right-click on the RoomsTests class and select "Run RoomTests"
   Or right-click on the method name inside the RoomTests class and click "Run" to run individual tests
   

6. **View Test Results:**
   After test execution, TestNG generates a detailed HTML report in the `test-output` directory. Open the HTML report in a web browser to view the test results, including test pass/fail status, logs, and screenshots (if configured).

# Optional Steps:

- **Customize Test Data:**
  Modify test data or parameters as needed, such as room booking details, email addresses, etc., in the test classes.

- **Enhance Reporting:**
  Customize Extent Reports configuration to include additional information like screenshots, logs, or custom metadata in the test reports.

- **Parallel Execution:**
  Configure TestNG to run tests in parallel to reduce execution time. You can specify parallel execution settings in the TestNG XML file.

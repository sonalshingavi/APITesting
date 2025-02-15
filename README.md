# API Testing Automation with REST-assured and Cucumber BDD

## Project Details

This project is designed for API testing using a combination of various testing frameworks and tools. It includes automated tests for various API endpoints, ensuring they function correctly and meet the specified requirements.

## Used Software and Tools

- **Java:** Programming language used for writing test scripts.
- **Cucumber:**  BDD framework used for writing human-readable test scenarios.
- **REST-assured:** Java library used for testing REST APIs.
- **TestNG:** Testing framework used for running and managing test cases.
- **Lombok:** Library that helps in reducing boilerplate code.
- **Jenkins:** Continuous Integration and Continuous Deployment (CI/CD) tool.

## How to Install the Project

1. **Clone the repository:**
   ```bash
   git clone https://github.com/sonalshingavi/APITesting.git
   cd APITesting

2. Set up Java:

3. Install Maven:

4. Install project dependencies:
   Run the following command to install all necessary dependencies:
   ```bash
   mvn clean install
   
## How to Run Tests

#### Run smoke tests:
`   mvn clean test -Dtest=SmokeRunner
`

#### Run GET API Cases
  ` mvn clean test -Dtest=GETRunner`

#### Run POST API Cases
   `mvn clean test -Dtest=POSTRunner`

## How to Check Reports
After running the tests, you can find the reports in the target/cucumber-reports directory.

#### HTML Report:
Open `target/cucumber-reports/cucumber.html` in a web browser to view the HTML report.

#### JSON Report:
The JSON report is located at `target/cucumber-reports/cucumber.json`.

## How to Add in Jenkins Pipeline
Utilize JenkinsFile for Jenkins Pipeline Integration

#### Sample Reports

![Smoke-Cucumber report](https://github.com/sonalshingavi/APITesting/assets/174613688/8cc529f0-0b72-424b-b659-66da8ea62666)

![jenkinsReport](https://github.com/sonalshingavi/APITesting/assets/174613688/c1d8bc7b-1a98-4a9e-b727-b5d37e6b0249)


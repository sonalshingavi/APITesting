pipeline {
    agent any

    environment {
        JAVA_HOME = '/path/to/your/JDK/17' // Update with your JDK 17 path
        MAVEN_HOME = tool 'Maven 3.8.5' // Ensure Maven 3.8.5 is configured in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    // Clone the repository
                    git 'https://github.com/sonalshingavi/APITesting.git'
                }
            }
        }

        stage('Set JDK 17') {
            steps {
                script {
                    // Set JDK 17
                    sh '''
                    export JAVA_HOME=${JAVA_HOME}
                    export PATH=${JAVA_HOME}/bin:${PATH}
                    java -version
                    '''
                }
            }
        }

        stage('Build with Maven') {
            steps {
                script {
                    // Maven clean install
                    sh 'mvn clean install'
                }
            }
        }

        stage('Run Smoke Tests') {
            steps {
                script {
                    // Run Cucumber tests tagged with @smoke
                    sh 'mvn clean test -Dcucumber.options="--tags @smoke"'
                }
            }

            post {
                always {
                    // Generate Cucumber HTML reports
                    cucumber buildStatus: 'UNSTABLE', jsonReportDirectory: 'target/cucumber-reports', skippedFails: true
                }
            }
        }
    }
}

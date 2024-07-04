pipeline {
    agent any

    environment {
        GIT_REPO = 'https://github.com/sonalshingavi/APITesting.git'
        JAVA_HOME_17 = '/Library/Java/JavaVirtualMachines/openjdk-17.jdk/Contents/Home'
    }

    stages {
        stage('Clone Repository') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: 'main']], userRemoteConfigs: [[url: "${GIT_REPO}"]]])
            }
        }

        stage('Check Java Version') {
            steps {
                script {
                    env.PATH = "${JAVA_HOME_17}/bin:${env.PATH}"
                    sh 'java -version'
                }
            }
        }

        stage('Build and Install') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Run Smoke Tests') {
            steps {
                sh 'mvn clean test -Dtest=GETRunner'
            }
        }

    }

    post {
        always {
           // Generate Cucumber HTML reports
              cucumber buildStatus: 'UNSTABLE',
              fileIncludePattern: '**/cucumber.json',
              jsonReportDirectory: 'target/cucumber-reports'
           }
        success {
            echo 'Pipeline execution completed successfully!'
        }
        failure {
            echo 'Pipeline execution failed!'
        }
    }
}

pipeline {
    agent {
        node {
            label 'maven'
        }
    }

    environment {
        PATH = "/opt/apache-maven-3.9.5/bin:$PATH"
    }

    stages {
        stage('clone-code'){
            steps {
                git branch: 'master', url: 'https://github.com/quangtung20/interview'
            }
        }

        stage('build'){
            steps {
                sh 'mvn dependency:go-offline'
                sh 'mvn package'
            }
        }

        stage('unit-test'){
            steps {
                sh 'mvn test'
            }
        }

        stage ('code-analysis-with-maven'){
            steps {
                sh 'mvn checkstyle:checkstyle'
            }
            post {
                success {
                    echo 'Generated Analysis Result'
                }
            }
        }

        stage('code-analysis-with-sonar') {
          
		  environment {
             scannerHome = tool 'sonar4.7'
          }

          steps {
            withSonarQubeEnv('sonar-8.3') {
                sh 'mvn sonar:sonar'
            }

            timeout(time: 10, unit: 'MINUTES') {
               waitForQualityGate abortPipeline: true
            }
          }
        }
    }
}

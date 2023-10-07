pipeline {
    agent {
        node {
            label 'maven'
        }
    }

    environment {
        PATH = "/opt/apache-maven-3.9.5/bin:$PATH"
        ECR_REGISTRY = '225186392430.dkr.ecr.us-east-1.amazonaws.com'
        IMAGE_NAME = 'interview'
        IMAGE_TAG = 'latest'
        ECR_REPO = '225186392430.dkr.ecr.us-east-1.amazonaws.com/my-docker-repo'
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
            steps {
                withSonarQubeEnv('sonarqube') {
                    sh 'mvn sonar:sonar'
                }
            }
        }

        stage('quality-gate'){
            steps {
                timeout(time: 1, unit: 'HOURS'){
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('building-image') {
            steps{
                sh "docker build -t $IMAGE_NAME:$IMAGE_TAG ."
            }
        }

        stage('push-image-ecr') {
            steps{  
                sh "aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin $ECR_REGISTRY"
                sh "docker push $ECR_REPO/$IMAGE_NAME:$IMAGE_TAG"
                }
        }
    }
}

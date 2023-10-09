pipeline {
    agent {
        node {
            label "maven-dev"
        }
    }

    environment {
        PATH = "/opt/apache-maven-3.9.5/bin:$PATH"
        IMAGE_NAME = "interview"
        IMAGE_TAG = "latest"
        APP_NAME = "interview-app"
    }

    stages {
        stage("clone-code"){
            steps {
                git branch: "dev", url: "https://github.com/quangtung20/interview"
            }
        }

        stage("build"){
            steps {
                sh "mvn dependency:go-offline"
                sh "mvn package"
            }
        }

        stage("unit-test"){
            steps {
                sh "mvn test"
            }
        }

        stage ("code-analysis-with-maven"){
            steps {
                sh "mvn checkstyle:checkstyle"
            }
            post {
                success {
                    echo "Generated Analysis Result"
                }
            }
        }

        stage("code-analysis-with-sonar") {
            steps {
                withSonarQubeEnv("sonarqube") {
                    sh "mvn sonar:sonar"
                }
            }
        }

        stage("quality-gate"){
            steps {
                timeout(time: 1, unit: "HOURS"){
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage("building-image") {
            steps{
                script {
                dockerImage = docker.build IMAGE_NAME
                }
            }
        }

        // stage("push-image-ecr") {
        //     steps{  
        //         sh "aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin $ECR_REGISTRY"
        //         sh "docker push $IMAGE_NAME:$IMAGE_TAG"
        //         }
        // }

        stage("deploy") {
            steps {
                script {
                    def releaseExists = sh(script: "docker ps --format '{{.Names}}' | grep $APP_NAME", returnStatus: true) == 0
                    if (releaseExists) {
                        sh "docker stop $APP_NAME"
                        sh "docker rm $APP_NAME" 
                    }
                    sh "sleep 5"
                    sh "docker run -d -p 8080:8080 --name $APP_NAME $IMAGE_NAME:$IMAGE_TAG"
                }
            }
        }
    }
}

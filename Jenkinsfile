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
    }
}

pipeline {
    agent {
        node {
            label 'maven'
        }
    }

    stages {
        stage('clone-code'){
            steps {
                git branch: 'master', url: 'https://github.com/quangtung20/interview'
            }
        }
    }
}

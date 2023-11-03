def branchName = env.BRANCH_NAME

def build(branchName) {
    sh "echo ${branchName}"
}

def clone(){
    git url: 'https://github.com/quangtung20/interview.git'
}

def masterPipeline(branchName){
    node('maven-prod') {
        stage('clone') {
            clone()
        }

        stage('build') {
            build(branchName)
        }
    }
}

def devPipeline(branchName){
    node('maven-dev') {
        stage('clone') {
            clone()
        }

        stage('build') {
            build(branchName)
        }
    }
}

def otherPipeline(branchName){
    node('maven-dev') {
        stage('annount error') {
            sh "echo ${branchName} is not config to run cicd"
        }
    }
}

switch(branchName) {
    case 'master':
        masterPipeline(branchName)
        break
    case 'dev':
        devPipeline(branchName)
        break
    default:
        masterPipeline(branchName)
        break
}

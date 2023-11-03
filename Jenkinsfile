def branchName = env.BRANCH_NAME

def build(branchName) {
    sh "echo ${branchName}"
}

def masterPipeline(branchName){
    node('maven-prod') {
        stage('Clone') {
            git url: 'https://github.com/quangtung20/interview.git'
        }

        stage('build') {
            build(branchName)
        }
    }
}

switch(branchName) {
    case 'master':
        masterPipeline(branchName)
        break
}
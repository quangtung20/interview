def build(branchName) {
    sh "echo ${branchName}"
}

def branchName = env.BRANCH_NAME

node('maven-prod') {
  stage('Clone') {
    git url: 'https://github.com/quangtung20/interview.git'
  }

  if(branchName == 'master'){
    stage('build') {
        build(branchName)
    }
  } else {
        stage('build') {
            build(branchName)
        }
    }
  }

//   stage('Build') {
//     sh 'echo hello world' 
//   }
}
node('maven-prod') {
  def branchName = env.BRANCH_NAME
  stage('Clone') {
    git url: 'https://github.com/quangtung20/interview.git'
  }

  if(branchName = 'master'){
    stage('build') {
        sh 'echo master'
    }
  } else {
        stage('build') {
        sh 'echo another'
    }
  }

//   stage('Build') {
//     sh 'echo hello world' 
//   }
}
pipeline {
    agent any
    tools { go 'go-1.19' }

    environment {
        COMMIT = "${env.GIT_COMMIT}"
        BRANCH = "${env.GIT_BRANCH}"
    }

    stages {
        stage('Build') {
            steps {
                dir('src') {
                    sh '''
                        export GOTMPDIR="$JENKINS_HOME/go-cache"
                        mkdir -p $GOTMPDIR
                        go build -o generator main.go
                    '''
                }
            }
        }
        stage('Test') {
            steps {
                dir('src') { sh 'go fmt *.go' }
            }
        }
        stage('Generate HTML') {
            steps {
                dir('src') { sh './generator' }
            }
        }
    }


    post {
        success {
            publishHTML([
                reportDir: 'src',
                reportFiles: 'index.html',
                reportName: 'Dynamic HTML generator',
                allowMissing: false,
                alwaysLinkToLastBuild: false,
                keepAll: false,])
        }
    }
}



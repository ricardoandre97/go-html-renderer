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
                sh '''
                    env
                    cd src
                    export GOTMPDIR="$JENKINS_HOME/go-cache"
                    mkdir -p $GOTMPDIR
                    go build -o generator main.go
                '''
            }
        }
        stage('Test') {
            steps {
                sh 'cd src && go fmt *.go'
            }
        }
        stage('Generate HTML') {
            steps {
                sh 'cd src && ./generator'
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



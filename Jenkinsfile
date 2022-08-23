pipeline {
    agent any
    tools { go 'go-1.19' }

    environment {
        COMMIT = "${env.COMMIT_ID}"
        BRANCH = "${env.BRANCH_NAME}"
    }

    stages {
        stage('Build') {
            steps {
                sh '''
                    export GOTMPDIR="$JENKINS_HOME/go-cache"
                    mkdir -p $GOTMPDIR
                    go build -o generator main.go
                '''
            }
        }
        stage('Test') {
            steps {
                sh 'go fmt *.go'
            }
        }
        stage('Generate HTML') {
            steps {
                sh './generator'
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



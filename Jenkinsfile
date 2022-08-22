pipeline {
    agent any
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
}
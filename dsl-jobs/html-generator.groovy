pipelineJob('htlm-generator') {

    parameters {
        stringParam('DEPLOYED_BY',
            defaultValue = '',
            description = 'Identifies who generated the latest HTML.'
        )
    }

    definition {
        cpsScm {
            scm {
                git {
                    remote { 
                        url('https://github.com/ricardoandre97/go-html-renderer.git')
                        credentials("go-html-renderer") // Credentials ID
                    }
                    branches('dynamic-html')
                    scriptPath('Jenkinsfile')
                }
            }
        }
    }
}



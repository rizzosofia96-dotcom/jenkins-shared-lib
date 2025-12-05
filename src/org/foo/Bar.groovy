package org.foo

class Bar implements Serializable {
    def steps

    Bar(steps) {
        this.steps = steps
    }

    def cloneAndAnalyze(String repoUrl, String branch = 'main', String credentialsId = null) {
        steps.node {
            steps.stage('Clone Repository') {
                def scmConfig = [
                    $class: 'GitSCM',
                    branches: [[name: "*/${branch}"]],
                    userRemoteConfigs: [[url: repoUrl]]
                ]
                if (credentialsId) {
                    scmConfig.userRemoteConfigs[0].credentialsId = credentialsId
                }
                steps.checkout(scmConfig)
            }

            steps.stage('SonarQube Analysis') {
                steps.withSonarQubeEnv('sonarqube') {
                    def scannerHome = steps.tool 'sonar-scanner'  
                    steps.bat "${scannerHome}\\bin\\sonar-scanner.bat"
                }
            }
        }
    }
}

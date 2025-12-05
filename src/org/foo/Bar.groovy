package org.foo
class Bar implements Serializable {
    def steps

    Bar(steps) {
        this.steps = steps
    }

    def cloneAndAnalyze(String repoUrl, String branch = 'main') {
        steps.echo "Cloning repo ${repoUrl} branch ${branch}"
        steps.checkout([$class: 'GitSCM',
                       branches: [[name: branch]],
                       userRemoteConfigs: [[url: repoUrl]]
        ])

        steps.echo "Running SonarQube analysis"
        steps.withSonarQubeEnv('sonarqube') {  // nome server Sonar configurato in Jenkins
            steps.sh 'sonar-scanner -Dsonar.projectKey=Gestionerubrica -Dsonar.sources=.'
        }
    }
}


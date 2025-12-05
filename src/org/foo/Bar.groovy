package org.foo

class Bar implements Serializable {

    def steps

    Bar(steps) {
        this.steps = steps
    }

    void analyzeRepo(String repoName) {
        steps.echo "Cloning repo: ${repoName}"
        def repoUrl = "https://github.com/rizzosofia96-dotcom/Sommaduenumeri"
        
        steps.dir(repoName) {
            steps.checkout([$class: 'GitSCM', branches: [[name: '*/main']], userRemoteConfigs: [[url: repoUrl]]])
            steps.echo "Analisi del repository ${repoName} in corso..."
            // Qui metti la tua logica di analisi
        }
    }
}
